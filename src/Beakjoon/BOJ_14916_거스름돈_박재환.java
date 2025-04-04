package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_14916_거스름돈_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	/*
	 * 거스름돈은 2원과 5원으로만 이루어져 있다. 
	 */
	static int[] coins = {2,5};
	static int money;
	static void init() throws IOException {
		money = Integer.parseInt(br.readLine().trim());
		
		getMinCoins();
	}
	
	/*
	 * 바텀업 방식으로 문제를 해결한다. 
	 */
	static void getMinCoins() {
		int[] dp = new int[money+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		// 0 원일 때
		dp[0] = 0;
		
		for(int i=1; i<money+1; i++) {
			// 현재 금액이 2 원보다 크고 -> 2원을 사용해 만들 수 있는 가능성이 있음 
			// 이전의 해에 2원 동전을 하나 더 추가할 수 있다면, 이전 해가 존재한다면 
			// -> 이전에 2원과 5원을 사용해 거스름돈을 만들었다면  
			if(i >= 2 && dp[i-2] != Integer.MAX_VALUE) {
				dp[i] = Math.min(dp[i], dp[i-2] + 1); 
			}
			
			// 5 원의 경우 
			if(i >= 5 && dp[i-5] != Integer.MAX_VALUE) {
				dp[i] = Math.min(dp[i], dp[i-5] + 1); 
			}
		}
		
//		System.out.println(Arrays.toString(dp));
		System.out.println(dp[money] == Integer.MAX_VALUE ? -1 : dp[money]);
	}
}
