package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 스택 수열
// https://www.acmicpc.net/problem/1874
public class no_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] targetArr = new int[n];
        for(int i=0; i<n; i++){
            targetArr[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        no_1874 problem = new no_1874();
        bw.write(problem.solution(targetArr));
        bw.flush();
        bw.close();
    }

    public String solution(int[] targetArr){
        Stack<Integer> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int num = 1;
        for(int i=0; i<targetArr.length; i++){
            int target = targetArr[i];

            if(num <= target){  // 목표값 보다 num 이 작은 경우 -> 스택에 num 을 증가시키면서 넣음
                while(num <= target) {
                    st.push(num++);
                    sb.append("+\n");
                }

                // 현재 stack 의 top 값과 목표 값이 같아지는 경우
                // num 의 값이 목표 값 보다 작아서 같아질때까지 넣은 뒤 뽑는다.
                st.pop();
                sb.append("-\n");
            } else {    // num 이 목표값보다 큰 경우
                int top = st.pop(); // 스택의 최 상위 값을 뽑음
                if(top > target) {  // 최상위 값이 목표값보다 크다면 처리할 수 있는 방법이 없음
                    return "No";
                } else {
                    sb.append("-\n");
                }
            }
        }
        return sb.toString();
    }
}
