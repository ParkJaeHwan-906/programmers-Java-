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
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        q = Integer.parseInt(st.nextToken());

        for(int test=1; test<=q; test++) {
            st = new StringTokenizer(br.readLine().trim());
            r1 = Integer.parseInt(st.nextToken());
            c1 = Integer.parseInt(st.nextToken());
            r2 = Integer.parseInt(st.nextToken());
            c2 = Integer.parseInt(st.nextToken());
            addMicro(r1, c1, r2, c2, test);
            findMicroGroupSize();
            calcAdjAreaSum();
        }
    }

    /**
     * [1. 추가]
     * 미생물을 추가한다.
     * 1. 미생물을 추가한다.
     * 2. 영역이 둘 이상으로 나누어진 미생물은 사라진다.
     */
    static void addMicro(int r1, int c1, int r2, int c2, int microId) {
        // 1. 미생물을 추가한다.
        for(int x=r1; x<r2; x++) {
            for(int y=c1; y<c2; y++) {
                board[x][y] = microId;
            }
        }
        // 2. 영역이 둘 이상 나눠진 미생물 군집이 있는지 확인한다.
        // 2-1. 존재한다면 없앤다.
        checkDivisionMicro();
    }

    static void checkDivisionMicro() {
        // 미생물 id, 군집의 개수
        Map<Integer, Integer> microGroupCnt = new HashMap<>();
        boolean[][] visited = new boolean[n][n];
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                int microId = board[x][y];
                if(microId == 0 || visited[x][y]) continue;

                microGroupCnt.put(microId, microGroupCnt.getOrDefault(microId, 0)+1);
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[] {x,y});
                visited[x][y] = true;
                while(!q.isEmpty()) {
                    int[] cur = q.poll();
                    int curX = cur[0];
                    int curY = cur[1];

                    for(int dir=0; dir<4; dir++) {
                        int nx = curX + dx[dir];
                        int ny = curY + dy[dir];
                        if(isNotBoard(nx, ny)) continue;
                        if(visited[nx][ny]) continue;
                        if(board[nx][ny] != microId) continue;
                        visited[nx][ny]= true;
                        q.offer(new int[] {nx, ny});
                    }
                }
            }
        }

        // 2. 나눠진 군집 처리
        for(Integer microId : microGroupCnt.keySet()) {
            if(microGroupCnt.get(microId) < 2) continue;
            for(int x=0; x<n; x++) {
                for(int y=0; y<n; y++) {
                    if(board[x][y] != microId) continue;
                    board[x][y] = 0;
                }
            }
        }
    }
    /**
     * [2. 이동]
     * 1. 각 미생물 군집의 크기 구하기
     * 2. 정렬 (크기순, 입력순)
     * 3. x,y 작은 곳으로 순차적 이동
     */
    static class Micro {
        int id;
        List<int[]> points;
        int size;
        Micro(int id, List<int[]> points) {
            this.id = id;
            this.points = points;
            this.size = points.size();
        }
    }
    static void findMicroGroupSize() {
        List<Micro> microGroups = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                int microId = board[x][y];
                if(microId == 0 || visited[x][y]) continue;

                Queue<int[]> q = new LinkedList<>();
                List<int[]> points = new ArrayList<>();
                q.offer(new int[] {x,y});
                points.add(new int[] {x, y});
                visited[x][y] = true;
                while(!q.isEmpty()) {
                    int[] cur = q.poll();
                    int curX = cur[0];
                    int curY = cur[1];

                    for(int dir=0; dir<4; dir++) {
                        int nx = curX + dx[dir];
                        int ny = curY + dy[dir];
                        if(isNotBoard(nx, ny)) continue;
                        if(visited[nx][ny]) continue;
                        if(board[nx][ny] != microId) continue;
                        visited[nx][ny]=true;
                        q.offer(new int[] {nx, ny});
                        points.add(new int[] {nx, ny});
                    }
                }

                // 미생물 군집 탐색 완료
                microGroups.add(new Micro(microId, points));
            }
        }
        moveNewBoard(microGroups);
    }

    // 2. 새로운 배양판으로 이동
    static void moveNewBoard(List<Micro> microGroups) {
        // 2-1. 정렬
        microGroups.sort((a, b) -> {
            if(a.size == b.size) return Integer.compare(a.id, b.id);
            return Integer.compare(b.size, a.size);
        });

        // 2-2. 순자적으로 이동
        int[][] newBoard = new int[n][n];
        for(Micro micro : microGroups) {
            // 2-2-1. 상대 좌표 구하기
            int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
            for(int[] point : micro.points) {
                minX = Math.min(minX, point[0]);
                minY = Math.min(minY, point[1]);
            }
            // 2-2-2. 상대 좌표 생성
            List<int[]> relPoints = new ArrayList<>();
            for(int[] point : micro.points) {
                relPoints.add(new int[] {point[0]-minX, point[1]-minY});
            }

            // 2-2-3. 이동 가능한 위치 찾기
            boolean move = false;
            for(int x=0; x<n&&!move; x++) {
                for(int y=0; y<n&&!move; y++) {
                    if(!canMove(relPoints, x, y, newBoard)) continue;
                    // 이동 가능
                    for(int[] point : relPoints) {
                        int nx = point[0] + x;
                        int ny = point[1] + y;
                        newBoard[nx][ny] = micro.id;
                    }
                    move = true;
                }
            }
        }

        board = newBoard;
    }

    static boolean canMove(List<int[]> relPoints, int x, int y, int[][] newBoard) {
        for(int[] point : relPoints) {
            int nx = point[0] + x;
            int ny = point[1] + y;
            if(isNotBoard(nx, ny)) return false;
            if(newBoard[nx][ny] != 0) return false;
        }
        return true;
    }
    /**
     * [3. 인접한 영역의 곱의 합 구하기]
     * 1. 각 미생물 군집의 크기 구하기
     * 2. 인접한 영역 구하기
     * 3. 인접한 영역의 곱의 합 구하기
     */
    static void calcAdjAreaSum() {
        int result = 0;
        // 1
        Map<Integer, Integer> microGroups = new HashMap<>();
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                int microId = board[x][y];
                if(microId == 0) continue;
                microGroups.put(microId, microGroups.getOrDefault(microId, 0)+1);
            }
        }
        // 2
        boolean[][] isChecked = new boolean[q+1][q+1];
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                int microId = board[x][y];
                if(microId == 0) continue;

                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(isNotBoard(nx, ny)) continue;
                    int otherMicroId = board[nx][ny];
                    if(isChecked[microId][otherMicroId] || isChecked[otherMicroId][microId]) continue;
                    if(otherMicroId == 0) continue;
                    if(microId == otherMicroId) continue;

                    isChecked[microId][otherMicroId] = isChecked[otherMicroId][microId] = true;
                    result += (microGroups.get(microId) * microGroups.get(otherMicroId));
                }
            }
        }
        sb.append(result).append('\n');
    }

    /**
     * 공통 영역 - DFS, BFS 범위 확인
     */
    static boolean isNotBoard(int x, int y) {
        return x<0||y<0||x>=n||y>=n;
    }
}
