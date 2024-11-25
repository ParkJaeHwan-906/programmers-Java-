package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 7회 정기 코딩 인증평가 기출] 순서대로 방문하기
// https://www.softeer.ai/practice/6246
public class no_6246 {
    static int[][] maps;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // maps 의 크기
        int n = Integer.parseInt(st.nextToken());
        maps = new int[n][n];
        visited = new boolean[n][n];

        // 방문해야하는 칸의 수
        int m = Integer.parseInt(st.nextToken());

        // maps 입력
        // 0 은 빈칸, 1은 벽
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<n; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] toVisit = new int[m][2];
        // 방문해야하는 위치
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            toVisit[i][0] = Integer.parseInt(st.nextToken()) -1;
            toVisit[i][1] = Integer.parseInt(st.nextToken()) -1;
        }
        br.close();

        no_6246 problem = new no_6246();
        int answer = problem.solution(toVisit);
        System.out.println(answer);
    }

    private int answer = 0;
    public int solution(int[][] toVisit) {
        // 방문해야하는 첫 지점이 출발점
        int x = toVisit[0][0];
        int y = toVisit[0][1];
        visited[x][y] = true;

        dfs(x, y, toVisit, 0);
        return answer;
    }

    private int[] dx = new int[] {0,1,0,-1};
    private int[] dy = new int[] {1,0,-1,0};
    private void dfs(int x, int y, int[][] toVisit, int seq){
        // 현재 방문해야하는 지점인 경우, 다음 지점을 목표로 설정해줌
        if(x == toVisit[seq][0] && y == toVisit[seq][1]) seq = seq+1;


        if(seq == toVisit.length){    // 모든 목표지점을 방문완료
            answer++;
            return;
        }

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나거나, 이미 방문한 경우
            if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps.length || visited[nx][ny]) continue;
            // 벽인 경우
            if(maps[nx][ny] == 1) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, toVisit, seq);
            visited[nx][ny] = false;
        }
    }
}
