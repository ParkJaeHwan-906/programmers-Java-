package Beakjoon;

import java.util.*;
import java.io.*;

public class 인구이동 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    /**
     * N x N -> (r,c) 위치에는 A[r][c] 명이 있다.
     *
     * [인구이동]
     * 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
     * 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
     * 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
     * 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구 수)/(연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
     * 연합을 해체하고, 모든 국경선을 닫는다.
     *
     * 각 냐라의 인구수가 주어졌을 때, 인구 이동이 며칠 동안 발생하는지 구하는 프로그램을 작성하시오.
     */
    static StringTokenizer st;
    static int n, l, r;
    static int[][] board;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        for(int x=0; x<n; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<n; y++) board[x][y] = Integer.parseInt(st.nextToken());
        }

        int passDays = 0;
        while(passOneDay()) {
            passDays++;
        }
        System.out.println(passDays);
    }

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static boolean passOneDay() {
        /**
         * 각 위치에서 연합국을 구한다.
         */
        int federationIdx = 1;
        int[][] visited = new int[n][n];    // 각 연합국이 어떤 연합국인지 구분하기 위해서 int 사용
        boolean flag = false;
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                if(visited[x][y] != 0) continue;
                if(findFederation(x, y, visited, federationIdx++) > 1) flag = true;
            }
        }
        // 더 이상 이민할 수 있는 나라가 없다면 false
        if(!flag) return false;

        // 아직 이민 가능
        Set<Integer> federationSet = new HashSet<>();
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                if(visited[x][y] == 0) continue;

                int targetFederationIdx = visited[x][y];
                if(federationSet.add(targetFederationIdx)) continue;

                List<int[]> federationList = new ArrayList<>();
                int totalPeopleCnt = 0;
                for(int nx=0; nx<n; nx++) {
                    for(int ny=0; ny<n; ny++) {
                        if(visited[nx][ny] != targetFederationIdx) continue;
                        federationList.add(new int[] {nx, ny});
                        totalPeopleCnt += board[nx][ny];
                    }
                }

                int samePeople = totalPeopleCnt / federationList.size();
                for(int[] loc : federationList) {
                    board[loc[0]][loc[1]] = samePeople;
                }
            }
        }

        return true;
    }

    static int findFederation(int x, int y, int[][] visited, int federationIdx) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});
        visited[x][y] = federationIdx;

        int federationSize = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curPeopleCnt = board[curX][curY];

            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if(visited[nx][ny] != 0) continue;
                int nCurPeopleCnt = board[nx][ny];

                int diffPeopleCnt = Math.abs(curPeopleCnt - nCurPeopleCnt);
                if(diffPeopleCnt < l || diffPeopleCnt > r) continue;

                visited[nx][ny] = federationIdx;
                q.offer(new int[] {nx, ny});
                federationSize++;
            }
        }
        return federationSize;
    }
}
