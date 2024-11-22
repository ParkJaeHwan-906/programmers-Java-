package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 숫자의 합
// https://www.acmicpc.net/problem/11720
public class no_11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        no_11720 problem = new no_11720();
        System.out.println(problem.makeSum(s));
    }

    public int makeSum(String s){
        int sum = 0;
        for(char c : s.toCharArray()){
            sum += (c-'0');
        }
        return sum;
    }
}
