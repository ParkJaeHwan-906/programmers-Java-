package CodeTree;

import java.util.*;
import java.io.*;

public class 민트초코우유 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }

    /**
     * T : 민트
     * C : 초코
     * M : 우유
     *
     * T : 100 : 4
     * C : 010 : 2
     * M : 001 : 1
     */
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static StringTokenizer st;
    static int n, t;
    static int[][] foods;
    static int[][] trusts;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        foods = new int[n][n];
        trusts = new int[n][n];
        for(int x=0; x<n; x++) {
            String str = br.readLine().trim();
            for(int y=0; y<n; y++) {
                char food = str.charAt(y);
                switch(food) {
                    case 'T' :
                        foods[x][y] = 4;
                        break;
                    case 'C' :
                        foods[x][y] = 2;
                        break;
                    case 'M' :
                        foods[x][y] = 1;
                        break;
                }
            }
        }
        for(int x=0; x<n; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<n; y++) {
                trusts[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        while(t-- > 0) {
            List<int[]> repList = step2();
            step3(repList);
        }
    }

    static List<int[]> step2() {
        boolean[][] visited = new boolean[n][n];
        List<int[]> repList = new ArrayList<>();        // 각 그룹의 대표들만 저장
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                if(visited[x][y]) continue;

                repList.add(findGroup(x, y, visited));
            }
        }
        return repList;
    }

    static void step3(List<int[]> repList) {
        // 정렬
        repList.sort((a, b) -> {
            int aFood = foodCnt(foods[a[0]][a[1]]);
            int bFood = foodCnt(foods[b[0]][b[1]]);
            if(aFood != bFood) return Integer.compare(aFood, bFood);
            int aTrust = trusts[a[0]][a[1]];
            int bTrust = trusts[b[0]][b[1]];
            if(aTrust != bTrust) return Integer.compare(bTrust, aTrust);
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        boolean[][] defense = new boolean[n][n];        // 방어상태
        for(int[] point : repList) {
            int x = point[0];
            int y = point[1];
            if(defense[x][y]) continue;
            int dir = trusts[x][y] % 4;
            int beg = trusts[x][y]-1;
            int food = foods[x][y];
            trusts[x][y] = 1;

            while(true) {
                x += dx[dir];
                y += dy[dir];
                if(isNotBoard(x, y)) break;
                if(foods[x][y] == food) continue;

                if(beg > trusts[x][y]) {
                    beg -= (trusts[x][y]+1);
                    trusts[x][y]++;
                    foods[x][y] = food;
                } else {
                    trusts[x][y]+=beg;
                    beg = 0;
                    foods[x][y] |= food;
                }
                defense[x][y] = true;
                if(beg == 0) break;
            }
        }

        int[] arr = new int[8];
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                arr[foods[x][y]] += trusts[x][y];
            }
        }

        sb.append(arr[7]).append(' ').append(arr[6]).append(' ')
                .append(arr[5]).append(' ').append(arr[3]).append(' ')
                .append(arr[1]).append(' ').append(arr[2]).append(' ')
                .append(arr[4]).append(' ').append('\n');
    }

    static int[] findGroup(int x, int y, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[x][y] = true;
        int hx = x, hy = y;     // 대표 좌표 후보
        int cnt = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for(int dir=0; dir<4; dir++) {
                int nx = curx + dx[dir];
                int ny = cury + dy[dir];

                if(isNotBoard(nx, ny)) continue;
                if(foods[x][y] != foods[nx][ny]) continue;
                if(visited[nx][ny]) continue;

                visited[nx][ny] = true;
                cnt++;
                q.offer(new int[] {nx, ny});
                // 대표 후보 비교
                if(trusts[hx][hy] < trusts[nx][ny]) {
                    hx = nx; hy = ny;
                } else if(trusts[hx][hy] == trusts[nx][ny]) {
                    if(hx > nx) {
                        hx = nx; hy = ny;
                    } else if(hx == nx && hy > ny) {
                        hx = nx; hy = ny;
                    }
                }
            }
        }
        trusts[hx][hy] += cnt;          // 아침 루틴을 합쳐버림
        return new int[] {hx, hy};
    }


    /**
     * 공통
     */
    static boolean isNotBoard(int x, int y) {
        return x<0||y<0||x>=n||y>=n;
    }

    static int foodCnt(int food) {
        switch (food) {
            case 1: case 2: case 4:
                return 1;
            case 3: case 5: case 6:
                return 2;
        }
        return 3;
    }
}
