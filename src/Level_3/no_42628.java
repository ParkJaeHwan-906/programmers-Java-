package Level_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// 프로그래머스 Lv.3
// 이중우선순위큐
// https://school.programmers.co.kr/learn/courses/30/lessons/42628
public class no_42628 {
    /*
    명령어
    I 숫자 : 큐에 주어진 숫자를 삽입합니다.
    D 1 : 큐에서 최댓값을 삭제합니다.
    D -1 : 큐에서 최솟값을 삭제합니다.

    operations : 큐에 할 연산
     */
    public int[] solution(String[] operations){

        List<Integer> list = new ArrayList<>();
        // 명령어를 순회
        for(String command : operations){
//            System.out.println(command);
            String [] arr = command.split(" ");
            switch(arr[0]) {
                case "I" :
                    list.add(Integer.parseInt(arr[1]));
                    Collections.sort(list);
                    break;
                case "D" :
                    if(!list.isEmpty()) {
                        list.remove(arr[1].equals("-1") ? 0 : list.size() - 1);
                    }
                    break;
            }
//            System.out.println("list : "+ toStringList(list));
        }

        return list.size() == 0 ? new int[] {0,0} : new int[] {list.get(list.size()-1), list.get(0)};
    }

    public String toStringList(List<Integer> list){
        StringBuilder sb = new StringBuilder();
        for(Integer i : list){
            sb.append(i+" ");
        }
        return sb.toString();
    }

    public int[] solution2(String[] operations) {
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((e1, e2) ->  e2- e1);

        for(String command : operations){
            String[] arr = command.split(" ");

            switch(arr[0]) {
                case "I":
                    maxQ.add(Integer.parseInt(arr[1]));
                    minQ.add(Integer.parseInt(arr[1]));
                    break;
                case "D":
                    if(arr[1].equals("-1") && !minQ.isEmpty()){
                        maxQ.remove(minQ.poll());
                    }else if(arr[1].equals("1") && !maxQ.isEmpty()){
                        minQ.remove(maxQ.poll());
                    }
            }
        }

        if(maxQ.isEmpty() && minQ.isEmpty()) return new int[] {0,0};

        return new int[] {maxQ.poll(), minQ.poll()};
    }

    public static void main(String[] args){
        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
//        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};

        no_42628 problem = new no_42628();
//        int[] answer = problem.solution(operations);
        int[] answer = problem.solution2(operations);

        for(int i : answer){
            System.out.print(i + " ");
        }
    }
}
