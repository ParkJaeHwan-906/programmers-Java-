package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 프로그래머스 Lv.3
// 입국심사
// https://school.programmers.co.kr/learn/courses/30/lessons/43238?language=java
public class no_43238 {
    static int n = 6;
    static int[] times = new int[] {7,10};
    static int result = 28;
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        no_43238 problem = new no_43238();
        System.out.println(problem.solution(n, times));
    }

    // times 요소의 최대값 1,000,000,000
    public long solution(int n, int[] times){
        // 1. 전체 시간의 범위를 구한다.
        // 1 이상 1,000,000,000 분 이하
        long left = 1;
        // 1,000,000,000 너무 큼 -> times 의 최대값을 기준으로 하자
        long right = 0;
        for(int i : times){
            right = Math.max(right, i);
        }
        right *= n;

        while(left <= right){
            // 중간값을 구한다.
            long mid = (left + right) / 2;
            // true : 범위를 더 좁혀도 됨
            boolean flag = binarySearch(mid, times, n);

            if(flag){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }

    public boolean binarySearch(long mid, int[] times, int n){
        long count = 0;


        for(int i : times){
            // 심사관이 해당 시간동안 통과시킬 수 있는 사람의 수
            count += (mid / i);
        }

        return count >= n;
    }
}

/*
    ⚠️ 파라메트릭 서치
    : 특정 값(최솟값, 최댓값)을 구하라는 문제를 결정문제(이분탐색)로 바꾸는 것

    문제의 조건 : 모든 사람이 심사를 받는데 걸리는 시간의 최솟값을 return 하여라
    => 전체 시간의 최솟값을 구하는 문제
    📌 전체 시간의 범위를 left = 1, right = (times 배열 요소 중 최댓값) * n
    전체 시간이 mid 일때, 각 심사관은 몇 명의 사람을 확인할 수 있는지 for 문으로 times[i]/mid 의 합계를 구한다.
    => 총 합계가 n 보다 크거나 같으면 조건을 만족하므로 전체 시간의 범위를 줄인다.
 */
