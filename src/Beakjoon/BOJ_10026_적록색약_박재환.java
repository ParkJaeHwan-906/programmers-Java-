package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_10026_적록색약_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        init();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int mapSize;     // 격자의 크기
    static char[][] map;    // 격자
    static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new char[mapSize][mapSize];

        for(int x=0; x<mapSize; x++) {
            String mapInput = br.readLine().trim();
            for(int y=0; y<mapSize; y++) {
                map[x][y] = mapInput.charAt(y);
            }
        }

        normal();
        sb.append(' ');
        JeonJaeJoon();
    }

    /*
        일반인이 보는 색상
        R G B
     */
    static void normal() {
        int area = checkNearArea('R', new boolean[mapSize][mapSize]) +
                checkNearArea('G', new boolean[mapSize][mapSize]) +
                checkNearArea('B', new boolean[mapSize][mapSize]);

        sb.append(area);
    }

    /*
        적록색약이 보는 색상
        R B
     */
    static void JeonJaeJoon() {
        // R - G 를 구분하기 못하기 때문에 map 에서 R 과 G를 동일 취급해준다.
        for(int x=0; x<mapSize; x++) {
            for(int y=0; y<mapSize; y++) {
                if(map[x][y] == 'G') map[x][y] = 'R';
            }
        }
        int area = checkNearArea('R', new boolean[mapSize][mapSize]) +
                checkNearArea('B', new boolean[mapSize][mapSize]);

        sb.append(area);
    }

    // 전달받은 색상 구역의 개수를 반환한다.
    static int checkNearArea(char color, boolean[][] isChecked) {
        int area = 0;
        for(int x=0; x<mapSize; x++) {
            for(int y=0; y<mapSize; y++) {
                if(map[x][y] == color && !isChecked[x][y]) {
                    doCheck(x,y,isChecked, color);
                    area++;
                }
            }
        }
        return area;
    }

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    /*
        현 위치와 인접한 구역 중, 찾고자하는 색상의 구역을 모두 방문처리한다.
     */
    static void doCheck(int x, int y, boolean[][] isChecked, char color) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x,y});
        isChecked[x][y] = true;

        while(!q.isEmpty()) {
            int[] curPoint = q.poll();
            int curX = curPoint[0];
            int curY = curPoint[1];

            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                // 범위를 벗어나는 경우
                if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;
                // 이미 확인한 구역인 경우
                if(isChecked[nx][ny]) continue;
                // 찾고자하는 색상이 아닌 경우
                if(map[nx][ny] != color) continue;

                // 체크할 수 있다.
                isChecked[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }
    }
}

/*
    적록색약이 아닌 사람은 R G B 총 3가지의 색을 구분한다.
    적록색약인 사람은 R B 총 2가지의 색을 구분한다.

    각 색상은 상하좌우로 근접해있다.

    1. 탐색하고자하는 색상을 지정한다.
    2. 원본 배열은 여러번 사용해야하므로, boolean 배열을 통해 방문처리를한다.
    3. 반복문을 통해 원하는 색상이 등장하면 인접한 구역 탐색을 시작한다.
        a. Queue 를 사용하여 상하좌우를 탐색하고, 이를 방문처리해준다.
 */
