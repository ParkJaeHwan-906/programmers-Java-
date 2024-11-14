package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 프로그래머스 Lv.2
// 큰 수 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/42883
public class no_42883 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String number = br.readLine();
        int k = Integer.parseInt(br.readLine());

        no_42883 problem = new no_42883();
        System.out.println(problem.solution(number, k));
    }

    // k 개의 수를 제거하여 큰 수를 만든다.
    public String solution(String number, int k) {
        Stack<Character> st = new Stack<>();
        // 목표 길이
        int result = number.length() - k;

        for(char c : number.toCharArray()){
            // 스택이 비어있지 않고, 스택의 값이 현재 값보다 작고, 뺄 수가 아직 남아있을 때
            while (!st.isEmpty() && st.peek() < c && k > 0){
                st.pop();
                k--;
            }
            st.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.insert(0, st.pop());
        }
        String answer = sb.toString();
        return answer.substring(0, result);
    }
}

