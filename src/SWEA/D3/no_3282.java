package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 3282. 0/1 Knapsack
public class no_3282 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int bag, itemNum;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine().trim());

        no_3282 problem = new no_3282();
        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=TC; tc++) {
            sb.append('#').append(tc).append(' ');
            init(); // 입력
            sb.append(problem.knapsack()).append('\n');
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[][] items;
    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        itemNum = Integer.parseInt(st.nextToken()); // 물건의 개수
        bag = Integer.parseInt(st.nextToken()); // 배낭의 무게

        items = new int[itemNum+1][2];
        for(int idx=1; idx<=itemNum; idx++) {
            st = new StringTokenizer(br.readLine().trim());
            // [부피, 가치]
            items[idx][0] = Integer.parseInt(st.nextToken());
            items[idx][1] = Integer.parseInt(st.nextToken());
        }
    }

    // 1. 완전탐색 ( 넣고, 안넣고 ) => n 은 최대 100, 즉 2^100 ❌ 시간초과
    // 2. DP
    int[][]dp;
    private int knapsack() {
        dp = new int[itemNum+1][bag+1];

        for(int item=1; item <= itemNum; item++) {  // item 개수 만큼의 물건을 사용할 수 있다
            for(int nowBag=1; nowBag <= bag; nowBag++) {    // 최대 nowBag 무게 만큼 물건을 넣을 수 있다
                dp[item][nowBag] = dp[item-1][nowBag];  // 이전의 값으로 초기값 설정

                if(items[item][0] <= nowBag) {  // 현재 아이템의 부피가 가방의 부피보다 작은 경우
                    // 현재 배낭 무게에, 이전 물건까지의 최대값
                    // 현재배낭 무게-넣고자하는 물건의 무게를 뺀 (해당물건을 넣을 수 있을만한 공간을 확보한) 값과 + 현재 물건의 가치
                    dp[item][nowBag] = Math.max(dp[item][nowBag],
                            dp[item - 1][nowBag - items[item][0]] + items[item][1]);
                }
            }
        }

        return dp[itemNum][bag];
    }
}
/*
물건을 1~N 번 물건만 고려하여, 가방의 부피를 K 만큼 사용 가능하다.
이때 최대 가치를
dp[N][K]
값으로 사용한다.

ex)
dp[3][5]
1,2,3, 물건을 사용하여
크기가 5인 가방에 넣을 수 있는
최대 값
 */