package Level_2;

// 프로그래머스 Lv.2
// 가장 큰 정사각형 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/12905
public class no_12905 {
    private int n,m;
    private class Square{
        int x1, x2, y1, y2, lenght;

        public Square(int x1, int y1, int x2, int y2, int length){
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
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
//                    System.out.println("반복");
                   Square square = new Square(i,j,i+1,j+1,1);
                   searchNearBy(board, square);
                }
            }
        }

        return answer*answer;
    }

    public void searchNearBy(int[][] board, Square square){
        if(!inRange(square.x2, square.y2)) return;
    /*
    0.1 | 0.2 | 0.3
    1.1 | 1.2 | 1.3
    2.1 | 2.2 | 2.3
     */
        for(int i= square.x1; i <= square.x2; i++){
//            System.out.println(i + " : " + (square.y2));
            if(!inRange(i, square.y2) && board[i][square.y2] == 0) return;
        }
        for(int i = square.y1; i <= square.y2; i++){
//            System.out.println(square.x2 + " : " + i);
            if(!inRange(square.x2, i) && board[square.x2][i] == 0) return;
        }
        answer = Math.max(square.lenght+1, answer);
//        System.out.println("-------------------------- 길이" + square.lenght);
        searchNearBy(board, new Square(square.x1, square.y1, square.x2+1, square.y2+1, ++square.lenght));
    }
    private boolean inRange(int x, int y){
        return x < n && y < m;
    }

    public static void main(String[] args){
        int[][] board = new int[][] {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
//        int[][] board = new int[][] {{0,1,1,1},
//                                     {1,1,1,1},
//                                     {1,1,1,1}};
        no_12905 problem = new no_12905();
        System.out.println(problem.solution(board));
    }
}


