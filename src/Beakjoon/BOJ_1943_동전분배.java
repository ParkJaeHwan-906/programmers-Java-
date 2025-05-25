package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1943_동전분배 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        /*
            3 번의 입력이 주어진다.
         */
        for(int i=0; i<3; i++) {
            init();
            sb.append('\n');
        }
        System.out.println(sb);
        br.close();
    }

    static StringTokenizer st;
    static int coinCnt;                 // 동전 개수
    static int[][] coins;               // 동전
    static int totalPrice;              // 동전으로 만들 수 있는 최대 금액
    static void init() throws IOException {
        totalPrice = 0;
        coinCnt = Integer.parseInt(br.readLine().trim());
        coins = new int[coinCnt+1][];
        for(int i=1; i<coinCnt+1; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int coin = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            totalPrice += (coin*cnt);
            coins[i] = new int[] {coin, cnt};
        }
        canSame();
    }

    /*
        💡 총 금액을 반으로 나눈 뒤, 해당 금액을 만들 수 있다면, 똑같이 나눌 수 있다.
        DP 를 사용
        [i번째 동전을 사용][현재 금액을 만들 수 있는지]
     */
    static void canSame() {
        if(totalPrice%2 == 1) {
            sb.append(0);
            return;
        }

        totalPrice /= 2;
        int[][] dp = new int[coinCnt+1][totalPrice+1];
        dp[0][0] = 1;       // 동전 0 개로 0 원을 만들 수 있음
        for(int coinIdx=1; coinIdx<coinCnt+1; coinIdx++) {
            for(int price=0; price<totalPrice+1; price++) {
                if(dp[coinIdx-1][price] == 1) {     // 이전 동전을 사용해서 현재 금액을 만들 수 있다면
                    // -> 현재 금액을 사용하여 추가로 더 만들 수 있다.
                    for(int cnt=0; cnt < coins[coinIdx][1]+1; cnt++) {
                        int addPrice = coins[coinIdx][0] * cnt + price;
                        if(addPrice <= totalPrice) {
                            dp[coinIdx][addPrice] = 1;
                        }
                    }
                }
            }
        }

        sb.append(dp[coinCnt][totalPrice] == 1 ? '1' : '0');
    }
}

/*
    N 가지 종류의 동전을 각 몇 개씩 주었을 때,
    그 돈을 반으로 나눌 수 있는지 판단한다.

    동전의 범위는 정해져있지 않은듯함
    금액을 똑같이 나눈다 -> 모든 종류의 동전의 개수가 짝수이다.
 */