package Programmers.Lv3;

import java.util.*;

public class PMMS_87694_아이템줍기 {
    public static void main(String[] args) {
        int[][] rectangle = {
                {1,1,7,4},
                {3,2,5,5},
                {4,3,6,9},
                {2,6,8,8}
        };
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;

        System.out.println(new PMMS_87694_아이템줍기().solution(rectangle, characterX, characterY, itemX, itemY));
    }

    /*
        캐릭터는 다각형의 둘레를 따라 이동한다.

        두 직사강형이 한 꼭짓점에서 만나거나, 변이 겹치는 경우는 없음

        현 위치에서 아이템 위치까지 이동하는 최단 거리는?
     */
    int[][] board;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        /*
             rectangle : {minX, minY, maxX, maxY}

             어떻게 외각만 따라 돌 것인지?
             - 도형의 내부를 못가게 채워버리자
         */
        // 도형 만들기
        fillRectangle(rectangle);
        // 최단 경로 찾기 (BFS 이용)
        return findShortestRoot(characterX*2, characterY*2, itemX*2, itemY*2);
    }

    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    int findShortestRoot(int startX, int startY, int endX, int endY) {
        boolean[][] visited = new boolean[101][101];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if(x == endX && y == endY) return dist/2;

            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx < 0 || nx > 100 || ny < 0 || ny > 100) continue;
                if(visited[nx][ny] || board[nx][ny] != 2) continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, dist+1});
            }
        }

        return -1;
    }

    void fillRectangle(int[][] rectangle) {
        board = new int[101][101];        // 범위가 0~50 까지 -> 이 범위로 잡으면 도형이 이어서 있는 경우 구분이 되지 않음
//        board = new int[21][21];        // 범위가 0~50 까지

        // 테두리를 구분해야함
        // 테두리를 다르게 표시?
        for(int[] arr : rectangle) {
            int x1 = arr[0]*2;
            int y1 = arr[1]*2;
            int x2 = arr[2]*2;
            int y2 = arr[3]*2;
            for(int i=x1; i<=x2; i++) {
                for(int j=y1; j<=y2; j++) {
                    // 사각형이 겹쳐있는 경우
                    // 한 사각형의 태두리가, 다른 사각형의 내부인 경우
                    if(board[i][j] == 1)continue;

                    // 테두리인 경우
                    if(i==x1||i==x2||j==y1||j==y2) board[i][j]=2;
                    else board[i][j]=1;
                }
            }
        }

//        for(int[] arr : board) System.out.println(Arrays.toString(arr));
    }

}
