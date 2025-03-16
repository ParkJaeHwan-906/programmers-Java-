package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_1260_DFS와BFS_박재환 {
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

    static int nodes, edges, startNode;
    static List<Integer>[] graph;
    static boolean[] visited;
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        nodes = Integer.parseInt(st.nextToken());
        edges = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken());

        graph = new ArrayList[nodes+1];
        for(int node=0; node<=nodes; node++) {
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge < edges; edge++) {
            st = new StringTokenizer(br.readLine().trim());

            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            graph[nodeA].add(nodeB);
            graph[nodeB].add(nodeA);
        }

        // 정점의 번호가 작은 것부터 방문
        for(int node=0; node<=nodes; node++) {
            Collections.sort(graph[node]);
        }
        br.close();

        visited = new boolean[nodes+1];
        visited[startNode] = true;
        DFS(startNode);
        sb.append('\n');
        visited = new boolean[nodes+1];
        visited[startNode] = true;
        BFS(startNode);
    }

    static void DFS(int nowNode) {
        sb.append(nowNode).append(' ');

        // 현재 노드와 연결되어 있는 노드를 처리
        for(int node : graph[nowNode]) {
            if(visited[node]) continue;

            visited[node] = true;
            DFS(node);
        }
    }

    static void BFS(int nowNode) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(nowNode);
        sb.append(nowNode).append(' ');

        while(!q.isEmpty()) {
            nowNode = q.poll();
            // 현재 노드와 연결되어 있는 노드를 처리
            for (int node : graph[nowNode]) {
                if (visited[node]) continue;

                visited[node] = true;
                sb.append(node).append(' ');
                q.offer(node);
            }
        }
    }
}
