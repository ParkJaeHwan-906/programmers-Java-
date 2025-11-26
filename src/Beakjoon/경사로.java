package Beakjoon;

import java.util.*;
import java.io.*;

public class 경사로 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int n, l;
    static int[][] board;
    static int answer;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());   // 격자 크기
        l = Integer.parseInt(st.nextToken());   // 경사로 길이
        answer = 0;
        board = new int[n][n];
        for(int x=0; x<n; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<n; y++) {
                board[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 가로로 확인
        for(int x=0; x<n; x++) {
            if(checkRow(x)) answer++;
        }
        // 2. 세로로 확인
        for(int y=0; y<n; y++) {
            if(checkCol(y)) answer++;
        }

        System.out.println(answer);
    }

    // 가로로 확인
    static boolean checkRow(int x) {
        // 경사로 설치 가능 유무에 따른 결과를 저장
        boolean[] arr = new boolean[n];

        for(int y=0; y<n-1; y++) {
             int cur = board[x][y];
             int next = board[x][y+1];

             // 높이가 같은 경우
            if(cur == next) continue;
            // 높이 차가 2 이상, 이동 불가
            if(Math.abs(cur-next) > 1) return false;

            // 경사로 설치 가능
            // 1. 높이가 높아지는 경우
            if(cur < next) {
                int s = y-l+1;
                int e = y;

                if(s < 0) return false;

                for(int i=s; i<=e; i++) {
                    if(board[x][i] != cur || arr[i]) return false;
                }

                for(int i=s; i<=e; i++) {
                    arr[i] = true;
                }
            }
            // 2. 높이가 낮아지는 경우
            else {
                 int s = y+1;
                 int e = y+l;

                if(e >= n) return false;

                for(int i=s; i<=e; i++) {
                    if(board[x][i] != next || arr[i]) return false;
                }

                for(int i=s; i<=e; i++) {
                    arr[i] = true;
                }
            }
        }

        return true;
    }

    // 세로로 확인
    static boolean checkCol(int y) {
        // 경사로 설치 가능 유무에 따른 결과를 저장
        boolean[] arr = new boolean[n];

        for(int x=0; x<n-1; x++) {
            int cur = board[x][y];
            int next = board[x+1][y];

            // 높이가 같은 경우
            if(cur == next) continue;
            // 높이 차가 2 이상, 이동 불가
            if(Math.abs(cur-next) > 1) return false;

            // 경사로 설치 가능
            // 1. 높이가 높아지는 경우
            if(cur < next) {
                int s = x-l+1;
                int e = x;

                if(s < 0) return false;

                for(int i=s; i<=e; i++) {
                    if(board[i][y] != cur || arr[i]) return false;
                }

                for(int i=s; i<=e; i++) {
                    arr[i] = true;
                }
            }
            // 2. 높이가 낮아지는 경우
            else {
                int s = x+1;
                int e = x+l;

                if(e >= n) return false;

                for(int i=s; i<=e; i++) {
                    if(board[i][y] != next || arr[i]) return false;
                }

                for(int i=s; i<=e; i++) {
                    arr[i] = true;
                }
            }
        }

        return true;
    }
}
