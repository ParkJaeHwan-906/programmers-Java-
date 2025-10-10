package CodeTree;

import java.util.*;
import java.io.*;
public class AI로봇청소기 {
    static BufferedReader br;
//    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        sb = new StringBuilder();
        init();
        br.close();
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
        int dir;

        RobotKing(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dir = -1;  // 초기값 ( 방향이 정해지지 않음 )
        }
    }
    static StringTokenizer st;
    static int mapSize, robotKingCnt, testCnt;
    static int[][] map;
    static List<RobotKing> robotKings;
    static void init() throws IOException {
        robotKings = new ArrayList<>();
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        robotKingCnt = Integer.parseInt(st.nextToken());
        testCnt = Integer.parseInt(st.nextToken());
        map = new int[mapSize][mapSize];
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
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            robotKings.add(new RobotKing(robotKing, x-1, y-1));
        }

        while(testCnt-- > 0) {
            moveRobotKings();
            cleanRobotKing();
            stackedDust();
            spreadDust();
//            for(int[] arr : map) System.out.println(Arrays.toString(arr));
        }

        int sum = 0;
        for(int x=0; x<mapSize; x++) {
            for(int y=0; y<mapSize; y++) {
                if(map[x][y] < 1) continue;
                sum += map[x][y];
            }
        }
        System.out.println(sum);
    }
    /**
     * [청소기 이동]
     * (상 좌 우 하) 순으로 우선 탐색
     */
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    static void moveRobotKings() {
        // 로봇 청소기를 id 순으로 이동 시킨다.
        // 물건이 있거나, 청소기가 있는 경우에는 이동할 수 없음
        boolean[][] isChecked = new boolean[mapSize][mapSize];
        for(int i=0; i<robotKings.size(); i++) {
            RobotKing robotKing = robotKings.get(i);
            isChecked[robotKing.x][robotKing.y] = true;
            for(int dist=1; dist<mapSize; dist++) {
                boolean find = false;
                for(int dir=0; dir<4; dir++) {
                    int nx = robotKing.x + dx[dir] * dist;
                    int ny = robotKing.y + dy[dir] * dist;

                    if(isNotMap(nx, ny)) continue;
                    if(isChecked[nx][ny]) continue;
                    if(map[nx][ny] < 1) continue;

                    robotKing.x = nx;
                    robotKing.y = ny;
                    robotKing.dir = dir;
                    isChecked[nx][ny] = true;
                    find = true;
                    break;
                }
                if(find) break;
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
     */
    static Map<Integer, int[][]> dirDxDy =
            Map.of(                     // 우 하 좌 상
                    0, new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}},
                    1, new int[][] {{-1,0}, {0,1}, {1,0}, {0,-1}},
                    2, new int[][] {{1,0}, {0,-1}, {-1,0}, {0,1}},
                    3, new int[][] {{0,-1}, {-1,0}, {0,-1}, {1,0}}
            );
    static void cleanRobotKing() {
        for(int i=0; i<robotKings.size(); i++) {
            RobotKing robotKing = robotKings.get(i);
            int curDust = map[robotKing.x][robotKing.y];
//            int maxDust = -1;   // 청소할 수 있는 최대 먼지 합
//            int maxDir = -1;
            int[][] dxdy = dirDxDy.get(robotKing.dir);
            for(int dir=0; dir<4; dir++) {
                int nx = robotKing.x + dxdy[dir][0];
                int ny = robotKing.y + dxdy[dir][1];
                if(isNotMap(nx, ny)) continue;
//                if(maxDust < curDust + map[nx][ny]) {
//                    maxDust = curDust + map[nx][ny];
//                    maxDir = dir;
//                }
                if(map[nx][ny] < 1) continue;

                if(map[nx][ny] > 20) map[nx][ny] -= 20;
                else map[nx][ny] = 0;
            }
            if(curDust > 20) map[robotKing.x][robotKing.y]-=20;
            else map[robotKing.x][robotKing.y]=0;
//            if(map[robotKing.x+dxdy[maxDir][0]][robotKing.y+dxdy[maxDir][1]] > 20) map[robotKing.x+dxdy[maxDir][0]][robotKing.y+dxdy[maxDir][1]] -= 20;
//            else map[robotKing.x+dxdy[maxDir][0]][robotKing.y+dxdy[maxDir][1]] = 0;
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
        boolean[][] isChecked = new boolean[mapSize][mapSize];
        for(int x=0; x<mapSize; x++) {
            for(int y=0; y<mapSize; y++) {
                if(map[x][y] != 0) continue;
                int dustSum = 0;
                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    if(isNotMap(nx, ny)) continue;
                    if(isChecked[nx][ny]) continue;
                    if(map[nx][ny] < 1) continue;
                    dustSum += map[nx][ny];
                }
                map[x][y] = dustSum / 10;
                isChecked[x][y] = true;
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