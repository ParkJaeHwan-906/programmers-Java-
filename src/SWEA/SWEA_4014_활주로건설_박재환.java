package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_4014_활주로건설_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase < TC+1; testCase++) {
			sb.append('#').append(testCase).append(' ');
			init();
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int mapSize, rampLen;	// 격자의 크기, 경사로의 길이 
	static int[][] map;
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		
		mapSize = Integer.parseInt(st.nextToken());
		rampLen = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize][mapSize];
		for(int x=0; x<mapSize; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y=0; y<mapSize; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		getMaxRunway();
	}
	
	/*
	 * 설치할 수 있는 최대 활주로의 개수를 구한다. 
	 * 가로 -> 세로 순으로 탐색한다. (최악의 경우 40가지)
	 * 
	 * 1. 높이가 변하면 경사로를 설치한다.
	 * 		1-1. 이때 경사로의 높이는 1로, 높이  차가 1 이상인 경우 활주로를 건설할 수 없다.
	 * 2. 높이가 증가하면, 해당 위치 이전에 K 만큼 동일 높이 구역이 확보되어야한다.
	 * 3. 높이가 감소하면, 해당 위치 이후에 K 만큼 동일 높이 구역이 확보되어야한다. 
	 */
	static void getMaxRunway() {
		int runWayCnt = 0;
		
		// 1. 가로로 활주로를 설치한다.
		for(int x=0; x<mapSize; x++) {
			boolean canPlace = true;	// 활주로를 설치할 수 있는지 
			// 이전에 경사로를 설치한적이 있는지 확인할 배열 
			boolean[] isUsed = new boolean[mapSize];
			
			int prev = map[x][0];	// 초기 값 설정 
			for(int y=0; y<mapSize; y++) {
				// 높이가 동일하면 검사 X 
				if(prev == map[x][y]) continue;
				
				canPlace = checkHeight(x, y, 0, prev, isUsed);
				// 높이가 다름 
				if(canPlace == false) {
					break;	// 활주로를 설치할 수 없다.
				}
				// 활주로 설치 가능 
				prev = map[x][y];
			}
			
			runWayCnt = canPlace ? runWayCnt+1 : runWayCnt;
		}
		
		// 2. 세로로 활주로를 설치한다.
		for(int y=0; y<mapSize; y++) {
			boolean canPlace = true;	// 활주로를 설치할 수 있는지 
			// 이전에 경사로를 설치한적이 있는지 확인할 배열 
			boolean[] isUsed = new boolean[mapSize];
			
			int prev = map[0][y];	// 초기 값 설정 
			for(int x=0; x<mapSize; x++) {
				// 높이가 동일하면 검사 X 
				if(prev == map[x][y]) continue;
				
				canPlace = checkHeight(x, y, 1, prev, isUsed);
				// 높이가 다름 
				if(canPlace == false) {
					break;	// 활주로를 설치할 수 없다.
				}
				// 활주로 설치 가능 
				prev = map[x][y];
			}
			
			runWayCnt = canPlace ? runWayCnt+1 : runWayCnt;
		}
		
		sb.append(runWayCnt);
	}
	
	/*
	 * 해당 위치를 기준으로 경사로를 설치할 수 있는지 확인한다. 
	 * [x, y, 현재 활주로 설치 진행방향 (0 : 가로, 1 : 세로), 이전의 높이]
	 */
	static boolean checkHeight(int x, int y, int category, int prev, boolean[] isUsed) {
		// 1. 높이가 증가하는지 감소하는지 확인한다.
		//		음수 : 증가, 양수 : 감소 
		int flag = prev - map[x][y];
		
		// 높이 차가 1 이상이라면 설치 불가 
		if(Math.abs(flag) != 1) return false;
		
		// 사용 여부 배열 복사
		boolean[] isUsed_copy = new boolean[mapSize];
		for(int i=0; i<mapSize; i++) {
			isUsed_copy[i] = isUsed[i];
		}
		
		// 2. 경사로를 설치할 수 있을만큼의 공간이 존재하는지 확인한다. 
		if(flag > 0) {	// 높이가 감소
			// 현위치부터 확인 
			// 범위 확인 
			int range = (category == 0 ? y+rampLen : x+rampLen)-1;

			if(range >= mapSize) return false;
			int temp = category == 0 ? map[x][y+1] : map[x+1][y];
			for(int after=category == 0? y : x; after<=range; after++) {
				// 높이가 동일하지 않다면
				// 이미 경사로를 설치했다면 
				if(temp != (category == 0 ? map[x][after] : map[after][y]) || isUsed[after]) {
					return false;
				}
				
				isUsed_copy[after] = true;
			}
		} else if (flag < 0) {	// 높이가 증가
			// 현위치 이전 확인 
			// 범위 확인 

			int range = category == 0 ? y-rampLen : x-rampLen;
			if(range < 0) return false;
			
			int temp = category == 0 ? map[x][y-1] : map[x-1][y];
			for(int before= category == 0 ? y-1 : x-1; before>=range; before--) {
				// 높이가 동일하지 않다면
				// 이미 경사로를 설치했다면 
				if(temp != (category == 0 ? map[x][before] : map[before][y]) || isUsed[before]) {
					return false;
				}
				isUsed_copy[before] = true;
			}
		}
		
		// isUsed 배열 갱신 
		for(int idx=0; idx<mapSize; idx++) {
			isUsed[idx] = isUsed_copy[idx];
		}
		return true;
	}
}

/* 
 * N x N 격자 (최대 20 x 20)
 * 각 칸은 해당 지형의 높이
 * 
 * 활주로는 높이가 동일한 구간에서 건설이 가능한다.
 * 높이가 다른 경우 경사로를 설치해야한다. 
 * 경사로의 길이 X(최대 4), 높이 1
 * 
 * 완탐시에 
 * 20 * 2 개의 행렬을 탐색
 */