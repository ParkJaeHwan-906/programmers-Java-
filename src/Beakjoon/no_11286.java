package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 절댓값 힙
// https://www.acmicpc.net/problem/11286
public class no_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        // 절댓값 기준 오름차순 -> 음수 우선
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            int absA = Math.abs(a);
            int absB = Math.abs(b);

            if(absA == absB) return a - b;

            return absA - absB;
        });

        for(int i=0; i<n; i++){
            int num = Integer.parseInt(br.readLine());

            if(num == 0){
                if(pq.isEmpty()) bw.write("0\n");
                else bw.write(pq.poll()+"\n");
            }else{
                pq.offer(num);
            }
        }
        br.close();
        bw.flush();
        bw.close();
    }
}
