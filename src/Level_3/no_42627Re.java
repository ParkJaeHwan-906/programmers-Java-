package Level_3;

import java.util.Arrays;
import java.util.PriorityQueue;

// 📌 재시도
// 프로그래머스 Lv.3
// 디스크 컨트롤러
// https://school.programmers.co.kr/learn/courses/30/lessons/42627
public class no_42627Re {
    public int solution(int[][] jobs){
        int answer = 0;

        // 1. jobs 를 정렬 (요청 시간 -> 수행 시간)    오름차순
        Arrays.sort(jobs, (e1,e2) -> {
            if(e1[0] == e2[0]) return e1[1] - e2[1];

            return e1[0] - e2[0];
        });

        // 프로세스 대기 큐
        PriorityQueue<int[]> process = new PriorityQueue<>((e1, e2) -> {return e1[1] - e2[1];});
        int idx = 0;
        int time = jobs[idx][0];
        process.add(jobs[idx++]);

        while(!process.isEmpty()){
            // 현재 대기큐에 들어가 있는 작업을 꺼내온다.
            int[] cur = process.poll();

            // 현재 작업이 완료되는 시간을 기록한다.
            time += cur[1];
            // 정답에 현재 작업이 완료된 시간 - 작업이 요청된 시간 -> 실제 작업이 완료되기까지 걸리는 시간
            answer += time - cur[0];

            // 주어진 프로세스의 범위를 벗어나지 않고, 이전 프로세스가 완료된 시간보다 작거나 같은 프로세스만 추가
            while(idx < jobs.length && jobs[idx][0] <= time){
                process.add(jobs[idx++]);
            }

            // 만약 현재 대기큐에 작업이 없다면
            if(process.isEmpty() && idx < jobs.length){
                time = jobs[idx][0];
                process.add(jobs[idx++]);
            }

        }


        return answer / jobs.length;
    }

    public static void main(String[] args){
        int[][] jobs = new int[][] {{0,3},{1,9},{2,6}};

        no_42627Re problem = new no_42627Re();

        System.out.println(problem.solution(jobs));
    }
}
