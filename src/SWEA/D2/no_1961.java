package SWEA.D2;

import java.util.*;
import java.io.*;

// SWEA D2
// 1691. 숫자 배열 회전
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pq-OKAVYDFAUq
public class no_1961 {
    public static void main(String[] args) throws IOException {
        no_1961 problem = new no_1961();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        // 테스트
        int tc = Integer.parseInt(br.readLine());

        for(int i=1; i<=tc; i++) {
            sb.append("#").append(i).append("\n");

            // n 입력
            int n = Integer.parseInt(br.readLine());

            // map 설정
            int[][] map = new int[n][n];
            for(int j=0; j<n; j++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int z=0; z<n; z++){
                    map[j][z] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] arr90 = problem.rotateArr(map, n);
            int[][] arr180 = problem.rotateArr(arr90, n);
            int[][] arr270 = problem.rotateArr(arr180, n);

            for(int k=0; k<n; k++){
                int[] arr1 = arr90[k];
                int[] arr2 = arr180[k];
                int[] arr3 = arr270[k];

                sb.append(problem.outPutArr(arr1)).append(" ")
                        .append(problem.outPutArr(arr2)).append(" ")
                        .append(problem.outPutArr(arr3)).append("\n");
            }
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int[][] rotateArr(int[][] map, int n) {
        int[][] result = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                result[i][j] = map[n-1-j][i];
            }
        }

        return result;
    }

    private String outPutArr(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for(int i : arr) {
            sb.append(i);
        }

        return sb.toString();
    }
}
