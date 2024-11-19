package CodingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HMGBootCampPreTest {
    static int n;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for(int i=0; i<n; i++){
            String[] arr = br.readLine().split(" ");

            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(arr[j]);
            }
        }
        HMGBootCampPreTest problem = new HMGBootCampPreTest();
        int[] answer = problem.solution();
        System.out.println(answer.length);
        for(int i : answer){
            System.out.printf("%d ", i);
        }
    }

    List<Integer> list = new ArrayList<>();
    public int[] solution(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                // 도형이라고 판단
                if(map[i][j] == 1){
                    int count = findPolygon(i, j);
                    list.add(count);
                }
            }
        }
        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }

    int[] dx = new int[] {0,1,0,-1};
    int[] dy = new int[] {1,0,-1,0};
    public int findPolygon(int x, int y){
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[] {x,y});
        int count = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int nowX = cur[0];
            int nowY = cur[1];

            if(map[nowX][nowY] == 0) continue;

//            System.out.printf("현위치 : (%d, %d) \n", nowX, nowY);
            // 방문처리
            map[nowX][nowY] = 0;
            count++;

            for(int i=0; i<4; i++){
                int nx = nowX + dx[i];
                int ny = nowY + dy[i];
//                System.out.printf("탐색위치 : (%d, %d) \n", nx, ny);
                // map 범위를 벗어나고, 0이라면 패스
                if(nx < 0 || ny < 0 || nx >= n || ny >= n || map[nx][ny] == 0) continue;
                q.add(new int[]{nx, ny});
            }
        }
        return count;
    }
}
