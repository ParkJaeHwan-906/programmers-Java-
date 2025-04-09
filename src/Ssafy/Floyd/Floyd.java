package Ssafy.Floyd;

import java.util.*;
import java.io.*;

public class Floyd {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		init();
		br.close();
		System.out.println(sb);
	}
	
	static final int INF = 987654321;
	static StringTokenizer st;
	static int nodes;	// 노드의 수
	static int[][] map;	// 노드 to 노드 의 연결 비용을 저장 
	static void init() throws IOException {
		nodes = Integer.parseInt(br.readLine().trim());
		
		map = new int[nodes][nodes];
		for(int x=0; x<nodes; x++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int y=0; y<nodes; y++) {
				map[x][y] = Integer.parseInt(st.nextToken());
				// 자기 자신이 아닌, 타 노드로 가는 경우가 연결되어 있지 않는 경우
				if(map[x][y] == 0 && x != y) {
					map[x][y] = INF;
				}
			}
		}
//		for(int [] arr : map) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println();
		floyd();
	}
	
	/*
	 * 각 정점에서 다른 정점으로의 최단 거리를 구한다.
	 */
	static void floyd() {		
		for(int mid=0; mid<nodes; mid++) {				// 경유 노드
			for(int start=0; start<nodes; start++) {	// 출발 노드
				for (int end = 0; end < nodes; end++) {	// 목적 노드
					map[start][end] = Math.min(map[start][end], 
							map[start][mid] + map[mid][end]);	// 최소 거리 찾기
				}
			}
		}
		
		printRoute();
	}
	
	static void printRoute() {
		for(int x=0; x<nodes; x++) {
			for(int y=0; y<nodes; y++) {
				sb.append(map[x][y] == INF ? 0 : map[x][y]).append(' ');
			}
			sb.append('\n');
		}
	}
}

