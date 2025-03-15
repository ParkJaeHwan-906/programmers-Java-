package Level_3;

import java.util.*;

public class PMMERS_49189_가장먼노드_박재환 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, { 3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(new PMMERS_49189_가장먼노드_박재환().solution(n,edge));
    }

    List<Integer>[] graph;  // 연결 정보를 기록
    int leafNode;     // 리프 노드 -> 루트 노드를 1 로 고정 시키고 가장 먼 노드들
    public int solution(int n, int[][] edge) {
        leafNode = 0;

        graph = new ArrayList[n+1];
        for(int node=0; node<=n; node++) {
            graph[node] = new ArrayList<>();
        }

        // 그래프 연결정보 입력
        addEdge(edge);

        fixedRootNode(1, n);

        return leafNode;
    }

    void fixedRootNode(int node, int n) {
        int[] dist = new int[n+1];  // 각 노드들이 루트 노드 (1) 을 기준으로 하는 거리를 저장
        boolean[] visited = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;

        int maxDist = 0;
        while(!q.isEmpty()) {
            int curNode = q.poll();

            for(int connNode : graph[curNode]) {
                // 이미 방문 처리한 노드라면
                if(visited[connNode]) continue;

                // 방문 가능한 노드라면
                // == 자식 노드가 있다면
                dist[connNode] = dist[curNode] + 1; // 이전 노드에서 한단계 더 들어간다.
                visited[connNode] = true;
                maxDist = Math.max(dist[connNode], maxDist);
                q.offer(connNode);
            }
        }

        for(int d : dist) {
            if(d == maxDist) leafNode++;
        }
    }

    // 그래프의 간선 정보를 입력 받는다.
    void addEdge(int[][] edge) {
        for(int[] nodes : edge) {
            int nodeA = nodes[0];
            int nodeB = nodes[1];

             graph[nodeA].add(nodeB);
             graph[nodeB].add(nodeA);
        }
    }
}
/*
    1 ~ n 의 노드가 있음
    1 번 노드에서 가장 멀리 떨어진 노드의 개수를 구하려한다.

    노드의 개수는 2만개가 최대
    간선은 양방향, 최대 간선 5만개

     1번 노드를 루트 노드로
     리프노드들을 구한다.

    BFS 로 탐색해서 각 트리의 높이별로 탐색한다
    이때 모든 리프노드가 가장 멀다고 생각하지 않는다.
    각 노드마다 루트 노드 1 을 기준으로 하는 거리를 저장한 뒤,
    최대 값을 비교한다.
 */