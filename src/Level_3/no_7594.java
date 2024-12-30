package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 나무 조경
// https://softeer.ai/practice/7594
public class no_7594 {
    static int n;
    static int[][] maps;
    public static void main(String[] args) throws IOException {
        no_7594 problem = new no_7594();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        maps = new int[n][n];

        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j=0; j<n; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        bw.write(String.valueOf(problem.solution()));
        bw.flush();
        bw.close();
    }

    private long answer = 0;
    public long solution() {
        int maxDepth = n == 2 ? 2 : 4;

        dfs(0, maxDepth, 0);

        return answer;
    }

    private boolean[][] visited;
    private int dx[] = {0,1};
    private int dy[] = {1,0};
    private void dfs(int depth, int maxDepth, int sum){
        visited = new boolean[n][n];
        if(depth == maxDepth){
            answer = Math.max(sum, answer);
            return;
        }

        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++) {
                if(visited[x][y]) continue;

                for(int i=0; i<2; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

                    visited[x][y] = true;
                    visited[nx][ny] = true;
                    dfs(depth+1, maxDepth, sum + maps[x][y] + maps[nx][ny]);
                    visited[x][y] = false;
                    visited[nx][ny] = false;
                }
            }
        }
    }

}
