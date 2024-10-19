package Level_2;
import java.util.*;

// 프로그래머스 Lv.2
// 더 맵게
// https://school.programmers.co.kr/learn/courses/30/lessons/42626?language=java
public class no_42626 {
    public int solution(int[] scoville, int K){
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i : scoville){
            q.add(i);
        }

        int cnt = 0;
        while(q.peek() < K){
            // 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우 -1 리턴
            if(q.size() == 1) return -1;
            int a = q.poll();
            int b = q.poll();
            q.add(a + b*2);
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args){
        int[] scoville = new int[] {1, 2, 3, 9, 10, 12};
        int K = 7;
        no_42626 problem = new no_42626();
        System.out.println(problem.solution(scoville, K));
    }
}
