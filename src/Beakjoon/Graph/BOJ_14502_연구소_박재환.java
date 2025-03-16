package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_14502_연구소_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        bw.write(String.valueOf(maxArea));
        bw.flush();
        bw.close();
    }

    static int row, col;
    static int[][] map;
    static List<int[]> wallList;    // 벽을 세울 수 있는 위치를 기록해둠
    static List<int[]> virusList;   // 바이러스가 있는 위치를 기록해둠
    static int maxArea;
    static void init() throws IOException {
        maxArea = 0;

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        wallList = new ArrayList<>();
        virusList = new ArrayList<>();
        map = new int[row][col];
        for(int x=0; x<row; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<col; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
                if(map[x][y] == 0) wallList.add(new int[] {x, y});
                else if(map[x][y] == 2) virusList.add(new int[] {x, y});
            }
        }
        br.close();
        buildWall(0,0, new int[3][]);
    }

    /*
        연구소의 크기가 커봤자 8 x 8
        완탐해도 될거같다.
     */
    static void buildWall(int wall, int wallListIdx, int[][] wallComb) {
        if(wall == 3) { // 벽을 다 세운 경우
            // 바이러스 확산을 시물레이션한다
            spreadVirus(wallComb);
            return;
        }

        if(wallListIdx == wallList.size()) {    // 조합을 다 채우지 못했지만, 탐색을 마친경우
            return;
        }

        // 벽을 세울 수 있음
        // 벽 세우기
        wallComb[wall] = wallList.get(wallListIdx);
        buildWall(wall+1, wallListIdx+1, wallComb);

        // 벽 안세우기
        buildWall(wall, wallListIdx+1, wallComb);
    }

    static void spreadVirus(int[][] wallComb) {
        // 맵을 복사한다.
        int[][] copyMap = new int[row][col];
        for(int idx=0; idx<row; idx++) {
            copyMap[idx]= map[idx].clone();
        }

        // 생성된 벽 조합을 반영한다.
        for(int[] wallPoint : wallComb) {
            int x = wallPoint[0];
            int y = wallPoint[1];

            copyMap[x][y] = 1;
        }

        // 바이러스를 확산시킨다.
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};

        Queue<int[]> q = new LinkedList<>();

        // 1. 모든 바이러스를 큐에 넣는다. (동시 처리)
        for(int[] virus : virusList) {
            q.offer(new int[] {virus[0], virus[1]});
        }

        // 2. 인접 구역을 감염시킨다.
        while(!q.isEmpty()) {
            int[] curPoint = q.poll();
            int x = curPoint[0];
            int y = curPoint[1];

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 범위 벗어남
                if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
                // 바이러스 확산 불가 ( 벽 )
                if(copyMap[nx][ny] != 0) continue;

                // 바이러스 확산 가능
                copyMap[nx][ny] = 2;
                q.offer(new int[] {nx, ny});
            }
        }

        // 3. 안전 구역의 크기를 확인한다.
        int areaSize = 0;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if(copyMap[x][y] == 0) areaSize++;
            }
        }

        maxArea = Math.max(areaSize, maxArea);
    }
}
