package Ssafy.Algorithm.Stack;

import java.util.*;

public class Calc {
    public static void main(String[] args) {
        String s = "6528-*2/+";

        Stack<Integer> st = new Stack<>();
        for(char c : s.toCharArray()) {
            int num1;
            int num2;
            switch(c) {
                case '+' :
                    if(isEmpty(st)) return;
                    num1 = st.pop();
                    num2 = st.pop();

                    st.push(num2+num1);
                    break;
                case '-' :
                    if(isEmpty(st)) return;
                    num1 = st.pop();
                    num2 = st.pop();

                    st.push(num2-num1);
                    break;
                case '*':
                    if(isEmpty(st)) return;
                    num1 = st.pop();
                    num2 = st.pop();

                    st.push(num2*num1);
                    break;
                case '/':
                    if(isEmpty(st)) return;
                    num1 = st.pop();
                    num2 = st.pop();

                    st.push(num2/num1);
                    break;
                case '%':
                    if(isEmpty(st)) return;
                    num1 = st.pop();
                    num2 = st.pop();

                    st.push(num2%num1);
                    break;
                default:
                    st.push((c-'0'));
            }
        }

        if(st.size() > 1) System.err.println("수식이 잘못되었습니다.");
        System.out.println(st.pop());
    }

    private static boolean isEmpty(Stack<Integer>st) {
        if(st.isEmpty()) {
            System.err.println("수식이 잘못되었습니다.");
            return true;
        }
        return false;
    }
}
