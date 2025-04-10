package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_9087_동전_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		while(TC-- > 0) {
			init(); 
		}
		br.close();
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int coinCnt, target;
	static int[] coins;
	static void init() throws IOException {
		coinCnt = Integer.parseInt(br.readLine().trim());
		
		st = new StringTokenizer(br.readLine().trim());
		coins = new int[coinCnt];
		for(int i=0; i<coinCnt; i++) {
			coins[i] = Integer.parseInt(st.nextToken());
		}
		
		target = Integer.parseInt(br.readLine().trim());
		
		getAllPayment();
	}
	
	/*
	 * 해당 금액을 만들 수 있는 모든 경우를 구한다.
	 * !! 동전은 무제한 -> 정방향으로 DP !! 
	 */
	static void getAllPayment() {
		// 최대 경우를 저장할 배열 
		int[] dpArr = new int[target+1];
		dpArr[0] = 1;
		
		for(int coin : coins) {
			for(int i=coin; i<target+1; i++) {
				// !! 경우의 수를 구하는 것이므로 Math.max 가 아닌, 이전의 값을 더해주면 된다 !! 
				dpArr[i] += dpArr[i-coin];
			}
		}
		
		sb.append(dpArr[target]).append('\n');
	}
}

/* 
 * 동전은 [1, 5, 10, 50, 100, 500] 이 있다. 
 * 
 * 목표 금액을 만드는 모든 방법을 세는 프로그램을 작성 -> DP
 * 
 * 모든 경우는 21억을 넘지 않음
 */