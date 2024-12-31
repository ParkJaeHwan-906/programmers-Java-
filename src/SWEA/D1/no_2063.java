package SWEA.D1;

import java.util.*;
import java.io.*;

// SWEA D1
// 2063. 중간값 찾기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5QPsXKA2UDFAUq
public class no_2063 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(arr);

        bw.write(String.valueOf(arr[n/2]));
        bw.flush();
        bw.close();
    }
}
