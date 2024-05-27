package Level_0;

import java.util.*;
// 프로그래머스 Lv.0
// 배열의 유사도
// https://school.programmers.co.kr/learn/courses/30/lessons/120903
public class no_120903 {
    public int solution(String[] s1, String[] s2){
        int answer = 0;
        List<String> list = Arrays.asList(s1);

        for(String s : s2){
            answer = list.contains(s) ? answer+= 1 : answer;
        }
        return answer;
    }

    public static void main(String[] args){
        String[] s1 = {"a", "b", "c"};
        String[] s2 = {"com", "b", "d", "p", "c"};
        no_120903 problem = new no_120903();
        int result = problem.solution(s1, s2);
        System.out.println(result);
    }
}
