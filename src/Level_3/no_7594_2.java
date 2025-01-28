package Level_3;

import CodingTest.HSAT241206_1;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 나무 조경
// https://softeer.ai/practice/7594
public class no_7594_2 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int mapSize;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        no_7594_2 problem = new no_7594_2();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        mapSize = Integer.parseInt(br.readLine().trim());   // 최소 2, 최대 4
        map = new int[mapSize][mapSize];

        for(int row=0; row<mapSize; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int col=0; col<mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        bw.write(String.valueOf(problem.getMaxPair()));
        bw.flush();
        bw.close();
    }


    // 우측과 하단으로만 탐색한다 ( 모든 경우 찾을 듯? )
    // 최대 쌍 찾기 -> 완탐?
    int maxValue;   // 최대 쌍 값을 기록할 변수
    boolean[][] visited;    // 중복 방지를 위한 방문처리 배열
    public int getMaxPair() {
        // 📌 최대 4개의 쌍을 찾아야함.
        // mapSize 가 2 일때는 4개를 찾을 수가 없음 ( 이 외에는 모두 4쌍 가능 )
        // 2 일때는 2개가 최대인듯
        int targetNum = mapSize == 2 ? mapSize : 4;

        maxValue = Integer.MIN_VALUE;   // 최소값으로 초기화
        visited = new boolean[mapSize][mapSize];

        findMaxValue(0, 0, targetNum);

        return maxValue;
    }

    // 우측과 하단만 탐색
    private int[] dx = {0,1};
    private int[] dy = {1,0};
    private void findMaxValue(int pairCount, int sum, int targetNum) {
        if(pairCount == targetNum) {    // 찾고자하는 쌍을 모두 찾은 경우
            maxValue = Math.max(maxValue, sum);
            return;
        }

        for(int x=0; x<mapSize; x++){
            for(int  y=0; y<mapSize; y++) {
                if(visited[x][y]) continue; // 이미 방문한 곳이라면

                movePoint(x,y, pairCount, sum, targetNum);
            }
        }
    }

    private void movePoint(int x, int y, int pairCount, int sum, int targetNum) {
        for(int dir=0; dir < 2; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // map 범위를 벗어난다면
            if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;
            // 이미 방문했다면
            if(visited[nx][ny]) continue;

            // 현재 쌍을 방문 처리
            visited[x][y] = true; visited[nx][ny] = true;
            // 계속 탐색
            findMaxValue(pairCount+1, sum + (map[x][y] + map[nx][ny]), targetNum);

            // 현재 쌍을 방문 처리 해제
            visited[x][y] = false; visited[nx][ny] = false;
        }
    }
}
