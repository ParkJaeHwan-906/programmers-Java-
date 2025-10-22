package Beakjoon;

import java.util.*;
import java.io.*;

public class 숨바꼭질3 {
    public static void main(String[] args) throws IOException {
        init();
    }

    static BufferedReader br;
    static StringTokenizer st;
    static int n, k;        // n : 수빈, K : 동생
    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        System.out.println(findSister());
        br.close();
    }

    /**
     * 수빈이는 걷거나, 순간이동을 할 수 있다.
     * 수빈이의 위치가 x 일 때
     * - 걷는다면 1초 후에 x-1, x+1 로 이동이 가능하다.
     * - 순간이동하는 경우 0 초 후에 2*x 의 위치로 이동한다.
     *
     * => 순간이동 0, 걷기 1
     */
    static final int INF = 100_000;
    static int findSister() {
        if(n == k) return 0;                // 찾을 필요가 없는 경우
        int[] arr = new int[INF+1];        // 최대 길이가 10만, 0 ~ 100,000
        Arrays.fill(arr, INF);             // 최대 값으로 초기화

        Deque<Integer> dq = new ArrayDeque<>();
        arr[n] = 0;                         // 시작 위치 0 초 초기화
        dq.offer(n);

        while(!dq.isEmpty()) {
            int x = dq.pollFirst();
            int time = arr[x];

            if(x == k) break;       // 동생을 찾음

            // 1. 순간이동
            int nx = 2*x;
            if(nx <= INF && arr[nx] > time) {
                arr[nx] = time;
                dq.offerFirst(nx);      // 가중치 0
            }
            // 2. 걷기
            // 2-1. +1
            nx = x+1;
            if(nx <= INF && arr[nx] > time+1) {
                arr[nx] = time+1;
                dq.offerLast(nx);      // 가중치 1
            }
            // 2-2. -1
            nx = x-1;
            if(nx > -1 && arr[nx] > time+1) {
                arr[nx] = time+1;
                dq.offerLast(nx);      // 가중치 1
            }
        }

        return arr[k];
    }
}
