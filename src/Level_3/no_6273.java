package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 택배 마스터 광우
// https://softeer.ai/practice/6273
public class no_6273 {
    static int n, m, k;
    static int[] rail;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 레일의 개수
        n = Integer.parseInt(st.nextToken());
        // 레일 선언
        rail = new int[n];
        // 택배 바구니의 무게
        m = Integer.parseInt(st.nextToken());
        // 일의 시행 횟수
        k = Integer.parseInt(st.nextToken());
        // 레일 초기화
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            rail[i] = Integer.parseInt(st.nextToken());
        }

        no_6273 problem = new no_6273();
        problem.dfs(new int[n], 0, new boolean[n]);
        System.out.println(answer);
    }

    // 완전탐색으로 모든 순서 구하기
    public void dfs(int[] seq, int depth, boolean[] visited){
        if(depth == n){ // 모든 레일 탐색 완료 (순서 생성 완료)
            // 순서를 기반으로 무게 계산
            answer = Math.min(answer, getValue(seq));

            return;
        }

        for(int i=0; i<n; i++){
            if(visited[i]) continue; // 이미 방문했다면

            visited[i] = true;
            // 순서 생성
            seq[depth] = rail[i];
            dfs(seq, depth+1, visited);
            visited[i] = false;
        }
    }

    public int getValue(int[] seq){
        int value = 0;

        int idx = 0;
        for(int i=0; i<k; i++){
            int box = 0;
            while(box + seq[idx%n] <= m){
                box += seq[(idx++)%n];
            }
            value += box;
        }
        return value;
    }
}
