package Level_3;

// 프로그래머스 Lv.3
// 퍼즐 조각 채우기
// https://school.programmers.co.kr/learn/courses/30/lessons/84021
public class no_84021 {
    public static void main(String[] args){
        // 빈칸 0
        int[][] game_board = new int[][]
                {{1,1,0,0,1,0},{0,0,1,0,1,0}
                 ,{0,1,1,0,0,1},{1,1,0,1,1,1}
                 ,{1,0,0,0,1,0},{0,1,1,1,0,0}};

        // 최소 1개에서 6까지 주어짐
        int[][] table = new int[][]
                {{1,0,0,1,1,0},{1,0,1,0,1,0}
                ,{0,1,1,0,1,1},{0,0,1,0,0,0},
                {1,1,0,1,1,0},{0,1,0,0,0,0}};
    }

    public int solution(int[][] game_board, int[][] table){

    }

    private void find(int[][] table) {
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table[i].length; j++){
                if(table[i][j] == 0) continue;
            }
        }
    }

    private void get(int x, int y, int[][] table) {
        for(int i = 1; i < 6; i++){
            
        }
    }
}
