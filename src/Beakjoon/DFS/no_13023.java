package Beakjoon.DFS;

import java.util.*;
import java.io.*;

// BAEKJOON
// ABCDE
// https://www.acmicpc.net/problem/13023
public class no_13023 {
    static int n, m;
    static ArrayList<Integer>[] friends;
    public static void main(String[] args) throws IOException {
        no_13023 problem = new no_13023();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 초기화
        problem.setDefault();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a].add(b);
            friends[b].add(a);
        }

        System.out.println(problem.solution());
    }

    private void setDefault(){
        friends = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            friends[i] = new ArrayList<>();
        }
    }

    boolean arrived = false;
    boolean[] visited;
    public int solution() {
        visited = new boolean[n];

        for(int i = 0; i < n; i++){
            dfs(i, 1);

            if(arrived) break;
        }

        return arrived ? 1 : 0;
    }

    public void dfs(int now, int length){
        if(length == 5 || arrived){    // 모두 알고있으면 종료
            arrived = true;
            return;
        }

        visited[now] = true;
        for(int i : friends[now]){
            if(!visited[i]){
                dfs(i, length+1);
            }
        }
        visited[now] = false;
    }
}
