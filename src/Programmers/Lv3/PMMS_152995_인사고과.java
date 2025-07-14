package Programmers.Lv3;

import java.util.*;

public class PMMS_152995_인사고과 {
    public static void main(String[] args) {
        int[][] scores = {{2,2},{1,4},{3,2},{3,2},{2,1}};
        System.out.println(new PMMS_152995_인사고과().solution(scores));
    }

    /*
        [근무 태도, 동료 평가]
        어떤 사원이 다른 임의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면
        그 사원은 인센티브를 받지 못한다.

        그렇지 않은 사원들에 대해, 두 점수 합이 높은 순으로 석차를 내어, 인센티브가 차등 지급된다.
        합이 동일한 사람들은 동석차이며, 동석차 수 만큼 다음 석차는 건너뛴다.
     */
    public int solution(int[][] scores) {       // 완호는 scores[0]
        int[] wanho = scores[0];
        /*
            정렬을 이용해야할 것 같음
            1. 완전 탐색 -> scores : 최대 100,000 ❌ 시간 초과
            2. 정렬?
         */

        // 1. 정렬
        // 근무 태도 -> 내림차순, 동료 평가 -> 오름 차순
        Arrays.sort(scores, (a,b) -> {
            if(a[0] == b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(b[0], a[0]);
        });

        int rank = 1;       // 완호의 등수 -> 초기값 1
        int maxPeerScore = 0;   // 사원들 중 최고 점수 (동료 평가 점수)
        
        for(int[] score : scores) {
            int workScore = score[0];   // 근무 점수
            int peerScore = score[1];   // 동료 점수

            // 현재까지 동료 평가 최대 점수보다 동료 평가 점수가 낮은 경우
            // -> 현재 사람은 탈락
            if(peerScore < maxPeerScore) {
                // 그게 만약 완호라면 바로 종료
                if(score == wanho) return -1;
                // 현재 사람 탈락 처리 ( 패스 )
                continue;
            }

            // 인센티브를 받을 수 있는 사람들

            // 최대 동료 점수 갱신
            maxPeerScore = Math.max(maxPeerScore, peerScore);
            // 완호와 비교하여 완호 등수 계산
            if(score != wanho && (wanho[0]+wanho[1] < score[0]+score[1])) rank++;
        }

        return rank;
    }
}
