package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_14503_로봇청소기  {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }
    static StringTokenizer st;
    static int N, M;
    static int x, y, dir;
    static int[][] map;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine().trim());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for(int x=0; x<N; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<M; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 확인
//        for(int[] arr : map) System.out.println(Arrays.toString(arr));
        System.out.println(roboKing());
    }

    /*
        칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다. 
        주변이 모두 청소되어 있는 경우, 바라보는 방향을 유지한 채, 한 칸 후진한다.
        후진이 불가한 경우 작동을 멈춘다.
        주변이 청소되어 있지 않다면 90도 회전한다.
     */
    // 북(0), 동(1), 남(2), 서(3) -> 진짜 문제 잘 읽자;; 문제에 방향 인덱스 줬음
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean[][] cleaned;     // 청소를 했는지 확인 여부
    static int roboKing() {
        int cnt = 0;
        cleaned = new boolean[N][M];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y, dir});
        cleaned[x][y] = true;
        cnt++;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDir = cur[2];

            int nDir = curDir;
            boolean move = false;
            for(int i=0; i<4; i++) {
                nDir = (nDir+3)%4;
                int nx = curX + dx[nDir];
                int ny = curY + dy[nDir];

                if(isBoard(nx, ny)) continue;
                if(cleaned[nx][ny]) continue;
                cleaned[nx][ny] = true;
                cnt++;
                q.offer(new int[] {nx, ny, nDir});
                move = true;
                break;
            }

            if(move) continue;

            // 주변이 깨끗한경우
            int backDir = (curDir + 2) % 4;
            int bx = curX + dx[backDir];
            int by = curY + dy[backDir];
            if(isBoard(bx, by)) break;
            q.offer(new int[] {bx, by, curDir});
        }

        return cnt;
    }

    static boolean isBoard(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M || map[x][y]==1;
    }
}
