package SWEA;

import java.util.*;

import comb.comb;

import java.io.*;

public class SWEA_5653_줄기세포배양_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine().trim());
		for(int testCase=1; testCase<TC+1; testCase++) {
			sb.append('#').append(testCase).append(' ');
			init();
			sb.append('\n');
		}
		
		br.close();
		System.out.print(sb);
	}
	
	static StringTokenizer st;
	static int initRow, initCol;	// 초기 세로 영역, 초기 가로 영역
	static int time;				// 제한 시간 
	static PriorityQueue<int[]> pq;	// 번식 대기중인 줄기세포들 
	static void init() throws IOException {
		// pq 선언, 발현 시간이 가장 짧은 애들 순으로 
		// [x, y, 발현 시간(진입 시점 + 생명령), 생명력]
		pq = new PriorityQueue<>((a, b) -> a[2]-b[2]);	
		
		st = new StringTokenizer(br.readLine().trim());
		initRow = Integer.parseInt(st.nextToken());
		initCol = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		for(int x=0; x<initRow; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y=0; y<initCol; y++) {
				int lifeCycle = Integer.parseInt(st.nextToken());
				if(lifeCycle == 0) continue;
				pq.offer(new int[] {x,y, lifeCycle, lifeCycle});
			}
		}
		
		// 초기 위치 출력
		for(int[] arr : pq) {
			System.out.println(Arrays.toString(arr));
		}
		
		getMaxValue();
	}
	
	
	/*
	 * 최대로 퍼져있는 줄기세포의 개수를 구한다. 
	 * BFS 로 각 시간마다의 상태를 시뮬레이션한다. 
	 */
	static Set<Integer> dead;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static void getMaxValue() {
		// 처리된 줄기 세포의 위치를 저장한다.
		dead = new HashSet<>();
		
		// 각 시간을 시뮬레이션한다.
		int now = 0;
		int cnt = 0;
		while(now <= time) {
			System.out.println("현 시간 : "+ now);
			
			// 현재 번식이 가능한 줄기세포들을 기록한다.
			Map<Integer, Integer> duplicated = new HashMap<>();	
			Queue<int[]> q = new LinkedList<int[]>();
			
			// 1. 활성화되는 줄기세포들을 구한다.
			//		활성화 시간이 지난다면, 번식 시킨다. 
			while(!pq.isEmpty() && pq.peek()[2] < now) {
				int[] cur = pq.poll();
				int x = cur[0];
				int y = cur[1];
				int activeTime = cur[2];
				int lifeCycle = cur[3];
				cnt++;
				
				int deadKey = x*50001+y;	// 좌표 정보로 key 를 만든다.
				
//				System.out.println("번식 가능");
//				System.out.println(Arrays.toString(cur));
//				System.out.println(deadKey);
				dead.add(deadKey);
				
				// 상 하 좌 우 로 번식한다. 
				for(int dir=0; dir<4; dir++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					int key = nx*50001+ny;	// 좌표 정보로 key 를 만든다.
					
					if(dead.contains(key)) continue;	// 이전의 위치 제거

					// 중복된 위치인 경우, 더 큰 놈이 이긴다
					if(duplicated.containsKey(key)) {
						// 큰 친구의 값만 갱신한다.
						duplicated.put(key, Math.max(duplicated.get(key), lifeCycle));
						continue;
					}
					
					q.offer(new int[] {nx, ny, lifeCycle});
				}
			}

			System.out.println("set");
			for(int i : dead) {
				System.out.println(i);
			}
			
			System.out.println("현재 pq");
			for(int[] arr : pq) {
				System.out.println(Arrays.toString(arr));
			}

			System.out.println("퍼진 친구들");
			for(int[] arr : q) {
				System.out.println(Arrays.toString(arr));
			}
			
			
			// 2. 줄기세포를 퍼뜨린다. 
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				int lifeCycle = cur[2];
				System.out.println(Arrays.toString(cur));
				int key = x*50001+y;	// 좌표 정보로 key 를 만든다.
				System.out.println(key);
				
				if(dead.contains(key)) continue;	// 이전의 위치 제거
				
				// 해당 위치의 lifeCycle 을 갱신한다. 
				if(lifeCycle != duplicated.get(key)) continue;
				
				pq.offer(new int[] {x, y, now+lifeCycle, lifeCycle});
			}
			
			now++;
		}
		
		sb.append(pq.size() + cnt);
	}
}

/* 
 * 배양 용기 - N x N 격자
 * 
 * 줄기 세포는 생명력 수치를 갖는다. 
 * -> X -> X 시간동안 비활성화 -> X 시간 초과시 활성상태 -> X 시간동안 살아있을 수 있음 
 * 죽은 상태로 격자 공간 차지함 
 * 
 *  활성화된 줄기세포는 상 하 좌 우 로 번식한다. 
 *  해당 방향에 이미 존재한다면 하지 않는다. 
 *  
 *  동시에 번식하려하는 경우, 생명력 수치가 더 큰 줄기세포가 이긴다. 
 *  
 *  배양 용기의 크기는 무제한이다. 
 */