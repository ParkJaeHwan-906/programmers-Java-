package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_7727_함께하는효도 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int mapSize, friendCnt;
	static int[][] map;
	static int[][] friends;
	static int maxValue;
	static void init() throws IOException {
		maxValue = 0;
		
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		friendCnt = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize+1][mapSize+1];
		for(int x=1; x<mapSize+1; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y=1; y<mapSize+1; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		friends = new int[friendCnt][2];	// [친구 번호][x,y]
		for(int idx=0; idx<friendCnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			friends[idx][0] = Integer.parseInt(st.nextToken());
			friends[idx][1] = Integer.parseInt(st.nextToken());
		}
		
		// 입력 확인 
//		for(int[] arr : map) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println();
//		
//		for(int[] arr : friends) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println();
		
		int nx = friends[0][0];
		int ny = friends[0][1];
		int initSum = map[nx][ny];
		map[nx][ny] = 0;
		getMaxValue(0, 0, nx, ny, initSum);
		
		System.out.println(maxValue);
	}
	
	/*
	 * DFS / BFS -> 동시 처리 BFS 를 하기에는 최대 값을 찾아야함 
	 * DFS 사용 
	 */
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static void getMaxValue(int friendIdx, int sec, int x, int y, int sum) {
		if(sec == 3) {	// 현재 인원의 이동 시간을 모두 채웠다면
			if(++friendIdx == friendCnt) {	// 모든 인원을 탐색했다면
				maxValue = Math.max(maxValue, sum);
			} else {	// 더 탐색 가능한 인원이 남아있음 
				// 다음 사람으로 이동 
				int nx = friends[friendIdx][0];
				int ny = friends[friendIdx][1];
				
				int temp = map[nx][ny];
				map[nx][ny] = 0;
				getMaxValue(friendIdx, 0, nx, ny, sum + temp);
				map[nx][ny] = temp;
			}
			return;
		}
		
		// 이동 가능
		for(int dir=0; dir<4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(isBoard(nx, ny)) continue;
			
			// 범위를 벗어나지 않고, 이동 가능
			// 이미 방문한 위치는 방문해도 되지만, 친구와 마주치는 경우는 없어야함 
			int temp = map[nx][ny];
			map[nx][ny] = 0;	// 친구가 마주쳤을 때, 싸움이 일어나니 ( 중복 수확 ) 0 처리 
			getMaxValue(friendIdx, sec+1, nx, ny, sum+temp);
			map[nx][ny] = temp;
		}
	}
	
	// 격자의 범위를 벗어나는지 확인 
	static boolean isBoard(int x, int y) {
		return x < 1 || y < 1 || x >= mapSize+1 || y >= mapSize+1;
	}
}

/* 
 * N x N 크기의 격자 
 * 각 칸에서 열매를 수확하는데 걸리는 시간은 0 초, 한 곳에 여러번 방문해도 1번만 수확 가능 
 * 친구를 마주치는 일은 없게한다. -> 이미 방문한 곳은 방문할 수 있지만, 마주치게 하지는 않는다. 
 * 
 * m 명의 친구들이 3초 동안 최대로 얻을 수 있는 열매 수확량의 총 합을 구하라 
 */