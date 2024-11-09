package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

// 프로그래머스 Lv.3
// 징검다리 건너기
// https://school.programmers.co.kr/learn/courses/30/lessons/64062
public class no_64062 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int[] stones = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            stones[i] = Integer.parseInt(arr[i]);
        }

        int k = Integer.parseInt(br.readLine());

        no_64062 problem = new no_64062();
        System.out.println(problem.solution(stones, k));
    }

    /*
    친구들의 수는 무제한이다.
    stones 배열의 크기는 1 이상 20만 이하다.
    배열의 각 원소들의 값은 1 이상 2억이하이다.
    k 는 1 이상 stones 길이 이하인 자연수이다.
     */


    /* ⚠️ 풀이
    출처 : https://20240228.tistory.com/372

    전형적인 이분탐색 문제

    이분탐색 시작 조건
    start = 0, end = 200,000,000 (문제의 최대 값)
    -> 모든 징검다리가 end 와 동일하면 최대 end 만큼 징검다리를 건널 수 있음

     */
    public int solution(int[] stones, int k) {
        int start = 0;
        int end   = 200000000;
        while(start < end){
            int mid = (start + end) / 2;
            boolean result = calculate(stones, k, mid);
            if(result){
                start = mid + 1;
            }
            else end = mid;
        }
        return start;
    }

    /*
    📌 현재 인원이 징검다리를 건널 수 있는지 확인하는 메서드
     */
    public static boolean calculate(int [] stones, int k, int mid){
        int cnt = 0;
        for(int i = 0; i < stones.length; i++){
            if(stones[i] - mid <= 0){
                cnt++;
            }
            else cnt = 0;
            if(cnt == k){
                return false;
            }
        }
        return true;
    }
}
