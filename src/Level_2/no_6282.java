package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Softeer Lv.2
// 장애물 인식 프로그램
// https://www.softeer.ai/practice/6282
public class no_6282 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 1 은 장애물, 0은 도로
        int[][] maps = new int[n][n];
        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<s.length(); j++){
                maps[i][j] = s.charAt(j) - '0';
            }
        }

        no_6282 problem = new no_6282();
        int[] answer = problem.solution(maps);
        System.out.println(answer.length);
        for(int i : answer){
            System.out.printf("%d\n", i);
        }
    }
    public int[] solution(int[][] maps){
        List<Integer> answer = new ArrayList<>();
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[i].length; j++){
                if(maps[i][j] == 1){    // 장애물을 만났을 경우
//                    System.out.printf("1 발견 : (%d, %d)\n", i,j);
                    // 장애물의 크기를 판단한다.
                    int size = findBlock(maps, i, j);
                    answer.add(size);
                }
            }
        }

        Collections.sort(answer);
        int[] result = new int[answer.size()];
        for(int i=0; i<result.length; i++){
            result[i] = answer.get(i);
        }
        return result;
    }

    private int[] dx = new int[] {0,1,0,-1};
    private int[] dy = new int[] {1,0,-1,0};
    private int findBlock(int[][] maps, int x, int y){
        Queue<int[]> q = new LinkedList<>();
        // 시작 위치 설정
        q.add(new int[] {x,y});
        int count = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int nowX = cur[0];
            int nowY = cur[1];

            // 이미 방문한 경우
            if(maps[nowX][nowY] == 0) continue;

            // 방문 처리하고, 카운트 해줌
            maps[nowX][nowY] = 0;
            count++;

            for(int i=0; i<4; i++){
                int newX = nowX + dx[i];
                int newY = nowY + dy[i];

                // 범위를 벗어나거나, 이미 방문한 경우(도로인 경우)
                if(newX < 0 || newY < 0 || newX >= maps.length || newY >= maps[0].length || maps[newX][newY] == 0) continue;
                q.add(new int[] {newX, newY});
            }
        }
        return count;
    }
}
