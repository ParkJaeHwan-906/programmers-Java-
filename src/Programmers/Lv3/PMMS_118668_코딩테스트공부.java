package Programmers.Lv3;

import java.util.*;

public class PMMS_118668_코딩테스트공부 {
    public static void main(String[] args) {
        int alp = 10, cop = 10;
        int[][] problems = {
                {10,15,2,1,2},
                {20,20,3,3,4}
        };

        System.out.println(new PMMS_118668_코딩테스트공부().solution(alp, cop, problems));
    }

    /*
        문제를 풀기 위해서는 문제가 요구하는 일정 이상의 알고력과 코딩력이 필요하다.

        알고력과 코딩력을 높이기 위해 아래의 방법이 있다.
        1. 알고력을 높이기 위해 알고리즘 공부를 한다. -> 1을 높이기 위해 1의 시간 필요
        2. 코딩력 높이기 위해 코딩 공부를 한다. -> 1을 높이기 위해 1의 시간 필요
        3. 현재 풀 수 있는 문제 중 하나를 풀어 알고력과 코딩력을 높인다.
            3-3. 각 문제마다 문제를 풀면 올라가는 알고력과 코딩력이 정해져있다.
        4. 문제를 하나 푸는 데 요구 시간이 정해져 있으며, 같은 문제를 여러 번 푸는 것이 가능하다.

        -> 모든 문제를 풀 수 있는 알고력과 코딩력을 얻는 최단시간을 반환
     */
    int alp, cop;
    int[][] problems;
    int maxAlp,maxCop;
    public int solution(int alp, int cop, int[][] problems) {
        // alp : 초기 알고력, cop : 초기 코딩력
        // problems : 최대 150 개
        // [alp_req, cop_req, alp_rwd, cop_rwd, cost]
        // alp_req : 문제를 푸는데 필요한 알고력
        // cop_req : 문제를 푸는데 필요한 코딩력
        // alp_rwd : 문제를 풀었을 때 증가하는 알고력
        // cop_rwd : 문제를 풀었을 때 증가하는 코딩력
        // cost    : 문제를 푸는데 드는 시간
        init(alp, cop, problems);
        return findMinTime();
    }

    void init(int alp, int cop, int[][] problems) {
        this.alp = alp;
        this.cop = cop;
        this.problems = problems;
        // 최대 알고력, 코딩력 찾기
        // 정렬보다, 순회가 빠름 -> 정렬 nlogn, 순회 n
        this.maxAlp = this.maxCop = Integer.MIN_VALUE;
        for(int[] problem : problems) {
            this.maxAlp = Math.max(this.maxAlp, problem[0]);
            this.maxCop = Math.max(this.maxCop, problem[1]);
        }
    }

    int findMinTime() {
        // DP 로 접근
        // [알고력][코딩력] = 최소시간
        int[][] timeTable = new int[maxAlp+1][maxCop+1];
        // alp 또는 cop 가 이미 더 큰 경우를 확인
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        // 테이블 초기화
        for(int[] arr : timeTable) Arrays.fill(arr, Integer.MAX_VALUE);
        timeTable[alp][cop] = 0;

        for(int curAlp=alp; curAlp<maxAlp+1; curAlp++) {
            for(int curCop=cop; curCop<maxCop+1; curCop++) {
                // 1 씩 증가시키기
                if(curAlp+1 < maxAlp+1) timeTable[curAlp+1][curCop] = Math.min(timeTable[curAlp+1][curCop], timeTable[curAlp][curCop]+1);
                if(curCop+1 < maxCop+1) timeTable[curAlp][curCop+1] = Math.min(timeTable[curAlp][curCop+1], timeTable[curAlp][curCop]+1);

                // 문제 풀기
                for(int[] problem : problems) {
                    int needAlp = problem[0];
                    int needCop = problem[1];
                    int needTime = problem[4];
                    int getAlp = problem[2];
                    int getCop = problem[3];
                    // 현재 문제를 풀 수 있는 조건 미충족
                    if(curAlp < needAlp || curCop < needCop) continue;
                    // 문제를 풀 수 있음
                    // 이때, 범위를 넘어가는 경우 방지
                    int alpSum = Math.min(curAlp+getAlp, maxAlp);
                    int copSum = Math.min(curCop+getCop, maxCop);
                    timeTable[alpSum][copSum] =
                            Math.min(
                                    timeTable[alpSum][copSum],
                                    timeTable[curAlp][curCop] + needTime
                                    );
                }
            }
        }
        return timeTable[maxAlp][maxCop];
    }
}
