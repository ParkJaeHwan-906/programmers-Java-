package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 1회 정기 코딩 인증평가 기출] 안전운전을 도와줄 차세대 지능형 교통시스템
// https://softeer.ai/practice/6274
public class no_6274 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        bw.flush();
        bw.close();
    }

    static int mapSize, time;   // map 의 크기, 제한 시간
    static int[][][] map; // 교차로는 N x N 개 존재한다, 각 교차로는 4개의 신호를 갖는다
    static boolean[][][] visited; // 자동차가 방문 가능한 교차로를 체크
    static int visitedNum; // 방문한 교차로의 개수 저장
    static void init() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());

        map = new int[mapSize][mapSize][4];
        visited = new boolean[mapSize][mapSize][4];

        for(int row=0; row<mapSize; row++) {
            for(int col=0; col<mapSize; col++) {
                // 각 교차로의 신호를 입력 받는다.
                st = new StringTokenizer(br.readLine().trim());

                for(int signIdx=0; signIdx<4; signIdx++) {
                    // 0-base
                    map[row][col][signIdx] = Integer.parseInt(st.nextToken())-1;
                }
            }
        }

        visitedNum = 0;
        moveCar();
        bw.write(String.valueOf(visitedNum));
    }

    // 신호를 정리....
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[][] signs = {
            {0,1,3},    // 1번 신호 (오른쪽 위 아래)
            {0,2,3},    // 2번 신호 (왼쪽 위 오른쪽)
            {3,0,2},    // 3번 신호 (왼쪽, 위, 아래)
            {0,2,1},    // 4번 신호 (왼쪽, 아래, 오른쪽)
            {0,3},    // 5번 신호 (오른쪽, 위)
            {2,3},    // 6번 신호 (왼쪽, 위)
            {2,1},    // 7번 신호 (왼쪽, 아래)
            {0,1},    // 8번 신호 (오른쪽,아래)
            {2,1},    // 9번 신호 (오른쪽, 아래)
            {0,3},    // 10번 신호 (위, 오른쪽)
            {2,3},    // 11번 신호 (왼쪽, 위)
            {2,1}    // 12번 신호 (왼쪽, 아래)
    };
    static int[] enterDir = {0,3,2,1};  // 들어오는 방향도 4개 반복 ^^
    // 자동차가 움직인다.
    static void moveCar() {
        // 탐색 수를 줄이기 위해 Queue 를 사용 ( 아마? )
        Queue<int[]> moveLog = new ArrayDeque<>();
        moveLog.offer(new int[] {0,0,0,3});   // 처음 시작, 아래에서 들어오고 있음

        // 역방향 금지?
        while(!moveLog.isEmpty()) {
            int[] curMove = moveLog.poll();

            int curX = curMove[0];
            int curY = curMove[1];
            int curTime = curMove[2];
            int nowDir = curMove[3];
            int signIdx = curTime % 4;  // 신호는 4개의 패턴을 가지고 있다.

            if(curTime == time+1) {    // time 까지 움직이고 종료
                continue;
            }
            if(visited[curX][curY][signIdx]) {    // 동일 교차로를 동일한 패턴의 신호에 방문한 적이 있다면 넘어간다.
                continue;
            }

            visited[curX][curY][signIdx] = true;  // 방문 처리
            visitedNum++;

            // 현재 교차로에서 현재 신호를 확인한다.
            int nowSign = map[curX][curY][signIdx];
            // 현재 신호가 가리키는 방향을 가져온다.
            int[] dirs = signs[nowSign];
            // 이동
            for(int dir : dirs) {
                // 현 신호를 받고 이동할 위치
                int nx = curX + dx[dir];
                int ny = curY + dy[dir];

                // 주어진 범위를 벗어나는 경우 패스
                if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;

                moveLog.offer(new int[] {nx, ny, curTime+1, dir});
            }
        }

    }
}

/*
    가로 세로 N 개의 교차로로 구성되어있다.

    신호등은 12 개의 상태 중 4가지를 가지고 무한으로 반복한다.
    자동차가 멈추지 않고 T 시간 이내 갈 수 있는 교차로의 수를 계산하라.

    처음에 (1,1) 의 아래쪽으로 진입하고 있다.

    현재 시간을 기록하며 해당 방향에 갈 수 있는 모든 경로를 탐색한다.
     ⚠️ 신호에 따라 갈 수 있는 방향을 어떻게 정할 것인가? -> 무식하게 때려박기?
 */