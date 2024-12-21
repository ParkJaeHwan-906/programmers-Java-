package Beakjoon.DFS_BFS;

import java.util.*;
import java.io.*;

// BAEKJOON
// 연결 요소의 개수
// https://www.acmicpc.net/problem/11724
public class no_11724 {
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static int n,m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 정점의 개수
        m = Integer.parseInt(st.nextToken());   // 간선의 개수

        arr = new ArrayList[n];
        visited = new boolean[n];

        for(int i=0; i<n; i++){
            arr[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            arr[a].add(b);
            arr[b].add(a);
        }

        no_11724 problem = new no_11724();
        System.out.println(problem.solution());

    }

    private int solution() {
        int answer = 0;

        for(int i=0; i<n; i++){
            if(!visited[i]) {
                dfs(i);
                answer++;
            }
        }

        return answer;
    }

    private void dfs(int node) {
        if(visited[node]) return;   // 이미 방문한경우

        visited[node] = true;
        for(int i : arr[node]){
            if(!visited[i]) dfs(i);
        }
    }
}
