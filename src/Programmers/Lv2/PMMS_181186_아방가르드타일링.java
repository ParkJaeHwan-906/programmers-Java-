package Programmers.Lv2;

import java.util.*;
import java.io.*;

public class PMMS_181186_아방가르드타일링 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static void init() throws IOException {
        int n = Integer.parseInt(br.readLine().trim());

        System.out.println(new PMMS_181186_아방가르드타일링().solution(n));
    }

    /*
        DP 사용
        => 이전 도형에서 1x3 타일을 추가하면, nx3 타일을 만들 수 있다.
     */
    static final int MOD = 1_000_000_007;

    public int solution(int n) {
        if (n < 7) {
            int[] base = {0, 1, 3, 10, 23, 62, 170};
            return base[n];
        }

        // 초기값 dp[1] ~ dp[6]
        long[] dp = {1, 3, 10, 23, 62, 170}; // dp[1] ~ dp[6]

        for (int i = 7; i <= n; i++) {
            long next = (dp[5] + 2 * dp[4] + 6 * dp[3] + dp[2] - dp[0]) % MOD;
            if (next < 0) next += MOD; // 음수 방지
            // dp를 슬라이딩
            for (int j = 0; j < 5; j++) dp[j] = dp[j + 1];
            dp[5] = next;
        }

        return (int) dp[5];
    }
}

/*
    가로 n, 세로 3 인 판을 타일링하는 의뢰를 맡음

    가지고 있는 타일의 종류는 2가지
    각 타일을 90도씩 회전시킬 수 있다.

    n x 3 크기의 판을 타일링 하는 방법의 수를 return 해라
 */