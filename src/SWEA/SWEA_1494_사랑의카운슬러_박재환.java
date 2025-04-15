package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1494_사랑의카운슬러_박재환 {
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
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int nodeCnt;		// 노드의 개수
	static int[][] nodes;	// 각 노드의 [x, y]
	static boolean[] visited;	// 노드의 처리 여부
	static long minVectorSum;	// 백터의 최소 합 
	static void init() throws IOException {
		minVectorSum = Long.MAX_VALUE;
		
		nodeCnt = Integer.parseInt(br.readLine().trim());
		
		visited = new boolean[nodeCnt];
		nodes = new int[nodeCnt][2];
		for(int nodeIdx=0; nodeIdx<nodeCnt; nodeIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			nodes[nodeIdx][0] = Integer.parseInt(st.nextToken());
			nodes[nodeIdx][1] = Integer.parseInt(st.nextToken());
		}
		
		getMinVectorSum(0,0);
		sb.append(minVectorSum);
	}
	
	/*
	 * 완전탐색을 통해 백터 합의 최소를 구한다. 
	 * [노드의 인덱스, 현재까지 탐색한 노드의 개수]
	 */
	static void getMinVectorSum(int nodeIdx, int count) {
		if(count == nodeCnt/2) {	// 짝을 짓는 것으로, 주어진 노드의 절반을 처리했다면 탐색 종료
			// 백터의 합을 구한다.
			long xSum = 0;
			long ySum = 0;
			
			for(int idx=0; idx<nodeCnt; idx++) {
				if(visited[idx]) {
					xSum += nodes[idx][0];
					ySum += nodes[idx][1];
				} else {
					xSum -= nodes[idx][0];
					ySum -= nodes[idx][1];
				}
			}
			
			minVectorSum = Math.min(minVectorSum, (xSum*xSum + ySum*ySum));
			return;
		}
		
		for(int idx=nodeIdx; idx<nodeCnt; idx++) {
			visited[idx] = true;
			getMinVectorSum(idx+1, count+1);
			visited[idx] = false;
		}
	}
}

/* 
 * N 명의 친구가 있음 
 * 임의의 두 친구들 매칭시킨 뒤, 한 친구를 다른 친구가 있는 곳으로 가게한다. 
 * 지렁이들이 움직인 벡터 합의 크기가 작기를 바란다. 
 * 
 * 백터 : |V| = |(x,y)| = x*x + y*y
 * 
 * 10초 -> 완탐해서 각 백터 노드를 구한다?
 * 이후에 조합?
 */