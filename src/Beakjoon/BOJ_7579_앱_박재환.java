package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_7579_앱_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static StringTokenizer st;
	static int applicationCnt, needMemories;	// 실행중인 앱의 개수, 필요 메모리 크기
	static int[][] applications;				// 각 애플리케이션 [메모리, 재실행비용]
	static int totalCost, UsedMemories;			// 총 비용의 합, 현재 사용중인 메모리 
	static void init() throws IOException {
		totalCost = UsedMemories = 0;
		
		st = new StringTokenizer(br.readLine().trim());
		applicationCnt = Integer.parseInt(st.nextToken());
		needMemories = Integer.parseInt(st.nextToken());
		
		applications = new int[applicationCnt][2];
		st = new StringTokenizer(br.readLine().trim());
		for(int application=0; application<applicationCnt; application++) {
			applications[application][0] = Integer.parseInt(st.nextToken());
			UsedMemories += applications[application][0]; 
		}
		st = new StringTokenizer(br.readLine().trim());
		for(int application=0; application<applicationCnt; application++) {
			applications[application][1] = Integer.parseInt(st.nextToken());
			totalCost += applications[application][1]; 
		}
		
		getMinValue();
	}
	
	/*
	 * 필요 메모리의 크기는 최대 1000만
	 * 실행중인 어플은 최대 100개
	 * 
	 * 완탐시 최대 10억?
	 * DP 사용 
	 */
	static void getMinValue() {
		// 남길 수 있는 메모리에서의 최대 합을 구한다. 
		int subMemories = UsedMemories - needMemories;
		int[] memories = new int[subMemories+1];
		
		for(int[] application : applications) {
			int memory = application[0];
			int cost = application[1];
			
			for(int m = subMemories; m >= memory; m--) {
				memories[m] = Math.max(memories[m], memories[m-memory]+cost);
			}
		}
		
		System.out.println(totalCost-memories[subMemories]);
	}
}

/* 
 * 스마트폰의 메모리는 제한적, 
 * 메모리 부족 시 활성화된 앱들 중 몇 개를 선택하여 메모리로부터 삭제한다. 
 * 
 * N 개의 앱이 활성화되어있고, 각 앱은 m[i] 만큼의 메모리를 사용한다. 
 * 이때 종료 후 재실행할 때 사용되는 비용을 c[i] 라고 한다. 
 * 
 * N 개의 앱을 종료하여 M 만큼의 메모리를 확보 -> 이때 c 의 합이 최소가 되도록
 */