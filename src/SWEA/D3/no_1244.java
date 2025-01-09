package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 1244. [S/W 문제해결 응용] 2일차 - 최대 상금
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15Khn6AN0CFAYD&
public class no_1244 {

    static int[] arr;
    static int n;
    static int answer;
    public static void main(String[] args) throws IOException {
        no_1244 problem = new no_1244();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());   // 테스트케이스의 수
        for(int i = 1; i <= tc; i++) {
            sb.append("#").append(i).append(" ");

            // 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            String num = st.nextToken();
            arr = new int[num.length()];
            for(int j=0; j<arr.length; j++) {
                arr[j] = num.charAt(j) - '0';
            }
            n = Integer.parseInt(st.nextToken());

            if(n > arr.length) {
                n = arr.length;
            }

            sb.append(problem.solution()).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int solution () {
        answer = 0;

        dfs(0,0);

        return answer;
    }

    private void dfs(int start, int depth){
        // 교환 완료
        if(depth == n) {
            answer = Math.max(makeNum(), answer);
            return;
        }

        // 교환
        for(int i = start; i<arr.length; i++) {
            for(int j=i+1; j<arr.length; j++){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;

                dfs(i, depth+1);

                // 백트래킹
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
    }

    private int makeNum() {
        int result = 0;
        for(int i : arr) {
            result = result * 10 + i;
        }

        return result;
    }
}
