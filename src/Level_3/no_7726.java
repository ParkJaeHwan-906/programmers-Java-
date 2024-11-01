package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 소프티어 Lv.3
// 나무 섭지
// https://softeer.ai/practice/7726
public class no_7726 {
    static Queue<int[]> ghost = new LinkedList<>();
    static Queue<int[]> human = new LinkedList<>();
    static char[][] map;
    static int n, m;
    static int[][] ghostMap;
    static int[][] humanMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n,m 입력
        String[] nm = br.readLine().split(" ");
        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);

        ghostMap = new int[n][m];
        humanMap = new int[n][m];

        // map 입력
        map = new char[n][m];
        for(int i=0; i<n; i++){
            // map 을 한줄씩 입력받음
            String s = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = s.charAt(j);
                // 유령, 목적지, 남수의 좌표를 기록
                if(s.charAt(j) == 'G'){
                    ghost.offer(new int[] {i,j});
                    ghostMap[i][j] = 1;
                }
                else if(s.charAt(j) == 'N'){
                    human.offer(new int[] {i,j});
                    humanMap[i][j] = 1;
                }
            }
        }

        no_7726 problem = new no_7726();
        boolean result = problem.solution(map);
        System.out.println(result ? "Yes" : "No");
    }


    public boolean solution(char[][] map){
        // 유령이 각 칸으로 갈 수 있는 시간 구하기

        ghostMove();

        boolean answer = humanMove();

        return answer;
    }

    int[] dx = new int[] {0, 1, 0, -1}; // x좌표 이동
    int[] dy = new int[] {1, 0, -1, 0}; // y좌표 이동

    // 유령의 동선 경로 계산
    public void ghostMove(){

        while(!ghost.isEmpty()){
            // 현재 위치
            int[] node = ghost.poll();
            // 방문 처리
            if(map[node[0]][node[1]] == 'D') return;

            // 상하좌우 이동
            for(int i=0; i<4; i++){
                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= ghostMap.length || ny >= ghostMap[0].length) continue;
                if(ghostMap[nx][ny] > 0) continue;  // 이미 방문한 적이 있음

                ghost.offer(new int[]{nx, ny});
                ghostMap[nx][ny] = ghostMap[node[0]][node[1]] + 1;
            }
        }
    }

    public boolean humanMove() {
        while(!human.isEmpty()){
            int[] node = human.poll();
            // 현재 위치 방문 처리
            if(map[node[0]][node[1]] == 'D') return true;

            for(int i=0; i<4; i++){
                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];

                // map 영역을 벗어나지 않고, 방문하지 않았으며, 벽이 아닌곳
                if(nx < 0 || ny < 0 || nx >= humanMap.length || ny >= humanMap[0].length) continue;
                if(humanMap[nx][ny] > 0) continue;  // 이미 방문한 적이 있음
                if(map[nx][ny] == '#') continue;
                if(ghostMap[nx][ny] <= humanMap[node[0]][node[1]] + 1 && ghostMap[nx][ny] > 0) continue;

                human.add(new int[] {nx, ny});
                humanMap[nx][ny] = humanMap[node[0]][node[1]] + 1;
            }
        }
        return false;
    }
}




/*
미로는 n x m 크기
각 격자 칸은 남우(N), 출구(D), 유령(G), 빈 공간(.), 벽(#) 으로 이루어짐

남우 : 1초에 상하좌우 한칸이동(벽이 아닌곳)
유령 : 1초에 상하좌우 아무곳이나 한칸 이동 (벽도 가능)
 */