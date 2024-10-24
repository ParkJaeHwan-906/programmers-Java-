package Level_2;

import java.util.PriorityQueue;

// 프로그래머스 Lv.2
// 더 맵게
// https://school.programmers.co.kr/learn/courses/30/lessons/42626
public class no_42626_2 {
    // 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
    // 모든 스코빌 지수가 K 이상이 될 때 까지
    public int solution(int[] scoville, int K){
        int answer = 0;

        // 오름차순으로 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : scoville){
            pq.add(i);
        }

        while(true){
            // 가장 안매운 음식이 K를 넘을 경우
            if(pq.peek() >= K) break;

            // 더 이상 만들 수 없을 때
            if(pq.size() == 1) return -1;

            pq.add(pq.poll() + pq.poll()*2);
            ++answer;

        }


        return answer;
    }

    public static void main(String[] args){
        int[] scoville = new int[] {1,2,3,9,10,12};
        int K = 7;
        // result : 2

        no_42626_2 problem = new no_42626_2();
        System.out.println(problem.solution(scoville, K));
    }
}
