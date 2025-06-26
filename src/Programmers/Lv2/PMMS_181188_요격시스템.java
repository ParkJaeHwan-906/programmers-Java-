package Programmers.Lv2;

import java.util.*;

public class PMMS_181188_요격시스템 {
    public static void main(String[] args) {
        int[][] targets = {{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}};
        System.out.println(new PMMS_181188_요격시스템().solution(targets));
    }

    public int solution(int[][] targets) {
        int answer = 0;
        /*
            미사일을 최대한 겹치게 제거해야한다.
            e 를 기준으로 오름차순 정렬 -> 현재 원소의 e 값보다 작은 s 의 값을 찾으면 한번에 요격 가능
         */

        // 1. 각 원소의 e 값을 기준으로 오름차순 정렬 -> pq 사용
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        for(int[] arr : targets) pq.offer(arr);
        /*
            현재 원소의 e 값보다 작은 s 를 갖는 원소는 한번에 요격이 가능하다.

            개구간이므로 e 와 s 가 같은 경우 요격이 불가하다.
         */
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            while(!pq.isEmpty() && pq.peek()[0] < cur[1]) pq.poll();
            answer++;
        }

        return answer;
    }
}
/*
    미사일을 최소로 사용해 모든 미사일을 요격하려함
    폭격 미사일은 x 축에 평행한 직선 형태 모양  (s, e) 로 표현
    요격 미사일은 y 축에 평행으로 발사한다.

    💡 미사일의 구간은 개구간 -> s < _ < e
 */