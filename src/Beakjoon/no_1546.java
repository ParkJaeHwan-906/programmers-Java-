package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 평균
// https://www.acmicpc.net/problem/1546
public class no_1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] score = new int[n];
        for(int i=0; i<n; i++){
            score[i] = Integer.parseInt(st.nextToken());
        }

        no_1546 problem = new no_1546();
        System.out.println(problem.solution(score));
    }

    public double solution(int[] score) {
        Arrays.sort(score);

        int max = score[score.length-1];

        double sum = 0;
        for(int i : score){
            sum += (double) i/max *100;
        }
        return sum / score.length;
    }
}
