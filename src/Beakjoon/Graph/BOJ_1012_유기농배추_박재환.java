package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_1012_유기농배추_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int tc=0; tc < TC; tc++) {
            init();
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    static int row, col, plants;
    static int[][] map;
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        plants = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        // 배추 심은 위치를 1로 표현
        for(int plant=0; plant<plants; plant++) {
            st = new StringTokenizer(br.readLine().trim());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            map[x][y] = 1;
        }

        needBugs();
    }

    static boolean[][] visited;
    static void needBugs() {
        int bugs = 0;
        visited = new boolean[row][col];
        for(int x=0; x<row; x++) {
            for(int y=0; y<col; y++) {
                // 배추가 있고, 아직 체크되지 않은 구역
                if(map[x][y] == 1 && !visited[x][y]) {
                    checkNearBounds(x,y);
                    bugs++;
                }
            }
        }
        sb.append(bugs);
    }

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void checkNearBounds(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] curPoints = q.poll();
            int curX = curPoints[0];
            int curY = curPoints[1];

            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                // 범위를 벗어나거나, 배추가 없는 경우
                if(nx < 0 || ny < 0 || nx >= row || ny >= col || map[nx][ny] == 0) continue;
                // 이미 체크한 구역인 경우
                if(visited[nx][ny]) continue;

                // 체크할 수 있는 구역인 경우
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }
    }
}
