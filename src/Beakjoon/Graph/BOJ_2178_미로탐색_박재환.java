package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_2178_미로탐색_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        bw.flush();
        bw.close();
    }

    static int row, col;
    static int[][] map;
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];

        for(int x=0; x<row; x++) {
            String mapInput = br.readLine().trim();
            for(int y=0; y<col; y++){
                map[x][y] = mapInput.charAt(y)-'0';
            }
        }
        br.close();

        bw.write(String.valueOf(minRoute()));
    }

    /*
        최단 경로를 찾아야한다.
        BFS 를 이용한다.
     */
    static int minRoute() {
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        boolean[][] visited = new boolean[row][col];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]  {0,0,1});    // (x,y,dist)
        visited[0][0] = true;

        while(!q.isEmpty()) {
            int[] curPoints = q.poll();
            int curX = curPoints[0];
            int curY = curPoints[1];
            int dist = curPoints[2];

            if(curX == row-1 && curY == col-1) {    // 목적지에 도착한 경우
                return dist;
            }

            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                // 범위를 벗어나는 경우, 또는 이동할 수 없는 경우
                if(nx < 0 || ny < 0 || nx >= row || ny >= col || map[nx][ny] == 0) continue;
                // 이미 방문한 경우
                if(visited[nx][ny]) continue;

                // 방문 가능한 경우
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny, dist+1});
            }
        }

        return -1;
    }
}
