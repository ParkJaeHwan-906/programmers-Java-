package CodeTree;

import java.util.*;
import java.io.*;
public class AI로봇청소기 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }
    /**
     * N x N 공간이 있다.
     * - 먼지가 있다.
     * - 먼지가 없다.
     * - 물건이 있다.
     *
     * 먼지가 있는 공간은 1~100 사이의 먼지 양(p)를 갖는다.
     * 각 로봇 청소기는 초기 위치를 가지며, 초기 위치에는 먼지가 없다.
     *
     * [청소기 이동]
     * - 각 청소기는 순서대로 이동 거리가 가장 가까운 오염된 격자로 이동한다. ( 상 좌 우 하 )
     *          -> 1 씩 이동하는 것이 아니라, 오염된 격자로 이동하는 듯
     * - 물건이 위치한 격자나, 청소기가 위치한 격자로는 지나갈 수 없다.
     * - 가장 가까운 격자가 여러 개일 경우, 행 번호가 가장 작은 격자로, 행 번호가 같을 경우 열 번호가 가장 작은 격자로 이동한다.
     *
     * [청소]
     * - 이동 방향은 바라보고 있는 방향을 기준 현 위치, 왼쪽, 위쪽, 오른쪽 을 청소할 수 있다.
     * - 청소할 수 있는 격자에서 먼지량이 가장 큰 방향으로 청소한다.
     *          -> 이동하면서 청소
     * - 격자마다 청소할 수 있는 최대 먼지량은 20이다.
     * - 합이 같은 방향이 여러개인 경우, 오른쪽, 아래쪽, 왼쪽, 위쪽 방향의 우선순위로 방향을 선택한다.
     * - 청소는 청소기마다 순서대로 진행된다.
     *
     * [먼지 축적]
     * - 먼지가 있는 모든 격자에 동시에 5씩 추가된다.
     *
     * [먼지 확산]
     * - 깨끗한 격자에 주변 4방향 격자의 먼지량 합을 10으로 나눈 값만큼 먼지가 확산된다.
     * - 편의상 나눗셈 과정에 생기는 소숫점 아래 수는 버린다.
     * - 모든 깨끗한 격자에 대해 동시에 확산이 이루어진다.
     *
     * [출력]
     * - 전체 공간의 총 먼지량을 출력한다.
     * - 먼지가 있는 곳이 없으면 0을 출력한다.
     */
    static class RobotKing {
        int id;
        int x, y;

        RobotKing(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
    static StringTokenizer st;
    static int mapSize, robotKingCnt, testCnt;
    static int[][] map;
    static boolean[][] isChecked;
    static List<RobotKing> robotKings;
    static void init() throws IOException {
        robotKings = new ArrayList<>();
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        robotKingCnt = Integer.parseInt(st.nextToken());
        testCnt = Integer.parseInt(st.nextToken());
        map = new int[mapSize][mapSize];
        isChecked = new boolean[mapSize][mapSize];
        for(int x=0; x<mapSize; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<mapSize; y++) {
                /**
                 * -1       : 물건
                 * 0        : 먼지 X
                 * 1 ~ 100  : 먼지 O
                 */
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        for(int robotKing=1; robotKing<=robotKingCnt; robotKing++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            robotKings.add(new RobotKing(robotKing, x, y));
            isChecked[x][y] = true;
        }

        while(testCnt-- > 0) {
            moveRobotKings();
            cleanRobotKing();
            stackedDust();
            spreadDust();
//            for(int[] arr : map) System.out.println(Arrays.toString(arr));
            int sum = 0;
            for(int x=0; x<mapSize; x++) {
                for(int y=0; y<mapSize; y++) {
                    if(map[x][y] < 1) continue;
                    sum += map[x][y];
                }
            }
            sb.append(sum).append('\n');
        }

    }
    /**
     * [청소기 이동]
     * (상 좌 우 하) 순으로 우선 탐색
     *
     * 💡수정 제안
     * 로봇 청소기는 (상, 하, 좌, 우)로 움직일 수 있고, 가장 근접한 오염된 공간으로 이동한다.
     * - 행의 번호가 적은 장소가 우선 순위
     * - 열의 번호가 작은 장소가 우선 순위
     * -> 따라서 (상 좌 우 하) 순으로 탐색한다.
     */
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    static void moveRobotKings() {
        // 로봇 청소기를 id 순으로 이동 시킨다.
        // 물건이 있거나, 청소기가 있는 경우에는 이동할 수 없음
        for(int i=0; i<robotKings.size(); i++) {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[mapSize][mapSize];
            RobotKing robotKing = robotKings.get(i);
            int minDist = Integer.MAX_VALUE;
            int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE;
            q.offer(new int[] {robotKing.x, robotKing.y, 0});       // x, y, dist
            visited[robotKing.x][robotKing.y] = true;
            // BFS 를 사용해 가장 근처에 있는 오염된 구역을 찾는다.
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                int curX = cur[0];
                int curY = cur[1];
                int curDist = cur[2];

                if(map[curX][curY] > 0) {   // 현 위치가 오염된 구역이라면
                    if(curDist < minDist) {
                        minDist = curDist;
                        minX = curX;
                        minY = curY;
                    } else if(curDist == minDist) { // 같은 거리에 오염된 구역이 존재
                        if(curX < minX) { // 행의 번호가 작은 순으로 우선 순위
                            minX = curX;
                            minY = curY;
                        } else if(curX == minX) { // 열의 번호가 작은 순으로 우선순위
                            minY = Math.min(curY, minY);
                        }
                    }
                    continue;
                }

                if(curDist >= minDist) continue;        // 이미 최적 해를 넘은 경우 더 이상 탐색하지 않음

                for(int dir=0; dir<4; dir++) {
                    int nx = curX + dx[dir];
                    int ny = curY + dy[dir];
                    if(isNotMap(nx, ny)) continue;
                    if(visited[nx][ny] || isChecked[nx][ny]) continue;
                    if(map[nx][ny] == -1) continue;

                    q.offer(new int[] {nx, ny, curDist+1});
                    visited[nx][ny] = true;
                }
            }
            // 로봇 청소기의 이동 반영
            if (minDist != Integer.MAX_VALUE) {
                isChecked[robotKing.x][robotKing.y] = false;
                isChecked[minX][minY] = true;
                robotKing.x = minX;
                robotKing.y = minY;
            }
//            System.out.printf("%d : [%d, %d]\n", robotKing.id, robotKing.x+1, robotKing.y+1);
        }
    }
    /**
     * [청소]
     * 바라보는 방향을 기준으로
     * - 현 위치
     * - 왼쪽
     * - 오른쪽
     * - 위쪽
     * 격자만 청소가 가능
     *
     * 한 격자에 최대 20 의 먼지만 청소가 가능
     *
     * 💡개선
     * 현재 위치를 기준으로 4방향을 모두 탐색한다는 가정하에,
     * 제외할 1 가지 방향만 정하면, 더 편하게 계산이 가능하다.
     */
    // 오른쪽, 아래쪽, 왼쪽, 위쪽 순으로 방향을 탐색한다.
    // 제외한다는 기준으로 순서를 왼쪽, 위쪽, 오른쪽, 아래쪽 방향으로 탐색한다.
    static int[] cleanDx = {0,-1,0,1,0};
    static int[] cleanDy = {-1,0,1,0,0};
    static void cleanRobotKing() {
        for(int i=0; i<robotKings.size(); i++) {
            RobotKing robotKing = robotKings.get(i);
            int maxDust = -1;   // 청소할 수 있는 최대 먼지 합
            int maxDir = -1;

            for(int exDir=0; exDir<4; exDir++) {        // 제외할 방향을 선택한다. [왼쪽, 위쪽, 오른쪽, 아래쪽]
                int accDust = 0;
                for(int dir=0; dir<5; dir++) {
                    if(exDir == dir) continue;      // 제외할 방향이라면 합에 포함하지 않는다.
                    int nx = robotKing.x + cleanDx[dir];
                    int ny = robotKing.y + cleanDy[dir];
                    if(isNotMap(nx, ny)) continue;
                    if(map[nx][ny] < 1) continue;       // 물건이 있거나, 빈 칸이라면 넘어간다.

                    accDust += Math.min(map[nx][ny], 20);       // 한 칸에 최대 20의 먼지만 청소가 가능하다.
                }

                if(accDust > maxDust) {
                    maxDir = exDir;
                    maxDust = accDust;
                }
            }
            // 청소 할 필요가 없다면 넘어간다.
            if(maxDust == -1 || maxDust == 0) continue;
            // 실제로 청소를 한다.
            for(int dir=0; dir<5; dir++) {
                if(maxDir == dir) continue;      // 제외할 방향이라면 합에 포함하지 않는다.
                int nx = robotKing.x + cleanDx[dir];
                int ny = robotKing.y + cleanDy[dir];
                if(isNotMap(nx, ny)) continue;
                if(map[nx][ny] < 1) continue;       // 물건이 있거나, 빈 칸이라면 넘어간다.

                map[nx][ny] = Math.max(0, map[nx][ny]-20);
            }
        }
    }
    /**
     * [먼지 축적]
     */
    static void stackedDust() {
        for(int x=0; x<mapSize; x++) {
            for(int y=0; y<mapSize; y++) {
                if(map[x][y] < 1) continue;
                map[x][y] += 5;
            }
        }
    }
    /**
     * [먼지 확산]
     */
    static void spreadDust() {
        boolean[][] visited = new boolean[mapSize][mapSize];
        for(int x=0; x<mapSize; x++) {
            for(int y=0; y<mapSize; y++) {
                if(map[x][y] != 0) continue;
                int dustSum = 0;
                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(isNotMap(nx, ny)) continue;
                    if(visited[nx][ny]) continue;
                    if(map[nx][ny] < 1) continue;
                    dustSum += map[nx][ny];
                }
                map[x][y] = dustSum / 10;
                visited[x][y] = true;
            }
        }
    }
    /**
     * [공통]
     */
    static boolean isNotMap(int x, int y) {
        return x < 0 ||  y < 0 || x >= mapSize || y >= mapSize;
    }
}
/**
6 3 1
-1 0 0 0 -1 0
0 8 8 0 -1 0
13 10 -1 -1 -1 12
11 -1 0 0 0 25
-1 0 0 10 0 0
-1 0 0 10 23 18
4 4
2 1
6 2
 */