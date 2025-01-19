package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 3307. 최장 증가 부분 수열
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWBOKg-a6l0DFAWr
public class no_3307 {
    static int[] arr;
    static int arrLen;
    public static void main(String args[]) throws IOException
    {
        no_3307 problem = new no_3307();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");	// 문제 번호

            arrLen = Integer.parseInt(br.readLine().trim());	// 배열의 길이
            arr = new int[arrLen];

            // 원소 입력 받음
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int i=0; i < arrLen; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(problem.solution()).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int solution() {
        //  dp 배열 생성
        int[] dp = new int[arrLen];
        Arrays.fill(dp, 1); 	// 1로 채움 (자기 자신)

        for(int i=1; i<arrLen; i++) {
            for(int j=0; j<i; j++) {
                if(arr[i] > arr[j]) {	// 기준 값이(i), 현재(j) 의 값보다 크다면
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        return dpMax(dp);
    }

    private int dpMax(int[] dp) {
        int max = Integer.MIN_VALUE;

        for(int i : dp) {
            max = Math.max(max, i);
        }

        return max;
    }
}
