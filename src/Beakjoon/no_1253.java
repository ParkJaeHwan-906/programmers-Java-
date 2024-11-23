package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON - 투포인터
// 좋다
// https://www.acmicpc.net/problem/1253
public class no_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        no_1253 problem = new no_1253();
        System.out.println(problem.solution(arr));
    }

    public long solution(long[] arr){
        long count = 0;

        Arrays.sort(arr);

        for(int i=0; i<arr.length; i++) {
            int left = 0;
            int right = arr.length - 1;
            while (left < right) {
                long sum = arr[left] + arr[right];

                if (sum == arr[i] && left != i && right != i) {
                    count++;
                    break;
                } else if (left == i){
                    left++;
                } else if (right == i) {
                    right--;
                } else if (sum > arr[i]) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return count;
    }
}
