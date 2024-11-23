package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 최솟값 찾기
// https://www.acmicpc.net/problem/11003
public class no_11003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Deque<long[]> deque = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            long cur = Long.parseLong(st.nextToken());

            // deque 가 비어있지 않고, 마지막 값이 현재 값보다 클 경우
            while(!deque.isEmpty() && deque.peekLast()[1] > cur){
                deque.pollLast();
            }

            deque.offerLast(new long[]{i, cur});

            // 범이 외의 값이 있는 경우 제거한다.
            if(deque.getFirst()[0] <= i - l){
                deque.pollFirst();
            }

            bw.write(deque.peekFirst()[1]+" ");
        }

        bw.flush();
        bw.close();
    }
}
