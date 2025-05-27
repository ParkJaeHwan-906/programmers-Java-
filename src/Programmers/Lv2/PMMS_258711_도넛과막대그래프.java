package Programmers.Lv2;

import java.util.*;

public class PMMS_258711_도넛과막대그래프 {
	public static void main(String[] args) {
		int[][] edges = {
				{2,3},
				{4,3},
				{1,1},
				{2,1}
		};
		
		System.out.println(Arrays.toString(new PMMS_258711_도넛과막대그래프().solution(edges)));
	}
	
	/*
	 * 도넛 모양 그래프
	 * -> 크기가 n 일 때, n 개의 정점과 n 개의 간선을 가짐
	 * -> 어떤 정점에서 출발하면 다시 출발정점으로 돌아온다.
	 * 막대 모양 그래프
	 * -> 크키가 n 일 때, n 개의 정점과 n-1 개의 정점을 가짐 
	 * -> 어느 한 정점에서 출발하면, 모든 정점을 만물할 수 있는 정점이 한개 존재한다.
	 * 8자 모양 그래프
	 * -> 크기가 n 일 때, 2n+1 개의 정점과 2n+2 개의 정점을 가짐
	 * -> 2 개의 도넛 모양 그래프를 합친 것이다.
	 */
	int nodeCnt;			// 정점의 개수 ( edges 에서 추출 )
	int[] inEdges;			// 각 정점에서 진입 차수
	int[] outEdges;			// 각 정점에서 진출 차수
	List<Integer>[] graph;	// 그래프
	boolean[] visited;		// 방문 처리
	int donut, stick, eight;// 도넛 모양, 막대 모양, 8자 모양
	public int[] solution(int[][] edges) {
		donut = stick = eight = 0;	// 초기값 설정 
		/*
		 * 주어진 edges 로 
		 * 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수
		 */
		
		// 1. 정점의 개수를 구한다. 
		nodeCnt = getNodeCnt(edges)+1;
//        System.out.println(nodeCnt);
		// 2. 각 정점의 진입/진출 차수를 구한다. 
		// 2-2. 진출 차수가 2 이상이고, 진입 차수가 0 인 차수를 찾는다. 
		//		-> 생성된 정점을 찾는다. 
		int createdNode = findCreatedNode(edges);
//		System.out.println(createdNode);
		
		// 그래프를 생성 
		makeGraph(edges);
		
		// 그래프 판별
		visited = new boolean[nodeCnt];
		// 1. 생성 노드에서 뻗어 나가는 노드들을 탐색한다.
		visited[createdNode] = true;
		
		for(int connNode : graph[createdNode]) {
			// 이미 처리된 노드라면 넘어간다.
			if(visited[connNode]) continue;
			
			graphType(connNode);
		}
		
        return new int[] {createdNode, donut, stick, eight};
    }
	
	// 그래프의 타입을 구분한다.
	void graphType(int node) {
		/*
		 * 그래프를 BFS 를 사용해 탐색하며
		 * 정점의 개수와, 간선의 개수를 구해 그래프를 판별한다.
		 */
		int nodeCnt = 0;
		int edgeCnt = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(node);
		visited[node] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			nodeCnt++;
			edgeCnt += graph[cur].size();
			
			for(int connNode : graph[cur]) {
				if(visited[connNode]) continue;
				visited[connNode] = true;
				q.offer(connNode);
			}
		}
		
		// 1. 도넛 -> 정점 n, 간선 n
		if(nodeCnt == edgeCnt) donut++;
		// 2. 막대 -> 정점 n, 간선 n-1
		else if(nodeCnt == edgeCnt+1) stick++;
		// 3. 8자 -> 그 외
		else eight++;
	}
	
	// 그래프를 생성한다. 
	void makeGraph(int[][] edges) {
		graph = new ArrayList[nodeCnt];
		for(int node=0; node<nodeCnt; node++) {
			graph[node] = new ArrayList<>();
		}
		for(int[] edge : edges) {
			graph[edge[0]].add(edge[1]);
		}
	}
	
	// 생성 정점을 찾는다. 
	int findCreatedNode(int[][] edges) {
		getInOutEdges(edges);
		
		// 진출 차수 2 이상, 진입 차수 0
		for(int node=1; node<nodeCnt; node++) {
			if(inEdges[node] == 0 && outEdges[node] > 1) return node;
		}
		return -1;
	}
	
	// 각 정점의 진입 / 진출 차수를 구한다. 
	void getInOutEdges(int[][] edges) {
		inEdges = new int[nodeCnt];
		outEdges = new int[nodeCnt];
		
		for(int[] edge : edges) {
			outEdges[edge[0]]++;
			inEdges[edge[1]]++;
		}
		
//		System.out.println(Arrays.toString(inEdges));
//		System.out.println(Arrays.toString(outEdges));
	}
	
	// 정점의 개수를 구한다.
	// [!! 정점의 번호가 연속적이지 않을 수 있다 !!]
	int getNodeCnt(int[][] edges) {
		int max = Integer.MIN_VALUE;
		for(int[] edge : edges) {
			max = Math.max(max, Math.max(edge[0], edge[1]));
		}
		return max;
	}
}
