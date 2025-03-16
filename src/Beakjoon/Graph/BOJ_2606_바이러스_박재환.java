package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_2606_바이러스_박재환 {
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
    static int answer;
    static void init() throws IOException {
        nodes = Integer.parseInt(br.readLine().trim());
        edges = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList[nodes+1];
        for(int node=0; node<=nodes; node++) {
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<edges; edge++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            graph[nodeA].add(nodeB);
            graph[nodeB].add(nodeA);
        }
        br.close();
        answer = 0;
        virus();

        bw.write(String.valueOf(answer));
    }

    static void virus() {
        boolean[] visited = new boolean[nodes+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;

        while(!q.isEmpty()) {
            int now = q.poll();
            for(int node : graph[now]) {
                if(visited[node]) continue;

                visited[node] = true;
                q.offer(node);
                answer++;
            }
        }
    }
}
