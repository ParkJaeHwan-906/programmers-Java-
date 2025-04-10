package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_17472_다리만들기2_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	} 
	
	static StringTokenizer st;
	static int row, col;
	static int[][] map;
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		map = new int[row][col];
		for(int x=0; x<row; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y=0; y<col; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
			}
		}
		
		getIslandInfo();
		makeConnInfo();
		
	}
	
	/*
	 * 각 섬의 경계들의 좌표로 연결할 수 있는 모든 섬의 연결정보를 구한다.  
	 */
	static PriorityQueue<int[]> pq;	// [from, to, dist] 를 저장한다. (MST 로 사용)
	static void makeConnInfo() {
		pq = new PriorityQueue<>((a, b) -> a[2]- b[2]);	// 가중치를 기준으로 오름차순 정렬
		
		// 각 섬의 좌표에서 직선으로 연결했을 때 나오는 섬들을 저장한다. 
		for(Integer islandIdx : islands.keySet()) {
			// 해당 섬의 경계면을 기준으로 다리를 건설한다. 
			for(int[] point : islands.get(islandIdx)) {
				/*
				 * 상 하 좌 우 로 건설해본다. 
				 * 이때 다리는 직선으로만 건설할 수 있고, 길이가 2 이상이여야한다. 
				 */
				int x = point[0];
				int y = point[1];
				
				for(int dir=0; dir<4; dir++) {
					int bridgeLen = 0;	// 다리 길이
					int nx = x, ny = y;
					// 다른 섬에 닿거나, 연결하지 못하는 경우 
					while(true) {
						nx += dx[dir];
						ny += dy[dir];
						
						// 범위 확인 
						if (nx < 0 || ny < 0 || nx >= row || ny >= col) break;
						// 다리를 세운다.
						if(map[nx][ny] == 0) {
							bridgeLen++;
							continue;
						}
						
						// 다른 땅에 도착!
			            if (map[nx][ny] == 1) {
			            	if(bridgeLen < 2 ) break;	// 길이 조건 미달 
			            	
			            	// 다른 섬에 도착했고, 길이도 충족한다. 
			            	// 어느 섬에 도착했는지 구한다. 
			            	int arrivedIdx = findIslandIdx(nx, ny);
			            	
			            	// 동일한 섬에 도착한 경우, 추가하지 않는다.
			            	if(islandIdx == arrivedIdx) break;
			            	// 다른 섬에 도착한 경우 이를 저장한다. 
			            	if(islandIdx != arrivedIdx) {
			            		pq.offer(new int[] {islandIdx, arrivedIdx, bridgeLen});
			            		break;
			            	}
			            }
					}
				}
			}
		}
		
		// test
//		System.out.println("pq test");
//		for(int[] arr : pq) {
//			System.out.println(Arrays.toString(arr));
//		}
		
		getMinDist();
	}
	
	/*
	 * 연결 정보를 가지고, MST 를 구성한다.
	 * PQ 를 사용해 크루스칼 알고리즘을 사용한다. 
	 */
	static void getMinDist() {
		int connCnt = 0, totalDist = 0;
		make();
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int from = cur[0];
			int to = cur[1];
			int dist = cur[2];
			
			// 연결
			if(union(from, to)) {
				totalDist += dist;
				if(++connCnt == islands.size()-1) break;
			}
		}
		
		// 모든 섬이 연결되었는지 확인한다. 
		System.out.println(connCnt == islands.size()-1 ? totalDist : -1);
	}
	
	// Union - Find 구현
	static int[] parent;
	static void make() {
		parent = new int[islands.size()];
		
		for(int i=0; i<islands.size(); i++) {
			parent[i] = i;
		}
	}
	
	static int find(int island) {
		if(parent[island] == island) return island;
		
		return parent[island] = find(parent[island]);
	}
	
	static boolean union(int islandA, int islandB) {
		int rootA = find(islandA);
		int rootB = find(islandB);
		
		if(rootA == rootB) return false;
		
		parent[rootB] = rootA;
		return true;
	}
	
	static int findIslandIdx(int x, int y) {
		for(Integer islandIdx : islands.keySet()) {
			for(int[] arr : islands.get(islandIdx)) {
				if(x == arr[0] && y == arr[1]) return islandIdx;
			}
		}
		
		return -1;
	}
	
	/*
	 * 각 섬의 경계들의 좌표를 저장한다. 
	 */
	static Map<Integer, List<int[]>> islands;	// 섬idx, 테두리 좌표들
	static void getIslandInfo() {
		islands = new HashMap<>();
		int islandIdx = 0; 
		
		// 땅을 찾는다면, 해당 섬 정보를 저장한다.
		boolean[][] visited = new boolean[row][col];	// 재방문 방지
		for(int x=0; x<row; x++) {
			for(int y=0; y<col; y++) {
				if(map[x][y] == 1 && !visited[x][y]) {	// 대륙 발견
					checkIsland(x, y, islandIdx, visited);
					islandIdx++;	// 다음 섬으로 타겟 변경 
				}
			}
		}
		
		// 탐색할 필요 없는 구역 제거 
		// -> 바다와 연결되어 있지 않은 구역 제거 = 사방이 육지로 쌓인 구역 
		for (Integer key : islands.keySet()) {
		    Iterator<int[]> it = islands.get(key).iterator();
		    while (it.hasNext()) {
		        int[] arr = it.next();
		        int x = arr[0];
		        int y = arr[1];
		        boolean isolation = true;
		        for (int dir = 0; dir < 4; dir++) {
		            int nx = x + dx[dir];
		            int ny = y + dy[dir];

		            if (nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
		            if (map[nx][ny] == 1) continue;

		            isolation = false;
		        }

		        if (isolation) it.remove(); 
		    }
		}

		
		// test
//		for(Integer key : islands.keySet()) {
//			System.out.println(key+" 번");
//			for(int[] arr : islands.get(key)) {
//				System.out.println(Arrays.toString(arr));
//			}
//			System.out.println();
//		}
	}
	
	/*
	 * 섬을 확인한다. 
	 * 땅을 기준으로 인접한 구역을 모두 탐색하여 처리한다.
	 */
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static void checkIsland(int x, int y, int islandIdx, boolean[][] visited) {
		// 해당 섬 저장 공간 초기화
		islands.put(islandIdx, new ArrayList<int[]>());
		
		// BFS 사용하여 탐색 
		Queue<int[]> island = new ArrayDeque<>();
		// 초기 위치 설정 
		islands.get(islandIdx).add(new int[] {x,y});
		island.offer(new int[] {x,y});
		visited[x][y] = true;
		
		while(!island.isEmpty()) {
			int[] cur = island.poll();
			int curX = cur[0];
			int curY = cur[1];
			
			// 인접구역 탐색
			for(int dir=0; dir<4; dir++) {
				int nx = curX + dx[dir];
				int ny = curY + dy[dir];
				
				// 범위 확인 
				if(nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
				// 방문 여부 및 땅인지 확인
				if(visited[nx][ny] || map[nx][ny] == 0) continue;
				
				// 땅이라면 체크
				visited[nx][ny] = true;
				islands.get(islandIdx).add(new int[] {nx,ny});
				island.offer(new int[] {nx,ny});
			}
		}
	}
}

/* 
 * N x M 크기의 격자 
 * 각 칸은 땅 / 바다 
 * 섬 : 상하좌우로 연결된 땅
 * 
 * 다리는 바다에만 건설할 수 있다. 
 * 다리를 연결해 모든 섬을 연결하려한다. 
 * 다리는 가로 또는 세로만 된다. 
 * 다리의 길이는 2 이상이어야한다. 
 * 다리의 양 끝에 닿아있는 섬들만 연결된다. 
 * 
 * 다리가 교차되는 경우도 있다. 
 * 겹치는 부분은 두 다리의 길이에 모두 포함된다. 
 * 
 * 나라의 정보가 주어졌을 때, 모든 섬을 연결하는 다리 길이의 최소를 구하라
 */