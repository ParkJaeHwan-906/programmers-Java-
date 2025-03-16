package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_2667_단지번호붙이기_박재환 {
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

    static int mapSize;
    static char[][] map;
    static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new char[mapSize][mapSize];

        for(int x=0; x<mapSize; x++) {
            String mapInput = br.readLine().trim();
            for(int y=0; y<mapSize; y++) {
                map[x][y] = mapInput.charAt(y);
            }
        }
        br.close();

        checkApt();

        sb.append(aptSize.size()).append('\n');  // 단지의 수
        Collections.sort(aptSize);
        for(int apt : aptSize) {
            sb.append(apt).append('\n');
        }
    }

    /*
        (0,0) 에서 부터 시작하여 집이 있는 부분을 찾는다.
            해당 구역에서 연속적으로 이어져있는 곳을 BFS 를 사용해 체크한다.
                -> 단지의 크기를 구할 수 있다.
            단지의 개수를 세어준다.
     */
    static boolean[][] isChecked;
    static List<Integer> aptSize;
    static void checkApt() {
        aptSize = new ArrayList<>();
        isChecked = new boolean[mapSize][mapSize];
        for(int x=0; x<mapSize; x++) {
            for(int y=0; y<mapSize; y++) {
                if(map[x][y] == '1' && !isChecked[x][y]) {    // 집이 있고, 타 단지가 아니라면
                    aptSize.add(checkAptSize(x,y));
                }
            }
        }
    }

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int checkAptSize(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});
        isChecked[x][y] = true;
        int aptSize = 1;

        while(!q.isEmpty()){
            int[] curPoints = q.poll();
            int curX = curPoints[0];
            int curY = curPoints[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                // 범위를 벗어나거나, 아파트가 아닌 경우
                if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize || map[nx][ny] == '0') continue;
                // 이미 체크한 경우
                if(isChecked[nx][ny]) continue;

                isChecked[nx][ny] = true;
                q.offer(new int[] {nx, ny});
                aptSize++;
            }
        }

        return aptSize;
    }


}
