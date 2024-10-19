package Level_2;

import java.util.ArrayList;
import java.util.*;
// 프로그래머스 Lv.2
// 모음사전
// https://school.programmers.co.kr/learn/courses/30/lessons/84512
public class no_84512 {
    // 모음 A E I O U
    List<String> dic = new ArrayList<>();
    String[] bowel = new String[] {"A", "E", "I", "O", "U"};
    public int solution(String word){
        dfs("", 0);
        return dic.indexOf(word)+1;
    }

    // DFS
    public void dfs(String s, int depth){
        if(depth == 5) return;

        for (String word : bowel) {
            dic.add(s + word);
            dfs(s + word, depth + 1);
        }
    }

    public static void main(String[] args){
        String word = "AAAAE";

        no_84512 problem = new no_84512();
        System.out.println(problem.solution(word));
    }
}
