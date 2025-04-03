package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1767_프로세서연결하기_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase < TC+1; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        System.out.println(sb);
    }


    /*
     * Core 클래스를 정의한다.
     * 각 x,y 좌표와
     * 사방면으로의 길이를 구한다.
     */
    static class Core {
        int x;
        int y;
        int[] dist; // 우 하 좌 상

        public Core(int x, int y, int[] dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    static int mapSize, minCost, maxCores;
    static int[][] map;
    static List<int[]> cores;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void init() throws IOException {
        cores = new ArrayList<>();

        mapSize = Integer.parseInt(br.readLine().trim());
        map = new int[mapSize][mapSize];

        /*
         * 입력과 동시에 필요없는 코어를 구분한다.
         */
        for(int x=0; x<mapSize; x++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<mapSize; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());

                // 가장자리에 있는 코어를 제외시킨다.
                if(map[x][y] == 1 && !(x == 0 || y == 0 || x == mapSize-1 || y == mapSize-1)) {
                    cores.add(new int[] {x,y});
                }
            }
        }

        // 최소 비용과 최대 코어 수 초기화
        minCost = Integer.MAX_VALUE;
        maxCores = 0;

        // 각 코어가 가질 수 있는 경우는 5가지
        // 5^12 가 최대 수
        getMinCost(0, 0, 0);

        sb.append(minCost);
    }

    /*
     * 구한 코어의 정보를 사용해서 조합을 구한다
     */
    static void getMinCost(int coreIdx, int connCores, int costSum) {
        if(coreIdx == cores.size()) {    // 모든 탐색을 완료한 경우
            if(connCores > maxCores || (connCores == maxCores && costSum < minCost)){
                maxCores = connCores;
                minCost = costSum;
            }

//            for(int[] arr : map) {
//                System.out.println(Arrays.toString(arr));
//            }
//            System.out.println();

            return;
        }

        if (connCores + (cores.size() - coreIdx) < maxCores) return;    // 중간 단계의 합이 현재 최소값보다 큰 경우 패스 XX
        // 값이 커져도 더 많은 코어를 설치할 수 있음

        int[] nowCore = cores.get(coreIdx);
        /*
         * 4 방향을 탐색한다.
         */
        for(int dir=0; dir<4; dir++) {
            int powerLen = 0;
            boolean canPut = true;

            int nx = nowCore[0] + dx[dir];
            int ny = nowCore[1] + dy[dir];

            while(isBoard(nx, ny)) {
                // 타 전선 혹 코어가 있다면 X
                if(map[nx][ny] != 0) {
                    canPut = false;
                    break;
                }
                powerLen++;
                nx += dx[dir];
                ny += dy[dir];
            }

            // 연결 가능하면 연결
            if(canPut && powerLen > 0) {
                nx = nowCore[0] + dx[dir];
                ny = nowCore[1] + dy[dir];

                while(isBoard(nx, ny)) {
                    map[nx][ny] = 10;
                    nx += dx[dir];
                    ny += dy[dir];
                }

                getMinCost(coreIdx+1, connCores+1, costSum+powerLen);

                nx = nowCore[0] + dx[dir];
                ny = nowCore[1] + dy[dir];

                // 원복
                while(isBoard(nx, ny)) {
                    map[nx][ny] = 0;
                    nx += dx[dir];
                    ny += dy[dir];
                }
            }
        }

        getMinCost(coreIdx+1, connCores, costSum);
    }




    static boolean isBoard(int x, int y) {
        return !(x < 0 || y < 0 || x >= mapSize || y >= mapSize);
    }
}

/*
 * 1 개의 cell 에는 1 개의 core 혹은 전선이 올 수 있음
 * 가장자리에는 전원이 흐름
 *
 * 전선을 교차하지 않고, 최대한 많은 core 에 전원을 연결할 때, 전선 길이의 합을 구하고자 한다.
 * 전선 길이가 최소가 되는 값은 ?
 *
 * 전선은 직선으로만 설치가 가능하다.
 *
 */
