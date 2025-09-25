package CodeTree;

import java.util.*;
import java.io.*;
public class 개구리의여행 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        System.out.println(sb);
        br.close();
    }

    /**
     * 돌의 종류
     * - 안전한 돌 ( . )
     * - 미끄러운 돌 ( S )
     * - 천적이 사는 돌 ( # )
     *
     * (r1, c1) -> (r2, c2)
     * 개구리의 초기 점프력은 1
     *
     * 1.
     * 상하좌우 방향 중 하나를 골라 점프력만큼 칸을 이동
     * 이동하려는 위치에 돌이 없다면 이동 불가
     * 도착 위치에 미끄러운 돌이 있다면 이동 불가
     * 이동 경로 중 천적이  사는 돌이 있다면 이동 불가
     * 점프에는 1 만큼의 시간 소요
     *
     * 2.
     * 점프력을 1 만큼 증가
     * -> 기존 점프력이 1, 2, 3, 4 중 하나인 경우에만 가능
     * -> 최대 점프력은 5
     * 점프력 증가 후 K -> K^2 만큼의 시간 소요
     *
     * 3.
     * 점프력 감소
     * 기존 점프력이 1, 2, .. , k-1 중 하나의 점프력을 갖도록 만들 수 있음
     * 점프력 감소는 1 만큼의 시간이 소요
     *
     * ---
     *
     * Q 번의 여행 계획이 있을 때, 개구리는 각 계획마다 최대한 짧은 시간 내 여행을 하고자 한다.
     * 각 여행에 걸리는 최소 시간을 출력하는 프로그램을 작성하라
     *
     * 도착이 불가하면 -1 을 출력하라.
     */
    static StringTokenizer st;
    static int n, q;
    static char[][] map;
    static void init() throws IOException {
        n = Integer.parseInt(br.readLine().trim());
        map = new char[n+1][n+1];
        for(int x=1; x<n+1; x++) {
            String str = br.readLine().trim();
            for(int y=1; y<n+1; y++) map[x][y] = str.charAt(y-1);
        }
        q = Integer.parseInt(br.readLine().trim());
        for(int trip=0; trip<q; trip++) {
            st = new StringTokenizer(br.readLine().trim());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            findMinTime(r1, c1, r2, c2);
        }
    }

    /**
     * 최단 시간을 구한다.
     * BFS / dijkstra
     *  -> 가중치 ( 점프 ) 가 존재한다. -> 다익스트라
     *
     * 최단 경로가 최단 시간을 보장하지 못함
     * 결국 완탐으로 이동?
     */
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static final int INF = 987654321;
    static void findMinTime(int r1, int c1, int r2, int c2) {
        // 시간이 짧은 순으로 정렬 -> 최단 시간 찾기
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[3], b[3]));      // (x, y, jump, time)
        int[][][] visited = new int[n+1][n+1][6];       // 각 map 에 대해 jump 가중치에 대한 최소 시간 기록
        for(int x=0; x<n+1; x++) {
            for(int y=0; y<n+1; y++) {
                Arrays.fill(visited[x][y], INF);
            }
        }
        // 1. 시작 위치 설정
        pq.offer(new int[] {r1, c1, 1, 0});
        visited[r1][c1][1] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curJump = cur[2];
            int curTime = cur[3];

            if(visited[curX][curY][curJump] < curTime) continue;    // 이전의 최적 해보다 작다면
            if(curX == r2 && curY == c2) {                          // 도착했다면
                sb.append(curTime).append('\n');
                return;
            }

            // 이동 가능
            // 1-1. curJump
            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir]*curJump;
                int ny = curY + dy[dir]*curJump;

                // map 을 벗어나는가
                if(isNotMap(nx, ny)) continue;
                // 이동 경로 상 천적이 있는가
                if(!isSafeRoute(curX, curY, nx, ny, dir)) continue;
                // 도착 위치에 천적 혹은 미끄러운 돌이 있는가
                if(map[nx][ny] != '.') continue;

                // 이전의 최적 해 보다 작은 경우만 진행
                if(curTime+1 < visited[nx][ny][curJump]) {
                    visited[nx][ny][curJump] = curTime + 1;
                    pq.offer(new int[]{nx, ny, curJump, curTime + 1});
                }
            }
            // 1-2. Upgrade Jump
            if(curJump < 5) {
                int tempJump = curJump + 1;
                int tempTime = curTime + tempJump*tempJump;

                if(visited[curX][curY][tempJump] > tempTime) {
                    visited[curX][curY][tempJump] = tempTime;
                    pq.offer(new int[] {curX, curY, tempJump, tempTime});
                }
            }
            // 1-3. Downgrade Jump
            if(curJump > 1) {
                int tempTime = curTime+1;
                for(int tempJump=1; tempJump<curJump; tempJump++) {
                    if(visited[curX][curY][tempJump] > tempTime) {
                        visited[curX][curY][tempJump] = tempTime;
                        pq.offer(new int[] {curX, curY, tempJump, tempTime});
                    }
                }
            }
        }
        sb.append(-1).append('\n');
    }

    static boolean isSafeRoute(int x1, int y1, int x2, int y2, int dir) {
        switch(dir) {
            case 1: case 3:
                while(x1 != x2) {
                    x1 += dx[dir];
                    if(map[x1][y1] == '#') return false;
                }
                break;
            default :
                while(y1 != y2) {
                    y1 += dy[dir];
                    if(map[x1][y1] == '#') return false;
                }
        }
        return true;
    }
    static boolean isNotMap(int x, int y) {
        return x < 1 || y < 1 || x >= n+1 || y >= n+1;
    }
}
