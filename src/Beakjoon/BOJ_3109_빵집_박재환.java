package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_3109_빵집_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        bw.flush();
        bw.close();
    }

    static int row, col;    // 격자의 크기
    static char[][] map;     // 격자
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        for (int r = 0; r < row; r++) {
            String input = br.readLine().trim();
            for (int c = 0; c < col; c++) {
                map[r][c] = input.charAt(c);
            }
        }
        br.close();

        getMaxPipe();
    }

    static void getMaxPipe() throws IOException {
        int stealCount = 0;

        for (int r = 0; r < row; r++) {
            if(canSteal(r,0)) stealCount++;
        }

        bw.write(String.valueOf(stealCount));
    }


    // 이동 가능한 곳으로 이동하며 파이프를 설치한다.
    // 우선적으로 위에서부터 설치하도록 한다.
    static boolean canSteal(int r, int c) {
        map[r][c] = '-';    // 파이프 설치

        if(c == col-1) {    // 끝까지 도착했다면 -> 파이프를 설치
            return true;
        }

        if(r > 0 &&  map[r-1][c+1] == '.') {    // 오른쪽 위로 이동이 가능하고, 파이프 설치가 가능한 경우
            if(canSteal(r-1, c+1)) return true;
        }

        if(map[r][c+1] == '.') {    // 오른쪽으로 파이프 설치가 가능한경우
            if(canSteal(r, c+1)) return true;
        }

        if(r+1 < row && map[r+1][c+1] == '.') {    // 오른쪽 아래로 이동이 가능하고, 파이프 설치가 가능한경우
            if(canSteal(r+1, c+1)) return true;
        }

        return false;
    }
}

/*
    격자는 R * C 로 표현된다.
    파이프는 (오른쪽, 오른쪽 아래, 오른쪽 위) 방향으로만 설치된다.

    최대한 많은 파이프를 설치하려한다. -> 각 열마다 파이프를 설치하는 것이 최대일듯
    파이프는 겹치거나, 접할 수 없다.
    
    -------------------------------------------------------------------
    
    파이프를 가장 많이 설치하려면 위에서부터 차례로 설치하는 경우가 가장 많이 설치할 수 있다. -> 겹치거나 접할 수 없으므로
    
    각 열의 첫 행마다 순회하며 파이프를 설치할 수 있는지 확인한다.
    
    탐색 순서는 
    1. 오른쪽 위
    2. 오른쪽
    3. 오른쪽 아래
    순으로 탐색하면 boolean 값을 사용하여 파이프의 설치 가능 여부를 확인한다.
 */