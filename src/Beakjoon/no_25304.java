package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 영수증
// https://www.acmicpc.net/problem/25304
public class no_25304 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long total = Long.parseLong(br.readLine());
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            total -= (Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()));
        }

        System.out.println(total == 0 ? "Yes" : "No");
    }
}
