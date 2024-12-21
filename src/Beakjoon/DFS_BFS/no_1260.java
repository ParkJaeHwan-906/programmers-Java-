package Beakjoon.DFS_BFS;

import java.util.*;
import java.io.*;

// BAEKJOON
// DFS와 BFS
// https://www.acmicpc.net/problem/1260
public class no_1260 {
    static int n, m, v;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        no_1260 problem = new no_1260();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        problem.setDefault();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            // 그래프 입력
            graph[a].add(b);
            graph[b].add(a);
        }

        problem.setSort();

        bw.write(problem.solution());
        bw.flush();
        bw.close();
    }

    public void setDefault(){
        graph = new ArrayList[n];

        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
    }

    public void setSort(){
        for(int i = 0; i < n; i++) {
            Collections.sort(graph[i]);
        }
    }

    StringBuilder sb = new StringBuilder();
    public String solution() {
        dfs(v-1, 1, new boolean[n]);
        sb.append("\n");
        bfs(v-1, new boolean[n]);

        return sb.toString();
    }

    private void dfs(int now, int depth, boolean[] visited) {
        if(depth == n){ // 모두 탐색했다면
            sb.append((now+1)+" ");
            return;
        }

        visited[now] = true;
        sb.append((now+1)+" ");

        for(int i : graph[now]) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(i, depth+1, visited);
        }
    }

    private void bfs(int start, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();

        q.offer(start);
        while(!q.isEmpty()) {
            int now = q.poll();

            if(visited[now]) continue;

            visited[now] = true;
            sb.append((now+1)+" ");

            for(int i : graph[now]) {
                q.offer(i);
            }
        }
    }
}
