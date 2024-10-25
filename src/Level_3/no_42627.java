package Level_3;

import jdk.jshell.spi.SPIResolutionException;

import java.util.*;

// 프로그래머스 Lv.3
// 디스크 컨트롤러
// https://school.programmers.co.kr/learn/courses/30/lessons/42627
public class no_42627 {
    // jobs 의 길이는 1 이상 500 이하
    // [작업이 요청되는 시점, 작업의 소요 시간]
    public int solution(int[][] jobs){

        // 들어오는 순으로 배열 정렬, 동시간대에 들어온다면 작업시간이 짧은 것이 우선
        Arrays.sort(jobs, (e1, e2) -> {
            if(e1[0] == e2[0]) return e1[1] - e2[1];
            return e1[0] - e2[0];
        });

        PriorityQueue<int[]> processQ = new PriorityQueue<>((e1, e2) -> e1[1]-e2[1]);

        processQ.add(jobs[0]);
        int end = jobs[0][0];
        int sum = 0;
        int idx = 1;

        while(!processQ.isEmpty()){
            int[] cur = processQ.poll();
            end += cur[1];
            sum += end - cur[0];

            while(idx < jobs.length && jobs[idx][0] <= end){
                processQ.add(jobs[idx++]);
            }

            if(idx < jobs.length && processQ.isEmpty()){
                end = jobs[idx][0];
                processQ.add(jobs[idx++]);
            }
        }
        return sum/ jobs.length;
    }

    public static void main(String[] args){
        int[][] jobs = new int[][] {{0,3}, {2,6}, {1,9}};

        no_42627 problem = new no_42627();
        System.out.println(problem.solution(jobs));
    }
}
