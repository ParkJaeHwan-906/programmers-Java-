package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Softeer Lv.2
// 지도 자동 구축
// https://www.softeer.ai/practice/6280
public class no_6280 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[0] = 2;

        for(int i=1; i<=n; i++){
            dp[i] = dp[i-1]*2 - 1;
        }

        System.out.println(dp[n]*dp[n]);
    }
}
