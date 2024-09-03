package Level_2;

// 프로그래머스 Lv.2
// 가장 큰 정사각형 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/12905
public class no_12905 {
    private int n,m;
    private class Square{
        int x1, x2, lenght;

        public Square(int x1, int x2, int length){
            this.x1 = x1;
            this.x2 = x2;
            this.lenght = length;
        }
    }

    private int answer = 0;

    public int solution(int [][]board){

        n = board.length;
        m = board[0].length;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                // 정사각형이라면 ( 주변 탐색 )
                if(board[i][j] == 1){
                   Square square = new Square(n,m,1);
                   searchNearBy(board, square);
                }
            }
        }

        return answer;
    }

    public void searchNearBy(int[][] board, Square square){
        for(int i = square.x1; i <= square.x1+ square.lenght; i++){
            for(int j = square.x2; j <= square.x2 + square.lenght; j++){
                if(board[i][j] == 0 || (square.x1 > n && square.x2 > m) ) return;
            }
        }
        answer = square.lenght;
        searchNearBy(board, new Square(square.x1+1, square.x2+1, square.lenght+1));
    }

    public static void main(String[] args){
        int[][] board = new int[][] {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
        no_12905 problem = new no_12905();
        System.out.println(problem.solution(board));
    }
}
