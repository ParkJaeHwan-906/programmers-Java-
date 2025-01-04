package SWEA.D2;

import java.io.*;
import java.util.*;

// SWEA D2
// 1959. 두 개의 숫자열
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpoFaAS4DFAUq&categoryId=AV5PpoFaAS4DFAUq&categoryType=CODE&problemTitle=%EB%91%90+%EA%B0%9C%EC%9D%98+%EC%88%AB%EC%9E%90%EC%97%B4&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_1959 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int i=1; i<= tc; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] nArr = new int[n];
            int[] mArr = new int[m];

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                nArr[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                mArr[j] = Integer.parseInt(st.nextToken());
            }

            long answer = n > m ? slidingWindow(mArr, nArr) : slidingWindow(nArr, mArr);

            sb.append("#").append(i).append(" ").append(answer).append("\n");

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    private static long slidingWindow(int[] arr1, int[] arr2){
        int arr1L = arr1.length;
        int arr2L = arr2.length;

        long answer = 0;

        for(int i = 0; i <= arr2L - arr1L; i++){
            long sum = 0;
            for(int j=0; j<arr1L; j++){
                sum += arr2[i+j] * arr1[j];
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }
}
