package Level_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 프로그래머스 Lv.4
// 징검다리
// https://school.programmers.co.kr/learn/courses/30/lessons/43236
public class no_43236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int distance = 25;
        int[] rocks = new int[] {2, 14, 11, 21, 17};
        int n = 2;

        no_43236 problem = new no_43236();
        System.out.println(problem.solution(distance, rocks, n));
    }

    public int solution(int distance, int[] rocks, int n){
        long left = 0;
        long right = distance;
        // 바위를 위치순으로 정렬
        Arrays.sort(rocks);

        while(left <= right){
            long mid = (left + right) / 2;

            boolean flag = binarySearch(mid, rocks, n, distance);

            if(flag){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return (int) right;
    }

    public boolean binarySearch(long mid, int[] rocks, int n, int distance){
        // 바위를 제거한 횟수
        int count = 0;
        // 이전 위치로부터의 거리
        int preDist = 0;
        for(int i : rocks){
            if(i - preDist < mid){
                count++;
            }else{
                preDist = i;
            }

        }

        // 마지막 바위에서 목적지까지의 거리
        if(distance - preDist < mid){
            count++;
        }

        return count <= n;
    }
}
/*
    출발점에서 distance 만큼 떨어진 곳에 도착지가 있음
    사이사이 바위 존재 -> n개 제거 예정
    => 바위 거리 사이의 최대 값 (지점과 지점 사이의 거리)
    => ⚠️ 바위끼리가 아니라, 출발지에서 바위까지, 바위에서 목적지까지 거리도 포함

    mid : 임의로 돌을 제거한 돌들 간격의 최소거리
    => mid 값을 가지고 있는 탐색 시점에서, mid 값도가 작은 돌 간격의 값이 있어서는 안된다.
 */
