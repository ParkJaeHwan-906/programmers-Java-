package SWEA.D4;

import java.util.*;
import java.io.*;

// SWEA D4
// 4193. 수영대회 결승전 ( 완전 탐색 + 구현 )
// https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWKaG6_6AGQDFARV
public class no_4193 {
    public static void main(String[] args) throws IOException {
        no_4193 problem = new no_4193();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int i=1; i<=tc; i++){
            // N 입력
            int n = Integer.parseInt(br.readLine());
            // map 생성
            int[][] map = new int[n][n];
            for(int x=0; x<n; x++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int y=0; y<n; y++){
                    map[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            // 출발, 도착
            int[][] points = new int[2][2];
            for(int j=0; j<2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int z=0; z<2; z++){
                    points[j][z] = Integer.parseInt(st.nextToken());
                }
            }

            int result = problem.solution(map, points[0], points[1], n);
            sb.append("#").append(i).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    /*
    소용돌이 패턴
    0, 1 (유지) - 2 가능
    3, 4 (유지) - 5 가능
    6, 7 (유지) - 8 가능
    9, 10 (유지) - 11 가능

    => %3 == 2
     */
    private int[] dx = new int[] {0,1,0,-1};
    private int[] dy = new int[] {1,0,-1,0};
    private int solution(int[][] map, int[] start, int[] end, int n) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        // x, y, 걸린시간
        q.offer(new int[] {start[0], start[1], 0});

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int now = cur[2];

            // 이미 방문함
//            if(visited[x][y]) continue;
            visited[x][y] = true;   // 방문 처리

            if(x == end[0] && y == end[1]) return now;

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어남, 이미 방문함
                if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
                // 장애물이라면
                if(map[nx][ny] == 1) continue;

                // 소용돌이
                if(map[nx][ny] == 2){
                    // 사라짐
                    if(now % 3 == 2) {
                        q.offer(new int[] {nx, ny, now+1});
                    } else {    // 생성됨
                        q.offer(new int[] {x, y, now + 1});
                    }
                } else {
                    q.offer(new int[] {nx, ny, now+1});
                }
            }
        }

        return -1;
    }

}
