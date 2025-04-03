package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1952_수영장_DP_박재환 {
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
		
		System.out.println(sb);
	}
	
	static StringTokenizer st; 
	static int[] tickets;	// 이용권 [1일, 1달, 3달, 1년]
	static int[] plans;		// 이용 계획
	static void init() throws IOException {
		tickets = new int[4];	// 이용권 종류 4가지 
		plans = new int[13];	// 1년 초기화, 1-base 
		
		// 1. 티켓 입력 
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<4; idx++) {
			tickets[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 이용 계획 입력 
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=1; idx<13; idx++) {
			plans[idx] = Integer.parseInt(st.nextToken());
		}
		
		bestPlan();
	}
	
	/*
	 * 최적의 이용권 조합을 찾는다. 
	 * 
	 * 1. 1일권만 사용한다.
	 * 2. 1일권보다 1달권이 이득인 경우를 찾아 변경한다. 
	 * 3. 1달권보다 3달권이 이득인 경우를 찾아 변경한다. 
	 * 4. 3번까지 과정 이후, 총 금액을 비교한 뒤, 1년치 요금과 비교한다. 
	 */
	static void bestPlan() {
		int[] bestPlan = new int[13];
		
		for(int month=1; month<13; month++) {
			// 1. 1일권만 사용했을 때와, 1달 요금제를 비교한다. 
			bestPlan[month] = Math.min(tickets[0]*plans[month], tickets[1]);
			
			// 2. 이전달에서부터의 누적 금액으로 기록 
			bestPlan[month] += bestPlan[month-1];
			
			// 3. 3달 이용권을 사용하기 위해서는 적어도 3월까지는 기록을 봐야함 
			if(month >= 3) {	// 묶을 수 있는 조건이 됐을 때 
				// 현재까지의 누적 금액과, 3달전까지 요금 + 3달 이용권 사용 비용 비교
				bestPlan[month] = Math.min(bestPlan[month], bestPlan[month-3] + tickets[2]);
			}
			
			// 4. 1년 비교
			if(month == 12) bestPlan[month] = Math.min(bestPlan[month], tickets[3]);
		}
		
		sb.append(bestPlan[12]);
	}
}

/* 
 * 1일 이용권, 1달 이용권, 3달 이용권, 1년 이용권 
 * 각 달의 이용 계획이 주어진다. 
 * 
 * 1일 이용권의 누적이 1달 이용권 보다 비싸진다면 1달 이용권을 끊는게 이득
 * 1달 이용권의 누적이 3달 이용권 보다 비싸진다면 3달 이용권을 끊는게 이득
 * 3달 이용권의 누적이 1년 이용권 보다 비싸진다면 1년 이용권을 끊는게 이득 
 */