package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_11724_연결요소의개수_박재환 {
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
    static List<Integer>[] graph;
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        nodes = Integer.parseInt(st.nextToken());
        edges = Integer.parseInt(st.nextToken());

        graph = new ArrayList[nodes+1];
        for(int node=0; node<=nodes; node++) {
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<edges; edge++) {
            st = new StringTokenizer(br.readLine().trim());

            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            graph[nodeA].add(nodeB);
            graph[nodeB].add(nodeA);
        }
        br.close();
        bw.write(String.valueOf(grouping()));
    }

    static boolean[] visited;
    static int grouping() {
        int group = 0;

        visited = new boolean[nodes+1];
        for(int node=1; node<=nodes; node++) {
            // 이미 묶인 노드라면 패스
            if(visited[node]) continue;

            visitCheck(node);
            group++;
        }

        return group;
    }

    static void visitCheck(int node) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(node);
        visited[node] = true;

        while(!q.isEmpty()) {
            int curNode = q.poll();

            for(int connNode : graph[curNode]) {
                if(visited[connNode]) continue;

                visited[connNode] = true;
                q.offer(connNode);
            }
        }
    }
}
