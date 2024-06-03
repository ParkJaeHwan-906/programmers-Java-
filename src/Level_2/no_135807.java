package Level_2;

import java.util.*;
// 프로그래머스 Lv.2
// 숫자 카드 나누기
// https://school.programmers.co.kr/learn/courses/30/lessons/135807
public class no_135807 {
    public int solution(int[] arrayA, int[] arrayB){
        /*
        A 배열을 모두 나누고, B 배열을 하나도 나눌 수 없거나
        B 배열을 모두 나누고, A 배열을 하나도 나눌 수 없는
        가장 큰 양의 정수 a 를 구해라
         */
        int answer = 0;
        Arrays.sort(arrayA);
        int gcdA = arrayA[0];
        for(int i = 1; i < arrayA.length; i++){
            gcdA = gcd(gcdA, arrayA[i]);
        }
        boolean A = true;
        for(int i : arrayB){
            if(i % gcdA == 0){
                A = false;
                break;
            }
        }
        Arrays.sort(arrayB);
        int gcdB = arrayB[0];
        for(int i = 1; i < arrayB.length; i++){
            gcdB = gcd(gcdB, arrayB[i]);
        }
        boolean B = true;
        for(int i : arrayA){
            if(i % gcdB == 0){
                B = false;
                break;
            }
        }

        if(A && B){
            answer = gcdA > gcdB ? gcdA : gcdB;
        }else if(A){
            answer = gcdA;
        }else if(B){
            answer = gcdB;
        }
        System.out.println(gcdA);
        System.out.println(gcdB);
        return answer;
    }

    public int gcd(int a, int b){
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        while( min > 0) {
            int r = max % min;
            max = min;
            min = r;
        }
        return max;
    }

    public static void main(String[] args){
        int[] arrayA = {10, 20};
        int[] arrayB = {5, 17};
        no_135807 problem = new no_135807();
        int i = problem.solution(arrayA, arrayB);
        System.out.println(i);
    }
}
