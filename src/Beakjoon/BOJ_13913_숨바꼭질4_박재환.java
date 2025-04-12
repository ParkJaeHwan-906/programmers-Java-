package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_13913_숨바꼭질4_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }

    static StringTokenizer st;
    static int n, k;    // 수빈, 동생
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        getFastTime();

        // 최소 이동 시간
        sb.append(time[k]-1).append('\n');

        // 경로 역추적
        int idx = k;
        Stack<Integer> st = new Stack<>();
        st.push(k);
        while(idx != n) {
            st.push(prev[idx]);
            idx = prev[idx];
        }

        while(!st.isEmpty()) {
            sb.append(st.pop()).append(' ');
        }
    }

    /*
        최단 시간과, 최단 시간을 구성하는 경로를 역추적 해야한다.
     */
    static int[] prev, time;    // 이전 경로, 이동 시간
    static void getFastTime() {
        prev = new int[100001];     // 현재 노드의 이전 노드를 저장
        time = new int[100001];     // 현재 노드까지의 이동 시간 저장

        Queue<Integer> q = new LinkedList<>();
        q.add(n);   // 수빈이의 초기 위치
        time[n] = 1;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if (cur == k) return;    // 동생을 찾았다면 종료

            // 수빈이가 이동할 수 있는 방법은 3 가지
            // 전진, 후진, 순간이동
            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) next = cur + 1;
                else if (i == 1) next = cur - 1;
                else next = cur * 2;

                // 이동 범위가 범위를 벗어나는지 확인
                if (next < 0 || next > 100000) continue;

                if (time[next] == 0) {
                    q.add(next);
                    time[next] = time[cur] + 1;
                    prev[next] = cur;
                }
            }
        }
    }
}

/*
    수빈이는 N 에 있고, 동생은 K 에 있다.
    수빈이는 걷거나, 순간이동을 할 수 있다.
    수빈이가 X 위치에 있을 때, 걷는다면 X+1 / X-1 이 된다.
    순간이동을 하는 경우레는 2*X 의 위치로 이동하게 된다.
 */