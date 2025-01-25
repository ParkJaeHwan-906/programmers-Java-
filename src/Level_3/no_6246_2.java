package Level_3;

/*
n*n 크기의 격자
0 은 빈칸, 1은 벽

이동방향은 상하좌우
벽은 이동 못함
한 번 지나간 지점은 다시 못지나감

목표 지점을 지날 수 있는 모든 경우의 수

완전탐색을 통해 모든 경우를 탐색한다?
n 이 2~4 완탐 가능
 */

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 7회 정기 코딩 인증평가 기출] 순서대로 방문하기
// https://softeer.ai/practice/6246
public class no_6246_2 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int mapSize, destinationNum;
    static int[][] map;
    static int[][] destinations;
    public static void main(String[] args) throws IOException {
        no_6246_2 problem = new no_6246_2();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        map = new int[mapSize][mapSize];
        destinationNum = Integer.parseInt(st.nextToken());
        destinations = new int[destinationNum][2];

        for(int x = 0; x < mapSize; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y = 0; y < mapSize; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        for(int idx=0; idx<destinationNum; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            destinations[idx][0] = Integer.parseInt(st.nextToken()) - 1;
            destinations[idx][1] = Integer.parseInt(st.nextToken()) - 1;
        }
        br.close();
        bw.write(String.valueOf(problem.findAllRoutes()));
        bw.flush();
        bw.close();
    }

    int routeCount;
    boolean[][] visited;
    public int findAllRoutes() {
        routeCount = 0; // 갈 수 있는 경로의 수
        visited = new boolean[mapSize][mapSize];    // 방문처리를 위한 배열

        // 출발지 방문 처리
        visited[destinations[0][0]][destinations[0][1]] = true;
        move(destinations[0][0], destinations[0][1], 0);

        return routeCount;
    }

    // 현 위치의 좌표와, 현재 이동중인 목적지를 전달 받음
    // 이동 방향은 상하좌우
    private int[] dx = {0,1,0,-1};
    private int[] dy = {1,0,-1,0};
    private void move(int x, int y, int seq) {
        // 목적지에 도착했다면, 다음 목적지를 설정
        if(x == destinations[seq][0] && y == destinations[seq][1]) seq++;

        if(seq == destinationNum) { // 모든 목적지에 도착한 경우
            routeCount++;
            return;
        }

        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // map 의 범위를 벗어나거나, 이미 방문한 곳인 경우
            if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize || visited[nx][ny]) continue;
            // 벽이라면 못감
            if(map[nx][ny] == 1) continue;

            // 아니면 탐색 ㄱㄱ
            visited[nx][ny] = true;
            move(nx, ny, seq);
            visited[nx][ny] = false;
        }
    }
}
