package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 22979. 문자열 옮기기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AZPOBiaqNo8DFAWB
public class no_22979
{
    static String s;
    static int n;
    static int[] arr;
    public static void main(String args[]) throws Exception
    {
        no_22979 problem = new no_22979();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T;
        T=Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            s = br.readLine();
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(st.nextToken()) % s.length();
            }

            sb.append(problem.solution()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    Deque<Character> deque = new ArrayDeque<>();
    public String solution() {
        // 문자열을 Deque 에 저장
        setDeque();

        for(int i : arr) {
            if(i > 0) plus(i);
            else if(i < 0) minus(Math.abs(i));
        }

        return toStringDeque();
    }

    private String toStringDeque() {
        StringBuilder sb = new StringBuilder();

        while(!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }

        return sb.toString();
    }

    private void setDeque() {
        for(char c : s.toCharArray()) {
            deque.offer(c);
        }
    }

    //  X > 0이면, S의 첫 번째 글자를 떼네어 마지막 글자 뒤에 붙이는 작업을 정확히 X회 반복한다.
    private void plus(int num){
        for(int i=0; i<num; i++) {
            deque.offerLast(deque.pollFirst());
        }
    }
    // X < 0이면, S의 마지막 글자를 떼네어 첫 번째 글자 앞에 붙이는 작업을 정확히 -X회 반복한다.
    private void minus(int num){
        for(int i=0; i<num; i++) {
            deque.offerFirst(deque.pollLast());
        }
    }
}
