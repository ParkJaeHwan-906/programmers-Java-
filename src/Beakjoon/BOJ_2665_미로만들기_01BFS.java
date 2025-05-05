package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_2665_미로만들기_01BFS {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static int mapSize;
    static char[][] map;
    static int minChange;
    static void init() throws IOException {
        minChange = Integer.MAX_VALUE;
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new char[mapSize][mapSize];

        for(int x=0; x<mapSize; x++) {
            String input = br.readLine().trim();
            for(int y=0; y<mapSize; y++) {
                map[x][y] = input.charAt(y);
            }
        }

        getMinChange();
        System.out.println(minChange);
    }

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void getMinChange() {
        /*
            0-1 BFS 를 사용한다.
            0 의 값을 가지는 것은 offerFist()
            1 의 값을 가지는 것은 offerLast()

            값을 빼는 것은 pollFirst() 만 사용
        */
        Deque<int[]> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[mapSize][mapSize];

        // 시작 위치 설정
        visited[0][0] = true;
        deque.offer(new int[] {0,0,0});    // x,y,changeCnt

        while(!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int curX = cur[0];
            int curY = cur[1];
            int curChange = cur[2];

            // 목적지에 도착
            if(curX == mapSize-1 && curY == mapSize-1) {
                minChange = Math.min(minChange, curChange);
                continue;
            }

            // 이전의 최적해를 넘는 경우
            if(curChange >= minChange) continue;

            // 인접 구역 탐색
            for(int dir=0; dir<4; dir++) {
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                //범위를 벗어나는지
                if(nx < 0 || nx >= mapSize || ny < 0 || ny >= mapSize) continue;
                // 이미 방문한 적이 있는지
                if(visited[nx][ny]) continue;

                // 방문 가능
                visited[nx][ny] = true;
                // 1. 흰방인 경우
                if(map[nx][ny] == '1') {
                    deque.offerFirst(new int[] {nx, ny, curChange});
                } else {    // 2. 검은 방인 경우
                    deque.offerLast(new int[] {nx, ny, curChange+1});
                }
            }
        }
    }
}