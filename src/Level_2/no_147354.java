package Level_2;

import java.util.ArrayList;
import java.util.Collections;

// 프로그래머스 Lv.2
// 테이블 해시 함수
// https://school.programmers.co.kr/learn/courses/30/lessons/147354
public class no_147354 {
    class Tuple implements Comparable<Tuple>{
        int[] arr;
        int standardIdx;

        public Tuple(int[] arr, int standardIdx){
            this.arr = arr;
            this.standardIdx = standardIdx;
        }

        // col (standardIdx) 을 기준으로 오름차순 정렬, 같을 경우 첫 번째 값을 기준으로 내림차순 정렬
        @Override
        public int compareTo(Tuple tuple){
            if(this.arr[standardIdx-1] == tuple.arr[standardIdx-1]){
                return tuple.arr[0] - this.arr[0];
            }
            return this.arr[standardIdx-1] - tuple.arr[standardIdx-1];
        }
    }

    public int solution(int[][] data, int col, int row_begin, int row_end){
        int answer = 0;

        ArrayList<Tuple> list = new ArrayList<>();

        for(int[] arr : data){
            list.add(new Tuple(arr, col));
        }

        Collections.sort(list);

        for(int i=row_begin; i<=row_end; i++){
            int sum = 0;
            for(int a : list.get(i-1).arr){
                sum += (a%i);
            }
            answer = answer ^ sum;
        }
        return answer;
    }

    public static void main(String[] args){
        no_147354 problem = new no_147354();
        int[][] data = new int[][] {{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
        int col = 2;
        int row_begin = 2;
        int row_end = 3;
        System.out.println(problem.solution(data, col, row_begin, row_end));
    }
}
