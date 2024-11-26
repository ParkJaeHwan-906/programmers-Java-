package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 오큰수
// https://www.acmicpc.net/problem/17298
public class no_17298 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        no_17298 problem = new no_17298();
        int[] answer = problem.goodSolution(n);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i : answer) {
            bw.write(i+" ");
        }
        bw.flush();
        bw.close();
    }

    // ⚠️ 시간 초과
    public int[] badSolution(int n) {
        int[] answer = new int[n];

        int i = 0;
        while(i < n){
            int cur = arr[i];

            for(int j=i+1; j<n; j++){
                if(arr[j] > cur){
                    answer[i] = arr[j];
                    break;
                }
            }
            if(answer[i] == 0) answer[i] = -1;
            i++;
        }

        return answer;
    }

    public int[] goodSolution(int n) {
        int[] answer = new int[n];

        // 스택에는 값이 아닌 인덱스 저장
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<n; i++) {
            while(!st.isEmpty() && arr[st.peek()] < arr[i]){
                answer[st.pop()] = arr[i];
            }

            st.push(i);
        }

        while(!st.isEmpty()) {
            answer[st.pop()] = -1;
        }

        return answer;
    }
}
