package Programmers.Lv2;

import java.util.*;

public class PMMS_160585_혼자서하는틱택토 {
    public static void main(String[] args) {
        String[] board = {"O.X", ".O.", "..X"};
//        String[] board = {"OOX", "OOX", "OOO"};
        System.out.println(new PMMS_160585_혼자서하는틱택토().solution(board));
    }

    /*
        선공 : O, 후공 : X
     */
    char[][] board;
    public int solution(String[] board) {
        /*
            게임이 이루어 질 수 없는 경우
            1. O, X 빙고가 둘 다 존재하는 경우
                + 빙고가 1개 이상인 경우
            2. 선공 후공 순서가 바뀐 경우
                - X 표시가 더 많은 경우
         */
        init(board);
        if(oCnt < xCnt) return 0;
        if (oCnt - xCnt > 1) return 0;

        bingo();
        // 1. O 승리 -> O 의 개수가 X 보다 1 개 많아야함
        if(oBingo > 0 && oCnt != xCnt+1) return 0;
        // 2. X 승리 -> O 의 개수와 X 의 개수가 같다
        else if(xBingo > 0 && oCnt != xCnt) return 0;
        // 3. 게임이 안끝남
        else if(oBingo > 0 && xBingo > 0) return 0;
        
        return 1;
    }

    int oCnt, xCnt;
    void init(String[] board) {
        this.board = new char[3][3];
        oCnt = xCnt = 0;
        for(int x=0; x<3; x++) {
            for(int y=0; y<3; y++) {
                this.board[x][y] = board[x].charAt(y);
                if(this.board[x][y] == 'O') oCnt++;
                else if(this.board[x][y] == 'X') xCnt++;
            }
        }

        // 확인
//        for(int i=0; i<3; i++) System.out.println(Arrays.toString(this.board[i]));
    }

    // 모든 빙고를 확인한다.
    int oBingo, xBingo;
    void bingo() {
        oBingo = xBingo = 0;

        // 1. 가로로 같은지 확인
        for(int x=0; x<3; x++) {
            char c = board[x][0];
            if(c == '.') continue;

            boolean b = true;
            for(int y=1; y<3; y++) {
                if(board[x][y] == c) continue;
                b = false;
                break;
            }
            if(!b) continue;
            switch (c) {
                case 'O':
                    oBingo++;
                    break;
                case 'X':
                    xBingo++;
                    break;
            }
        }

        // 2. 세로로 같은지 확인
        for(int y=0; y<3; y++) {
            char c = board[0][y];
            if(c == '.') continue;

            boolean b = true;
            for(int x=1; x<3; x++) {
                if(board[x][y] == c) continue;
                b = false;
                break;
            }
            if(!b) continue;
            switch (c) {
                case 'O':
                    oBingo++;
                    break;
                case 'X':
                    xBingo++;
                    break;
            }
        }

        // 3. 대각선으로 같은지 확인
        // 3-1. 우하
        char c = board[0][0];
        boolean b = true;
        for(int x=1; x<3; x++) {
            if(board[x][x] == c) continue;
            b = false;
            break;
        }
        if(b) {
            switch (c) {
                case 'O':
                    oBingo++;
                    break;
                case 'X':
                    xBingo++;
                    break;
            }
        }
        // 3-2. 좌상
        c = board[2][0];
        b = true;
        for(int x=1; x>-1; x--) {
            if(board[x][Math.abs(x-2)] == c) continue;
            b = false;
            break;
        }
        if(b) {
            switch (c) {
                case 'O':
                    oBingo++;
                    break;
                case 'X':
                    xBingo++;
                    break;
            }
        }
        // 확인
//        System.out.println(oBingo);
//        System.out.println(xBingo);
    }
}
