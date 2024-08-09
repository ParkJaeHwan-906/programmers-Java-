package Level_2;

import java.util.*;

// 프로그래머스 Lv.2
// 줄 서는 방법
// https://school.programmers.co.kr/learn/courses/30/lessons/12936
public class no_12936 {
    /*
    n 명의 사람이 줄을 선다. ( 1 ~ n )
    사람의 수 n 명과 k 번째 방법
     */

    // 1. 완전탐색을 이용한 풀이방법 - 시간 초과 ( n 이 20 이하의 자연수, K 는 n! 이하의 자연수 )
    public static long cnt;
    public static boolean[] visited;
    public static int[] answer;

    public int[] solution(int n, long k){
        answer = new int[n];
        visited = new boolean[n];
        dfs(0, new int[n], k);

        return answer;
    }

    public static void dfs(int depth, int[] out, long k){
        if(depth == out.length){
            cnt++;
            if(cnt == k){
                for(int i=0; i<out.length; i++){
                    answer[i] = out[i];
                }
            }
            return;
        }
        for(int i=0; i< visited.length; i++){
            if(!visited[i]){
                visited[i] = true;
                out[depth] = i+1;
                dfs(depth+1, out, k);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args){
        no_12936 problem = new no_12936();
        int n = 3;
        long k = 5;
        int[] result = problem.solution(n,k);
        System.out.print("[ ");
        for(int i : result){
            System.out.print(i+" ");
        }
        System.out.print("]");
    }
}
