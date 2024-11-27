package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 수 정렬하기2
// https://www.acmicpc.net/problem/2751
public class no_2751 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Arrays.sort(arr);
        for(int i : arr){
            bw.write(i+"\n");
        }
        bw.flush();
        bw.close();

    }
}
