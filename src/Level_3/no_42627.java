package Level_3;

import java.util.*;

// 프로그래머스 Lv.3
// 디스크 컨트롤러
// https://school.programmers.co.kr/learn/courses/30/lessons/42627
public class no_42627 {
    // jobs 의 길이는 1 이상 500 이하
    // [작업이 요청되는 시점, 작업의 소요 시간]
//    public int solution(int[][] jobs){
//        int answer = 0;
//
//        // 들어오는 순으로 배열 정렬, 동시간대에 들어온다면 작업시간이 짧은 것이 우선
//        Arrays.sort(jobs, (e1, e2) -> {
//            if(e1[0] == e2[0]) return e1[1] - e2[1];
//            return e1[0] - e2[0];
//        });
//
//        int sec = 0;
//        int idx = 0;
//        int progress = 0;
//
//        PriorityQueue<Integer> waitQ = new PriorityQueue<>();
//        Queue<Integer> processQ = new LinkedList<>();
//
//        while(sec < 500*1000){
//            if(processQ.isEmpty()){    //실행중인 업무가 없을 때
//                try {
//                    if (waitQ.isEmpty()) {    // 대기큐에 업무가 없을 때
//                        processQ.add(jobs[idx++][1]);   // 들어오는 업무 바로 실행
//                    } else { // 대기중인 업무가 있을 때
//                        processQ.add(waitQ.poll()); // 대기큐의 업무 실행
//                    }
//                    progress = 0;
//                }catch(Exception e){}
//            }else{  // 실행중인 업무가 있을 때
//                try {
//                    if (progress < processQ.peek()) {    // 업무가 실행중일 때
//                        if (sec >= jobs[idx][0]) {    // 업무가 들어오는 시간인 경우
//                            waitQ.add(jobs[idx++][1]);  // 대기큐에 업무 저장
//                        }
//                    } else {  // 실행중인 업무가 종료되었을 때
//                        processQ.poll();
//                        answer += sec;
//                        processQ.add(waitQ.poll());
//                        progress = 0;
//                    }
//                } catch(Exception e){}
//
//
//                if(processQ.isEmpty() && waitQ.isEmpty()) break;
//            }
//            ++progress;
//            ++sec;
//        }
//
//
//        return (answer) / jobs.length;
//    }
    public int solution(int[][] jobs){
        int answer = 0;

        // 들어오는 순으로 배열 정렬, 동시간대에 들어온다면 작업시간이 짧은 것이 우선
        Arrays.sort(jobs, (e1, e2) -> {
            if(e1[0] == e2[0]) return e1[1] - e2[1];
            return e1[0] - e2[0];
        });

        int sec = 0;
        int idx = 0;
        int progress = 0;

        PriorityQueue<Process> waitQ = new PriorityQueue<>();
        Queue<Process> processQ = new LinkedList<>();

        while(sec < 500*1000){
            if(processQ.isEmpty()){    //실행중인 업무가 없을 때
                try {
                    if (waitQ.isEmpty()) {    // 대기큐에 업무가 없을 때
                        processQ.add(new Process(jobs[idx][0],jobs[idx++][1]));   // 들어오는 업무 바로 실행
                    } else { // 대기중인 업무가 있을 때
                        processQ.add(waitQ.poll()); // 대기큐의 업무 실행
                    }
                    progress = 0;
                }catch(Exception e){}
            }else{  // 실행중인 업무가 있을 때
                try {
                    if (progress < processQ.peek().time) {    // 업무가 실행중일 때
                        if (sec >= jobs[idx][0]) {    // 업무가 들어오는 시간인 경우
                            waitQ.add(new Process(jobs[idx][0],jobs[idx++][1]));  // 대기큐에 업무 저장
                        }
                    } else {  // 실행중인 업무가 종료되었을 때
                        Process p = processQ.poll();
                        answer += sec;
                        processQ.add(waitQ.poll());
                        progress = 0;
                    }
                } catch(Exception e){}


                if(processQ.isEmpty() && waitQ.isEmpty()) break;
            }
            ++progress;
            ++sec;
        }


        return (answer) / jobs.length;
    }

    class Process{
        int enter;
        int time;

        public Process(int enter, int time){
            this.enter = enter;
            this.time = time;
        }
    }

    public static void main(String[] args){
        int[][] jobs = new int[][] {{0,3}, {2,6}, {1,9}};

        no_42627 problem = new no_42627();
        System.out.println(problem.solution(jobs));
    }
}
