package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// ATM
// https://www.acmicpc.net/problem/11399
public class no_11399 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(shortestTime());
    }

    public static int shortestTime() {
        int time = 0;
        int answer = 0;

        // 걸리는 시간이 적은 순으로 정렬
        Arrays.sort(arr);
        for(int i : arr){
            time = time + i;
            answer += time;
        }

        return answer;
    }
}
