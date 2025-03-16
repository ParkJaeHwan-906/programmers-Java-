package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_7576_토마토_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        bw.flush();
        bw.close();
    }

    static int col, row;
    static int[][] map;
    static int yetTomato;   // 익혀야하는 토마토
    static Queue<int[]> q;  // 익은 토마토의 위치
    static void init() throws IOException {
        yetTomato = 0;
        q = new LinkedList<>();

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        /*
            1   : 익은 토마토
            0   : 익지 않은 토마토
            -1  : 빈칸
         */
        map = new int[row][col];
        for(int x=0; x<row; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<col; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
                if(map[x][y] == 0) {
                   yetTomato++;
                } else if(map[x][y] == 1) {
                    q.offer(new int[] {x,y,0});
                }
            }
        }
        br.close();
        bw.write(String.valueOf(dayPass()));
    }

    static int dayPass() {
        if(yetTomato == 0) return 0;     // 안 익은 토마토가 없다면

        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        int finalDay = 0;
        while(!q.isEmpty()) {
            int[] curPoint = q.poll();
            int curX = curPoint[0];
            int curY = curPoint[1];
            int day = curPoint[2];

            finalDay = Math.max(day, finalDay);
            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                // 범위를 벗어나는경우
                if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
                // 덜 익은 토마토가 아닌 경우
                if(map[nx][ny] != 0) continue;

                // 익힐 수 있음!
                map[nx][ny] = 1;
                yetTomato--;
                q.offer(new int[]{nx, ny, day+1});
            }
        }

        return yetTomato == 0 ? finalDay : -1;
    }
}
