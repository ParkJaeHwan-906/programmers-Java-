package Beakjoon.BinarySearch;

import java.util.*;
import java.io.*;

// BAEKJOON
// 수 찾기
// https://www.acmicpc.net/problem/1920
public class no_1920_1 {
    static int n, m;
    static int[] target;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        m = Integer.parseInt(br.readLine());
        target = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            target[i] = Integer.parseInt(st.nextToken());
        }
        br.close();


        for(int i : target){
            if(Arrays.binarySearch(arr, i) >= 0) {
                bw.write(1+"\n");
            } else{
                bw.write(0+"\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
