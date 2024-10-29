package Level_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 프로그래머스 Lv.1
// K 번째 수
// https://school.programmers.co.kr/learn/courses/30/lessons/42748
public class no_42748 {
    public int[] solution(int[] array, int[][] commands){
        int[] answer = new int[commands.length];

        for(int idx = 0; idx < commands.length; idx++) {
            int i = commands[idx][0];
            int j = commands[idx][1];
            int k = commands[idx][2];
            List<Integer> sortList = new ArrayList<>();
            for (int x = i; x <= j; x++) {
                sortList.add(array[x- 1]);
            }

            Collections.sort(sortList);

            answer[idx] = sortList.get(k-1);

        }
        return answer;
    }

    public String toString(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for(Integer i : list){
            sb.append(i+" ");
        }
        return sb.toString();
    }

    public static void main(String[] args){
        int[] array = new int[] {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = new int[][] {{2, 5, 3},{4, 4, 1},{1, 7, 3}};

        no_42748 problem = new no_42748();
        int[] answer = problem.solution(array,commands);

        Arrays.stream(answer).forEach(System.out::println);
    }
}
