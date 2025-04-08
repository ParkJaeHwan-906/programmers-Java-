package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1194_달이차오른다가자_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int row, col;
	static char[][] map;
	static int[] startPoint;
	static void init() throws IOException {
		startPoint = new int[2];
		
		st = new StringTokenizer(br.readLine().trim());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new char[row][col];
		for(int x=0; x<row; x++) {
			String inputStr = br.readLine().trim(); 
			for(int y=0; y<col; y++) {
				map[x][y] = inputStr.charAt(y);
				
				if(map[x][y] == '0') {
					startPoint[0] = x;
					startPoint[1] = y;
				}
			}
		}
		
		getMinDist();
	} 
	
	static class Minsu {
		int x, y;
		// 가지고 있는 열쇠를 비트로 표현
		/*
		 * a : 000001
		 * b : 000010
		 * c : 000100
		 * d : 001000
		 * e : 010000
		 * f : 100000
		 * 2^6 => 64 -> visits[][][64]
		 */
		int keyBits;	
		int dist;
		
		public Minsu(int x, int y, int keyBits, int dist) {
			this.x = x;
			this.y = y;
			this.keyBits = keyBits;
			this.dist = dist;
		}
		
		void printInfo() {
			System.out.println("현위치 : " + x + ", " +y);
			System.out.println("키 : " + keyBits);
		}
	}
	/*
	 * BFS 를 기준으로 탐색을 수행
	 * 3차원 방문 배열을 확인하여, 해당 키를 가지고 방문한 적이 있는지 체크한다. 
	 * [키 없음, a, b, c, d, e, f] => 7가지
	 */
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static void getMinDist() {
		boolean[][][] visited = new boolean[row][col][64];
		
		Queue<Minsu> q = new LinkedList<>();
		// 초기 설정 
		q.offer(new Minsu(startPoint[0], startPoint[1], 0,0));
		visited[startPoint[0]][startPoint[1]][0] = true;
		
		while (!q.isEmpty()) {
			Minsu cur = q.poll();

			if (map[cur.x][cur.y] == '1') {	// 출구에 도착했다면 함수 종료
				System.out.println(cur.dist);
				return;
			}

			// 현 위치에서 상하좌우로 이동 
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];

				// 격자를 벗어난다면 
				if (nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
				// 벽이라면 
				if (map[nx][ny] == '#') continue;
				// 이미 방문했다면 ( 같은 키들을 가지고 )
				if(visited[nx][ny][cur.keyBits]) continue;

				// 열쇠를 주움
				if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
					int newKey = cur.keyBits | (1 << map[nx][ny]-'a');
					
					visited[nx][ny][newKey] = true;
					q.offer(new Minsu(nx, ny, newKey, cur.dist+1));
				}
				else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {	// 문을 만남
					// 일치하는 키가 있는지 확인 
					if(((1 << map[nx][ny]-'A') & cur.keyBits) == 0) continue;
					
					// 키가 일치함 -> 이동 가능 
					visited[nx][ny][cur.keyBits] = true;
					q.offer(new Minsu(nx, ny, cur.keyBits, cur.dist+1));
				}
				else {	// 빈칸
					visited[nx][ny][cur.keyBits] = true;
					q.offer(new Minsu(nx, ny, cur.keyBits, cur.dist+1));
				}
			}
		}

		// 출구를 못 찾았다면, -1
		System.out.println(-1);
	}
}

/* 
 * N(세로) x M(가로) 격자
 * . : 빈 칸
 * # : 벽 
 * a,b,c,d,e,f : 열쇠
 * A,B,C,D,E,F : 문 ( 대응하는 열쇠가 있어야만 갈 수 있음 )
 * 0 : 민식이의 현 위치 
 * 1 : 출구 
 * 
 * 이동 횟수의 최소값
 * 방문처리..? -> 열쇠가 뒤에있거나 하는 경우 방문 처리를 어떻게 해야하는지  
 * -> 해당 열쇠를 가지고 똑같이 방문한 적이 있는지 확인 
 * [N][M][6] -> 열쇠는 6가지 종류 확정인가?
 */