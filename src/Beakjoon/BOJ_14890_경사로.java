package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_14890_경사로 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    /**
     * 경사로의 높이는 항상 1이며, 길이는 L 이다.
     * - 경사로는 낮은 칸에 놓으며, L개의 연속된 칸에 경사로의 바닥이 모두 접해야한다.
     */
    static StringTokenizer st;
    static int mapSize, laneLen, possibleRoute;
    static int[][] map;
    static void init() throws IOException {
        possibleRoute = 0;
        st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        laneLen = Integer.parseInt(st.nextToken());

        map = new int[mapSize][mapSize];
        for(int x=0; x<mapSize; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<mapSize; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        // -------- 입력확인 --------
        System.out.printf("N : %d, L : %d\n", mapSize, laneLen);
        for(int[] arr : map) System.out.println(Arrays.toString(arr));
    }

    /**
     * 가로로 탐색합니다.
     */
    static void findRowRoute() {
        boolean isOk;
        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            isOk = true;
            boolean[] isUsed = new boolean[mapSize];
            // 첫 번째 칸을 기준으로 이전 값과 지속적으로 비교합니다.
            int prev = map[rowIdx][0];
            // 가로로 이동하며, 이동 가능한지 판단합니다.
            for (int y = 1; y < mapSize; y++) {
                int cur = map[rowIdx][y];
                // 1. 별도의 경시로 없이 이동이 가능한 경우
                if (cur == prev) continue;
                // 2. 경사로 설치가 필요한 경우
                // 2-1. 높이 차이가 1 이상인 경우 ( 경사로 설치가 불가 )
                if (Math.abs(cur - prev) > 1) {
                    isOk = false;
                    break;
                }
                // 2-2. 경사로 설치가 가능한 경우
                // 2-2-1. 현재 위치를 위해 경사로 설치가 가능한지 확인
                //      - 현재 위치를 기준으로, 이전 위치에 대해서만 확인하면 되나?
                int checkStartLoc = y - laneLen + 1;
                if(checkStartLoc < 0) {
                    isOk = false;
                    break;
                }

                boolean[] copyIsUsed = isUsed.clone();
                for(int ny=checkStartLoc; ny<=y; ny++) {
                    if(copyIsUsed[ny]) {
                        isOk = false;
                        break;
                    }

                    copyIsUsed[ny] = true;
                }

                isUsed = copyIsUsed;
            }
            if (isOk) possibleRoute++;
        }
    }
    /**
     * 세로로 탐색합니다.
     */
    static void findColRouter() {

    }
}
