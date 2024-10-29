package Level_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// 프로그래머스 Lv.2
// H-Index
// https://school.programmers.co.kr/learn/courses/30/lessons/42747
public class no_42747 {
    /*
    n 편 중에 h 번 이상 인용된 논문이 h편 이상
    나머지 논문들이 h 번 이하로 인용
    ==> H-Index 이다.
     */
    public int solution(int[] citations){
        int answer = 0;

        int n = citations.length;

        // 내림차순으로 우선순위 큐
        PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> e2 - e1);
        for(int i : citations){
            pq.offer(i);
        }

        while(!pq.isEmpty()){
            int h = pq.poll();
            int count = 0;
            for(int i : citations){
                // h 번 이상 인용되었다면
                if(i >= h){
                    count++;
                }
            }

            // n 편 중에 h 번 이상 인용된 논문이 h편 이상
            //    나머지 논문들이 h 번 이하로 인용
            if(h <= count && n-count <= h){
                answer = h;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args){
        int[] citations = new int[] {5,6,7,8};
        no_42747 problem = new no_42747();
        System.out.println(problem.solution(citations));
    }
}
