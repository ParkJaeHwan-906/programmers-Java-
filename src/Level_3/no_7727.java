package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소프티어 Lv.3
// 함께하는 효도
// https://softeer.ai/practice/7727
public class no_7727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        // map 저장
        String[][] maps = new String[n][n];
        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            String[] arr = br.readLine().split(" ");
            maps[i] = arr;
        }

        // 친구 좌표 저장
        int[][] friends = new int[m][2];
        for(int i = 0; i < m; i++){
            String[] s = br.readLine().split(" ");
            friends[i][0] = Integer.parseInt(s[0]) -1;
            friends[i][1] = Integer.parseInt(s[1]) -1;
        }

        no_7727 problem = new no_7727();
        System.out.println(problem.solution(maps, friends));
    }
    int answer = 0;
    public int solution(String[][] maps, int[][] friends){
        boolean[][] visited = new boolean[maps.length][maps.length];

        // 시작 위치에서의 수확
        for(int[] arr : friends){
            answer += Integer.parseInt(maps[arr[0]][arr[1]]);
        }
        dfs(maps, visited, friends[0][0], friends[0][1], 0, 0);


        return answer;
    }
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    public void dfs(String[][] maps, boolean[][] visited, int x, int y, int answer, int depth){
        answer += Integer.parseInt(maps[x][y]);
        if(depth == 3){ // 3초 이동 완료
            return;
        }

        for(int i = 0; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(nx >= 0 && ny >= 0 && nx < maps.length && ny < maps.length && visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(maps, visited, nx, ny,answer + Integer.parseInt(maps[nx][ny]), depth+1);
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