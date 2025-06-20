package Programmers.Lv3;

import java.util.*;
import java.io.*;

public class PMMS_258705_산모양타일림 {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
    }

    static void init() throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        int[] tops = new int[n];
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<n; i++) tops[i] = Integer.parseInt(st.nextToken());

        System.out.println(new PMMS_258705_산모양타일림().solution(n, tops));
    }

    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][2];

        dp[0][0] = tops[0] == 1 ? 3 : 2;
        dp[0][1] = 1;

        for(int i=1; i<n; i++) {
            dp[i][0] = (dp[i-1][0] * (tops[i] == 1 ? 3 : 2))%10007
                    + (dp[i-1][1] * (tops[i] == 1 ? 2 : 1))%10007;

            dp[i][1] = (dp[i-1][0] + dp[i-1][1])%10007;
        }

        return (dp[n-1][0] + dp[n-1][1]) % 10007;
    }
}

/*
    한 변의 길이가 1 인 정삼각형 2n+1 개를 붙여,
    윗변의 길이가 n, 아랫변 길이가 n+1 인 사다리꼴을 만들 수 있다.

    [타일]
    사다리꼴의 윗 변을 공유하는 n 개의 정삼각형 중
    일부의 위쪽에 같은 크기의 정삼각형을 붙여 새로운 모양을 만들었다.

    타일을 정삼각형 또는 정삼각형 2개를 이어붙인 마름모 타일로 빈 곳이 없도록 채우려고 한다.

 */