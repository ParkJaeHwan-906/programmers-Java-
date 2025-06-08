package Programmers.Lv3;

import java.util.*;

public class PMMS_49189_가장먼노드 {
    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};

        System.out.println(new PMMS_49189_가장먼노드().solution(n, vertex));
    }

    List<int[]>[] graph;          // 그래프를 구성할 리스트
    int farNodes;                 // 가장 멀리 떨어진 노드들의 개수
    public int solution(int n, int[][] edge) {
        makeGraph(n, edge);
        dijkstra();
        return farNodes;
    }

    /*
        그래프를 생성한다.
        노드에서 노드간의 거리는 임의로 1로 설정
     */
    void makeGraph(int n, int[][] edge) {
        graph = new ArrayList[n+1];
        for(int i=0; i<n+1; i++) graph[i] = new ArrayList<>();

        for(int[] conn : edge) {
            int from = conn[0], to = conn[1];

            graph[from].add(new int[] {to, 1});
            graph[to].add(new int[] {from, 1});
        }

        // 생성 확인
//        for(int i=1; i<n+1; i++) {
//            System.out.print(i + " : ");
//            for(int[] arr : graph[i]) {
//                System.out.print(Arrays.toString(arr)+" ");
//            }
//            System.out.println();
//        }
    }

    /*
        다익스트라를 사용해, 1번 노드에서 모든 노드로의 최단 거리를 구한다.
     */
    void dijkstra() {
        // [도착 노드, 누적 거리]
        // 누적거리 기준 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1]-b[1]);
        // 거리를 기록할 배열
        int[] dist = new int[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 1. 초기 출발 노드 세팅
        dist[1] = 0;
        pq.add(new int[] {1,dist[1]});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0], curDist = cur[1];

            if(curDist > dist[curNode]) continue;

            // 현재 노드와 연결되어있는 노드들을 확인
            for(int[] conn : graph[curNode]) {
                int connNode = conn[0];
                int connDist = conn[1];

                if(dist[connNode] > curDist + connDist) {
                    dist[connNode] = curDist + connDist;
                    pq.add(new int[] {connNode, dist[connNode]});
                }
            }
        }

        // 가장 먼 노드의 개수 찾기
        findFarNodes(dist);
    }
    
    void findFarNodes(int[] dist) {
        farNodes = 0;

        int farDist = Integer.MIN_VALUE;
        for(int i = 1; i < graph.length; i++) {
            farDist = Math.max(farDist, dist[i]);
        }

        for(int i=1; i<graph.length; i++) {
            if(dist[i] == farDist) farNodes++;
        }
    }
}

/*
    n 개의 노드가 있고, 노드들은 1 ~ n 의 번호를 가지고 있다.
    1 번 노드에서 가장 멀리 떨어진 노드의 갯수를 구하려한다.

    -> 1 번 노드에서 가장 멀리 떨어진 노드 찾기
    -> 1. 다익스트라를 사용해서 1 번 노드에서 각 노드로의 최단 거리 구하기
    -> 2. 트리처럼 구성해서 Depth 로 판단하기
 */