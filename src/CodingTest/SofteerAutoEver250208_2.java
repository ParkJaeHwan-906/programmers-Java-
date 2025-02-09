package CodingTest;

import java.util.*;
import java.io.*;

public class SofteerAutoEver250208_2 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int mapSize, arrowNum, score;
    static int[][] map;
    static int[] arrows;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());
        arrowNum = Integer.parseInt(st.nextToken());
        score = Integer.parseInt(st.nextToken());

        map = new int[mapSize][mapSize];
        for(int x=0; x<mapSize; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<mapSize; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        arrows = new int[arrowNum];
        st = new StringTokenizer(br.readLine().trim());
        for(int idx=0; idx<arrowNum; idx++) {
            arrows[idx] = Integer.parseInt(st.nextToken());
        }

        br.close();
        SofteerAutoEver250208_2 problem = new SofteerAutoEver250208_2();
        bw.write(String.valueOf(problem.getMinPower()));
        bw.flush();
        bw.close();
    }

    long[][][] scoreMap;    // 각 화살별 칸마다 얻을 수 있는 점수를 기록하는 배열
    boolean isOk;
    public int getMinPower() {
        scoreMap = new long[arrowNum][mapSize][mapSize];

        for(int idx=0; idx<arrowNum; idx++) {
            for(int x=0; x<mapSize; x++) {
                for(int y=0; y<mapSize; y++) {
                    calcScore(idx+1, x, y);
                }
            }
        }

        // 점수 합산 출력 테스트
//        for(int idx=0; idx<arrowNum; idx++) {
//            System.out.println("idx : " + idx);
//            for(int x=0; x<mapSize; x++) {
//                for(int y=0; y<mapSize; y++) {
//                    System.out.print(scoreMap[idx][x][y] + " ");
//                }
//                System.out.println();
//            }
//        }

        // 이분 탐색을 위한 최대, 최소 구하기
        int min = Integer.MAX_VALUE;
        int max = 0;
        for(int power : arrows) {
            min = Math.min(power, min);
            max += power;
        }

        int answer = Integer.MAX_VALUE;
        while(min <= max) {
            int mid = (min + max) / 2;  // 현재 힘
            isOk = false;   // 현재 힘으로 점수를 달성할 수 있는지 여부를 확인

            canMakePower(mid, 0, 0, new int[4]);

            if(isOk) {
                max = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                min = mid + 1;
            }

        }

        return answer;
    }

    // 1. mid 값을 만들 수 있는 조합을 찾는다.
    // 2. 해당 조합으로 score 을 달성 가능한지 판별한다.
    public void canMakePower(int mid, int idx, int sum, int[] arr){
        if(isOk) return;    // 탐색을 줄이기 위한 조건

        if(idx == arrowNum) {   // 모든 탐색을 마쳤다면
            if(sum == mid) {    // 목표로 하는 힘을 만들 수 있다면
                // 점수 달성이 가능한지 판별한다
                canMakeScore(0, 0, arr);
            }
            return;
        }

        if(sum > mid) return;   // 목표하려는 힘보다 커지는 경우

        // 현재 화살 포함
        arr[idx] = 1;
        canMakePower(mid, idx+1, sum + arrows[idx], arr);

        // 현재 화살 미포함
        arr[idx] = 0;
        canMakePower(mid, idx+1, sum, arr);
    }

    // 점수가 달성 가능한지 확인한다.
    public void canMakeScore(int idx, long sum, int[] arr) {
        if(isOk) return; // 탐색을 줄이기 위한 조건

        if(idx == arrowNum) {   // 끝까지 탐색 완료한경우
            if(sum == score) {  // 점수 달성을 했다면
                isOk = true;    // 점수 달성 여부 체크
            }
            return;
        }

        if(arr[idx] == 0) { // 현재 화살을 사용하지 않았다면, 다음 화살로 넘겨준다.
            canMakeScore(idx+1, sum, arr);
            return;
        }

        if(sum > score) return; // 점수를 초과했다면

        for(int x=0; x<mapSize; x++) {
            for(int y=0; y<mapSize; y++) {
                canMakeScore(idx+1 , sum + scoreMap[idx][x][y] , arr);
            }
        }
    }

    // 상하좌우대각선
    // 0~3 : 상하좌우
    // 4~7 : 대각선
    int[] dx = new int[] {0,1,0,-1,1,1,-1,-1};
    int[] dy = new int[] {1,0,-1,0,1,-1,1,-1};
    public void calcScore(int idx, int x, int y) {
        int scoreSum = map[x][y];  // 현 위치에서 수집가능한 최대 값

        // 상하좌우
        // i-1 만큼의 범위를 수집할 수 있다.
        for(int dir=0; dir<4; dir++) {
            for(int bound=1; bound <= idx-1; bound++) {
                int nx = x + dx[dir]*bound;
                int ny = y + dy[dir]*bound;

                // 범위를 벗어난다면
                if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;
                scoreSum += map[nx][ny];
            }
        }

        // 대각선
        // i-2 만큼의 범위를 수집할 수 있다.
        for(int dir=4; dir<8; dir++) {
            for(int bound=1; bound <= idx-2; bound++) {
                int nx = x + dx[dir]*bound;
                int ny = y + dy[dir]*bound;

                // 범위를 벗어난다면
                if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;
                scoreSum += map[nx][ny];
            }
        }

        scoreMap[idx-1][x][y] = scoreSum;
    }
}

/*
N x N 크기의 과녁이 있다
각 구역마다 획득할 수 있는 점수가 있다.

화살은 K 개 존재한다. 각 화살의 두께는 1 ~ K 이다.
두께가 i 인 화살은 |x2-x1| + |y2-y1| 영역의 점수를 모두 획득할 수 있다.

각 화살을 쏘는데 필요한 힘은 B[i] 이다.

최소한의 힘을 사용하여 목표하는 점수를 정확하게 획득한다.

제한
1<= N <= 10
k <= N
1 <= B[i] <= 100000

ex)
3 2 8
1 1 1
1 1 1
1 1 3
1 2
-> 3
 */
