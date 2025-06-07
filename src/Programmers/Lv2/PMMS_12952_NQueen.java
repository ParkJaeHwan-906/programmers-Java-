package Programmers.Lv2;

import java.util.*;
import java.io.*;

public class PMMS_12952_NQueen {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        System.out.println(new PMMS_12952_NQueen().solution(n));
    }

    /*
        가로 세로 길이가 n 일 때,
        n 개의 Queen 을 둘 수 있는 경우를 찾는다.

        체스판에 n 개의 퀸이 서로 공격할 수 없도록 배치하고 싶다.
     */
    int caseCnt;        // 경우의 수 개수
    int[][] board;      // 체스판
    public int solution(int n) {
        caseCnt = 0;
        board = new int[n][n];

        /*
            백 트래킹 기법을 사용해, 각 칸에 Queen 이 놓였을 때의 경우의 수를 구한다.
         */
        makeCase(0, n);
        return caseCnt;
    }

    void makeCase(int row, int n) {
        if(row == n) {  // 마지막 열까지 퀸을 놓았다면
            caseCnt++;
            return;
        }

        // Queen 을 놓을 수 있다면
        // 현재 행에 Queen 을 놓을 수 있는 모든 경우를 확인한다.

        for(int col=0; col<n; col++) {
            if(!canPut(row, col, n)) continue;

            // 현 위치에 Queen 을 둘 수 있다.
            board[row][col] = 1;
            makeCase(row+1, n);
            board[row][col] = 0;
        }
    }

    /*
        위에서 아래로의 탐색만 진행하기 때문에
        현재 위치보다 위의 영역만 확인한다.
     */
    boolean canPut(int row, int col, int n) {
        // 1. 상단
        for(int x=row-1; x>-1; x--) {
            if(board[x][col] == 1) return false;
        }

        // 2. 좌상단
        int y = col;
        for(int x=row-1; x>-1; x--) {
            y--;
            if(y < 0) break;
            if(board[x][y] == 1) return false;
        }

        // 3. 우상단
        y = col;
        for(int x=row-1; x>-1; x--) {
            y++;
            if(y >= n) break;
            if(board[x][y] == 1) return false;
        }

        return true;
    }
}
