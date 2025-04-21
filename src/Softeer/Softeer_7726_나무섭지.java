package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_7726_나무섭지 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int row, col;
	static char[][] map;
	static int namX, namY;
	static int ghostTime;
	static int exitX, exitY;
	static void init() throws IOException {
		ghostTime = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine().trim());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new char[row][col];
		List<int[]> list = new ArrayList<>();
		for(int x=0; x<row; x++) {
			String input = br.readLine().trim();
			for(int y=0; y<col; y++) {
				map[x][y] = input.charAt(y);
				
				if(map[x][y] == 'N') {
					namX = x;
					namY = y;
				} else if(map[x][y] == 'G') {	// 유령이 들어올 떄마다 위치 기록 
					list.add(new int[] {x,y});
				} else if(map[x][y] == 'D') {
					exitX = x;
					exitY = y;					
				}
			}
		}
		
		// 유령이 출구까지의 최단 시간 계산 
		for(int[] arr : list) {
			int x = arr[0];
			int y = arr[1];
			
			int dist = Math.abs(exitX-x) + Math.abs(exitY-y);
			
			ghostTime = Math.min(ghostTime, dist);
		}
		// 유령 최단 시간 확인
//		System.out.println(ghostTime);

		// 남우가 출구까지 최단 시간
		minArrivedTime();
	}
	/*
		남우가 출구까지 이동하는 최단 시간을 구한다.
	 */
	static void minArrivedTime() {
		int[] dx = {0,1,0,-1};
		int[] dy = {1,0,-1,0};

		Queue<int[]> q = new LinkedList<int[]>();
		boolean[][] visited = new boolean[row][col];
		
		q.offer(new int[] {namX, namY, 0});
		visited[namX][namY] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			int time = cur[2];

			if(x == exitX && y == exitY) {	// 도착한 경우
				System.out.println(ghostTime > time ? "Yes" : "No");
				return;
			}

			// 최단 경로 탐색
			for(int dir=0; dir<4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];

				// 범위 확인
				if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
				// 이전 방문 여부 확인
				if(visited[nx][ny] || map[nx][ny] == '#') continue;

				// 방문 가능
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny, time+1});
			}
		}

		// 여기까지 나왔다는 것은 도달할 수 없다는 뜻
		System.out.println("No");
	}
}
