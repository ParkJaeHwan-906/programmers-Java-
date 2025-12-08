package Beakjoon;

import java.util.*;
import java.io.*;

public class 주사위_굴리기 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        System.out.print(sb);
    }

    static int row, col, x, y, commandCnt;
    static int[][] board;
    static int[] commands;
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        commandCnt = Integer.parseInt(st.nextToken());

        board = new int[row][col];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        commands = new int[commandCnt];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < commandCnt; i++)
            commands[i] = Integer.parseInt(st.nextToken());

        doRoll();
    }

    static int[] dice = new int[7]; // 1~6 사용
    // 동, 서, 북, 남
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static void rollDice(int dir) {
        int d1 = dice[1], d2 = dice[2], d3 = dice[3];
        int d4 = dice[4], d5 = dice[5], d6 = dice[6];

        if (dir == 1) { // 동
            dice[1] = d4;
            dice[3] = d1;
            dice[6] = d3;
            dice[4] = d6;
        } else if (dir == 2) { // 서
            dice[1] = d3;
            dice[4] = d1;
            dice[6] = d4;
            dice[3] = d6;
        } else if (dir == 3) { // 북
            dice[1] = d5;
            dice[2] = d1;
            dice[6] = d2;
            dice[5] = d6;
        } else if (dir == 4) { // 남
            dice[1] = d2;
            dice[5] = d1;
            dice[6] = d5;
            dice[2] = d6;
        }
    }

    static void doRoll() {
        for (int cmd : commands) {
            int nx = x + dx[cmd];
            int ny = y + dy[cmd];

            if (nx < 0 || ny < 0 || nx >= row || ny >= col)
                continue;

            x = nx;
            y = ny;

            rollDice(cmd);  // 주사위 회전

            if (board[x][y] == 0) {
                board[x][y] = dice[6];
            } else {
                dice[6] = board[x][y];
                board[x][y] = 0;
            }

            sb.append(dice[1]).append("\n"); // top 출력
        }
    }
}
