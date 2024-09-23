package Level_2;

import java.util.ArrayList;

// 프로그래머스 Lv.2
// 우박수열 정적분
// https://school.programmers.co.kr/learn/courses/30/lessons/134239?language=java
public class no_134239 {
    /*
    ⚠️ 콜라츠 추측
    1-1. 입력된 수가 짝수라면 2로 나눕니다.
    1-2. 입력된 수가 홀수라면 3을 곱하고 1을 더합니다.
    2.결과로 나온 수가 1보다 크다면 1번 작업을 반복합니다.
     */
    public double[] solution(int k, int[][] ranges){
        ArrayList<Double> result = new ArrayList<>();
        // 우박수열
        int[][] arr = makeArr(k);

        int n = arr.length;
        for(int i = 0; i < ranges.length; i++){
            int a = ranges[i][0];
            int b = n + ranges[i][1];

            // ⚠️ 범위의 시작 a 가 끝 보다 큰 경우 -1 을 반환
            if(a >= b){
                result.add(-1.0);
                continue;
            }
            result.add(calc(arr, a, b));
        }

        return result.stream().mapToDouble(i->i).toArray();
    }

    // 구간 면적 계산
    private double calc(int[][] arr, int a, int b){
        double result = 0;
        for(int i = a+1; i < b; i++ ){
            // 사다리꼴 넓이 공식 : (y1+y2) / 2 * ( x2 - x1 )
            result += (arr[i-1][1] + arr[i][1]) / 2.0;
        }
        return result;
    }

    // 콜라츠 추측 구현 (우박수열)
    private int[][] makeArr(int k){
        ArrayList<int[]> list = new ArrayList<>();
        int x = 0;
        list.add(new int[]{x,k});
        while(k != 1){
            if(k % 2 == 0){ // 입력된 수가 짝수 : 2로 나눈다.
                k = k/2;
            } else {    // 입력된 수가 홀수 : 3을 곱하고 1을 더한다.
                k = (k * 3) + 1;
            }
            list.add(new int[]{++x,k});
        }
        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args){
        int k = 5;
        // x 에 대한 범위 [a, b]
        int[][] ranges = new int[][] {{0,0},{0,-1},{2,-3},{3,-3}};

        no_134239 problem = new no_134239();
        double[] answer = problem.solution(k, ranges);

        for(double d : answer){
            System.out.print(d+" ");
        }
    }
}



