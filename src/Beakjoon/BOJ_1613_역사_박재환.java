package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1613_역사_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		init();
		br.close();
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static final int INF = 987654321;
	static int nodes, edges;
	static boolean[][] conn;
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		nodes = Integer.parseInt(st.nextToken());
		edges = Integer.parseInt(st.nextToken());
		
		conn = new boolean[nodes+1][nodes+1];	// 1-base
		// 초기화
		// 기본 값이 false 이니, 자기 자신만 true 로 
		for(int x=0; x<=nodes; x++) {
			for(int y=0; y<=nodes; y++) {
				if(x == y) {
					conn[x][y] = true;
				} 
			}
		}
		
		// 연결관계를 입력 받음 
		while(edges-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			int front = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			conn[front][end] = true;
		}
		
		// 경유해서 갈 수 있는 경로가 있는지 찾음 
		findMid();
		
		int tc = Integer.parseInt(br.readLine().trim());
		while(tc-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			int front = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(conn[front][end]) {
				sb.append(-1);
			} else if (conn[end][front]) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			sb.append('\n');
		}
	}
	
	
	static void findMid() {
		for(int mid=1; mid<=nodes; mid++) {
			for(int start=1; start<=nodes; start++) {
				for(int end=1; end<=nodes; end++) {
					// 이미 연결되어있다면 확인할 필요가 없음
					if(conn[start][end]) continue;
					// 경유지를 통해 이동할 수 있는지 구한다. 
					conn[start][end] = conn[start][mid] && conn[mid][end];
				}
			}
		}
	}
}

/* 
 * 일부 사건들의 전후관계들이 주어질 때, 주어진 사건들의 전후 관계를 알 수 있는지 구하라 
 */