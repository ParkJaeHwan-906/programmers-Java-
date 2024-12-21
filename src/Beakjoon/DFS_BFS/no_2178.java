package Beakjoon.DFS_BFS;

import java.util.*;
import java.io.*;

// BAEKJOON
// 미로 탐색
// https://www.acmicpc.net/problem/2178
public class no_2178 {
    static int[][] maps;
    static boolean[][] visited;
    static int n, m;
    public static void main(String[] args) throws IOException {
        no_2178 problem = new no_2178();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maps = new int[n][m];
        visited = new boolean[n][m];
        // maps 입력
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            for(int j = 0; j < m; j++) {
                maps[i][j] = s.charAt(j)-'0';
            }
        }

        System.out.println(problem.bfs(0,0));
    }

    int[] dx = new int[] {0,1,0,-1};
    int[] dy = new int[] {1,0,-1,0};
    // 출발지에서 목적지까지 최소한의 칸의 수
    public int bfs(int x, int y){
        int answer = 0;

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {x, y, 1});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int depths = cur[2];

//            System.out.printf("현위치 : [%d][%d], depth : %d\n", curX, curY, depths);

            if(curX == n-1 && curY == m-1) return depths;

            // 이미 방문했다면
            if(visited[curX][curY]) continue;

            visited[curX][curY] = true;
            for(int i = 0; i < 4; i++){
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                // maps 의 영역을 넘어감, 이미 방문함
                if(nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) continue;
                // 벽임
                if(maps[nx][ny] == 0) continue;

//                System.out.printf("\t [%d][%d] : %d\n", nx, ny, maps[nx][ny]);

                q.offer(new int[] {nx, ny, depths+1});
            }
        }

        return 0;
    }

}
