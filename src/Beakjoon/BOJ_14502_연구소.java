package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_14502_연구소 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    /*
        바이러스는 상하좌우 인접한 빈 칸으로 모두 퍼져나갈 수 있음
        0 : 빈칸, 1 : 벽, 2 : 바이러스

        새롭게 세울 수 있는 벽의 수는 3개이며, 꼭 세워야한다.
     */
    static StringTokenizer st;
    static int N, M, maxArea;
    static int[][] map;
    static List<int[]> virus, empty;        // 초기에 바이러스가 있는 위치, 빈 칸 위치
    static void init() throws IOException {
        virus = new ArrayList<>();
        empty = new ArrayList<>();
        maxArea = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine().trim());
        // N, M ( 3 ~ 8 ) -> 범위가 크지 않아 완전 탐색이 될 것 같다
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int x=0; x<N; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<M; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
                if(map[x][y] == 0) empty.add(new int[] {x,y});
                else if(map[x][y] == 2) virus.add(new int[] {x,y});
            }
        }

        buildWall(0,0, new int[3][2]);
        System.out.println(maxArea);
    }

    static void buildWall(int wallIdx, int selectedIdx,int[][] builtWall) {
        if(selectedIdx == 3) {
            // 3개의 벽을 모두 세운 경우
            spreadVirus(builtWall);
            return;
        }
        if(wallIdx >= empty.size()) return;     // 더 이상 조합을 찾을 수 없는 경우
        // 현재 위치에 벽을 세우거나, 세우지 않거나
        builtWall[selectedIdx] = empty.get(wallIdx);
        buildWall(wallIdx+1, selectedIdx+1, builtWall);
        buildWall(wallIdx+1, selectedIdx, builtWall);
    }
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void spreadVirus(int[][] builtWall) {
        int[][] copyMap = new int[N][M];
        // map 을 copy 한다
        for(int x=0; x<N; x++) {
            for(int y=0; y<M; y++) {
                copyMap[x][y] = map[x][y];
            }
        }
        // 벽 세우기
        for(int[] point : builtWall) {
            copyMap[point[0]][point[1]] = 1;
        }
        // BFS 를 사용해 바이러스 퍼뜨리기
        Queue<int[]> q = new LinkedList<>();
        for(int[] point : virus) {
            q.offer(new int[] {point[0], point[1]});
        }
        while(!q.isEmpty()) {
            int[] point = q.poll();

            for(int dir=0; dir<4; dir++) {
                int nx = point[0] + dx[dir];
                int ny = point[1] + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(copyMap[nx][ny] != 0) continue;
                copyMap[nx][ny] = 2;
                q.offer(new int[] {nx, ny});
            }
        }

        // 안전 구역 구하기
        safeArea(copyMap);
    }

    static void safeArea(int[][] copyMap) {
        int area = 0;
        for(int x=0; x<N; x++) {
            for(int y=0; y<M; y++) {
                if(copyMap[x][y] == 0) area++;
            }
        }
        maxArea = Math.max(maxArea, area);
    }
}
