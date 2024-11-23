package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON - 투포인터
// 주몽
// https://www.acmicpc.net/problem/1940
public class no_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        no_1940 problem = new no_1940();
        System.out.println(problem.solution(arr, m));
    }

    public long solution(int[] arr, int m){
        int left = 0;
        int rigth = arr.length-1;

        // ⚠️ 범위를 좁혀가며 탐색하기 위해 오름차순 정렬
        Arrays.sort(arr);

        long count = 0;
        while(left < rigth){
            int sum = arr[left] + arr[rigth];

            if(sum == m){
                count++;
                left++;
                rigth--;
            }else if(sum > m){
                rigth--;
            }else{
                left++;
            }
        }
        return count;
    }
}
