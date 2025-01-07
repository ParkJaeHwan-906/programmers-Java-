package SWEA.D4;

import java.util.*;
import java.io.*;

// SWEA D4
// 1868. 파핑파핑 지뢰찾기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LwsHaD1MDFAXc
public class no_1868 {
    static int n;
    static char[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        no_1868 problem = new no_1868();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for(int i = 1; i <= tc; i++) {
            sb.append("#").append(i).append(" ");

            // 지도 생성
            n = Integer.parseInt(br.readLine());
            map = new char[n][n];
            visited = new boolean[n][n];
            for(int x=0; x<n; x++){
                String s = br.readLine();
                for(int y=0; y<n; y++){
                    map[x][y] = s.charAt(y);
                }
            }
            for(char[] cArr : map) {
                for(char c : cArr){
                    System.out.print(c);
                }
                System.out.println();
            }
            sb.append(problem.solution()).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int[] dx = new int[] {0,1,0,-1,1,1,-1,-1};
    private int[] dy = new int[] {1,0,-1,0,1,-1,1,-1};
    private int solution() {
        int answer = 0;

        // 지뢰를 체크한다.
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                if(map[x][y] == '*') {  // 지뢰인 경우, 방문할 필요 X
                    visited[x][y] = true;
                }
                else if(map[x][y] == '.') { // 방문해야하는 곳인 경우
                    // 주변을 체크한다.
                    findBomb(x, y);
                }
            }
        }

        for(char[] cArr : map) {
            for(char c : cArr){
                System.out.print(c);
            }
            System.out.println();
        }

        // 처음부터 탐색 ( 최소 클릭 구하기 )
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                // 주변에 지뢰가 없고, 방문하지 않은 곳 ( 연쇄 클릭 )
                if(map[x][y] == '0' && !visited[x][y]) {
                    dfs(x, y);
                    answer++;
                }
            }
        }
        return answer;
    }

    // 주변을 탐색한다.
    private void findBomb(int x, int y) {
        int cnt = 0;
        for(int i=0; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나거나, 방문한 이력이 있는 곳이면
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
            if(map[nx][ny] == '*') cnt++;
        }

        map[x][y] = String.valueOf(cnt).charAt(0);
    }

    // 주어진 위치에서 시작해 연결된 빈 칸을 클릭
    private void dfs(int x, int y) {
        visited[x][y] = true; // 현재 칸을 방문 처리
        for(int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue; // 범위를 벗어나거나 이미 방문함
            visited[nx][ny] = true; // 현재 칸을 방문 처리
            if(map[nx][ny] == '0') dfs(nx, ny); // 인접한 빈 칸 탐색
        }
    }
}
