package Level_2;

import java.util.*;

// 프로그래머스 Lv.2
// 시소 짝궁
// https://school.programmers.co.kr/learn/courses/30/lessons/152996
public class no_152996 {
    public long solution(int[] weights){
        /*
        시소가 하나 있다. 좌석은 2, 3, 4 지점에 하나씩 있다.
        시소를 두 명이 마주보고 탔을 때, 균형이 맞는 경우
        ( 탑승한 사함의 무게와 시소 축과 좌석 간 거리의 곱이 같은 경우 )
         */

        /*
        ⚠️ 시간 초과
        solution 두 수의 최소 공배수를 찾아
        나눈 값이 2 <= n <= 4 이면 true
         */

        /*
        나올 수 있는 경우
        2 : 2
        2 : 3
        2 : 4
        3 : 3
        3 : 4
        즉 비율을
        1 : 1
        2 : 3
        1 : 2
        3 : 4
        가 존재한다.
         */
        long answer = 0;
        Arrays.sort(weights);
        Map<Double, Integer> map = new HashMap<>();
        for(int i : weights){
            double a = (i * 1);
            double b = (i * 2) / 3;
            double c = (i * 1) / 2;
            double d = (i* 3) / 4;
            if(map.containsKey(a)) answer += map.get(a);
            if(map.containsKey(b)) answer += map.get(b);
            if(map.containsKey(c)) answer += map.get(c);
            if(map.containsKey(d)) answer += map.get(d);

            map.put(a, map.getOrDefault(a, 0)+1);
        }
        System.out.println(map);
        return answer;

    }
/*
    private int gcd(int a, int b){
        int r = 0;
        while(b > 0){
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    private  int lcm(int r, int a, int b){
        return a * b / r;
    }
*/
    public static void main(String[] args) {
        no_152996 problem = new no_152996();
        int[] weight = {100, 180, 360, 100, 270};
        long result = problem.solution(weight);
        System.out.println(result);
    }
}
