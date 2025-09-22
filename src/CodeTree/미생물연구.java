package CodeTree;

import java.util.*;
import java.io.*;

public class 미생물연구 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }

    static StringTokenizer st;
    static int n, q;
    static int[][] board;
    static int r1, c1, r2, c2;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        board = new int[n+1][n+1];
        q = Integer.parseInt(st.nextToken());

        for(int test=1; test<=q; test++) {
            st = new StringTokenizer(br.readLine().trim());
            r1 = Integer.parseInt(st.nextToken());
            c1 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());
            addMicro(r1, c1, r2, c2, test);
            moveMicro();
            sb.append(calcValidMicroArea()).append('\n');
        }
    }

    // 미생물 추가
    // 미생물을 추가하면서, 연결이 끊어지는 미생물을 제거
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void addMicro(int r1, int c1, int r2, int c2, int tc) {
        // 1. 미생물 추가
        for(int x=r1; x<=r2; x++) {
            for(int y=c1; y<=c2; y++) {
                board[x][y] = tc;
            }
        }
        // 2. 연결이 끊어지는 미생물 제거
        // 각 미생물의 영역의 개수를 저장
        Map<Integer, Integer> micros = new HashMap<>();     // id, areaCnt
        boolean[][] visited = new boolean[n+1][n+1];
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                int id = board[x][y];
                if(id==0 || visited[x][y]) continue;
                micros.put(id, micros.getOrDefault(id, 0) + 1);
                checkVisited(x, y, id, visited);
            }
        }

        for(Integer id : micros.keySet()) {
            if(micros.get(id) > 1) removeMicro(id);
        }
    }
    // BFS 를 활용하여, 인접한 같은 id 위치를 체크
    static void checkVisited(int x, int y, int id, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                if(isNotBoard(nx, ny)) continue;
                if(visited[nx][ny] || board[nx][ny] != id) continue;
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }
    }
    // 격자 내부에 있는지 확인 
    static boolean isNotBoard(int x, int y) {
        return (x < 0 || x >= n+1 || y < 0 || y >= n+1);
    }
    // 영역이 2개 이상인 미생물을 격자에서 제거
    static void removeMicro(int id) {
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                if(board[x][y] == id) board[x][y] = 0;
            }
        }
    }

    static class Micro {
        int id;             // 미생물 번호
        int size;           // 영역 크기
        List<int[]> points; // 미생물이 위치한 좌표들

        Micro(int id, List<int[]> points) {
            this.id = id;
            this.points = points;
            this.size = points.size();
        }
    }
    // 미생물 이동
    static void moveMicro() {
        List<Micro> microGroups = findMicroGrop();
        if(microGroups.isEmpty()) return;       // 옮길 것이 없으면 패스
        // 영역이 큰 순으로 이동
        // 크기가 같다면 먼저 들어온 순
        microGroups.sort((a, b) -> {
            if(a.size == b.size) return Integer.compare(a.id, b.id);
            return Integer.compare(b.size, a.size);
        });

        int[][] newBoard = new int[n+1][n+1];
        for(Micro micro : microGroups) {
            // 상대 좌표 계산
            // 현재 군집에서 가장 기준이 되는 좌표를 구한다.
            int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
            for(int[] point : micro.points) {
                minX = Math.min(minX, point[0]);
                minY = Math.min(minY, point[1]);
            }
            // 상대 좌표 값을 저장
            // 상대 좌표를 기준으로, 나머지 좌표들의 거리를 저장
            List<int[]> relPoints = new ArrayList<>();
            for(int[] point : micro.points) {
                relPoints.add(new int[] {point[0]-minX, point[1]-minY});
            }

            // 새로운 위치 탐색
            boolean move = false;
            for(int x=0; x<n+1 && !move; x++) {
                for(int y=0; y<n+1 && !move; y++) {
                    if(!canMove(relPoints, x, y, newBoard)) continue;
                    for(int[] point : relPoints) {
                        newBoard[x+point[0]][y+point[1]] = micro.id;
                    }
                    move = true;
                }
            }
        }
        board = newBoard;
    }

    // 이동 가능한지
    static boolean canMove(List<int[]> relPoints, int x, int y, int[][] newBoard) {
        for(int[] point : relPoints) {
            int nx = x + point[0];
            int ny = y + point[1];
            if(isNotBoard(nx, ny)) return false;
            if(newBoard[nx][ny] != 0) return false;
        }
        return true;
    }

    // 각 미생물의 그룹 정의
    static List<Micro> findMicroGrop() {
        List<Micro> list = new ArrayList<>();           // 각 미생물의 좌표 저장
        boolean[][] visited = new boolean[n+1][n+1];
        // 그룹 찾기
        for(int x=0; x<n+1; x++) {
            for(int y=0; y<n+1; y++) {
                int id = board[x][y];
                if(id==0 || visited[x][y]) continue;
                // 영역 탐색
                Queue<int[]> q = new LinkedList<>();
                List<int[]> points = new ArrayList<>();
                q.offer(new int[] {x, y});
                visited[x][y] = true;

                while(!q.isEmpty()) {
                    int[] cur = q.poll();
                    int curX = cur[0];
                    int curY = cur[1];
                    points.add(new int[] {curX, curY});

                    for(int dir=0; dir<4; dir++) {
                        int nx = curX + dx[dir];
                        int ny = curY + dy[dir];

                        if(isNotBoard(nx, ny)) continue;
                        if(visited[nx][ny] || board[nx][ny] != id) continue;
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                    }
                }

                list.add(new Micro(id, points));
            }
        }
        return list;
    }


    // 미생물 영역 확인
    static int calcValidMicroArea() {
        int result = 0;
        // 각 군집의 크기 구하기
        Map<Integer, Integer> micros = new HashMap<>();
        for(int x=0; x<n+1; x++) {
            for(int y=0; y<n+1; y++) {
                if(board[x][y] == 0) continue;
                micros.put(board[x][y], micros.getOrDefault(board[x][y], 0) + 1);
            }
        }

        boolean[][] isAdj = new boolean[q+1][q+1];      // 미생물 간의 연결 관계
        for(int x=0; x<n+1; x++) {
            for(int y=0; y<n+1; y++) {
                if(board[x][y] == 0) continue;
                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(isNotBoard(nx, ny)) continue;
                    int adjId = board[nx][ny];
                    if(adjId == 0 || adjId == board[x][y]) continue;
                    isAdj[board[x][y]][adjId] = true;
                    isAdj[adjId][board[x][y]] = true;
                }
            }
        }

        // 영역 계산
        for(int m1=1; m1<=q; m1++) {
            for(int m2=1; m2<=q; m2++) {
                if(!isAdj[m1][m2]) continue;
                result += micros.get(m1) * micros.get(m2);
                isAdj[m1][m2] = isAdj[m2][m1] = false;
            }
        }
        return result;
    }
}
