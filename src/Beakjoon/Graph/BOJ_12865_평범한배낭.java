package Beakjoon.Graph;

import java.util.*;
import java.io.*;
public class BOJ_12865_평범한배낭 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int itemCnt, limitWeight;
    static int[][] items;                 // [무게, 가치]
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        itemCnt = Integer.parseInt(st.nextToken());
        limitWeight = Integer.parseInt(st.nextToken());
        items = new int[itemCnt][];
        for(int i=0; i<itemCnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i] = new int[] {w, v};
        }

        getMaxValue();
    }

    static void getMaxValue() {
        int[] dp = new int[limitWeight+1];

        for(int[] item : items) {
            int w = item[0];
            int v = item[1];
            for(int i=limitWeight; i>w-1; i--) {
                if(dp[i] < dp[i-w]+v) {
                    dp[i] = dp[i-w]+v;
                }
            }
        }

        System.out.println(dp[limitWeight]);
    }
}
