package Ssafy.Algorithm.Stack;

import java.util.*;
import java.io.*;

public class Parentheses {
    static BufferedReader br;
    static BufferedWriter bw;
    static String s;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        init(); // 입력 받기
        bw.write(String.valueOf(ckeckParenthese()));
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        s = br.readLine().trim();
        br.close();
    }

    private static boolean ckeckParenthese() {
        Stack<Character> st = new Stack<>();

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if(c == ')') {  // 닫는 괄호가 나오면, Stack 에 있는 여는 괄호를 꺼내온다.
                if(st.isEmpty()) return false;  // 꺼낼 괄호가 없다면 -> 올바른 괄호가 아님
                st.pop();
            } else {
                st.push(c); // 여는 괄호는 Stack 에 넣는다.
            }
        }

        return st.isEmpty();    // 스택에 값이 남아있다면 짝이 맞지 않은것
    }
}

/*
프로그래머스
https://school.programmers.co.kr/learn/courses/30/lessons/12909

괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다. 예를 들어

"()()" 또는 "(())()" 는 올바른 괄호입니다.
")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.
'(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.

제한사항
문자열 s의 길이 : 100,000 이하의 자연수
문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.

입출력
    s	    answer
"()()"	    true
"(())()"	true
")()("	    false
"(()("	    false
 */