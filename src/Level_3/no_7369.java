package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 나무 수확
// https://softeer.ai/practice/7369
public class no_7369 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int mapSize;
    static int[][][] map;
    public static void main(String[] args) throws IOException {
        no_7369 problem = new no_7369();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // mapSize 입력
        // 인덱스를 1부터 시작하기 위해 길이를 1 늘려줌
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new int[mapSize+1][mapSize+1][2];
        // map 입력
        for(int x=1; x<=mapSize; x++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int y=1; y<=mapSize; y++) {
                // [x][y][0] : 해당 위치까지의 최대 누적값
                // [x][y][1] : 해당 위치까지의 최대 좌표 값 (스프링 쿨러 설치 위치)
                map[x][y][0] = Integer.parseInt(st.nextToken());
                map[x][y][1] = map[x][y][0];
            }
        }
        br.close();

        bw.write(String.valueOf(problem.findMaxValue()));
        bw.flush();
        bw.close();
    }

    long answer;
    public long findMaxValue() {
        calcMap();

        /*
        calcMap 과 동일하게, 위에서 내려오는 값과, 왼쪽에서 오는 값을 비교한다.
        이때 최종적으로 결과를 내야하기 때문에 스프링쿨러를 설치할 위치도 포함하여 최대 값을 찾는다.

        이후 마지막 위치의 값을 더해준다.
         */
        answer = Math.max (
                map[mapSize-1][mapSize][0] + Math.max(map[mapSize-1][mapSize][1], map[mapSize][mapSize][1]),
                map[mapSize][mapSize-1][0] + Math.max(map[mapSize][mapSize-1][1], map[mapSize][mapSize][1])
        ) + map[mapSize][mapSize][0];

        return answer;
    }

    private void calcMap() {    // map 을 탐색하며 각 위치까지의 최대 값을 갱신한다
        // 마지막 칸 직전 위치까지만 구한다
        for(int x=1; x<=mapSize; x++) {
            for(int y=1; y<=mapSize; y++) {
                // 마지막 위치에 도달한다면 계산하지 않고 끝낸다.
                if(x == mapSize && y == mapSize) return;

                if(map[x-1][y][0] < map[x][y-1][0]) {   // 위에서 내려오는 값보다, 왼쪽에서 오는 값이 더 큰 경우
                    map[x][y][0] = map[x][y-1][0] + map[x][y][0];   // 현재 위치 값 + 이전 까지 최대 값
                    map[x][y][1] = Math.max(map[x][y][1], map[x][y-1][1]);  // 현재 위치의 값과, 이전 위치까지의 최대 값을 갖는 칸을 비교 ( 스프링 쿨러 설치 위치 )
                } else {    // 위에서 내려오는 값이, 왼쪽에서 오는 값보다 큰 경우
                    map[x][y][0] = map[x-1][y][0] + map[x][y][0];   // 현재 위치 값 + 이전 까지 최대 값
                    map[x][y][1] = Math.max(map[x][y][1], map[x-1][y][1]);  // 현재 위치의 값과, 이전 위치까지의 최대 값을 갖는 칸을 비교 ( 스프링 쿨러 설치 위치 )
                }
            }
        }
    }
}
