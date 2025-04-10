package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_17845_수강과목_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int limitTime, itemCnt;		// 제한 시간, 과목 수
	static int[][] items;				// 과목 [중요도, 시간]
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		limitTime = Integer.parseInt(st.nextToken());
		itemCnt = Integer.parseInt(st.nextToken());
		
		items = new int[itemCnt][2];
		for(int i=0; i<itemCnt; i++) {
			st = new StringTokenizer(br.readLine().trim());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		getMaxValue();
	}
	
	/*
	 * 주어진 시간 내에 최대 중요도를 구한다.
	 * 배낭 채우기 -> 각 과목은 한 번 씩만 쓸 수 있다. 
	 */
	static void getMaxValue() {
		int[] dpArr = new int[limitTime+1];
		
		for(int[] item : items) {
			int value = item[0];
			int time = item[1];
			
			for(int i=limitTime; i>time-1; i--) {
				dpArr[i] = Math.max(dpArr[i], dpArr[i-time]+value);
			}
		}
		
		System.out.println(dpArr[limitTime]);
	}
}

/* 
 * 모든 과목의 [중요도, 필요 시간] 이 주어진다. 
 * 
 * 한계 시간을 초과하지 않으며, 중요도의 합이 최대가 되게한다. -> 배낭 채우기 
 */ 