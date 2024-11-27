package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 카드2
// https://www.acmicpc.net/problem/2164
public class no_2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        no_2164 problem = new no_2164();
        long s1 = System.currentTimeMillis();
        System.out.println(problem.solution(n));
        long e1 = System.currentTimeMillis();
        System.out.println((double) (e1-s1)/1000);


        long s2 = System.currentTimeMillis();
        System.out.println(problem.solution2(n));
        long e2 = System.currentTimeMillis();
        System.out.println((double) (e2-s2)/1000);
    }

    Deque<Integer> deque = new ArrayDeque<>();
    public int solution(int n){
        // 초기 세팅
        for(int i=1; i<=n; i++) {
            deque.offerLast(i);
        }
        mix();
        return deque.poll();
    }

    Queue<Integer> q = new LinkedList<>();
    public int solution2(int n){
        // 초기 세팅
        for(int i=1; i<=n; i++) {
            q.offer(i);
        }
        mix2();
        return q.poll();
    }

    private void mix(){
        while(deque.size() > 1){
            // 상단 카드를 버린다.
            deque.pollFirst();

            deque.offerLast(deque.pollFirst());
        }
    }

    private void mix2(){
        while(q.size() > 1){
            // 상단 카드를 버린다.
            q.poll();

            q.offer(q.poll());
        }
    }
}
