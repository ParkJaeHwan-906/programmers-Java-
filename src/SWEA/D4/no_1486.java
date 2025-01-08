package SWEA.D4;

import java.util.*;
import java.io.*;

// SWEA D4
// 1486. 장훈이의 높은 선반
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV2b7Yf6ABcBBASw
public class no_1486 {
    static int n, b;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        no_1486 problem = new no_1486();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int test=1; test<=tc; test++) {
            sb.append("#").append(test).append(" ");

            // 문제 TC 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());   // 점원의 수
            arr = new int[n];
            b = Integer.parseInt(st.nextToken());   // 장훈이 키

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            //-----------------------

            sb.append(problem.solution()).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    private int min;  // 최소 높이를 저장
    private int solution() {
        min = Integer.MAX_VALUE;  // 최소 초과 높이 초기화

        dfs(0,0);

        return min - b;
    }

    private void dfs(int idx, int sum) {
        if(sum >= b) {
            min = Math.min(sum, min);
            return;
        }

        if (idx == n) return;

        dfs(idx + 1, sum + arr[idx]);  // 현재 점원을 선택
        dfs(idx + 1, sum);  // 현재 점원을 선택하지 않음
    }

}
