package Level_2;

import java.io.*;

// Softeer Lv.2
// 바이러스
// https://www.softeer.ai/practice/6284
public class no_6284 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] kpn = br.readLine().split(" ");
        long k = Long.parseLong(kpn[0]);
        long p = Long.parseLong(kpn[1]);
        long n = Long.parseLong(kpn[2]);

        // ⚠️ 수가 커서 뭐로 나눠야한다는건 매 계산시마다 나눠주기
        for(int i=1; i<=n; i++){
            k = (k * p) % 1000000007;
        }

        System.out.println(k);
    }
}
