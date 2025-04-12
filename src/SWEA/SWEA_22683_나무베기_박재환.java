package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_22683_나무베기_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase<TC+1; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        br.close();
        System.out.println(sb);
    }

    static StringTokenizer st;
    static int mapSize, tree;
    static char[][] map;
    static boolean[][][][] visited; // [x,y,dir,tree]
    static int sX, sY, eX, eY;      // 출발 위치, 도착 위치
    static int minCommand;          // 최소 명령 횟수
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        tree = Integer.parseInt(st.nextToken());

        map = new char[mapSize][mapSize];
        visited = new boolean[mapSize][mapSize][4][tree+1];
        for(int x=0; x<mapSize; x++) {
            String input = br.readLine().trim();
            for(int y=0; y<mapSize; y++) {
                map[x][y] = input.charAt(y);

                if(map[x][y] == 'X') {
                    sX = x;
                    sY = y;
                } else if (map[x][y] == 'Y') {
                    eX = x;
                    eY = y;
                }
            }
        }

        minCommand = Integer.MAX_VALUE;
        getMinCommand();

        sb.append(minCommand == Integer.MAX_VALUE ? -1 : minCommand);
    }

    /*
        최단 거리를 구하는 것이 아닌 최소 명령 횟수를 구하는 것

        항상 위를 바라보는 형태로 시작한다. -> 방향도 기록
     */
    // 우 하 좌 상
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void getMinCommand() {
        Queue<int[]> q = new LinkedList<>();
        visited[sX][sY][3][0] = true;   // 시작 위치에서 위쪽 방향을 보고 있음
        q.offer(new int[] {sX, sY, 3, 0, 0});   // [x,y, dir, command, tree]

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dir = cur[2];
            int command = cur[3];
            int cut = cur[4];

            // 목적지 도달
            if(x == eX && y == eY) {
                minCommand = command;
                return;
            }

            // 1. 앞으로 이동
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if(!isBoard(nx, ny)) {  // 범위 내부라면
                if(map[nx][ny] == 'T' && cut < tree) {    // 나무를 만났을 때, 자를 수 있다면
                    // 같은 위치에 같은 조건으로 방문한 적이 있는지 확인
                    if(!visited[nx][ny][dir][cut+1]) {
                        // 탐색 가능
                        visited[nx][ny][dir][cut + 1] = true;
                        q.offer(new int[]{nx, ny, dir, command+1, cut + 1});
                    }
                } else if (map[nx][ny] != 'T') {    // 그냥 풀인경우 -> 나무가 아닌 경우
                    // 같은 위치에 같은 조건으로 방문한 적이 있는지 확인
                    if(!visited[nx][ny][dir][cut]) {
                        // 탐색 가능
                        visited[nx][ny][dir][cut] = true;
                        q.offer(new int[] {nx, ny, dir, command+1, cut});
                    }
                }
            }

            // 회전
            // 1. 왼쪽
            int lDir = (dir+3) % 4;
            // 같은 위치에 같은 조건으로 방문한 적이 있는지 확인
            if(!visited[x][y][lDir][cut]) {
                // 탐색 가능
                visited[x][y][lDir][cut] = true;
                q.offer(new int[]{x, y, lDir, command+1, cut});
            }
            // 2. 오른쪽
            int rDir = (dir+1) % 4;
            // 같은 위치에 같은 조건으로 방문한 적이 있는지 확인
            if(!visited[x][y][rDir][cut]) {
                // 탐색 가능
                visited[x][y][rDir][cut] = true;
                q.offer(new int[] {x, y, rDir, command+1, cut});
            }
        }
    }

    // 격자 내에 존재하는지
    static boolean isBoard(int x, int y) {
        // 범위가 벗어나는 경우 true
        return x < 0 || y < 0 || x >= mapSize || y >= mapSize;
    }
}

/*
    출발지 -> 목적지
    !!최소 조작!!

    N x N 격자
    벨 수 있는 나무의 수가 주어진다.
 */