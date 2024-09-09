package Level_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 프로그래머스 Lv.2
// 무인도 여행
// https://school.programmers.co.kr/learn/courses/30/lessons/154540
public class no_154540 {
    int days = 0;
    public int[] solution(String[] maps){
        int weight = maps.length;
        int height = maps[0].length();
        // maps 를 그래프로 표현하기
        int[][] map = new int[weight][height];
        for(int i=0; i<weight; i++){
            for(int j=0; j<height; j++){
                if(maps[i].charAt(j) == 'X') map[i][j] = 0;
                else map[i][j] = maps[i].charAt(j) -'0';
//                System.out.print(map[i][j]);
            }
//            System.out.println();
        }

        // DFS 사용
        // 방문 여부
        boolean[][]visited = new boolean[weight][height];
        // 정답 저장
        List<Integer> answer = new ArrayList<>();
        for(int i=0; i< weight; i++){
            for(int j=0; j<height; j++){
                // 방문 이력이 없고, 섬이라면 ( 0 초과 )
                if(!visited[i][j] && map[i][j] > 0){
                    dfs(map, visited, i, j);

                    answer.add(days);
                    days = 0;
                }
            }
        }
        if(answer.isEmpty()) return new int[] {-1};
        Collections.sort(answer);
        // 리스트를 배열로 변환
        return answer.stream().mapToInt(i->i).toArray();
    }

    final int[] dx = {1,-1,0,0};
    final int[] dy = {0,0,1,-1};
    public void dfs(int[][] map, boolean[][] visited, int x, int y){
        // 카운트 후 방문 처리
        days += map[x][y];
        visited[x][y] = true;

        for(int i=0; i<4; i++){
            // 상하좌우 이동
            int new_x = x+dx[i];
            int new_y = y+dy[i];

            // 범위 확인
            if(new_x < 0 || new_y < 0 || new_x >= map.length || new_y >= map[0].length) continue;

            // 방문 하지 않았고, 섬이라면 ( 0 초과 )
            if(!visited[new_x][new_y] && map[new_x][new_y] > 0){
                dfs(map, visited, new_x, new_y);
            }

        }
    }

    public static void main(String[] args){
        no_154540 problem = new no_154540();
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        int[] result = problem.solution(maps);

        for(int i : result){
            System.out.print(i+" ");
        }
    }
}
