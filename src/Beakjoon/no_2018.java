package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON - 투포인터
// 수들의 합 5
// https://www.acmicpc.net/problem/2018
public class no_2018 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for(int i=1; i<=n; i++){
            arr[i-1] = i;
        }

        no_2018 problem = new no_2018();
        System.out.println(problem.solution(arr, n));
    }

    // 연속된 자연수의 합이 n 이 되게함
    //  ⚠️ 해당 문제는 이분탐색처럼 범위를 줄이지 않음, 시작과 끝 지점의 설정 값이 둘 다 0
    public long solution(int[] arr, int n){
        int left = 0;
        int right = 0;
        long sum = 0;

        int count = 1;  // n 자기 자신 하나만 있는 경우를 포함

        while(right < n){
            if(sum == n) {
                count++;
                sum += arr[right++];
            } else if(sum > n) {
                sum -= arr[left++];
            } else {
                sum += arr[right++];
            }
        }
        return count;
    }
}
