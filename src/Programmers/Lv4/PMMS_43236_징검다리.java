package Programmers.Lv4;

import java.util.*;

public class PMMS_43236_징검다리 {
    public static void main(String[] args) {
        int distance = 25;
        int[] rocks = {2,14,11,21,17};
        int n = 2;

        System.out.println(new PMMS_43236_징검다리().solution(distance, rocks, n));
    }

    public int solution(int distance, int[] rocks, int n) {
        // 탐색 범위 설정
        int l = 0, r = distance;

        Arrays.sort(rocks);     // 오름 차순 정렬 -> 위치 순 정렬

        /*
            원소가 삽입될 위치가 아닌,
            최적해를 찾는 것
            Lower Bound / Uppder Bound 개념보단 mid 값 그 자체가 정답
         */
        // mid 값 자체가 정답이 되기 때문에 <= 연산 수행
        while(l <= r) {
            int mid = l + (r-l)/2;

            // 만족하는지 확인
            // mid 값을 찾는것이므로, mid 값을 제외하고 탐색
            if(removeRocks(rocks, mid, n, distance)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return r;
    }

    boolean removeRocks(int[] rocks, int mid, int n, int distance) {
        // mid 값을 돌 사이의 거리 설정
        int prev = 0;   // 이전 돌의 위치 ( 시작점은 0 )
        int removes = 0;    // 제거한 돌의 개수
        
        /*
            돌과 돌 사이의 거리를 확인하며, mid 값과 비교
         */
        for(int loc : rocks) {
            // 돌 사이의 거리가 목표 거리보다 좁다면, 제거하여 거리를 넓힘
            if(loc-prev < mid) {
                removes++;
            } else {
                prev = loc;
            }
        }
        
        // 마지막 돌 위치 비교
        if(distance-prev < mid) removes++;
        return removes <= n;
    }
}

/*
    출발지점으로부터 distance 만큼 떨어진 곳에 도착지점이 있다.
    사이에 바위들이 있다. -> 그 중 몇개를 제거하려한다.

 */