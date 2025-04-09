package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1263_사람네트워크2_박재환 {
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
	
	/*
	 * 입력은 한 줄에 주어진다. 
	 * 사람 수인 양의 정수 N 이 주어진 다음
	 * 인접행렬이 행 우선 순으로 주어진다.
	 * 
	 *  사람은 최대 1000 
	 *  최대 1000 * 1000
	 */
	static final int INF = 987654321;	// 무한대를 표현 
	static StringTokenizer st;
	static int nodes;			// 사람의 수
	static int[][] connInfo;	// 연결 정보를 인접배열로 나타냄 
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		nodes = Integer.parseInt(st.nextToken());
		
		connInfo = new int[nodes][nodes];
		for(int x=0; x<nodes; x++) {
			for(int y=0; y<nodes; y++) {
				connInfo[x][y] = Integer.parseInt(st.nextToken());
				
				// 자기 자신 경로가 아닌, 연결되지 않은 곳 INF 
				if(x != y && connInfo[x][y] == 0) {
					connInfo[x][y] = INF;
				}
			}
		}
		
		getAllMinDist();
	}
	
	/*
	 * 각 정점에서 모든 정점으로의 최단 거리를 구한다. 
	 */
	static void getAllMinDist() {
		for(int mid=0; mid<nodes; mid++) {				// 경유지
			for(int start=0; start<nodes; start++) {	// 출발지
				for(int end=0; end<nodes; end++) {		// 도착지
					connInfo[start][end] = Math.min(	// 현재 연결 거리와, 경유해서 가는 거리를 비교
							connInfo[start][end],
							connInfo[start][mid] + connInfo[mid][end]
							);
				}
			}
		}
		// 최소 CC 를 찾아 출력한다.
		getMinCC();
	}
	
	static void getMinCC() {
		long min = Integer.MAX_VALUE;
		
		for(int x=0; x<nodes; x++) {
			long sum = 0;
			for(int y=0; y<nodes; y++) {
//				if(connInfo[x][y] == INF) continue;	// 연결되지 않는 정점은 제외?
				sum += connInfo[x][y];
			}
			min = Math.min(min, sum);
		}
		
		sb.append(min);
	}
}

/* 
 * N 명의 사람이 있다. 
 * 누가 가장 영향력이 있는지 분석한다. 
 * 
 * CC : 한 사용자가 다른 모든 사람에게 얼마나 가까운가 
 * -> 각 정점에서 모든 정점으로의 거리를 구한다. 
 */