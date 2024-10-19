package Level_2;

import java.util.*;
import java.util.stream.Collectors;

// 프로그래머스 Lv.2
// 기능개발
// https://school.programmers.co.kr/learn/courses/30/lessons/42586?language=java
public class no_42586 {
    public int[] solution(int[] progresses, int[] speeds){
        int max = 0;
        int cnt = 0;
        // 1.업무 완성까지 걸리는 기간 구하기
        List<Integer> answer = new ArrayList<>();
        for(int i = 0; i < progresses.length; i++){
            int remain = 100 - progresses[i];
            int day = remain%speeds[i] == 0 ? remain/speeds[i] : remain/speeds[i]+1;

            if(max != 0 && (max < day)){
                answer.add(cnt);
                cnt= 1;
                max = day;
                continue;
            }

            max = Math.max(day, max);
            cnt++;
        }
        answer.add(cnt);
        // ⚠️ stream() 을 쓰는 것보다는 for 문이 빠름
//        return answer.stream().mapToInt(i->i).toArray();

        int[] result = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++){
            result[i] = answer.get(i);
        }
        return result;
    }

    public static void main(String[] agrs){
        int[] progresses = new int[] {93, 30, 55};
        int[] speeds = new int[] {1, 30, 5};

        no_42586 problem = new no_42586();
        int[] answer = problem.solution(progresses, speeds);

        Arrays.stream(answer).forEach(e -> {
            System.out.print(e+" ");
        });
    }
}
