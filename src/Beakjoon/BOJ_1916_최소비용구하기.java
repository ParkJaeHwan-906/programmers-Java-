package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1916_최소비용구하기 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int cityCnt, busCnt;
    static List<int[]>[] graph;     // 각 정점에서 다른 정점으로의 연결 정보 저장 [도착, 가중치]
    static void init() throws IOException {
        cityCnt = Integer.parseInt(br.readLine().trim());
        busCnt = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList[cityCnt+1];   // 1-base
        for(int i=0; i<cityCnt+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<busCnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new int[] {to, weight});
        }

        st = new StringTokenizer(br.readLine().trim());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        getMinDist(from, to);
    }

    static void getMinDist(int from, int to) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        int[] dist = new int[cityCnt+1];    // 1-base
        Arrays.fill(dist, 987654321);
        dist[from] = 0;     // 출발지 초기화
        pq.offer(new int[] {from, 0});     // [도착지, 현재까지의 가중치]

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curDist = cur[1];

            if(dist[curNode] < curDist) continue;

            // 현 노드와 연결되어있는 노드들을 확인
            for(int[] conn : graph[curNode]) {
                int connNode = conn[0];
                int connDist = conn[1];

                if(dist[connNode] > curDist + connDist) {   // 최단 거리 갱신이 가능하다면
                    dist[connNode] = curDist + connDist;
                    pq.offer(new int[] {connNode, dist[connNode]});
                }
            }
        }

        System.out.println(dist[to]);
    }
}

/*
    도시의 개수가 주어진다. ( N )
    버스의 개수가 주어진다. ( M )
    출발 -> 도착 , 비용

    A -> B 까지 가는데 드는 최소비용 : 다익스트라?
 */