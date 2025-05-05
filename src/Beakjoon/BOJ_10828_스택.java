package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_10828_스택 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        System.out.print(sb);
        br.close();
    }

    static StringTokenizer st;
    static void init() throws IOException {
        Stack<Integer> stack = new Stack<>();

        int command = Integer.parseInt(br.readLine().trim());
        while(command-- > 0) {
            st = new StringTokenizer(br.readLine().trim());

            String str = st.nextToken();
            int num;
            switch(str) {
                case "push" :
                    num = Integer.parseInt(st.nextToken());
                    stack.push(num);
                    continue;
                case "pop" :
                    sb.append(stack.isEmpty() ? -1 : stack.pop());
                    break;
                case "size" :
                    sb.append(stack.size());
                    break;
                case "empty" :
                    sb.append(stack.isEmpty() ? 1 : 0);
                    break;
                case "top" :
                    sb.append(stack.isEmpty() ? -1 : stack.peek());
                    break;
            }
            sb.append('\n');
        }
    }
}