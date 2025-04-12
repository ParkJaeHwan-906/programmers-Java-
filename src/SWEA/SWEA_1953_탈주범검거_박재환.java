package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1953_탈주범검거_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase < TC+1; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }
        br.close();
        System.out.println(sb);
    }

    /*
        첫 줄에 N ( 세로 ) M ( 가로 ) 가 주어진다
        / 초기 위치 R ( 세로 ), C ( 가로 ) 가 주어진다.
        / 소요 시간 L 이 주어진다.
     */
    static StringTokenizer st;
    static int row, col, startX, startY, time;
    static int[][] map;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];

        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());

        time = Integer.parseInt(st.nextToken());

        for (int x = 0; x < row; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int y = 0; y < col; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        checkRoute();
    }

    /*
        시간에 따라 도둑이 이동할 수 있는 경로를 확인한다.
        BFS 를 이용해, 각 시간에 움직일 수 있는 경로를 한 번에 처리한다.
     */
    // 상 하 좌 우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static void checkRoute() {
        Queue<int[]> q = new LinkedList<>();            // 이동 처리를 위한 Queue
        boolean[][] visited = new boolean[row][col];    // 방문 처리를 위한 배열

        // 초기 위치 설정
        // 방향을 같이 설정해주어야한다.
        for(int dir : tunnels[map[startX][startY]]) {
            q.offer(new int[] {startX, startY, dir, 1});    // [x, y, 진행 방향, 시간]
        }
        visited[startX][startY] = true;

        while(!q.isEmpty()) { // 더 이상 이동할 수 없거나, 시간이 모두 지난 경우
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int inputDir = cur[2];
            int curTime = cur[3];
//            System.out.println("현 위치 : " + Arrays.toString(cur));
//            System.out.println("현 터널 : " + map[x][y]);
            // 더 이상 움직일 수 없음
            if(time == curTime) continue;

            // 더 움직일 수 있는 경우
            // 1. 현재 이동하는 방향으로 이동
            int nx = x + dx[inputDir];
            int ny = y + dy[inputDir];
//            System.out.println("이동 : " + "(" + nx + ", "+ny+")");
            // 1-1. 범위를 벗어나는지 확인한다.
            if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
            // 1-2. 이미 방문한 위치인지 확인한다. / 통로가 있는지 확인한다.
            if(visited[nx][ny] || map[nx][ny] == 0) continue;

//            System.out.println("이동 터널 : " + map[nx][ny]);
            // 2. 이동한 방향으로 들어갈 수 있는지 확인
            //      가려는 방향의 터널의 각 방향을 확인
            boolean canGo = false;
            for(int dirs : tunnels[map[nx][ny]]) {
                if(dirs == canMoveCases[inputDir]) {
                    canGo = true;
                    break;
                }
            }
            // 진입할 수 없다면 다음 탐색으로 이동
            if(!canGo) continue;

            visited[nx][ny] = true;
            // 3. 해당 터널의 각 출구로 이동
            for(int dirs : tunnels[map[nx][ny]]) {
                q.offer(new int[] {nx, ny, dirs, curTime+1});
            }
        }

        int moves = 0;
        for(boolean[] arr : visited) {
            for(boolean b : arr) {
                if(b) moves++;
            }
        }
        sb.append(moves);
    }


    // 진입한 방향으로 들어갈 수 있는 입구가 있는지 확인한다.
    static int[] canMoveCases = {1,0,3,2};
    // 터널 종류별 방향 설정
    static int[][] tunnels = {
            {},
            {0,1,2,3},
            {0,1},
            {2,3},
            {0,3},
            {1,3},
            {1,2},
            {0,2}
    };
}

/*
    탈주범은 시간당 1의 거리를 움직일 수 있다.

    터널은 총 7 종류가 있다.
    1 : 상 하 좌 우
    2 : 상 하
    3 : 좌 우
    4 : 우 상
    5 : 우 하
    6 : 좌 하
    7 : 좌 상
 */