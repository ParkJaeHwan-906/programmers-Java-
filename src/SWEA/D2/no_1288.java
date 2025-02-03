package SWEA.D2;

import java.io.*;

// SWEA D2
//
public class no_1288 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String args[]) throws Exception
    {
        no_1288 problem = new no_1288();
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for(int tc=1; tc<=T; tc++) {
            sb.append('#').append(tc).append(' ');

            int N = Integer.parseInt(br.readLine().trim());
            sb.append(problem.sheepCount(N)).append('\n');
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    int target;
    public int sheepCount(int N) {
        target = (1 << 10) - 1; // 초기 값 세팅

        int now = 0;
        int num = 0;

        // 현재 숫자의 각 자리수를 확인하여 체크
        while(now != target) {
            num++;
            int nowNum = num * N;

            while(nowNum > 0) {
                int mod = nowNum%10;

                now|=(1<<mod);

                nowNum/= 10;
            }
        }

        return (N * num);
    }
}
