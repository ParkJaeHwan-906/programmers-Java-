package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_17070_파이프옮기기1_박재환 {
    static BufferedReader br;
    static int mapSize;
    static int[][] map;
    static int cnt;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }
    
    static StringTokenizer st;
    
    // 파이프의 현재 상태를 기록
    static int[] head, tail;
    
    static void init() throws IOException {
        cnt = 0;
        
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new int[mapSize][mapSize];
        
        // 맵 정보 입력 받기
        for (int x = 0; x < mapSize; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int y = 0; y < mapSize; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 초기 파이프 위치 설정 (0, 0) -> (0, 1)
        head = new int[] {0, 1}; 
        tail = new int[] {0, 0}; 
        
        movePipe(head[0], head[1], tail[0], tail[1], 0);  // 파이프 이동 시작
        System.out.println(cnt);
    }
    
    // 파이프 이동 처리
    static void movePipe(int hx, int hy, int tx, int ty, int dir) {
        // 파이프가 목표 지점에 도달
        if (hx == mapSize - 1 && hy == mapSize - 1) {
            cnt++;
            return;
        }
        
        // 파이프 가로, 세로, 대각선 상태 구분 
        if (dir == 0) {  // 가로
            // 1. 오른쪽으로 가기
            if (canMove(hx, hy + 1)) {
                movePipe(hx, hy + 1, tx, ty, 0);
            }
            // 2. 대각선으로 가기
            if (canMove(hx + 1, hy + 1) && canMove(hx, hy + 1) && canMove(hx + 1, hy)) {
                movePipe(hx + 1, hy + 1, tx + 1, ty + 1, 2);
            }
        } else if (dir == 1) {  // 세로
            // 1. 아래로 가기
            if (canMove(hx + 1, hy)) {
                movePipe(hx + 1, hy, tx + 1, ty, 1);
            }
            // 2. 대각선으로 가기
            if (canMove(hx + 1, hy + 1) && canMove(hx, hy + 1) && canMove(hx + 1, hy)) {
                movePipe(hx + 1, hy + 1, tx + 1, ty + 1, 2);
            }
        } else {  // 대각선
            // 1. 오른쪽으로 가기
            if (canMove(hx, hy + 1)) {
                movePipe(hx, hy + 1, tx, ty + 1, 0);
            }
            // 2. 아래로 가기
            if (canMove(hx + 1, hy)) {
                movePipe(hx + 1, hy, tx + 1, ty, 1);
            }
            // 3. 대각선으로 가기
            if (canMove(hx + 1, hy + 1) && canMove(hx, hy + 1) && canMove(hx + 1, hy)) {
                movePipe(hx + 1, hy + 1, tx + 1, ty + 1, 2);
            }
        }
    }
    
    // 격자 범위 내에 있고 벽이 아닌지 확인
    static boolean canMove(int x, int y) {
        return (x >= 0 && y >= 0 && x < mapSize && y < mapSize && map[x][y] == 0);
    }
}


/* 
 * N x N 격자 
 * 파이프는 1*2 크기 
 * 회전 가능 
 * 
 * 파이프는 3 가지 이동하는 경우를 갖는다. 
 * 모든 경우를 구해라 
 */