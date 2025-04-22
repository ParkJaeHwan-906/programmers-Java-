package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_7649_효도여행 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static class Node {
		int to;		// 도착 노드
		char c;		// 경로 알파벳
		Node prev;	// 이전 노드
		
		public Node(int to, char c, Node prev) {
			this.to = to;
			this.c = c;
			this.prev = prev;
		}
	}
	
	/*
	 * 경로를 역추적하는 경우 2 번 테스트 테이스에서 시간초과 발생 
	 * -> 매번 경로를 저장 
	 */
	static StringTokenizer st;
	static int nodeCnt, strLen;			// 정점의 개수, 문자열의 길이
	static List<Node>[] tree;			// 트리
	static char[] cArr;					// 주어지는 문자 배열
	static int maxMatched;				// 최장 공통 부분 수열 
	static void init() throws IOException {
		maxMatched = 0;
		
		st = new StringTokenizer(br.readLine().trim());
		nodeCnt = Integer.parseInt(st.nextToken());
		strLen = Integer.parseInt(st.nextToken());
		
		// 초기화
		tree = new ArrayList[nodeCnt+1];
		for(int i=0; i<nodeCnt+1; i++) {
			tree[i] = new ArrayList<>();
		}
		
		// 비교 문자
		cArr = br.readLine().trim().toCharArray();
		// 트리 생성 
		for(int i=1; i<nodeCnt; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			
			tree[from].add(new Node(to, c, null));
			tree[to].add(new Node(from, c, null));
		}
		
		findAllRoute();
		
		System.out.println(maxMatched);
	}
	
	
	/*
	 * 탐색할 수 있는 모든 경로를 탐색한다. 
	 * ?? 루트 노드는 항상 1 인가 ??
	 */
	static void findAllRoute() {
		// 중복되는 경로가 존재하나? 
		Set<String> set = new HashSet<>();
		
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[nodeCnt+1];
		// 1번 정점을 출발 노드로 잡는다. 
		q.offer(new Node(1,'-',null));
		visited[1] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			boolean isLeaf = true;
			// 현재 노드와 연결되어 있는 모든 노드를 탐색한다. 
			for(Node conn : tree[cur.to]) {
				if(visited[conn.to]) continue;
				isLeaf = false;
				visited[conn.to] = true;
				q.offer(new Node(conn.to, conn.c, cur));
			}
			
			// 더 이상 탐색할 노드가 없다면 
			if(isLeaf) {
				String route = traceRoute(cur);
				
				if(!set.add(route)) continue;
				
				// LCS 를 구한다. 
				calcLcs(route);
			}
		}
	}
	
	static void calcLcs(String route) {
		int[][] dp = new int[route.length()+1][strLen+1];
		
		for(int i=1; i<route.length()+1; i++) {
			for(int j=1; j<strLen+1; j++) {
				if(cArr[j-1] == route.charAt(i-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		
		maxMatched = Math.max(maxMatched, dp[route.length()][strLen]);
	}
	
	static String traceRoute(Node endNode) {
		Node now = endNode;
		StringBuilder route = new StringBuilder();
		while(now != null) {
			route.append(now.c);
			now = now.prev;
		}
		
		return route.reverse().toString();
	}
}
