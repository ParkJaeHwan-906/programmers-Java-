package Level_1;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 프로그래머스 Lv.1
// 카드 뭉치
// https://school.programmers.co.kr/learn/courses/30/lessons/159994?language=java
public class no_159994 {
    /*
    1. 원하는 카드 뭉치에서 카드를 순서대로 한장씩 사용
    2. 한 번 사용한 카드는 재사용 X
    3. 카드는 반드시 사용해야 함
    4. 기존에 주어진 카드 뭉치의 단어 순서 변화 X
     */
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int idx1 = 0;
        int idx2 = 0;

        for(String s : goal){
            if (idx1 < cards1.length && s.equals(cards1[idx1])) {
                idx1++;
            } else if (idx2 < cards2.length && s.equals(cards2[idx2])) {
                idx2++;
            } else {
                return "No";
            }
        }

        return "Yes";
    }

    public static void main(String[] args){
//        String[] cards1 = new String[] {"i", "water", "drink"};
        String[] cards1 = new String[] {"i", "drink", "water"};
//        String[] cards1 = new String[] {"a", "b", "c"};
        String[] cards2 = new String[] {"want", "to"};
//        String[] cards2 = new String[] {"d", "e", "f"};
        String[] goal = new String[] {"i", "want", "to", "drink", "water"};
//        String[] goal = new String[] {"a", "d", "f"};

        no_159994 problem = new no_159994();
        System.out.println(problem.solution(cards1, cards2, goal));
    }
}
