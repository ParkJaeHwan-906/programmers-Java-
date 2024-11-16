package Level_3;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

// 프로그래머스 Lv.3
// 아이템 줍기
// https://school.programmers.co.kr/learn/courses/30/lessons/87694
public class no_87694 {
    public static void main(String[] args)throws IOException {
        int[][] rectangle = new int[][]
                {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;
        int result = 17;

        no_87694 problem = new no_87694();
        int answer = problem.solution(rectangle, characterX, characterY, itemX, itemY);
        System.out.println(answer);

    }

    // ⚠️ 테두리 표현을 위해 2배로 늘려줌
    int[][] map = new int[101][101];  // 최대 길이 * 2
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 사각형 그리기
        makeMap(rectangle);
        int result = move(characterX*2, characterY*2, itemX*2, itemY*2);

        // ⚠️ 좌표를 모두 2배 처리 해줬기에, 답도 2배임 따라서 /2 해줌
        return result/2;
    }

    private void makeMap(int[][] rectangle) {
        for(int[] points : rectangle){
            // 우측 상단 -> 좌측 하단
            int x1 = points[0] * 2;
            int y1 = points[1] * 2;
            int x2 = points[2] * 2;
            int y2 = points[3] * 2;

            for(int i=y1; i<=y2; i++){
                for(int j=x1; j<=x2; j++){
                    if(map[i][j] == 1) continue;    // 이미 다른 사각형의 내부로 채워진 곳이라면
                    if(i == y1 || i == y2 || j == x1 || j == x2){   // 테두리라면
                        map[i][j] = 2;
                    }else{  // 내부라면
                        map[i][j] = 1;
                    }
                }
            }
        }
    }

    private int[] dx = new int[] {0,1,0,-1};
    private int[] dy = new int[] {1,0,-1,0};
    private int move(int startX, int startY, int goalX, int goalY){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        // [x,y,이동거리]
        q.add(new int[] {startY, startX, 0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int y = cur[0];
            int x = cur[1];
            int dist = cur[2];

            if(x == goalX && y == goalY) return dist;

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어남
                if(nx < 0 || nx >= 101 || ny < 0 || ny >= 101 || visited[ny][nx]) continue;
                // 테두리가 아닌 경우
                if(map[ny][nx] != 2) continue;

                visited[ny][nx] = true;
                q.add(new int[] {ny, nx, dist+1});
            }
        }
        return -1;
    }

}


/*
1 1 7 4

[10][10]
0 0 0 0 1 1 1 0 0 0
0 0 1 1 2 2 2 1 1 0
0 0 1 1 2 2 2 1 1 0
0 0 1 1 2 2 2 1 1 0
0 0 0 1 2 2 1 0 0 0
0 1 1 2 3 3 2 1 0 0
0 1 1 2 3 3 2 1 0 0
0 1 1 2 2 2 1 1 0 0
0 1 1 1 1 1 1 1 0 0
0 0 0 0 0 0 0 0 0 0

좌하단 -> 우상단
[1, 8] -> [7, 5]


 */
