package Level_2;
import java.util.*;

// 프로그래머스 Lv.2
// 의상
// https://school.programmers.co.kr/learn/courses/30/lessons/42578
public class no_42578 {
    public int solution(String[][] clothes){
        // 카테고리별 분류
        Map<String, Integer> map = new HashMap<>();
        for(String[] s : clothes){
            map.put(s[1], map.getOrDefault(s[1], 0)+1);
        }


        int answer = 1;
        for(String s : map.keySet()){
            answer *= (map.get(s)+1);
        }

        return answer-1;
    }

    public static void main(String[] args){
        String[][] clothes = new String[][]
                {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        no_42578 problem = new no_42578();
        System.out.println(problem.solution(clothes));
    }
}
