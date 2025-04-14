package SWEA;

import java.util.*;
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
	static Set<Integer> dead;
	static void init() throws IOException {
		// 처리된 줄기 세포의 위치를 저장한다.
		dead = new HashSet<>();

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
				int deadKey = x*50001+y;	// 좌표 정보로 key 를 만든다.
				dead.add(deadKey);
			}
		}

//		// 초기 위치 출력
//		System.out.println("초기 설정");
//		for(int[] arr : pq) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println();

		getMaxValue();
	}

	/*
	 * 최대로 퍼져있는 줄기세포의 개수를 구한다.
	 * BFS 로 각 시간마다의 상태를 시뮬레이션한다.
	 */
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static void getMaxValue() {
		// 각 시간을 시뮬레이션한다.
		int now = 0;
		// 활성화 시기를 기록한다.
		List<Integer> lifeCycleList = new ArrayList<>();
		while(now < time) {
			System.out.println("현 시간 : "+ now);

			// 현재 번식이 가능한 줄기세포들을 기록한다.
			Map<Integer, Integer> duplicated = new HashMap<>();
			Queue<int[]> q = new LinkedList<int[]>();

			// 1. 활성화되는 줄기세포들을 구한다.
			//		활성화 시간이 지난다면, 번식 시킨다.
			while (!pq.isEmpty() && pq.peek()[2] < now) {
				int[] cur = pq.poll();
				int x = cur[0];
				int y = cur[1];
				int activeTime = cur[2];
				int lifeCycle = cur[3];

				lifeCycleList.add(now+lifeCycle);

				for (int dir = 0; dir < 4; dir++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					int key = nx * 50001 + ny;

					if (dead.contains(key)) continue;

					// 중복 처리
					if (duplicated.containsKey(key)) {
						duplicated.put(key, Math.max(duplicated.get(key), lifeCycle));
					} else {
						duplicated.put(key, lifeCycle);
						q.offer(new int[] {nx, ny, lifeCycle});
					}
				}
			}

//			System.out.println("set");
//			for(int i : dead) {
//				System.out.println(i);
//			}

//			System.out.println("처리된 샛기");
//			for (int i : lifeCycleList) {
//				System.out.println(i);
//			}
//			System.out.println();
//
//			System.out.println("현재 pq");
//			for(int[] arr : pq) {
//				System.out.println(Arrays.toString(arr));
//			}
//			System.out.println();
//			System.out.println("퍼진 친구들");
//			for(int[] arr : q) {
//				System.out.println(Arrays.toString(arr));
//			}


			// 2. 줄기세포를 퍼뜨린다.
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];
				int lifeCycle = cur[2];
				int key = x * 50001 + y;

				// 생명력 수치가 동일할 때만 선택
				if (lifeCycle == duplicated.get(key)) {
					pq.offer(new int[] {x, y, now + lifeCycle, lifeCycle});
					dead.add(key);  // 이제 확정된 줄기세포 위치만 dead에 추가
				}
			}
//			System.out.println("---------------------");

			System.out.println(pq.size() + lifeCycleList.size());

			now++;
		}

//		for(int[] arr : pq) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println();
		int cnt = 0;
		// list 에서 활성화 되어있는 애들을 세어준다.
		for(int i : lifeCycleList) {
			if(i >= time) cnt++;
//			System.out.println(i);
		}
//		System.out.println();
		sb.append(cnt+pq.size());
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
 *
 * !! 살아 있는 줄기세포 -> 활성 + 비활성 !!
 */