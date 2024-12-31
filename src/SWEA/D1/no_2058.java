package SWEA.D1;

import java.util.*;
import java.io.*;

// SWEA D1
// 2058. 자릿수 더하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5QPRjqA10DFAUq
public class no_2058 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String n = br.readLine();

        int result = 0;
        for(char c : n.toCharArray()) {
            result += c - '0';
        }

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }
}
