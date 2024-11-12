package Beakjoon.DynamicProgramming;

import java.io.*;
// 백준 - 다이나믹 프로그래밍
// 설탕 배달
// https://www.acmicpc.net/problem/2839
public class no_2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        br.close();
        solution(n);
    }

    public static void solution(int n){
        if (n == 4 || n == 7) {
            System.out.println(-1);
        }
        else if (n % 5 == 0) {
            System.out.println(n / 5);
        }
        else if (n % 5 == 1 || n % 5 == 3) {
            System.out.println((n / 5) + 1);
        }
        else if (n % 5 == 2 || n % 5 == 4) {
            System.out.println((n / 5) + 2);
        }
    }
}
