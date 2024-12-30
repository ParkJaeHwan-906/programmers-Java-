package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 나무 조경
// https://softeer.ai/practice/7594
public class no_7594 {
    static int n;
    static int[][] maps;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        no_7594 problem = new no_7594();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        maps = new int[n][n];
        visited = new boolean[n][n];

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
        // n 이 2 일 때는 4개의 쌍을 만들 수 없음
        int maxDepth = n == 2 ? 2 : 4;

        dfs(0, maxDepth, 0);

        return answer;
    }

    // ⚠️ 우측과 하단만 확인한다. ( 모든 경우의 수 ❌ )
    private int dx[] = {0,1};   // 하단
    private int dy[] = {1,0};   // 우측
    private void dfs(int depth, int maxDepth, int sum){
        if(depth == maxDepth){
            answer = Math.max(sum, answer);
            return;
        }

        // maps 전체 순회
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++) {
                // 아직 방문하지 않은 곳을 찾기 
                if(visited[x][y]) continue;

                for(int i=0; i<2; i++){ // (x, y) 에서의 우측과 하단을 확인함
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // maps 의 범위를 벗어나면 continue
                    if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;

                    // 방문처리
                    visited[x][y] = true;
                    visited[nx][ny] = true;

                    dfs(depth+1, maxDepth, sum + maps[x][y] + maps[nx][ny]);

                    // 백트래킹
                    visited[x][y] = false;
                    visited[nx][ny] = false;
                }
            }
        }
    }

}
