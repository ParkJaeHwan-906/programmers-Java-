package Beakjoon;

import java.util.*;
import java.io.*;

public class 알고스팟 {
    public static void main(String[] args) throws IOException {
        init();
    }
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    static char[][] arr;
    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[m][n];            // 1-based
        for(int x=0; x<m; x++) {
            String inputLn = br.readLine().trim();
            for(int y=0; y<n; y++) {
                arr[x][y] = inputLn.charAt(y);
            }
        }

        System.out.println(findMinCost());

        br.close();
    }
    /**
     * N x M 크기의 격자
     * - 빈 방
     * - 벽
     * 으로 이루어져있음
     *
     * 빈 방은 자유롭게 다닐 수 있음
     * 벽은 부수지 않으면 이동할 수 없음
     *
     * 어떤 방에서 이동할 수 있는 방향은 [상, 하, 좌, 우]
     *
     * => 1,1 -> N,M 으로 이동하려면 벽을 최소 몇 개 부숴여하는지 구해라
     */
    static int[] dx = new int[] {0,1,0,-1};
    static int[] dy = new int[] {1,0,-1,0};
    static int findMinCost() {
        /**
         * 1. 벽을 부순다 (1)
         * 2. 벽을 부수지 않는다. (0)
         */
        int[][] cost = new int[m][n];
        for(int i=0; i<m; i++) Arrays.fill(cost[i], Integer.MAX_VALUE);

        Deque<int[]> dq = new ArrayDeque<>();
        // 초기 위치 설정
        dq.offer(new int[] {0,0});
        cost[0][0] = 0;

        // 0-1 BFS
        while(!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int curX = cur[0];
            int curY = cur[1];
            int curCost = cost[curX][curY];

            if(curX == m && curY == n) break;

            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                if(isNotBoard(nx, ny)) continue;

                // 1. 벽
                if(arr[nx][ny] == '1' && cost[nx][ny] > curCost+1) {
                    dq.offerLast(new int[] {nx, ny});
                    cost[nx][ny] = curCost+1;
                }
                // 2. 빈 방
                else if(arr[nx][ny] == '0' && cost[nx][ny] > curCost) {
                    dq.offerFirst(new int[] {nx, ny});
                    cost[nx][ny] = curCost;
                }
            }
        }

        return cost[m-1][n-1];
    }

    static boolean isNotBoard(int x, int y) {
        return x < 0 || y < 0 || x >= m || y >= n;
    }
}
