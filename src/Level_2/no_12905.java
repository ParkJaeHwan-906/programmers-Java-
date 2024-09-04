package Level_2;

// 프로그래머스 Lv.2
// 가장 큰 정사각형 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/12905
public class no_12905 {
    private int n,m;
    private class Square{
        int x, y, lenght;

        public Square(int x, int y, int length){
            this.x = x;
            this.y = y;
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
                   Square square = new Square(i,j,1);
                   searchNearBy(board, square);
                }
            }
        }

        return answer;
    }

    public void searchNearBy(int[][] board, Square square){
        if(!inRange(square.x+1, square.y+1)) return;
        System.out.println("시작 : "+ square.x +" , " +square.y);
        for(int i= square.x; i <= square.x+1; i++){
            System.out.println(i + " : " + (square.y+1));
            if(!inRange(i, square.y+1) && board[i][square.y+1] == 0) return;
        }
        for(int i = square.y; i <= square.y+1; i++){
            System.out.println(square.x+1 + " : " + i);
            if(!inRange(square.x+1, i) && board[square.x+1][i] == 0) return;
        }
        answer = square.lenght;
        System.out.println("--------------------------");
        searchNearBy(board, new Square(square.x, square.y+1, square.lenght+1));
    }
    private boolean inRange(int x, int y){
        return x < n && y < m;
    }
    /*
    0.1 | 0.2 | 0.3
    1.1 | 1.2 | 1.3
    2.1 | 2.2 | 2.3
     */
    public static void main(String[] args){
//        int[][] board = new int[][] {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
        int[][] board = new int[][] {{0,1,1,1},
                                     {1,1,1,1},
                                     {1,1,1,1}};
        no_12905 problem = new no_12905();
        System.out.println(problem.solution(board));
    }
}


