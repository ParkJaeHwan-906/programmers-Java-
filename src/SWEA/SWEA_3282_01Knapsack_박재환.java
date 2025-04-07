package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_3282_01Knapsack_박재환 {
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
	static int itemCnt, limitW;	// 물건의 개수, 가방의 최대 무게
	static int[][] items;			// 물건 정보 [부피, 가치]
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		itemCnt = Integer.parseInt(st.nextToken());
		limitW = Integer.parseInt(st.nextToken());
		
		items = new int[itemCnt][2];
		for(int item=0; item<itemCnt; item++) {
			st = new StringTokenizer(br.readLine().trim());
			int w = Integer.parseInt(st.nextToken());	// 부피
			int v = Integer.parseInt(st.nextToken());	// 가치
			
			items[item] = new int[] {w, v};
		}
		
//		getMaxValue();
		getMaxValue2();
	}
	
	/*
	 * 배낭의 최대 가치의 물건을 넣는다.
	 */
	static void getMaxValue() {
		int[] bag = new int[limitW+1];	// 각 배낭이 i kg 일때, 가질 수 있는 최대 가치
		
		for(int[] item : items) {
			int w = item[0];
			int v = item[1];
			
			for(int weight=limitW; weight >= w; weight--) {
				bag[weight] = Math.max(bag[weight], bag[weight-w]+v);
			}
		}
		
		sb.append(bag[limitW]);
	}
	
	static void getMaxValue2() {
		int[][] bag = new int[itemCnt+1][limitW+1];	// 각 배낭이 i 개의 물건을 넣을 때, j kg 에서 최대 가치
		
		for(int item=0; item<itemCnt+1; item++) {
			for(int weight=0; weight<limitW+1; weight++) {
				// 1. i = 0 || w = 0 은 무조건 0 이다. 
				if(item == 0 || weight == 0) {
					bag[item][weight] = 0;
				}
				// 2. i > 0 && weight < w[i] 은 db[i-1][w] 이다.
				else if(item > 0 && weight < items[item-1][0]) {
					bag[item][weight] = bag[item-1][weight];
				}
				// 나머지 max(db[i-1][w], v[i] + dp[i-1][w-w[i]])
				else {
					bag[item][weight] = Math.max(bag[item-1][weight], bag[item-1][weight-items[item-1][0]]+items[item-1][1]); 
				}
			}
		}
		
		sb.append(bag[itemCnt][limitW]);
	}
}
