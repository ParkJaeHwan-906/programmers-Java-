package Level_2;

import java.util.Arrays;

// 프로그래머스 Lv.2
// 전화번호 목록
// https://school.programmers.co.kr/learn/courses/30/lessons/42577?language=java
public class no_42577 {
    public boolean solution(String[] phone_book){
        Arrays.sort(phone_book);

        for(int i = 1; i < phone_book.length; i++){
            if(phone_book[i].startsWith(phone_book[i-1])) return false;
        }

        return true;
    }

    public static void main(String[] args){
        String[] phone_book = new String[] {"119", "97674223", "1195524421"};
        no_42577 problem = new no_42577();
        System.out.println(problem.solution(phone_book));
    }
}
