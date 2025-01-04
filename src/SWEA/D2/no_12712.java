package SWEA.D2;

import java.util.*;
import java.io.*;

// SWEA D2
// 12712. 파리퇴치3
// https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AXuARWAqDkQDFARa
public class no_12712 {
    public static void main(String[] args) throws IOException {
        no_12712 problem = new no_12712();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int i=0; i<tc; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());   // 5 이상 15 이하
            int m = Integer.parseInt(st.nextToken());   // 2 이상 N 이하

            // map 생성
            int[][] map = new int[n][n];
            for(int j=0; j<n; j++){
                st = new StringTokenizer(br.readLine());

                for(int z=0; z<n; z++){
                    map[j][z] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for(int x=0; x<n; x++){
                for(int y=0; y<n; y++){
                    // x, +
                    int result = Math.max(problem.plusMove(x,y,n,m,map), problem.xMove(x,y,n,m,map));

                    answer = Math.max(result, answer);
                }
            }

            sb.append("#").append(i+1).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int plusMove(int x, int y, int n, int m, int[][] map) {
        int[] dx = new int[] {0,1,0,-1};
        int[] dy = new int[] {1,0,-1,0};

        int answer = map[x][y];

        // 방향
        int seq = 0;
        // 길이
        int l = 1;

        int copyX = x;
        int copyY = y;

        while(seq < 4) {
            int nx = copyX + dx[seq];
            int ny = copyY + dy[seq];

            // 범위를 벗어나는 경우, m(분사범위) 을 넘어가는 경우
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || l == m) {
                // 방향 전환
                seq++;
                l = 1;
                // 좌표 중앙으로 이동
                copyX = x;
                copyY = y;

                continue;
            }

//            System.out.printf("좌표 : [%d, %d]\n", nx, ny );
            answer += map[nx][ny];
            copyX = nx;
            copyY = ny;
            l++;
        }

        return answer;
    }

    private int xMove(int x, int y, int n, int m, int[][] map) {
        int[] dx = new int[] {1,1,-1,-1};
        int[] dy = new int[] {1,-1,-1,1};

        int answer = map[x][y];

        // 방향
        int seq = 0;
        // 길이
        int l = 1;

        int copyX = x;
        int copyY = y;

        while(seq < 4) {
            int nx = copyX + dx[seq];
            int ny = copyY + dy[seq];

            // 범위를 벗어나는 경우, m(분사범위) 을 넘어가는 경우
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || l == m) {
                // 방향 전환
                seq++;
                l = 1;
                // 좌표 중앙으로 이동
                copyX = x;
                copyY = y;

                continue;
            }

//            System.out.printf("좌표 : [%d, %d]\n", nx, ny );
            answer += map[nx][ny];
            copyX = nx;
            copyY = ny;
            l++;
        }

        return answer;
    }
}
