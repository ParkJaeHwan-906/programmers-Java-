package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_7704_활자그래프 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int graphCnt;			// 활자 그래프의 개수 
	static long[] minDists;			// 각 그래프의 1->2 혹은 2->1 의 최단 경로를 저장한다. 
	static final long INF = 1_000_000_000_000_000_000L;	// 최대 값 지정 
	static void init() throws IOException {
		graphCnt = Integer.parseInt(br.readLine().trim());
		minDists = new long[graphCnt+1];		// 1-base
		
		// 1. 활자 그래프를 입력 받음 
		for(int i=1; i<graphCnt+1; i++) {
			// 2. 현재 활자 그래프의 정점의 개수와, 간선/타 활자 그래프를 찍은 횟수나 주어짐
			st = new StringTokenizer(br.readLine().trim());
			int nodeCnt = Integer.parseInt(st.nextToken());
			int doSomeThing = Integer.parseInt(st.nextToken());	// 간선을 찍을 수도, 타 그래프을 찍을수도 있음 
			
			// 2-1. 현재 그래프의 정점의 개수만큼 공간을 할당해줌 
			List<long[]>[] graph = new ArrayList[nodeCnt+1];		// 1-base
			for(int node=0; node<nodeCnt+1; node++) {
				graph[node] = new ArrayList<>();
			}
			
			// 2-2. 간선 혹은 그래프를 찍는다. 
			while(doSomeThing-- > 0) {
				st = new StringTokenizer(br.readLine().trim());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				long costOrGraph = Long.parseLong(st.nextToken());
				
				// 단방향 그래프
				graph[from].add(new long[] {to, costOrGraph});
			}
			
			// 해당 그래프의 1->2 / 2->1 의 최단 경로를 지정한다
			// 이는 그래프가 중간에 찍히게 될 경우를 대비해서임
			minDists[i] = getMinDist(graph, 1, 2);
			if(i < graphCnt) {	// 마지막 그래프가 아니라면 
				minDists[i] = Math.min(minDists[i], getMinDist(graph, 2, 1));
			}
		}
	
		System.out.println(minDists[graphCnt] == INF ? -1 : minDists[graphCnt]);
	}
	
	/*
	 * start -> end 까지의 최소 경로를 구한 뒤, 이를 반환한다.
	 * 다익스트라 알고리즘을 사용한다. 
	 */
	static long getMinDist(List<long[]>[] graph, int start, int end) {
		// 가중치를 기준으로 오름차순 정렬 
		PriorityQueue<long[]> pq = 
				new PriorityQueue<>((a,b) -> Long.compare(a[1], b[1]));
		
		int nodeCnt = graph.length;		// 이전에 1-base 로 선언했으므로, 그대로 사용 
		
		long[] dp = new long[nodeCnt];
		// 최대값으로 지정 
		Arrays.fill(dp, INF);
		// 출발지는 제외 
		dp[start] = 0;
		pq.offer(new long[] {start, 0});
		
		while(!pq.isEmpty()) {
			long[] cur = pq.poll();
			int from = (int)cur[0];
			long accSum = cur[1];
			
			// 이전의 값 중 최적 해가 존재한다면, 더 이상 탐색하지 않는다.
			if(accSum > dp[from]) continue;
			
			// 현재 노드와 연결되어 있는 노드들을 순회한다.
			for(long[] conn : graph[from]) {
				int to = (int)conn[0];
				long costOrGraph = conn[1];
				
				// costOrGraph 가 양수, 음수 일때 구분 
				if(costOrGraph >= 0) {	// 간선 연결 
					
					if(costOrGraph >= INF) continue;
					
					if(dp[to] > accSum + costOrGraph) {	// 최적해라면 
						dp[to] = accSum + costOrGraph;
						pq.offer(new long[] {to, dp[to]});
					}
				} else {	// 그래프 연결 
					int graphNo = (int) Math.abs(costOrGraph);
					
					if(minDists[graphNo] >= INF) continue;
					
					if(dp[to] > accSum + minDists[graphNo]) {
						dp[to] = accSum + minDists[graphNo];
						pq.offer(new long[] {to, dp[to]});
					}
				}
			}
		}
		
		return dp[end];
	}
}

/* 
 * 활자 그래프에서의 최단 경로
 * T 개의 활자 그래프가 있음 (1~T)
 * 
 * 각 활자 그래프는 1~N 의 정점을 가지고 있음 
 * 간선 : v 번 정점에서 w번 정점으로 가는 가중치가 있는 단방향 간선을 추가한다.
 * 활자 그래프 : i 번째 활자 그래프의 시작점과, t 번 활자 그래프의 v 번 정점과 w 번 정점이 되도록 
 * 				i 번째 활자 그래프의 정점과 간선을 추가한다. -> 이때 생기는 i 번째 활자 그래프의 정점들에는 번호 X 
 * 
 *  모든 활자 그래프의 시작은 1, 끝점은 2 
 *  
 *  T 번 활자 그래프의 1 -> 2 의 최단 거리를 구해라 
 */