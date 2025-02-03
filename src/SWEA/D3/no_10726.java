package SWEA.D3;

import java.io.*;
import java.util.*;
// SWEA D3
// 10726. 이진수 표현
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AXRSXf_a9qsDFAXS
public class no_10726 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        no_10726 problem = new no_10726();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int tc=1; tc<=TC; tc++) {
            sb.append('#').append(tc).append(' ');

            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            // M 의 이진수 표현의 마지막 N 비트가 모두 1로 채워져있는지
            sb.append(problem.isFiiled(M,N)).append('\n');
        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private final String TRUE = "ON";
    private final String FALSE = "OFF";
    public String isFiiled(int M, int N) {
        int mask = (1 << N) - 1;  // N개의 하위 비트가 모두 1인 값 생성
        // 처음 & 연산을 통해 mask 길이의 비트열만 남긴 뒤 비교한다.
        return (M & mask) == mask ? TRUE : FALSE;
    }
}

/*
4 47
101111
1111 ok 만족
 */