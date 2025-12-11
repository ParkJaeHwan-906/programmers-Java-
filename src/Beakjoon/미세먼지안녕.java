package Beakjoon;

import java.util.*;
import java.io.*;

public class 미세먼지안녕 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    /**
     * R x C 격자판이 있다.
     * (r, c) -> r 헹 c 열
     *
     * 공기청정기는 항상 1번 열에 설치된다. 크기는 두 행을 차지한다.
     * (r, c)칸에 있는 미세먼지의 양은 A[r][c] 이다.
     *
     * 1초 동안 아래와 같은 일이 일어난다.
     * - 미세먼지가 확산된다. -> 미세먼지가 있는 모든 칸에서 동시에 일어난다.
     *      - 인접한 네 방향으로 확산된다.
     *      - 인접한 방향에 공기청정기가 있거나, 칸이 없는 경우는 확산이 일어나지 않는다.
     *      - (r, c) 에 남은 미세먼지의 양은 A[r][c] - |A[r][c]/5| * 확산된 방향의 개수 이다.
     * - 공기청정기가 작동한다.
     *      - 공기청정기에서 바람이 나온다.
     *      - 위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
     *      - 바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
     *      - 공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.
     */
    static StringTokenizer st;
    static int r, c, t;
    static int[][] board;
    static List<Integer> airPurifierLoc;
    static void init() throws IOException {
        airPurifierLoc = new ArrayList<>();
        st = new StringTokenizer(br.readLine().trim());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new int[r][c];
        t = Integer.parseInt(st.nextToken());

        for(int x=0; x<r; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<c; y++) board[x][y] = Integer.parseInt(st.nextToken());
        }

        while(t-- > 0) {
            spreadDust();
            airPurifier();
        }

        int totalDust = 0;
        for(int x=0; x<r; x++) {
            for(int y=0; y<c; y++) {
                if(board[x][y] > 0) totalDust+=board[x][y];
            }
        }
//        for(int[] arr : board) System.out.println(Arrays.toString(arr));
        System.out.println(totalDust);
    }
    // 우 하 좌 상
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void spreadDust() {
        // 현재 미세먼지가 존재하는 위치 찾기
        Queue<int[]> dustLoc = new LinkedList<>();
        findDust(dustLoc);
        // 추가될 미세먼지에 대한 정보를 가지고 있음
        List<int[]> addDust = new ArrayList<>();

        while(!dustLoc.isEmpty()) {
            int[] cur = dustLoc.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDust = board[curX][curY];
            int spread = curDust/5;

            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                // 칸을 벗어나는 경우
                if(nx < 0 || ny < 0 || nx >= r || ny >= c) continue;
                if(board[nx][ny] == -1) continue;
                addDust.add(new int[] {nx, ny, spread});
                curDust -= spread;
            }

            // 남은 먼지 갱신
            board[curX][curY] = curDust;
        }

        // board 갱신
        for(int[] arr : addDust) {
            int x = arr[0];
            int y = arr[1];
            int dust = arr[2];
            board[x][y] += dust;
        }
    }

    static void findDust(Queue<int[]> dustLoc) {
        for(int x=0; x<r; x++) {
            for(int y=0; y<c; y++) {
                if(board[x][y] > 0) dustLoc.offer(new int[] {x, y});
            }
        }
    }

    // 상 우 하 좌
    static int[] upsideDx = {-1,0,1,0};
    static int[] upsideDy = {0,1,0,-1};
    // 하 우 상 좌
    static int[] downSideDx = {1,0,-1,0};
    static int[] downSideDy = {0,1,0,-1};
    static void airPurifier() {
        //  초기 실행시에만 초기화 작업
        if(airPurifierLoc.isEmpty()) findAirPurifier();

        // 역순으로 한 칸씩 당기면 될듯
        // 위쪽은 반시계방향
        int x = airPurifierLoc.get(0)-1;
        int y = 0;
        int minXLimit = airPurifierLoc.get(0);

        for(int dir=0; dir<4; dir++) {
            while(true) {
                int nx = x + upsideDx[dir];
                int ny = y + upsideDy[dir];

                if (nx < 0 || nx > minXLimit || ny < 0 || ny >= c) break;
                if(board[nx][ny] == -1) break;
                // 이동 가능
                int tempDust = board[nx][ny];
                board[nx][ny] = 0;
                if(board[x][y] != -1) {
                    board[x][y] = tempDust;
                }
                x = nx;
                y = ny;
            }
        }

        // 아래쪽은 시계방향
        x = airPurifierLoc.get(1)+1;
        y = 0;
        int maxXLimit = airPurifierLoc.get(1);

        for(int dir=0; dir<4; dir++) {
            while(true) {
                int nx = x + downSideDx[dir];
                int ny = y + downSideDy[dir];

                if (nx < maxXLimit || nx >= r || ny < 0 || ny >= c) break;
                if(board[nx][ny] == -1) break;
                // 이동 가능
                int tempDust = board[nx][ny];
                board[nx][ny] = 0;
                if(board[x][y] != -1) {
                    board[x][y] = tempDust;
                }
                x = nx;
                y = ny;
            }
        }
    }

    static void findAirPurifier() {
        for(int x=0; x<r; x++) {
            if(board[x][0] == -1) airPurifierLoc.add(x);
        }
    }
}
