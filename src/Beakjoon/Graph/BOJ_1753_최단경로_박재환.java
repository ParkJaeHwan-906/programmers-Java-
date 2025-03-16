package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_1753_최단경로_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        init();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int nodes, edges, startPoint;
    static List<int[]>[] graph;
    static int[] distArr;
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        nodes = Integer.parseInt(st.nextToken());
        edges = Integer.parseInt(st.nextToken());

        startPoint = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList[nodes+1];
        distArr = new int[nodes+1];
        for(int node=0; node<=nodes; node++) {
            graph[node] = new ArrayList<int[]>();
        }

        for(int edge=0; edge<edges; edge++) {
            st = new StringTokenizer(br.readLine().trim());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[nodeA].add(new int[] {nodeB, dist});
        }
        br.close();

        findRoute(startPoint);

       for(int node=1; node<=nodes; node++) {
           if(distArr[node] == Integer.MAX_VALUE) sb.append("INF");
           else sb.append(distArr[node]);
           sb.append('\n');
       }
    }

    static void findRoute(int startPoint) {
        Arrays.fill(distArr, Integer.MAX_VALUE);
        // 거리를 기준으로 가까운 노드부터 처리한다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {startPoint, 0});
        distArr[startPoint] = 0;    // 자기 자신으로 경로는 0으로 초기화

        while(!pq.isEmpty()) {
            int[] curInfo = pq.poll();
            int curNode = curInfo[0];
            int curDist = curInfo[1];

            if(curDist > distArr[curNode]) continue;

            for(int[] connNodes : graph[curNode]) {
                int connNode = connNodes[0];
                int connNodeDist = connNodes[1];

                if(curDist+connNodeDist > distArr[connNode]) continue;

                distArr[connNode] = curDist+connNodeDist;
                pq.offer(new int[] {connNode, curDist+connNodeDist});
            }
        }
    }
}
