package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_5215_햄버거다이어트_DP_박재환 {
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
	
	static StringTokenizer st;
	static int itemCnt, limit;
	static int[][] items;
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		itemCnt = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		
		items = new int[itemCnt+1][2];	// [맛, 칼로리 ]
		for(int item=1; item<itemCnt+1; item++) {
			st = new StringTokenizer(br.readLine().trim());
			
			items[item][0] = Integer.parseInt(st.nextToken());
			items[item][1] = Integer.parseInt(st.nextToken());
		}
		
		getMaxScore();
	}
	
	/*
	 * 재료의 조합을 구한다.
	 * 재료는 
	 * 1. 넣는다.
	 * 2. 넣지 않는다.
	 * 2 가지의 경우를 가지고 있다. 
	 * 
	 * DP 로 풀기 위해서
	 * 
	 * 각 칼로리에서 넣을 수 있는 재료의 최대 맛의 합을 구하고 싶다. 
	 */
	static void getMaxScore() {
		long[] bestScore = new long[limit+1];
		
		for(int item=1; item<itemCnt+1; item++) {
			// 탑다운 방식으로 최대값 갱신
			// 현재 재료가 칼로리를 기준으로 탐색
			for(int cal=limit; cal > items[item][1]-1; cal--) {
				// 현재 칼로리에서 가질 수 있는 최대 맛의 합을 구한다. 
				bestScore[cal] = Math.max(bestScore[cal], 
						bestScore[cal-items[item][1]] + items[item][0]);	
			}
		}
		
		sb.append(bestScore[limit]);
	}
}

/* 
 * 햄버거 재료의 수와, 제한 칼로리가 주어진다.
 * 각 재료에 대한 맛과 칼로리가 주어진다. 
 * 총 점수는 맛의 합으로 계산한다. 
 * 
 * 제한 칼로리 이하로, 최고의 맛을 구해라
 */