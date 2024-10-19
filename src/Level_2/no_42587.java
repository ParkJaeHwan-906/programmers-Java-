package Level_2;

import java.util.LinkedList;
import java.util.Queue;

// 프로그래머스 Lv.2
// 프로세스
// https://school.programmers.co.kr/learn/courses/30/lessons/42587?language=java
public class no_42587 {
    class Process {
        int idx;
        int priority;

        public Process(int idx, int priority){
            this.idx = idx;
            this.priority = priority;
        }
    }
    public int solution(int[] priorities, int location){
        Queue<Process> q = new LinkedList<>();
        for(int i = 0; i < priorities.length; i++){
            q.add(new Process(i, priorities[i]));
        }
        int max = findMax(q);
        int seq = 1;
        while(!q.isEmpty()){
            // 1. 대기중인 프로세스를 하나 꺼냄
            Process now = q.poll();

            // 우선순위를 비교한다.
            if(now.priority < max){ // 우선순위가 작다면 다시 대기 큐로 들어간다.
                q.add(now);
            }else { // 우선순위가 가장 높다.
                if(now.idx == location) break;  // 찾으려는 프로세스와 일치한다.
                max = findMax(q);
                seq++;
            }
        }
        return seq;
    }

    // 큐에 들어있는 가장 높은 우선순위를 찾음
    public int findMax(Queue<Process> q){
        int max = 0;
        // 임시 큐로 저장
        for(Process p : q) {
            max = Math.max(max, p.priority);
        }
        return max;
    }

    public static void main(String[] args){
        int[] priorities = new int[] {1, 1, 9, 1, 1, 1};
        int location = 0;

        no_42587 problem = new no_42587();
        System.out.println(problem.solution(priorities, location));
    }
}
