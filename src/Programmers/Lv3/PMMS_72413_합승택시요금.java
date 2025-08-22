package Programmers.Lv3;

import java.util.*;

public class PMMS_72413_합승택시요금 {
    public static void main(String[] args) {
        int n = 6, s = 4, a = 6, b = 2;
        int[][] fares = {
                {4,1,10},
                {3,5,24},
                {5,6,2},
                {3,1,4},
                {5,1,24},
                {4,6,50},
                {2,4,66},
                {2,3,22},
                {1,6,25}
        };

        System.out.println(new PMMS_72413_합승택시요금().solution(n,s,a,b,fares));
    }

    class Route {
        int to;
        int cost;

        public Route(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    List<Route>[] nodes;
    int singleCost;
    int multiCost;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        /*
            n : 3 ~ 200
            fares : (from, to, cost)
            ---
            고려사항
            1. 경유지가 존재한다.
            2. 노드의 개수가 300 개이다. ( 1 ~ n 번 )
            3. 합승을 하지 않고 이동하는 경우가 더 싸다면 합승을 하지 않는다.
         */
        init(n, fares);     // 경로 연결 구성
        findMinCost(s, a, true);    // 혼자 타고 가는 경우
        findMinCost(s, b, false);
        int nCost = multiCost/2;
        multiCost = Integer.MAX_VALUE;
        findMinCost(b, a, false);
        nCost += multiCost;
        return Math.min(singleCost, nCost);
    }

    void init(int n, int[][] fares) {
        singleCost = multiCost = Integer.MAX_VALUE;
        nodes = new ArrayList[n+1];
        for(int i=0; i<n+1; i++) nodes[i] = new ArrayList<>();

        for(int[] conn : fares) {
            int from = conn[0];
            int to = conn[1];
            int cost = conn[2];
            nodes[from].add(new Route(to, cost));
        }
    }

    void findMinCost(int start, int end, boolean isSingle) {
        int targetCost = isSingle ? singleCost : multiCost;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
        // 1. 시작 위치 설정
        pq.offer(new int[] {start, 0});
        // 2. 최단거리 찾기
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];
            if(curNode == end) {    // 2-1. 목적지에 도착
                targetCost = Math.min(targetCost, curCost);
                continue;
            }
            // 2-2. 이전의 최적해를 넘는 경우
            if(curCost >= targetCost) continue;

            for(Route route : nodes[curNode]) {
                pq.offer(new int[] {route.to, curCost+route.cost});
            }
        }

        if(isSingle) singleCost = targetCost;
        else multiCost = targetCost;
    }
}
