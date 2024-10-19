package Level_2;

import java.util.Stack;

// 프로그래머스 Lv.2
// 올바른 괄호
// https://school.programmers.co.kr/learn/courses/30/lessons/12909?language=java
public class no_12909 {
    boolean solution(String s){
        // 괄호의 짝이 맞으면 true, 맞지 않으면 false
        // stack 을 이용하여 접근
         Stack<Character> stack = new Stack<>();

         for(char c : s.toCharArray()){
             try{
                 switch (c){
                     case '(':
                         stack.push(c);
                         break;
                     default:
                         stack.pop();
                 }
             } catch(Exception e){  // 괄호의 순서가 잘못됨
                 return false;
             }
         }

        return stack.isEmpty();
    }

    public static void main(String[] args){
        String s = ")()(";

        no_12909 problem = new no_12909();
        System.out.println(problem.solution(s));
    }
}
