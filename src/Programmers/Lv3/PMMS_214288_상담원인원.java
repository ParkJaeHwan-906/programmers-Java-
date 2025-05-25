package Programmers.Lv3;

import java.util.*;
import java.io.*;

public class PMMS_214288_상담원인원 {
    public static void main(String[] args) {
        int k = 3;
        int n = 5;
        int[][] reqs = {
                {10,60,1},
                {15,100,3},
                {20,30,1},
                {30,50,3},
                {50,40,1},
                {60,30,2},
                {65,30,1},
                {70,100,2}
        };
        System.out.println(new PMMS_214288_상담원인원().solution(k, n, reqs));
    }

    // k : 상담의 유형 -> 최대 5개 -> Queue 로 관리
    // n : 멘토의 수
    int mentoCnt;       // 멘토의 수
    int counselCnt;     // 상담 유형의 수
    int reqCnt;
    List<int[]>[] waitList; // [요청 시간, 상담 시간]
    int minTotalWaitTime;     // 최소 대기 시간
    public int solution(int k, int n, int[][] reqs) {
        minTotalWaitTime = Integer.MAX_VALUE;
        reqCnt = reqs.length;
        mentoCnt = n;
        counselCnt = k;
        waitList = new ArrayList[counselCnt+1];
        for(int i=0; i<counselCnt+1; i++) {
            waitList[i] = new ArrayList<int []>();
        }

        // 요청 시간을 기준으로 오름 차순 정렬되어 있음
        for(int[] req : reqs) {
            int reqTime = req[0];
            int duringTime = req[1];
            int counselIdx = req[2];

            waitList[counselIdx].add(new int[] {reqTime, duringTime});
        }
        // 각 멘토는 유형별로 1명씩은 있어야한다.
        int[] mentoComb = new int[counselCnt+1];
        Arrays.fill(mentoComb, 1);
        getAllMentoComb(1,0, mentoComb);

        return minTotalWaitTime;
    }

    // 멘토의 조합
    void getAllMentoComb(int counselIdx, int mentoIdx, int[] mentoComb) {
        if(mentoIdx == mentoCnt-counselCnt) {  // 모든 멘토의 조합을 완성
            // 시간 구하기
            System.out.println(Arrays.toString(mentoComb));
            getTotalWaitTime(mentoComb);
            return;
        }

        // 멘토 조합 구할 수 있음

        // 현재 멘토가 가질 수 있는 상담 유형
        for(int i=counselIdx; i<counselCnt+1; i++) {
            mentoComb[i]++;
            getAllMentoComb(i, mentoIdx+1, mentoComb);
            mentoComb[i]--;
        }
    }

    void getTotalWaitTime(int[] mentoComb) {
        int totalWaitTime = 0;

        // 상담 유형별로 처리하기
        for(int counselIdx=1; counselIdx < counselCnt+1; counselIdx++) {
            // 배정된 멘토가 없는데, 대기 인원이 있는경우 말이 안됨
            if(mentoComb[counselIdx] == 0 && waitList[counselIdx].size() > 0) return;

            // 멘토를 배정
            PriorityQueue<Integer> pq = new PriorityQueue<>();      // 끝나는 시간을 기록

            // 요청을 순서대로 처리하기
            // 들어온 순으로 처리해야하기에, 끝나는 시간이 가장 빠른 멘토에게 할당
            for(int[] req : waitList[counselIdx]) {
                if(pq.size() < mentoComb[counselIdx]) { // 상담중이지 않은 멘토가 있다면 바로 할당
                    pq.offer(req[0]+req[1]);
                    continue;
                }

                // 상담중이거나, 이미 상담이 끝난 상태라면
                int lastTime = pq.poll();       // 멘토 중 가장 빨리 끝나는 시간
                if(lastTime > req[0]) { // 기다려야한다면
                    totalWaitTime += (lastTime - req[0]);
                    pq.offer(lastTime+req[1]);
                } else {
                    pq.offer(req[0]+req[1]);
                }

                // 가지치기
                if(totalWaitTime >= minTotalWaitTime) return;
            }
        }


        minTotalWaitTime = Math.min(minTotalWaitTime, totalWaitTime);
    }
}
