package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_5650_핀볼게임_박재환 {
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
		br.close();
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int mapSize;		// 격자의 크기
	static int[][] map;		// 격자 
	static List<int[]>[] allPoints;		// 각 격자의 정보를 저장 
	static void init() throws IOException {
		allPoints = new ArrayList[11];	// 빈 공간부터, 블록, 웜홀의 정보를 기록
		for(int i=0; i<11; i++) {
			allPoints[i] = new ArrayList<>();
		}
		
		mapSize = Integer.parseInt(br.readLine().trim());
		map = new int[mapSize][mapSize];
		
		for(int x=0; x<mapSize; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y=0; y<mapSize; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				
				if(map[x][y] == -1) continue; 
				allPoints[map[x][y]].add(new int[] {x,y});
			}
		}
		
		getMaxScore();
	}
	
	// 모든 빈간에서 핀볼을 시작해본다. 
	static int startX, startY;	// 초기 시작 위치를 기억
	static int maxScore;	// 최대 점수
	static void getMaxScore() {
		maxScore = 0;
		for(int[] startPoint : allPoints[0]) {
			startX = startPoint[0];
			startY = startPoint[1];
			
			// 현 위치에서 시작하는 시뮬레이션을 돌린다. 
			// 진행 방향을 임의로 선정이 가능하다. 
			for(int dir=0; dir<4; dir++) {
				playGame(startX, startY, dir);
//				System.out.println();
			}
		}
		
		sb.append(maxScore);
	}
	
	/*
	 * 각 게임을 시물레이션한다. 
	 * 출발 위치와, 진행 방향을 받는다. 
	 */
	static void playGame(int x, int y, int dir) {
		int score = 0;
		
		while(true) {
//			System.out.println(x+", "+y);
			// 새로운 이동 위치 
			x += dx[dir];
			y += dy[dir];
			
			// 0. 격자 외부로 나가는 경우 -> 방향을 반대로 전환한다. ( 5번 블록과 동일하게 처리 )
			if(x < 0 || y < 0 || x >= mapSize || y >= mapSize) {
				dir = blockMoves[5][dir];
//				x += dx[dir];
//				y += dy[dir];
				score++;
				continue;
			}
			
			// 1. 종료 조건
			// 블랙홀을 만나거나, 출발지로 돌아오는 경우
			if(map[x][y] == -1 || (x==startX && y==startY)) break;
			
			// 2. 블록을 만나기 전까지는 계속해서 이동 
			if(map[x][y] == 0) continue;
			
			// 4. 블록 혹은 웜홀을 만나는 경우 
			switch (map[x][y]) {
			// 블록을 만나는 경우
			case 1: case 2: case  3: case 4: case 5:
				// 방향 전환 후, 이전 위치로 되돌린다.
				dir = blockMoves[map[x][y]][dir];
//				x += dx[dir];
//				y += dy[dir];
				score++;
				break;
			// 웜홀을 만나는 경우, 반대편으로 이동한다. 
			case 6: case 7: case 8: case 9: case 10:
				for(int[] wormHole : allPoints[map[x][y]]) {
					if(wormHole[0] != x || wormHole[1] != y) {
						x = wormHole[0];
						y = wormHole[1];
						break;
					}
				}
				break;
			}
		}
//		System.out.println(score);
		maxScore = Math.max(score, maxScore);
	}
	
	/*
	 * 각 블록의 진입방향에 따라 dx, dy 값을 저장한다. 
	 */
	// 우 하 좌 상
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[][] blockMoves = {
			// 우, 하, 좌, 상 으로 진입시 반사각 idx
			{},
			{2,0,3,1},	// 1 번 삼각형
			{2,3,1,0},	// 2 번 삼각형
			{1,3,0,2},	// 3 번 삼각형
			{3,2,0,1},	// 4 번 삼각형
			{2,3,0,1}	// 5 번 사각형
	};
			
}
/* 
 * N x N 격자
 * 정사각형 블록과, 4가지 형태의 삼각형 블록들이 섞여 있다. 
 * 웜홀과 블랙홀이 존재한다. 
 * 
 * 삼각형 위치에 따라 번호가 주어짐 
 * 1 : 좌하단 
 * 2 : 좌상단
 * 3 : 우상단
 * 4 : 우하단
 * => 삼각형을 만날때, 빗변에 닿으면 90도로 꺾임, 나머지는 반대로 반사 ( 벽도 마찬가지 ) => 이게 중요 
 * 5 : 정사각형 
 * 
 * 6 ~ 10 : 웜홀 ( 웜홀에 빠지면, 같은 번호를 갖는 다른 위치로 이동됨 -> 이동방향은 그대로 유지 )
 * -> 반드시 쌍으로 유지
 * -1 : 블랙홀 -> 만나면 즉시 게임 종료 
 * 
 * 게임 종료 조건 
 * - 블랙홀에 빠짐
 * - 출발 위치로 돌아옴 
 * 
 * 점수 : 벽이나, 블록에 부딪힌 횟수 -> 웜홀은 포함 X 
 */