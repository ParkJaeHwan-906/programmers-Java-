package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_3055_탈출_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int row, col;
	static char[][] map;
	static int[] start;
	static Queue<int[]> q;
	static int[][] waterMap;	// 각 칸에 물이 도달하는 최소 시간을 기록할 배열 
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static void init() throws IOException {
		start = new int[2];	// 고슴도치 위치
		q = new LinkedList<int[]>();	// 초기 물의 위치를 저장할 큐 
		
		st = new StringTokenizer(br.readLine().trim());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		waterMap = new int[row][col];
		map = new char[row][col];
		for (int x = 0; x < row; x++) {
			String inputStr = br.readLine().trim();
			for (int y = 0; y < col; y++) {
				map[x][y] = inputStr.charAt(y);
				
				if(map[x][y] == 'S') {	// 출발 위치
					start[0] = x;
					start[1] = y;
				} else if(map[x][y] == '*') {	// 물의 위치 + 초기 시간 0초
					q.offer(new int[] {x,y,0});	
				} 
			}
		}
		
		canRun();
	}
	
	/*
	 * 고슴도치가 물보다 빨리 비버굴에 도착할 수 있는지 확인한다
	 * 사전에 구한 waterMap 보다 빠른 시간에 비버굴에 도착할 수 있다면 true
	 * 아니면 false
	 */
	static void canRun() {
		waterFlow();
		
		// 큐 초기화
		q.clear();
		// 방문 처리 배열
		boolean[][] visited = new boolean[row][col];
		// 시작 위치 설정
		q.offer(new int[] {start[0], start[1], 0});
		visited[start[0]][start[1]] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int time = cur[2];
			// 목적지에 도착했는지 확인 
			if(map[x][y] == 'D') {
				System.out.println(time);
				return;
			}
			
			// 물이 더 빨리 도착한다면 해당 경로는 이동할 수 없음 
			if(waterMap[x][y] <= time) continue;
			
			// 고슴도치를 이동시킨다. 
			for(int dir=0; dir<4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				// 격자 범위를 벗어나는지
				if(nx < 0 || ny < 0 || nx >= row || ny >= col || visited[nx][ny]) continue;
				// 이동이 가능한 지역인지
				if(map[nx][ny] == 'X') continue;
				// 더 빠르게 이동한 물이 있는지
				if(waterMap[nx][ny] < time+1) continue;
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, time+1});
			}
		}
		System.out.println("KAKTUS");
	}
	/*
	 * 물이 흘러 각 칸에 도착하는 최소 시간을 구한다. 
	 */
	static void waterFlow() {
		// waterMap 초기 값 설정 
		for(int x=0; x<row; x++) {
			Arrays.fill(waterMap[x], Integer.MAX_VALUE);
		}
		
		// BFS 를 사용해 물이 각 칸으로 도착하는 최소 시간을 DP 를 이용해 기록 
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int time = cur[2];
			
			if(waterMap[x][y] <= time) continue;
			waterMap[x][y] = time;
			
			// 물을 상하좌우로 이동시킨다. 
			for(int dir=0; dir<4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				// 격자를 벗어나는지
				if (nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
				// 이동할 수 있는지 
		        if (map[nx][ny] == 'X' || map[nx][ny] == 'D') continue;
				
				q.offer(new int[] {nx, ny, time+1});
			}
		}
//		for(int[] arr : waterMap) {
//			System.out.println(Arrays.toString(arr));
//		}
	}
}
/* 
 * R x C 격자 
 * . : 반 컨
 * * : 물
 * X : 돌 
 * D : 비버 굴 
 * S : 고슴도치 위치
 * 
 * 고슴도치 상하좌우 이동 가능
 * 물 도 매 분마다 비어있는 칸으로 이동 ( 상하좌우 한번에? )
 * 
 * 이동 불가하다면 
 * KAKTUS 출력 
 */