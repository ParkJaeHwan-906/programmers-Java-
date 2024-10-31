package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// 소프티어 Lv.3
// 함께하는 효도
// https://softeer.ai/practice/7727
public class no_7727 {
    static int[][] friends;
    static int answer = 0;
    static boolean[][] visited;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        // map 저장
        map = new int[n][n];
        visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            String[] arr = br.readLine().split(" ");
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(arr[j]);
            }
        }

        // 친구 좌표 저장
        friends = new int[m][2];
        for(int i = 0; i < m; i++){
            String[] s = br.readLine().split(" ");
            friends[i][0] = Integer.parseInt(s[0]) -1;
            friends[i][1] = Integer.parseInt(s[1]) -1;
        }

        dfs(new int[] {friends[0][0], friends[0][1]}, map[friends[0][0]][friends[0][1]], 0, 0);

        System.out.println(answer);
    }
    static int idx = 0; // 현재 움직이고 있는 사람의 idx
    static int[] dx = new int[] {0,1,0,-1};
    static int[] dy = new int[] {1,0,-1,0};
    public static void dfs(int[] start, int sum, int depth, int idx){
        visited[start[0]][start[1]] = true;
        if(depth == 3){ // 3초 이동 완료
            if(idx + 1 < friends.length){   // 더 움직일 사람이 있음
                // 다음 사람 움직임
                dfs(new int[] {friends[idx+1][0], friends[idx +1][1]}, sum + map[friends[idx][0]][friends[idx][1]], 0, idx + 1);
            } else{ // 더 움직일 사람이 없음
                // 최대값 비교
                answer = Math.max(answer, sum);
            }
        }

        for(int i=0; i<4; i++){
            int nx = start[0] + dx[i];
            int ny = start[1] + dy[i];

            // map 의 범위를 벗어나지 않고, 방문하지 않은경우
            if(nx >= 0 && ny >= 0 && nx < map.length && ny < map.length && !visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(new int[] {nx, ny}, sum + map[nx][ny], depth+1, idx);
                visited[nx][ny] = false;
            }
        }
    }
}
/*
m 명의 친구를 불러 나무에서 열매를 수확함
나무들은 n * n 모양 크기에 땅에 심어져있음 -> 나무마다 가능한 열매 수확량 지정

1초에 상하좌우 한칸씩 이동 -> 3초 동안 최대값
 */