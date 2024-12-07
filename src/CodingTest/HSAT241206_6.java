package CodingTest;

import java.util.*;
import java.io.*;

public class HSAT241206_6 {
    static int n, max;
    static int[][] maps;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // N x N 지도생성
        max = Integer.parseInt(st.nextToken()); // 최대 등산 경로 길이

        maps = new int[n][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        HSAT241206_6 problem = new HSAT241206_6();
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                problem.findRoute(x, y, 1, 0, new boolean[n][n]);
            }
        }

        System.out.println(answer);
    }

    private int[] dx = new int[] {0,1,0,-1};
    private int[] dy = new int[] {1,0,-1,0};
    public void findRoute(int x, int y, int depth, int sub, boolean[][] visited){
        if(depth == max) {
            answer = Math.min(answer, sub);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나거나, 이미 방문한 경우
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
            // 이전의 높이보다 낮거나 같은 경우
            if(maps[nx][ny] <= maps[x][y]) continue;

            visited[nx][ny] = true;
            findRoute(nx, ny, depth+1, Math.max(sub, maps[nx][ny] - maps[x][y]), visited);
            visited[nx][ny] = false;
        }
    }
}
