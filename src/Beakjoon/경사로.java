package Beakjoon;

import java.util.*;
import java.io.*;

public class 경사로 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        br.close();
    }

    static StringTokenizer st;
    static int n, l;
    static int[][] board;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());   // 격자 크기
        l = Integer.parseInt(st.nextToken());   // 경사로 길이

        board = new int[n][n];
        for(int x=0; x<n; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<n; y++) {
                board[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 가로로 확인

        // 2. 세로로 확인

    }

    // 가로로 확인
    static void checkRow() {
        for(int x=0; x<n; x++) {
            boolean[] arr = new boolean[n];
            int prev = board[x][0];
            boolean isOk = true;
            for(int y=1; y<n; y++) {
                int cur = board[x][y];

                if(prev == cur) continue;       // 높이 변화가 없음
                if(Math.abs(prev-cur) > 1) {    // 경사로로 커버할 수 없음
                    isOk = false;
                    break;
                }

                // 경사로로 커버할 수 있음
                // 1. 높이가 높아진 경우
                // -> 이전 위치에서 현재 위치까지 확인
                
                // 2. 높이가 낮아진 경우
                // -> 현재 위치 + l 위치까지 확인
            }
        }
    }

    // 세로로 확인
    static void checkCol() {

    }
}
