package Ssafy.Algorithm.TraceBfs;

import java.util.*;
import java.io.*;

public class TraceBfs {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }

    static class MyTeam {
        int x;
        int y;
        MyTeam prev;

        public MyTeam(int x, int y, MyTeam prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "["+x+" ,"+ y+"]";
        }
    }

    static StringTokenizer st;
    static int row, col;                    // 맵의 세로, 맵의 가로
    static String[][] map;                 // 맵
    static int teamCnt, enemyCnt;         // 아군 수, 적군 수
    static MyTeam teams;                // 아군 정보
    static int passwordCnt;            // 주어진 암호문의 수
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        teamCnt = Integer.parseInt(st.nextToken());
        enemyCnt = Integer.parseInt(st.nextToken());
        passwordCnt = Integer.parseInt(st.nextToken());

        // 배열 초기화
        map = new String[row][col];
        for(int x=0; x<row; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<col; y++) {
                map[x][y] = st.nextToken();
                
                // 현 위치 정보 저장
                if(map[x][y].charAt(0) == 'A') {    // 1. 아군인 경우
                    teams = new MyTeam(x, y, null);
                }
            }
        }

        findMinRoute();
    }

    // 우 하 좌 상
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
//    static String[] sDir = {"R", "D", "L" , "U"};
    // A 아군이 X 로 도착하는 최소 경로를 추적한다.
    static void findMinRoute() {
        boolean[][] visited = new boolean[row][col];
        Queue<MyTeam> q = new LinkedList<>();
        q.offer(teams); // 초기 위치
        visited[teams.x][teams.y] = true;

        while(!q.isEmpty()) {
            MyTeam cur = q.poll();
            int x = cur.x;
            int y = cur.y;

            // 타워에 도착한 경우
            if(map[x][y].equals("X")) {
                // 경로를 역추적
                traceRoute(cur);
                return;
            }

            // 이동한다.
            // 상 하 좌 우 이동
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위를 벗어나는지 확인
                if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
                // 이미 방문한 경우
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                q.offer(new MyTeam(nx, ny, cur));
            }
        }
    }

    static void traceRoute(MyTeam cur) {
        // 목적지 -> 출발지 경로를 역추적한다.
        Stack<int[]> st = new Stack<>();
        MyTeam traceTeam = cur;

        while(traceTeam != null) {
            st.push(new int[] {traceTeam.x, traceTeam.y});
            traceTeam = traceTeam.prev;
        }

        while(!st.isEmpty()) {
            sb.append(Arrays.toString(st.pop())).append('\n');
        }
    }
}

/*
10 10 1 2 0
G G G R G G G R G X
G R G G G R G G G G
G R G R R G G G W W
G G R T T R G W W G
R G R T T R G G G G
G G T T T T R R E1 R
G R W W W T R G G G
G G G G G G G G G G
A G G W W R R G G R
G G F W W G R R G G
 */