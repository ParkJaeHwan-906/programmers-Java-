package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 스마트 물류
// https://softeer.ai/practice/6279
public class no_6279 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int railNum, range;
    static List<Integer> robots = new ArrayList<>();    // 로봇 위치 저장을 위한 리스트
    static List<Integer>  parts = new ArrayList<>();    // 부품 위치 저장을 위한 리스트
    public static void main(String[] args) throws IOException {
        no_6279 problem = new no_6279();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        railNum = Integer.parseInt(st.nextToken()); // 생산 라인의 길이
        range = Integer.parseInt(st.nextToken());   // 로봇의 범위

        String railInput = br.readLine().trim();
        for(int idx=0; idx < railNum; idx++) {
            if(railInput.charAt(idx) == 'P'){   // 로봇 위치
                robots.add(idx);
            } else {    // 부품 위치
                parts.add(idx);
            }
        }
        br.close();

        bw.write(String.valueOf(problem.maxRobots()));
        bw.flush();
        bw.close();
    }

    private int maxCount;
    private int maxRobots() {
        maxCount = 0;   // 초기값 설정

        // 각 리스트에 포인터 설정
        int robotPoint = 0;
        int partPoint = 0;

        // 각 포인터의 매칭
        while(robotPoint < robots.size() && partPoint < parts.size()) {
            int robot = robots.get(robotPoint);
            int part = parts.get(partPoint);

            if(Math.abs(robot - part) <= range) {   // 로봇이 부품을 집을 수 있다면
                maxCount++;
                robotPoint++;
                partPoint++;
            } else if (robot < part) {    // 로봇의 위치가 부품의 위치보다 작다면
                robotPoint++;
            } else {    // 부품의 위치가 로봇보다 작다면
                partPoint++;
            }
        }

        return maxCount;
    }
}

/*
로봇들의 위치에서 거리가 k 이하인 부품만 잡을 수 있다 ( 왼쪽 오른쪽 상관 X )

부품을 집을 수 있는 최대 로봇의 수를 구해야함
라인의 길이(N)는 최대 2만, K 는 최대 10
로봇은 P
부품은 H

최대는 10^10 완탐은 X

로봇의 위치와 부품의 위치를 따로따로 관리
로봇의 위치와, 부품의 위치에 각각 포인터를 둔다
    로봇과 부품이 매칭되는지 확인한다
        |로봇 위치 - 부품 위치| <= 범위

 */

//
//    no_6279 problem = new no_6279();
//        try {
//        br = new BufferedReader(new InputStreamReader(System.in));
//        bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        StringTokenizer st = new StringTokenizer(br.readLine().trim());
//        railNum = Integer.parseInt(st.nextToken()); // 생산 라인의 길이
//        range = Integer.parseInt(st.nextToken());   // 로봇의 범위
//
//        rail = br.readLine().trim();    // 레일 입력 받기
//        if(rail.length() != railNum) throw new IllegalArgumentException("입력이 일치하지 않습니다.");
//
//        br.close();
//        bw.write(String.valueOf(problem.maxRobot()));
//        bw.flush();
//        bw.close();
//    } catch(Exception e) {
//        System.err.println(e.getMessage());
//    }
//}
//
//private int robotCount;
//private boolean[] visited;  // 물건을 가져갔는지 유무 확인
//private int maxRobot() {
//    robotCount = 0; // 초기값 설정
//    visited = new boolean[railNum];
//
//    checkRobotsLoc();
//    // 가장 첫 번째 로봇의 위치에서부터 탐색 시작
//    robotWork(rail.indexOf('P'), 0);
//
//    return robotCount;
//}
//
//// 로봇의 위치를 확인해준다
//private void checkRobotsLoc() {
//    for(int idx=0; idx<railNum; idx++) {
//        if(rail.charAt(idx) == 'P') visited[idx] = true;
//    }
//}
//
//// 왼쪽과 오른쪽을 확인해야한다
//private int[] leftRight = {1, -1};
//private void robotWork(int idx, int count) {
//    if(idx == -1) { // 모든 로봇 탐색 완료
//        robotCount = Math.max(robotCount, count);
//        return;
//    }
//    int nextRobot = nextRobot(idx);
//
//    for(int search=1; search<=range; search++) {
//        for(int dir=0; dir<2; dir++) {
//            int checkPoint = idx + search*leftRight[dir];
//
//            // 탐색 범위를 벗어나는 경우
//            if(checkPoint < 0 || checkPoint >= railNum) continue;
//            // 이미 탐색했거나, 로봇의 위치인경우
//            if(visited[checkPoint]) continue;
//
//            // 다음 로봇 위치에서 탐색
//            visited[checkPoint] = true;
//            robotWork(nextRobot, count+1);
//            visited[checkPoint] = false;
//        }
//    }
//
//    // 주변에 부품이 없을 경우, 다음 로봇에서 탐색
//    robotWork(nextRobot, count);
//}
//
//// 다음 로봇의 위치를 반환한다
//// 마지막 로봇이라면 -1 을 반환한다
//private int nextRobot(int idx) {
//    for(int next=idx+1; next<railNum; next++) {
//        if(rail.charAt(next) == 'P') return next;
//    }
//
//    return -1;
//}