package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1922_네트워크연결_프림_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        bw.flush();
        bw.close();
    }

    static int nodes, edges;
    static List<int[]>[] graph;
    static void init() throws IOException {
        nodes = Integer.parseInt(br.readLine().trim());
        edges = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList[nodes+1];
        for(int node=0; node<nodes+1; node++) {
            graph[node] = new ArrayList<>();
        }

        while(edges-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[nodeA].add(new int[] {nodeB, dist});
            graph[nodeB].add(new int[] {nodeA, dist});
        }

        getMinCost();
    }

    static void getMinCost() throws IOException{
        // 가중치를 기준으로 오름차순 정렬한다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        boolean[] visited = new boolean[nodes+1];   // 방문 처리를 위한 배열

        int totalCost = 0;
        int nodeCnt = 0;

        // 초기 위치 설정
        pq.offer(new int[] {1,0});

        while(!pq.isEmpty()) {  // 모든 노드를 처리할 때 까지
            int[] curInfo = pq.poll();
            int toNode = curInfo[0];

            if(visited[toNode]) continue;

            visited[toNode] = true;
            totalCost += curInfo[1];

            if(++nodeCnt == nodes) break;

            for(int[] connNode : graph[toNode]) {
                if(visited[connNode[0]]) continue;  // 이미 방문 처리한 노드면 패스

                pq.offer(connNode);
            }
        }

        bw.write(String.valueOf(totalCost));
    }
}

/*
 * 컴퓨터의 수 N은 1이상 1,000이하이다.
 * 연결할 수 있는 선의 수 1이상 100,000이하이다.
 *
 *
 * 프림 사용
 */