package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_촛불이벤트 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase<=TC; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static long candles;
    static void init() throws IOException{
        candles = Long.parseLong(br.readLine().trim());

        if(!isTriangle()) sb.append(-1);
    }

    static boolean isTriangle() {
        long l = 1;
        long r = (long) Math.sqrt(2 * candles) + 1; // K(K+1)/2 <= N 을 만족하는 K의 대략적인 범위

        while(l <= r) {
            long mid = l+(r-l)/2;

            long totalCandles = mid*(mid+1)/2;

            if(totalCandles == candles) {
                sb.append(mid);
                return true;
            }

            if(totalCandles < candles) {
                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return false;
    }
}
/*
    촛불을 K 단 크기로 배치하면
    1 단에는 K 개의 양초
    2 단에는 K-1 개의 양초
    ...
    K 단에는 1 개의 양초
    를 배치하여 총 K(K+1)/2 개의 양초가 필요하다.

    양초의 개수 N 개가 주어질 때, 이 양초를 보두 사용하면 몇 단 크기의 촛불 삼각형을 만들 수 있는지 구하여라
    N 의 범위는 10^18

    총 개수로 삼각형의 높이를 찾아야한다.
 */