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

//        long beforeTime = System.currentTimeMillis();
//        System.out.println("Queue 를 이용한 풀이 : " + problem.solutionQueue(n,k));
//        long afterTime = System.currentTimeMillis();
//        System.out.println("걸린 시간 : " + (double)(afterTime - beforeTime)/1000);

//        long beforeTime2 = System.currentTimeMillis();
//        System.out.println("Stack 를 이용한 풀이 : " + problem.solutionStack(n,k));
//        long afterTime2 = System.currentTimeMillis();
//        System.out.println("걸린 시간 : " + (double)(afterTime2 - beforeTime2)/1000);


//        long beforeTime3 = System.currentTimeMillis();
//        System.out.println("PriorityQueue 를 이용한 풀이 : " + problem.solutionPriorityQueue());
//        long afterTime3 = System.currentTimeMillis();
//        System.out.println("걸린 시간 : " + (double)(afterTime3 - beforeTime3)/1000);

//        long beforeTime4 = System.currentTimeMillis();
//        System.out.println("BinarySearch 를 이용한 풀이 : " + problem.solutionBinarySearch(n,k));
//        long afterTime4 = System.currentTimeMillis();
//        System.out.println("걸린 시간 : " + (double)(afterTime4 - beforeTime4)/1000);
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

            if(loc == n && speed <= k) return time;

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
        Set<String> duplicatedRoute = new HashSet<>();
        Stack<int[]> stack = new Stack<>();

        // 시작 설정
        stack.push(new int[] {1,0,0});
        duplicatedRoute.add(1+","+0);

        while(!stack.isEmpty()){
            int[] cur = stack.pop();
            int loc = cur[0];
            int speed = cur[1];
            int time = cur[2];
//            duplicatedRoute.add(loc+" "+speed+" "+time);
            if(loc == n && speed <= k) return time;

            for(int i = -k; i <= k; i++){
                int newSpeed = speed + i;
                // 속도가 음수인 경우
                if(newSpeed < 0) continue;

                int newLoc = loc + newSpeed;
                // 거리를 벗어나거나, 제자리인 경우
                if(newLoc > n || newLoc == loc) continue;
                if(duplicatedRoute.contains(newLoc+","+newSpeed)) continue;
                stack.push(new int[] {newLoc, newSpeed, time+1});
                duplicatedRoute.add(newLoc+","+newSpeed);
            }
        }
        return 0;
    }

    public int solutionPriorityQueue() {
       Set<String> route = new HashSet<>();
       // [위치, 속도, 시간]
        // 시간 기준 오름차순으로 정렬
       PriorityQueue<int[]> pq = new PriorityQueue<>((e1 ,e2) -> {return e1[2] - e2[2];});

       pq.offer(new int[] {1,0,0});
       route.add(1+","+0);
       while(!pq.isEmpty()){
           int[] cur = pq.poll();
           int loc = cur[0];
           int speed = cur[1];
           int time = cur[2];

           if(loc == n && speed <= k) return time;

           for(int i=-k; i<=k; i++){
               int newSpeed = speed + i;
               // 속도가 음수인 경우
               if(newSpeed < 0) continue;

               int newLoc = loc + newSpeed;
               // 거리를 벗어나거나, 제자리인 경우
               if(newLoc > n || newLoc == loc) continue;
               // 이미 같은 조건이 있었던 경우
               if(route.contains(newLoc+","+newSpeed)) continue;

               route.add(newLoc+","+newSpeed);
               pq.offer(new int[] {newLoc, newSpeed, time+1});
           }
       }
       return -1;
    }

//    // 이분탐색 풀이
//    public int solutionBinarySearch(int n, int k){
//        int left = 1;
//        int right = n;
//
//        while(left <= right){
//            int mid = (left + right) / 2;
//
//            if (isPossible(mid)) {  // mid 시간 안에 도달 가능하다면
//                right = mid - 1;  // 더 짧은 시간 탐색
//            } else {
//                left = mid + 1;  // 더 긴 시간 탐색
//            }
//        }
//        return left;
//    }
//
//    public static boolean isPossible(int T) {
//        // BFS로 탐색하며 T 시간 안에 도달 가능 여부 확인
//        Queue<int[]> q = new LinkedList<>();
//        Set<String> visited = new HashSet<>();
//        q.offer(new int[]{1, 0});  // 초기 위치와 속도
//        visited.add(1 + "," + 0);  // 방문 기록
//
//        while (!q.isEmpty()) {
//            int[] cur = q.poll();
//            int loc = cur[0];
//            int speed = cur[1];
//
//            if (loc == n && speed == 0) return true;  // 목적지 도달
//
//            for (int i = -k; i <= k; i++) {
//                int newSpeed = speed + i;
//                if (newSpeed < 0) continue;
//
//                int newLoc = loc + newSpeed;
//                if (newLoc < 1 || newLoc > n) continue;  // 유효한 범위 벗어남
//
//                String state = newLoc + "," + newSpeed;
//                if (visited.contains(state)) continue;  // 이미 방문한 상태
//
//                // T 초를 넘기지 않는다면 추가 탐색
//                if (Math.abs(newLoc - n) <= newSpeed * (T - 1)) {
//                    q.offer(new int[]{newLoc, newSpeed});
//                    visited.add(state);
//                }
//            }
//        }
//
//        return false;  // T 초 안에 도달 불가
//    }
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