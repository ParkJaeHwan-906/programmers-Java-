package Beakjoon;

import java.util.*;
import java.io.*;

// Baekjoon
// 벽 부수고 이동하기
// https://www.acmicpc.net/problem/2206
public class no_2206 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int x, y;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        map = new int[x][y];
        for(int i=0; i<x; i++) {
            String input = br.readLine().trim();
            for(int j=0; j<y; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        br.close();
        bw.write(String.valueOf(findMinRoute()));
        bw.flush();
        bw.close();
    }

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static boolean[][][] visited;
    public static int findMinRoute() {
        visited = new boolean[x][y][2];
        Queue<int[]> q = new ArrayDeque<>(); // [위치, 좌표, 이동, 블록 부숨]

        q.offer(new int[] {0,0,1,0});

        while(!q.isEmpty()) {
            int[] point = q.poll();
            int curX = point[0];
            int curY = point[1];
            int count = point[2];
            int block = point[3];
            if(curX == x-1 && curY == y-1) return count;
            visited[curX][curY][0] = true;

            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                if(nx < 0 || ny < 0 || nx >= x || ny >= y) continue;

                if(map[nx][ny] == 1 && block == 0 && !visited[nx][ny][1]) {    // 벽을 만나고 아직 부수지 않은 경우
                    visited[nx][ny][1] = true;
                    q.offer(new int[] {nx, ny, count+1, 1});
                } else if(map[nx][ny] == 0 && !visited[nx][ny][block]) {
                    q.offer(new int[] {nx, ny, count+1, block});
                }
            }
        }
        return -1;
    }
}
