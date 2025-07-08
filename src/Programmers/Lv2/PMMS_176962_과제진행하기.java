package Programmers.Lv2;

import Ssafy.Algorithm.LinkedList.IStack;

import java.util.*;

public class PMMS_176962_과제진행하기 {
    public static void main(String[] args) {
//        String[][] plans = {{"korean", "11:40", "30"},
//                {"english", "12:10", "20"},
//                {"math", "12:30", "40"}};

//        String[][] plans = {{"aaa", "12:00", "20"},
//                {"bbb", "12:10", "30"},
//                {"ccc", "12:40", "10"}};

//        String[][] plans = {{"science", "12:40", "50"},
//                {"music", "12:20", "40"},
//                {"computer", "12:30", "100"},
//                {"history", "14:00", "30"}};

        String[][] plans = {{ "A","00:00","60" },
                {"B","00:10","60"},
                {"C","00:20","60"},
                {"D","02:20","60"},
                {"E","03:20","10"},
                {"F","03:40","20"},
                {"G","04:40","60"}};

        System.out.println(Arrays.toString(new PMMS_176962_과제진행하기().solution(plans)));
    }

    // 과제
    class Subject implements Comparable<Subject>{
        String name;
        int hour;
        int minute;
        int remain;

        public Subject(String name, int hour, int minute, int remain) {
            this.name = name;
            this.hour = hour;
            this.minute = minute;
            this.remain = remain;
        }

        @Override
        public int compareTo(Subject o) {
            if(this.hour == o.hour) return this.minute - o.minute;

            return this.hour - o.hour;
        }

//        @Override
//        public String toString() {
//            return name + "->" + hour + ":" + minute + "(" + remain + ")";
//        }
    }

    PriorityQueue<Subject> flow;
    Stack<Subject> wait;
    List<String> result;
    public String[] solution(String[][] plans) {
        flow = new PriorityQueue<>();
        wait = new Stack<>();
        result = new ArrayList<>();
        
        // 시작 시간 순으로 정렬
        for(String[] subject : plans) {
            String name = subject[0];
            String[] time = subject[1].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            int remain = Integer.parseInt(subject[2]);

            flow.offer(new Subject(name, hour, minute, remain));
        }
        // 확인
        PriorityQueue<Subject> tmp = new PriorityQueue<>(flow);
        while (!tmp.isEmpty()) {
            System.out.println(tmp.poll());
        }
        System.out.println();

        // 시작 시간 설정
        int curH = flow.peek().hour;
        int curM = flow.peek().minute;

        // flow 에 있는 과목들 우선으로 처리
        // 시간이 비면 점프하긴 해야할 듯
        while(!flow.isEmpty() || !wait.isEmpty()) {
//            System.out.printf("현시간 : %d : %d\n", curH, curM);

            Subject cur = null;
            /*
                과제는 시작할 시간이 되었을 때 시작한다.
                -> 이때 진행 중이던 과제가 있었다면, 중지하고 새로운 과제를 한다.
             */
            // 시작할 시간이 된 과제를 우선적으로 실행
            if(!flow.isEmpty() && flow.peek().hour == curH && flow.peek().minute == curM) {
                cur = flow.poll();
//                System.out.println("현재 작업 : " + cur.toString());
                // 예상 종료 시간을 구한다.
                int finishH = curH + (curM+cur.remain) / 60;
                int finishM = (curM+cur.remain) % 60;
                int finishTotal = finishH*60+finishM;
//                System.out.println("예상 종료 시간 : " + finishH + " : " + finishM);
                // 1. 다음 과목 전에 다 끝낼 수 있는 경우
                // 2. 다음 과목 전에 다 끝낼 수 없는 경우 -> 대기
                if(!flow.isEmpty() && flow.peek().hour*60 + flow.peek().minute < finishTotal) {
                    cur.hour = flow.peek().hour;
                    cur.minute = flow.peek().minute;
                    // 실행한 시간만큼 남은 시간에서 빼주기
                    cur.remain = cur.remain - ((cur.hour*60+cur.minute)-(curH*60+curM));
                    wait.add(cur);

                    curH = cur.hour;
                    curM = cur.minute;
                } else {    // 다 끝나는 경우
                    curH = finishH;
                    curM = finishM;
//                    System.out.println(cur.name);
                    result.add(cur.name);
                }
            }
            /*
                진행 중인 과제가 끝났을 때, 대기 중인 과제를 처리한다.
                -> 가장 최근에 멈춰둔 과제부터 시작한다.
             */
            // 새로 시작할 과제가 없거나, 현재 진행할 과제가 없는 경우
            else {
                // 대기 중인 과제가 없음
                if(wait.isEmpty()) {
                   curH = flow.peek().hour;
                   curM = flow.peek().minute;
                   continue;
                }

//                System.out.println("대기!!!");
                cur = wait.pop();
//                System.out.println("현재 작업 : " + cur.toString());
                // 예상 종료 시간을 구한다.
                int finishH = curH + (curM+cur.remain) / 60;
                int finishM = (curM+cur.remain) % 60;
                int finishTotal = finishH*60+finishM;
//                System.out.println("예상 종료 시간 : " + finishH + " : " + finishM);
                // 1. 다음 과목 전에 다 끝낼 수 있는 경우
                // 2. 다음 과목 전에 다 끝낼 수 없는 경우 -> 대기
                if(!flow.isEmpty() && flow.peek().hour*60 + flow.peek().minute < finishTotal) {
                    cur.hour = flow.peek().hour;
                    cur.minute = flow.peek().minute;
                    // 예상 종료 시간에서, 남은 시간 구하기
                    cur.remain = ((finishH*60+finishM)-(cur.hour*60+cur.minute));
//                    System.out.println("다시 저장 : " + cur.toString());
                    wait.add(cur);

                    curH = cur.hour;
                    curM = cur.minute;
                } else {    // 다 끝나는 경우
                    curH = finishH;
                    curM = finishM;
//                    System.out.println(cur.name);
                    result.add(cur.name);
                }
            }
//            System.out.println("flow");
//            for(Subject s : flow) System.out.println(s.toString());
//            System.out.println("wait");
//            for(Subject s : wait) System.out.println(s.toString());
//            System.out.println();
        }

        return listToArr();
    }

    String[] listToArr() {
        String[] arr = new String[result.size()];
        for(int i=0; i<arr.length; i++) arr[i] = result.get(i);
        return arr;
    }
}
/*
    과제는 시작하기로 한 시각이 되면 시작한다.
    새로운 과제를 끝냈을 때, 잠시 멈춘 과제가 있다면, 멈춘 과제를 이어서 진행한다.
    멈춘 과제가 여러 개일 경우, 가장 최근에 멈춘 과제부터 시작한다.
 */
