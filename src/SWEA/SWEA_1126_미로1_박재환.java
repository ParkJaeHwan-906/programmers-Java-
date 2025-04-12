package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1126_미로1_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int TC = 10;
        while(TC-- > 0) {
            sb.append('#').append(br.readLine().trim()).append(' ');
            init();
            sb.append('\n');
        }
        br.close();
        System.out.println(sb);
    }

    static int[][] map;
    static int startX, startY, endX, endY;
    static void init() throws IOException {
        map = new int[16][16];
        for (int x=0; x<16; x++) {
            String input = br.readLine().trim();
            for(int y=0; y<16; y++) {
                map[x][y] = input.charAt(y) - '0';

                if(map[x][y] == 2) {
                    startX = x;
                    startY=y;
                } else if (map[x][y] == 3) {
                    endX = x;
                    endY=y;
                }
            }
        }

        sb.append(canExit() ? 1 : 0);
    }
    /*
        BFS 를 이용해 미로를 탈출하는 최단 경로를 찾는다.
     */
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static boolean canExit() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[16][16];
        q.offer(new int[] {startX, startY});
        visited[startX][startY] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            // 탈출했는지
            if(map[x][y] == 3) return true;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위 체크
                if(nx < 0 || ny < 0 || nx >= 16 || ny >= 16) continue;
                // 이미 방문했는지, 빈 공간인지
                if(visited[nx][ny] || map[nx][ny] == 1) continue;

                // 이동 가능
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }
        return false;
    }
}

/*
    16 x 16 격자
    벽 : 1
    길 : 0

    출발지 : 2
    도착지 : 3

    출발지 -> 도착지로 이동이 가능한지 판단
    가능 : 1
    불가능 : 0
 */