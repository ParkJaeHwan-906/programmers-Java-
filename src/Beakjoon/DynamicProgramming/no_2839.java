package Beakjoon.DynamicProgramming;

import java.util.*;
import java.io.*;
// 백준 - 다이나믹 프로그래밍
// 설탕 배달
// https://www.acmicpc.net/problem/2839
public class no_2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();

        int sum = n / 5;
        n = n%5;
        sum += n / 3;
        System.out.println(n % 3 == 0 ? sum : -1);
//        no_2839 problem = new no_2839();
//        System.out.println(problem.solution(n));
    }

//    public int solution(int n){
//        int[] dp = new int
//    }
}
