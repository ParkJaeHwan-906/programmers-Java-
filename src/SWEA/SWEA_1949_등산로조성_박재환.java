package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1949_등산로조성_박재환 {
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
	static int mapSize, maxDeep, maxDist;
	static int[][] map; 
	static boolean[][] visited;
	static List<int[]> maxPlaces;
	static void init() throws IOException {
		maxDist = 0;
		maxPlaces = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine().trim());
		mapSize = Integer.parseInt(st.nextToken());
		maxDeep = Integer.parseInt(st.nextToken());
		
		map = new int[mapSize][mapSize];
		visited = new boolean[mapSize][mapSize];
		int max = 0;
		for(int x=0; x<mapSize; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y=0; y<mapSize; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				
				max = Math.max(max, map[x][y]);
			}
		}
		
		// 등산로의 시작은 가장 높은 봉우리를 기준으로 
		for(int x=0; x<mapSize; x++) {
			for(int y=0; y<mapSize; y++) {
				if(map[x][y] == max) maxPlaces.add(new int[] {x, y});
			}
		}
		
		// 각 높은 봉우리에서 탐색한다.
		for(int[] point : maxPlaces) {
			visited[point[0]][point[1]] = true;
			bestRoute(point[0], point[1], 1, false);
			visited[point[0]][point[1]] = false;
		}
		
		sb.append(maxDist);
	}
	
	/*
	 * DFS 를 통해 탐색한다? 
	 * 현 위치를 기준 상하좌우 탐색하다가, 높아지는/같은 위치 발생 시 깎기?
	 */
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static void bestRoute(int x, int y, int dist, boolean dig) {
		boolean move = false;
		
		// 인접한 방향으로 탐색한다. 
		for(int dir=0; dir<4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			// 범위를 벗어난다면 더 이상 진행할 수 없음 
			// 이미 방문한 경우 재탐색할 필요 없음 
			if(isBoard(nx, ny) || visited[nx][ny])  continue; 
			// 높이가 동일하거나, 높은데, 깎을 수 없다면 탐색 X 
			if(map[x][y] <= map[nx][ny] && dig) continue;
			// 깎을 수 있지만, 깊이가 부족함 
			if(map[nx][ny]-map[x][y] >= maxDeep) continue;
			
			// 어찌저찌 이동이 가능함 
			move = true;
			visited[nx][ny] = true;
			if(map[x][y] <= map[nx][ny]) {	// 산을 깎아야하는 경우
				int temp = map[nx][ny];
				map[nx][ny] = map[x][y]-1;
				bestRoute(nx, ny, dist+1, true);
				map[nx][ny] = temp;
			}
			else {
				// 깎지 않아도 됨 
				bestRoute(nx, ny, dist+1, dig);
			}
			visited[nx][ny] = false;
		}
		
		// 만약 움직일 수 없다면 해당 루트는 끝임 
		if(!move) {
//			for(boolean[] arr : visited) {
//				System.out.println(Arrays.toString(arr));
//			}
//			System.out.println();
			maxDist = Integer.max(maxDist, dist);
		}
	}
	
	static boolean isBoard(int x, int y) {
		return x < 0 || y < 0 || x >= mapSize || y>= mapSize;
	}
}

/* 
 * 최대한 긴 등산로를 만들 계획이다. 
 * 각 칸의 숫자는 지형의 높이를 나타낸다. 
 * 
 * 1. 등산로는 가장 높은 봉우리에서 시작해야한다. 
 * 2. 높은 지형에서 낮은 지형으로 연결되어야한다. ( 상하좌우 ) 
 * 3. 딱 한 곳을 정해서 최대 K 깊이 만큼 지형을 깎는 공사를 할 수 있다. -> 최소로 깎는게 좋은거 아닌가? -> 높아야 갈 수 있는 곳이 많으니
 */