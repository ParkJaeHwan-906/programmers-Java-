package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_7594_나무조경 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int mapSize;				// 격자 크기
	static int[][] map;				// 격자
	static boolean[][] visited;		// 방문 처리
	static int maxValue;			// 최대 가치
	static void init() throws IOException {
		mapSize = Integer.parseInt(br.readLine().trim());
		visited = new boolean[mapSize][mapSize];
		map = new int[mapSize][mapSize];
		for(int x=0; x<mapSize; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y=0; y<mapSize; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 예외 -> 격자의 크기가 2 x 2 인 경우 -> 4개의 쌍이 생길 수 없다. 
		//		=> 각 칸의 합이 최대 크기 
		if(mapSize == 2) {
			for(int[] arr : map) {
				for(int i : arr) {
					maxValue += i;
				}
			}
		} else {	// 2. 완전 탐색으로 최대 가치 찾기 
			getMaxValue(0, 0);
		}
		
		System.out.println(maxValue);
	}
	
	/*
	 * 격자의 크기가 최대 4 x 4 이므로 완전 탐색 ( n-queen ) 처럼 탐색한다. 
	 * 
	 * 25.04.22 ( 개선 ) 
	 * 탐색 방향은 오른쪽 아래로만 탐색한다면, 모든 경우를 탐색할 수 있다. 
	 */
//	static int[] dx = {0,1,0,-1};
//	static int[] dy = {1,0,-1,0};
	static int[] dx = {0,1};
	static int[] dy = {1,0};
	static void getMaxValue(int pairIdx, int sum) {
		if(pairIdx == 4) {	// 최대 4개의 쌍이 가능 
			maxValue = Math.max(sum, maxValue);
			return;
		}
		
		// 현재 쌍을 구해야하는 경우
		// 이전에 구한 위치에 대해서는 중복 탐색 방지 
		for(int x=0; x<mapSize; x++) {
			for(int y=0; y<mapSize; y++) {
				// 이미 묶인 위치에 대해서는 탐색 X 
				if(visited[x][y]) continue;
				
				visited[x][y] = true;
				// 새로운 쌍을 생성할 수 있음 
//				for(int dir=0; dir<4; dir++) {
				for(int dir=0; dir<2; dir++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					// 격자 내부인지
					if(isBoard(nx, ny)) continue;
					// 이미 묶인 위치인지
					if(visited[nx][ny]) continue;
					
					// 묶을 수 있다면 
					visited[nx][ny] = true;
					
					getMaxValue(pairIdx+1, sum+map[x][y]+map[nx][ny]);
					
					visited[nx][ny] = false;
				}
				visited[x][y] = false;
			}
		}
	}
	
	/*
	 * 현 좌표가 격자 내부에 있는지 
	 * 있다면 false, 밖이라면 true
	 */
	static boolean isBoard(int x, int y) {
		return x < 0 || y < 0 || x >= mapSize || y >= mapSize;
	}
}

/* 
 * N x N 격자
 * 상하좌우로 닿아있는 경우를 인접하다 한다. 
 * 최대 4번 인접한 두 나무를 묶으려한다. 
 * 나무들은 서로 겹치면 안된다. 
 * 
 * 나무 합을 최대로 만든다.
 */