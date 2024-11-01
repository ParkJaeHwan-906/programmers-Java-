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

    // 전역으로 사용할 visited 와 map 생성 + 작업자 위치
    static int[][] map;
    static boolean[][] visited;
    static int[][] worker;

    // 전역변수 answer
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n 과 m 입력
        String[] nm  = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        map = new int[n][n];
        visited = new boolean[n][n];
        // map 입력
        for(int i=0; i<n; i++){
            String[] mapArr = br.readLine().split(" ");
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(mapArr[j]);
            }
        }

        // 작업자 위치 입력
        worker = new int[m][2];
        for(int i=0; i<m; i++){
            String[] workerArr = br.readLine().split(" ");
            // i 작업자의 x 좌표
            worker[i][0] = Integer.parseInt(workerArr[0]) -1;
            // i 작업자의 y좌표
            worker[i][1] = Integer.parseInt(workerArr[1]) -1;
        }

        // 가장 첫 작업자부터 시작
        int startX = worker[0][0];
        int startY = worker[0][1];
        // 방문처리
        visited[startX][startY] = true;
        dfs(new int[] {startX, startY}, map[startX][startY],0, 0);

        System.out.println(answer);
    }

    // 상하좌우
    static int[] dx = new int[] {0,1,0,-1};
    static int[] dy = new int[] {1,0,-1,0};
    public static void dfs(int[] start, int sum, int workerIdx, int depth){
        if(depth == 3){ // 3초동안 움직임
            if(workerIdx + 1 < worker.length){  // 더 움직일 수 있는 작업자가 있음
                int startX = worker[workerIdx+1][0];
                int startY = worker[workerIdx+1][1];
                visited[startX][startY] = true;
                dfs(new int[] {startX, startY}, sum + map[startX][startY], workerIdx +1, 0);
            }else{  // 더 이상 움직일 작업자가 없음
                answer = Math.max(sum, answer);
            }
            // 종료
            return;
        }

        for(int i = 0; i < 4; i++){
            // 상하좌우로 이동
            int nx = start[0] + dx[i];
            int ny = start[1] + dy[i];

            // map 의 안에 있고 방문하지 않음
            if(nx >= 0 && nx < map.length && ny >= 0 && ny < map.length && !visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(new int[] {nx, ny}, sum + map[nx][ny], workerIdx, depth +1);
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