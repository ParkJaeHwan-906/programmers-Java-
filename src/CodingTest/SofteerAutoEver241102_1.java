package CodingTest;

import java.util.*;
import java.io.*;

public class SofteerAutoEver241102_1 {
    static int n, k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        k = Integer.parseInt(nm[1]);

        SofteerAutoEver241102_1 problem = new SofteerAutoEver241102_1();

        long beforeTime = System.currentTimeMillis();
        System.out.println("Queue 를 이용한 풀이 : " + problem.solutionQueue(n,k));
        long afterTime = System.currentTimeMillis();
        System.out.println("걸린 시간 : " + (double)(afterTime - beforeTime)/1000);

        long beforeTime2 = System.currentTimeMillis();
        System.out.println("Stack 를 이용한 풀이 : " + problem.solutionStack(n,k));
        long afterTime2 = System.currentTimeMillis();
        System.out.println("걸린 시간 : " + (double)(afterTime2 - beforeTime2)/1000);
    }

    // 1. Queue 를 이용한 풀이
    public int solutionQueue(int n, int k){
        // [현재 위치, 현재 속도, 시간]
        Queue<int[]> q = new LinkedList<>();

        // 시작 기록 [1,0,0]
        q.offer(new int[] {1,0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int loc = cur[0];
            int speed  = cur[1];
            int time = cur[2];

            if(loc == n && speed == k) return time;

            // 모든 경우를 탐색함 (감속 -> 가속)
            for(int i = -k; i<= k; i++){
                int newSpeed = speed + i;

                // 속도가 음수일 수 없음
                if(newSpeed < 0) continue;
                int newLoc = loc + newSpeed;
                // 주어진 거리를 벗어남, 제자리인경우
                if(newLoc > n || newLoc == loc) continue;
                q.offer(new int[] {newLoc, newSpeed, time+1});
            }
        }
        return 0;
    }

    // 2. Stack 을 이용한 풀이
    public int solutionStack(int n, int k){
        Stack<int[]> stack = new Stack<>();

        // 시작 설정
        stack.push(new int[] {1,0,0});

        while(!stack.isEmpty()){
            int[] cur = stack.pop();
            int loc = cur[0];
            int speed = cur[1];
            int time = cur[2];

            if(loc == n && speed == k) return time;

            for(int i = -k; i <= k; i++){
                int newSpeed = speed + i;
                // 속도가 음수인 경우
                if(newSpeed < 0) continue;

                int newLoc = loc + newSpeed;
                // 거리를 벗어나거나, 제자리인 경우
                if(newLoc > n || newLoc == loc) continue;
                stack.push(new int[] {newLoc, newSpeed, time+1});
            }
        }
        return 0;
    }
}
/*
자동차 주행 테스트를 하려한다.
N 길이의 도로가 있고, 자동차의 최대 가속력/감속력은 K이다.

가속과 감속에 걸리는 시간은 없으며, 1에서 출발해 N으로 도착하고, 도착시에는 속도가 0이 되어야한다.

걸리는 시간을 반환하여라

예들 들어
N : 9 K : 2 일때
1초에는 2의 가속을 하여 3으로 이동한다
2초에는 2를 가속해 4의 속도로 7로 이동한다
3초에는 2를 감속해 9에 도착하고 0으로 감속한다.

n은 100,000,000 보다 작거나 같고
k는 1이상 2이하이다
*/