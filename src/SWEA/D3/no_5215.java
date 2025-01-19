package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 5215. 햄버거 다이어트
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT
public class no_5215 {
    static int[][] item;	// 재료 [맛, 칼로리]
    static int N, L;		// 재료 개수, 칼로리 제한
    public static void main(String args[]) throws IOException
    {
        no_5215 problem = new no_5215();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            sb.append("#").append(test_case).append(" ");	// 문제 번호

            // 재료의 개수와, 제한 칼로리 입력
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            // 배열 크기 선언
            item = new int[N][2];
            // 재료 입력
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine().trim());

                item[i][0] = Integer.parseInt(st.nextToken());	// 선호도
                item[i][1] = Integer.parseInt(st.nextToken());	// 칼로리
            }

            sb.append(problem.solution()).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    long maxLike;
    boolean[] isUsed;
    // 제한 칼로리 내 최대 선호도를 구한다.
    private long solution() {
        maxLike = Integer.MIN_VALUE;
        isUsed = new boolean[N];
        dfs(0,0,0);
        return maxLike;
    }


    // 모든 경우의 수를 구함
    private void dfs(int depth, int limit, int like) {
        if(limit > L) return;	// 제한 칼로리를 넘김
        if(depth == N) {	// 탐색을 완료
            maxLike = Math.max(like, maxLike);	// 정답 갱신
            return;
        }

        dfs(depth+1, limit + item[depth][1], like + item[depth][0]);
        dfs(depth+1, limit, like);
    }
}
