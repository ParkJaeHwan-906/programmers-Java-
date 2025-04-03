package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1149_RGB거리_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int houseCnt, minCost;
	static int[][] paintPrices;
	static void init() throws IOException {
		minCost = Integer.MAX_VALUE;
		
		houseCnt = Integer.parseInt(br.readLine().trim());
		paintPrices = new int[houseCnt][3];	// 각 집 [빨, 초, 파] 비용 
		
		for(int houseIdx=0; houseIdx<houseCnt; houseIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			paintPrices[houseIdx][0] = Integer.parseInt(st.nextToken());
			paintPrices[houseIdx][1] = Integer.parseInt(st.nextToken());
			paintPrices[houseIdx][2] = Integer.parseInt(st.nextToken());
		}
		
//		getMinCost(0, -1, 0);
		getMinCost();
		
		System.out.println(minCost);
	}
	
	/*
	 * XXXX 시간 초과 
	 * 완전 탐색을 사용해 모든 조합을 구한다. 
	 * [집 인덱스, 이전 컬러 인덱스(첫 시작은 -1)]
	 */
//	static void getMinCost(int houseIdx, int preColor, int cost) {
//		if(houseIdx == houseCnt) {	// 모든 탐색을 완료
//			minCost = Math.min(cost, minCost);
//			return;
//		}
//		
////		if(minCost <= cost) return;	// 중간단계가 이전의 최소값과 같거나 크다면, 탐색 필요 X 
//		
//		for(int color=0; color<3; color++) {
//			if(color == preColor) continue;	// 이전 집의 색상과 같은 것은 피한다. 
//			
//			if(minCost <= cost+paintPrices[houseIdx][color]) continue;
//			getMinCost(houseIdx+1, color, cost+paintPrices[houseIdx][color]);
//		}
//	}
	
	/*
	 * DP 를 사용한다. 
	 */
	static void getMinCost() {
		// 각 집에서 누적된 작업비의 최적합을 구하기 위한 배열 
		int[][] bestChoice = new int[houseCnt][3];
		
		// 첫번째 집의 작업 비용 
		bestChoice[0][0] = paintPrices[0][0];
		bestChoice[0][1] = paintPrices[0][1];
		bestChoice[0][2] = paintPrices[0][2];
		
		// 이전의 최적해를 이용해 현재의 최적해를 구한다.
		for(int houseIdx=1; houseIdx<houseCnt; houseIdx++) {
			// 현재 집에서 칠하려는 색상을 제외한 다른 색상 중 최소값을 찾는다. 
			bestChoice[houseIdx][0] = Math.min(bestChoice[houseIdx-1][1], 
					bestChoice[houseIdx-1][2]) 
					+ paintPrices[houseIdx][0];
			bestChoice[houseIdx][1] = Math.min(bestChoice[houseIdx-1][0], 
					bestChoice[houseIdx-1][2]) 
					+ paintPrices[houseIdx][1];
			bestChoice[houseIdx][2] = Math.min(bestChoice[houseIdx-1][1], 
					bestChoice[houseIdx-1][0]) 
					+ paintPrices[houseIdx][2];
		}
		
		// 마지막 집에서의 최소 누적값을 찾는다.
		for(int price : bestChoice[houseCnt-1]) {
			minCost = Math.min(price, minCost);
		}
	}
}

/* 
 * RGB 거리에는 집이 N 개 있다. 
 * 거리는 선분으로 나타낼 수 있고, 1번 집부터 N 번 집이 순서대로 있다. 
 * 
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야한다. 
 * 각 집을 빨강, 초록, 파랑 으로 칠하는 비용이 주어진다. 
 * 모든 집을 칠하는 비용의 최솟값을 구하라 
 * 
 * 1. 1번 집의 색은 2번 집과 달라야한다. 
 * 2. N 번 집의 색은 N-1 집과 달라야한다. ( 인접한 집들이 서로 색이 달라야한다. (좌 우))
 */