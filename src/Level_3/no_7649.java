package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Softeer Lv.3
// 효도 여행
// https://softeer.ai/practice/7649
public class no_7649 {
    static int n,m;
    static String s;
    static String[][] graph;  // 기본값 null으로 채워짐
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        // 정점의 개수
        n = Integer.parseInt(nm[0]);
        visited = new boolean[n][n];
        graph = new String[n][n];
        // 문장의 길이
        m = Integer.parseInt(nm[1]);
        // 문자열 입력받음
        s = br.readLine();

        for(int i=0; i<n-1; i++){
            String[] arr = br.readLine().split(" ");
            int a = Integer.parseInt(arr[0]) -1;
            int b = Integer.parseInt(arr[1]) -1;

            // 그래프 연결
            graph[a][b] = arr[2];
            graph[b][a] = arr[2];
        }
//
//        for(String[] arr1 : graph){
//            for(String s : arr1){
//                if(s == null) System.out.print("null ");
//                else System.out.print(s+" ");
//            }
//            System.out.println();
//        }

        no_7649 problem = new no_7649();
        System.out.println(problem.solution());
    }

    int answer = 0;
    public int solution(){
        makeRoute(0,0,"");

        return answer;
    }

    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    // 1. DFS 로 이동할 수 있는 경우의 수 구하기
    public void makeRoute(int x, int y, String str){
        System.out.println(x + ", " + y);
        System.out.println(str);
        answer = Math.max(lcs(str), answer);
        if(answer == m) return;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나면 제외
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny]) continue;
            // 연결되어있지 않다면
            if(graph[nx][ny] == null || graph[ny][nx] == null) continue;

            visited[nx][ny] = true;
            visited[ny][nx] = true;
            makeRoute(nx, ny, s + graph[nx][ny]);
            visited[nx][ny] = false;
            visited[ny][nx] = false;
        }
    }

    // 최장공통부분수열 알고리즘
    public int lcs(String str){
        int sLen = s.length();
        int strL = str.length();

        int[][] dp = new int[sLen+1][strL+1];

        for(int i=1; i<=sLen; i++){
            for(int j=1; j<=strL; j++){
                if(s.charAt(i-1) == str.charAt(j-1)) dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
            }
        }

        return dp[sLen][strL];
    }
}
