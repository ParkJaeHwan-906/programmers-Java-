package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 1206. [S/W 문제해결 기본] 1일차 - View
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV134DPqAA8CFAYh&categoryId=AV134DPqAA8CFAYh&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_1206 {

    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        no_1206 problem = new no_1206();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 10개가 주어짐
        for(int tc=1; tc<=10; tc++){
            sb.append("#").append(tc).append(" ");

            n = Integer.parseInt(br.readLine());
            arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++){
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
        int answer = 0;
        // 좌측, 우측 2칸은 0
        for(int i=2; i<n-2; i++){
            // 현재 건물 왼쪽에서 가장 높은 건물
            int left = Math.max(arr[i-1], arr[i-2]);
            // 현재 건물 오른쪽에서 가장 높은 건물
            int right = Math.max(arr[i+1], arr[i+2]);

            int max = Math.max(left, right);

            answer = arr[i] > max ? arr[i] - max + answer : answer;
        }

        return answer;
    }
}
