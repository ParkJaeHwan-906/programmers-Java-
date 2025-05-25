package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_2169_로봇조종하기 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int row, col;
    static int[][] map;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row+1][col+1];

        for(int x=1; x<row+1; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=1; y<col+1; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        maxValue();
    }

    /*
        지역을 탐사하며 최대 가치를 얻는다.
        이때 로봇은 왼쪽 오른쪽 아래 방향으로 이동 가능하다.
        -> DP 접근
     */
    static void maxValue() {
        int[][] dp = new int[row+1][col+1];

        // 1. 첫 줄은 -> 방향으로 누적이 최대값
        for(int i=1; i<col+1; i++) {
            dp[1][i] = dp[1][i-1] + map[1][i];
        }
//        System.out.println(Arrays.toString(dp[1]));

        // 2. 두 번째 줄부터는, 경우를 생각해야한다.
        //      - 좌측에서 들어오는 경우
        //      - 우측에서 들어오는 경우
        //      - 상단에서 들어오는 경우
        for(int x=2; x<row+1; x++) {
            // 2-1. 우측에서 들어오는 경우
            int[] rightDpArr = new int[col+2];  // 계산을 편하게 하기 위해 +2 범위
            // ⚠️ 음수의 값이 있을 수 있으므로, 각 방향의 첫번째 값은 위에서만 내려오도록 초기화한다.
            rightDpArr[col] = dp[x-1][col] + map[x][col];
            for(int y=col-1; y>-1; y--) {
                rightDpArr[y] = Math.max(rightDpArr[y+1], dp[x-1][y]) + map[x][y];
            }

            // 2-2. 좌측에서 들어오는 경우
            int[] leftDpArr = new int[col+2];  // 계산을 편하게 하기 위해 +2 범위
            // ⚠️ 음수의 값이 있을 수 있으므로, 각 방향의 첫번째 값은 위에서만 내려오도록 초기화한다.
            leftDpArr[1] = dp[x-1][1] + map[x][1];
            for(int y=2; y<col+1; y++) {
                leftDpArr[y] = Math.max(dp[x-1][y], leftDpArr[y-1]) + map[x][y];
            }

            // 2-3. 두 DP 배열을 비교하여 최대값으로 갱신
            for(int y=1; y<col+1; y++) {
                dp[x][y] = Math.max(rightDpArr[y], leftDpArr[y]);
            }
        }

        System.out.println(dp[row][col]);
    }
}
