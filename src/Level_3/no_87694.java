package Level_3;

import java.io.IOException;

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

    }

    int[][] map = new int[10][10];  // 최대 길이
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 사각형 그리기
        makeMap(rectangle);

        // 테두리 구분하기
        //checkBoarder();

        for(int[] bArr : map){
            for(int b : bArr){
                System.out.print(b+" ");
            }
            System.out.println();
        }



        int answer = 0;

        return answer;
    }

    private void makeMap(int[][] rectangle) {
        for(int[] points : rectangle){
            // 우측 상단 -> 좌측 하단
            int leftDownX = points[0];
            int leftDownY = 9 - points[1];
            int rightUpX = points[2];
            int rightUpY = 9 - points[3];

            for(int y = rightUpY; y <= leftDownY; y++){
                for(int x = leftDownX; x <= rightUpX; x++){
                    map[y][x]++;
                }
            }
        }
    }

    private void checkBoarder() {    // 4방향 모두 1 이상으로 감싸져 있다면 테두리가 아님?
        for(int i=1; i<9; i++){
            for(int j=1; j<9; j++){
                if(map[i-1][j] >= 1 && map[i][j-1] >= 1 && -
                        map[i+1][j] >= 1 && map[i][j+1] >= 1) map[i][j] = 0;
            }
        }
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
