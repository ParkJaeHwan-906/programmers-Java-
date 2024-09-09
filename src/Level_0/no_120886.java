package Level_0;

import java.util.Arrays;
// 프로그래머스 Level 0
// A로 B 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/120886
public class no_120886 {
    public int solution(String before, String after) {
        before = sort(before);
        after = sort(after);

        return before.equals(after) ? 1 : 0;
    }
    public String sort(String s){
        String[] arr = s.split("");
        Arrays.sort(arr);
        return String.join("", arr);
    }

    public static void main(String[] args) {
        no_120886 problem = new no_120886();
        System.out.println(problem.solution("olleh", "hello"));
    }
}
