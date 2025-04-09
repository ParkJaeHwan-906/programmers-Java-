package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_9205_맥주마시면서걸어가기_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
			sb.append('\n');
		}
		br.close();
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int gs25Cnt;		// 편의점 개수
	static int[] home;		// 집의 좌표
	static int[][] gs25;	// 편의점 좌표
	static int[] festival;	// 축제 좌표
	static boolean isReached;	// 도달했는지 여부
	static void init() throws IOException {
		isReached = false;
		
		gs25Cnt = Integer.parseInt(br.readLine().trim());
		gs25 = new int[gs25Cnt][2];
		
		// 집 좌표
		st = new StringTokenizer(br.readLine().trim());
		home = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		
		// 편의점 좌표
		// 편의점의 좌표가 순차적으로 주어진다는 보장이 없음. 
		// 편의점 좌표를 집과의 거리가 가까운 순으로 정렬 
		for(int idx=0; idx<gs25Cnt; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			gs25[idx] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		// 축제좌표
		st = new StringTokenizer(br.readLine().trim());
		festival = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		
		canHappy();
	}
	
	/*
	 * 편의점은 최대 100 개 
	 * 집 편의점 축제 최대 개수는 102개 
	 * 
	 * 모든 거리 101 개?
	 * 
	 * 테스트 케이스 최대 50개 
	 * 
	 * 101 * 50 -> 완전탐색 가능할 듯 
	 * 
	 * 순열 -> 순서가 중요함 
	 */
	static void canHappy() {
		// 집에서 축제까지 바로 갈 수 있는 경우
		if (Math.abs(home[0] - festival[0]) + Math.abs(home[1] - festival[1]) <= 1000) {
		    sb.append("happy");
		    return;
		}
		/*
		 * BFS 로 현재 위치에서 이동 가능한 모든 위치의 경우를 탐색한다 
		 */
		// 방문한 편의점 처리를 위한 배열 
		boolean[] visited = new boolean[gs25Cnt];
		Queue<int[]> q = new LinkedList<>();
		
		// 초기 설정 
		// 집에서 방문 가능한 모든 편의점을 확인
		for(int gsIdx=0; gsIdx < gs25Cnt; gsIdx++) {
			int dist = Math.abs(home[0]-gs25[gsIdx][0])+Math.abs(home[1]-gs25[gsIdx][1]);
			if(dist <= 1000) {	// 방문 가능한 편의점이라면 Queue 에 삽입 
				q.offer(gs25[gsIdx]);
				visited[gsIdx] = true;
			}
		}
		
		// 더 이상 탐색 가능한 경로가 없을 때까지
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			// 축제로 바로 갈 수 있는지 확인 
			if(Math.abs(festival[0]-x) + Math.abs(festival[1]-y) <= 1000) {
				sb.append("happy");
				return;
			}
			
			// 다른 편의점을 경유해야하면 
			for(int gsIdx=0; gsIdx < gs25Cnt; gsIdx++) {
				if(visited[gsIdx]) continue;	// 이미 방문한 편의점은 가지 않는다.
				int dist = Math.abs(x-gs25[gsIdx][0]) + Math.abs(y-gs25[gsIdx][1]);
				if(dist <= 1000) {	// 방문 가능한 편의점이라면 Queue 에 삽입 
					q.offer(gs25[gsIdx]);
					visited[gsIdx] = true;
				}
			}
		}
		
		// 축제에 도달하지 못함 
		sb.append("sad");
	}
}

/* 
 * 맥주 한 박스에는 맥주 20 개가 들어 있다. 
 * 50 미터에 한 병씩 마신다. -> 맥주 한 박스 ( 풀박스 ) 로 갈 수 있는 최대 거리는 1000 미터 
 * 
 * 거리에 따라 맥주를 더 구매해야할 수도 있다. 
 * 더 구매할 수는 있지만, 박스에 들어있는 맥주는 20병을 넘을 수 없다. 
 */