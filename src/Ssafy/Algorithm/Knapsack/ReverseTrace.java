package Ssafy.Algorithm.Knapsack;

import java.util.*;
import java.io.*;

public class ReverseTrace {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int itemCnt, peopleCnt, limitW;          // 물건 개수, 사람 수, 배낭 용량
    static int[][] items;                           // [물건 인덱스][무게, 가치]
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        itemCnt = Integer.parseInt(st.nextToken());
        peopleCnt = Integer.parseInt(st.nextToken());
        limitW = Integer.parseInt(st.nextToken());

        items = new int[itemCnt][2];
        for(int i=0; i<itemCnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            items[i] = new int[] {w, v};    // 무게, 가치 순으로 입력
        }

        packingBag();
    }

    static int maxValue;            // 챙길 수 있는 최대 가치
    static boolean[] isUsed;        // 각 물건이 사용되었는지를 기록
    static void packingBag() {
        maxValue = 0;
        isUsed = new boolean[itemCnt];
        // 사람이 차례로 가방을 싼다.
        for(int p=0; p<peopleCnt; p++) {
            knapsack(p);
        }

        System.out.println(maxValue);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<itemCnt; i++) {
            if(isUsed[i]) sb.append(i+1).append(' ');
        }
        System.out.println(sb);
    }
    static void knapsack(int pIdx) {
        int[] dp = new int[limitW+1];       // 현재 무게에서 넣을 수 있는 최대의 양
        int[] trace = new int[limitW+1];    // 사용한 물건을 기록
        Arrays.fill(trace, -1);

        for(int i=0; i<itemCnt; i++) {
            if(isUsed[i]) continue;     // 이전에 이미 사용했다면 패스!

            int w = items[i][0];
            int v = items[i][1];

            // 물건을 사용할 수 있다면
            for(int j=limitW; j>w-1; j--) {
                if(dp[j] < dp[j-w]+v) { // 현재 물건을 사용하는 것이 최대 가치라면
                    dp[j] = dp[j-w]+v;
                    trace[j] = i;
                }
            }

        }

//        System.out.println(Arrays.toString(dp));
//        System.out.println(Arrays.toString(trace));

        // 선택한 물건 찾아 제거하기
        int w = limitW;
        while(w > 0 && trace[w] != -1) {
            int itemNo = trace[w];
            isUsed[itemNo] = true;
            w -= items[itemNo][0];
        }
        maxValue += dp[limitW];
    }
}
