package Level_2;

// 프로그래머스 Lv.2
// 가장 큰 정사각형 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/12905
public class no_12905 {
    private int n,m;

    public int solution(int [][]board){
        n = board.length;
        m = board[0].length;
        int answer = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                 board[i][j] = board[i][j] == 1 ?  (i == 0 || j == 0) ? board[i][j] : Math.min(board[i-1][j], Math.min(board[i][j-1], board[i-1][j-1]))+1 : board[i][j];
                 answer = Math.max(board[i][j], answer);
            }
        }
        return answer*answer;
    }

    public static void main(String[] args){
//        int[][] board = new int[][] {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};
//        int[][] board = new int[][] {{0,1,1,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
//        int[][] board = new int[][] {{0,0,0,0},{1,0,0,0},{1,0,0,0},{0,0,0,0}};
        int[][] board = new int[][] {{1,0,0,0},
                                     {1,0,0,0},
                                     {1,1,1,1}};
        no_12905 problem = new no_12905();
        System.out.println(problem.solution(board));
    }
}


