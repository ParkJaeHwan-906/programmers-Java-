package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_2239_스도쿠_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		init();
		br.close();
		System.out.println(sb);
	}
	
	static final int MAP_SIZE = 9;
	static int[][] map;
	static boolean[][] rowUsed;	// 방문 처리를 위한 배열 ( 각 자리에는 1~9 까지의 수가 올 수 있음 )
	static boolean[][] colUsed;	// 방문 처리를 위한 배열 ( 각 자리에는 1~9 까지의 수가 올 수 있음 )
	static boolean[][] squareUsed;	// 3 x 3 공간의 방문 처리를 위한 배열 
	static List<int[]> blanks;	// 채워야할 공간을 기록 
	static boolean find;
	static void init() throws IOException {
		find = false;
		map = new int[MAP_SIZE][MAP_SIZE];
		squareUsed = new boolean[MAP_SIZE][10];
		blanks = new ArrayList<>();
		
		// 가로
		rowUsed = new boolean[MAP_SIZE][10];	// 1-base
		// 세로
		colUsed = new boolean[MAP_SIZE][10];	// 1-base
		for(int x=0; x<MAP_SIZE; x++) {
			String input = br.readLine().trim();
			for(int y=0; y<MAP_SIZE; y++) {
				map[x][y] = input.charAt(y) - '0';
				
				if(map[x][y]==0) {
					blanks.add(new int[] {x,y});
					continue;
				}
				
				// 사용처리 
				rowUsed[x][map[x][y]] = true;
				colUsed[y][map[x][y]] = true;
				// x, y 좌표로 3 x 3 크기의 영역을 구별함 
				int squareIdx = (x / 3) * 3 + (y / 3);
				squareUsed[squareIdx][map[x][y]] = true;
			}
		}
		
		finishGame(0);
	}
	
	static void finishGame(int blankIdx) {
		if(find) return;	// DFS 이므로, 가장 첫번째 값이 작을걸?
		
		if(blankIdx == blanks.size()) {	// 마지막까지 칸을 다 채웠다면
			for(int x=0; x<MAP_SIZE; x++) {
				for(int y=0; y<MAP_SIZE; y++) {
					sb.append(map[x][y]);
				}
				sb.append('\n');
			}
			find = true;
			return;
		}
		
		// 조합을 더 짤 수 있다면
		// 현자리
		int[] cur = blanks.get(blankIdx);
		int x = cur[0];
		int y = cur[1];
		int squareIdx = (x/3)*3 + (y/3);
		
		// 현재 자리에 올 수 있는 최적의 수를 구해본다. 
		for(int num=1; num<10; num++) {
			// 현재 행과 열에서 사용된 적이 없는 수만 탐색한다. 
			if(rowUsed[cur[0]][num] || colUsed[cur[1]][num]|| squareUsed[squareIdx][num]) continue;
			
			rowUsed[cur[0]][num] = true; 
			colUsed[cur[1]][num] = true;
			squareUsed[squareIdx][num] = true;
			map[cur[0]][cur[1]] = num;
			finishGame(blankIdx+1);
			squareUsed[squareIdx][num] = false;
			rowUsed[cur[0]][num] = false; 
			colUsed[cur[1]][num] = false;
		}	
	}
}

/* 
 * 스도쿠
 * 각 행과 열에 1~9 숫자를 중복없이 채우면 된다. 
 * + 3 x 3 크기의 보드에 1~9 숫자가 중복없이 나와야한다. 
 * 
 * 9 x 9 격자가 주어짐 
 * 완탐 시 9 ^ 81 ??
 * 
 * n-queen 같은거 아닌가 
 * 백트래킹? 
 */