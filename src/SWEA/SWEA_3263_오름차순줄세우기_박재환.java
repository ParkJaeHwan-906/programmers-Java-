package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_3263_오름차순줄세우기_박재환 {
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
	static int arrLen;	// 수열의 길이
	static int[] arr;	// 수열
	static int[] loc;	// 각 원소들이 존재하는 실제 위치
	static void init() throws IOException {
		arrLen = Integer.parseInt(br.readLine().trim());
		arr = new int[arrLen];
		loc = new int[arrLen+1];	// 실제 원소의 값은 1~N 이다.
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<arrLen; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
			loc[arr[idx]] = idx;	// 해당 원소의 실 위치를 기록한다. -> 실 위치를 기준으로 LIS 를 구한다.
		}
		getMinTry();
	}
	
	
	/*
	 * 아이디어 
	 * 1. 이미 오름차순으로 정렬되어 있는 원소들은 자리에 두고, 나머지를 이동시켜 전체를 정렬시킨다.
	 * 2. 이때 오름차순으로 정렬되어 있다는 말은 각 원소들의 위치를 기준으로한다. 
	 * 
	 * DP 시간 초과
	 */
//	static void getLis() {
//		int maxLen = -1;
//		int[] dpArr = new int[arrLen+1];
//		
//		for(int idx1=1; idx1<arrLen+1; idx1++) {
//			dpArr[idx1] = 1;
//			for(int idx2=1; idx2<idx1; idx2++) {
//				if(loc[idx1] > loc[idx2]) {	// 부분 증가 수열이라면
//					dpArr[idx1] = Math.max(dpArr[idx1], dpArr[idx2]+1);
//				}
//			}
//			maxLen = Math.max(maxLen, dpArr[idx1]);
//		}
//		
//		sb.append(arrLen-maxLen);
//	}
	
	// 이분탐색 구현
//	static void getLis() {
//		List<Integer> list = new ArrayList<>();
//		list.add(loc[1]);
//		
//		for(int idx=2; idx<arrLen+1; idx++) {
//			if(list.get(list.size()-1) < loc[idx]) {
//				list.add(loc[idx]);
//			} else {
//				list.set(getInsertIdx(list, loc[idx]), loc[idx]);
//			}
//		}
//		
//		sb.append(arrLen-list.size());
//	}
//	
//	static int getInsertIdx(List<Integer> list, int target) {
//		int l = 0, r = list.size()-1;
//		
//		while(l<r) {
//			int mid = l + (r-l)/2;
//			
//			if(list.get(mid) < target) l = mid+1;
//			else r = mid;
//		}
//		
//		return l;
//	}
	
	/*
	 * 위치를 기준으로 증가하고, 이들의 관계가 연속적인 수를 구한다. 
	 */
	static void getMinTry() {
		int[] dpArr = new int[arrLen+1];
	
		int maxLen = -1;
		for(int idx=0; idx<arrLen; idx++) {	// 위치 기준
			dpArr[arr[idx]] = dpArr[arr[idx]-1] + 1;	// 연속적인 수
			
			maxLen = Math.max(dpArr[arr[idx]], maxLen);
		}
		
		
		sb.append(arrLen-maxLen);
	}
}

/* 
 * 줄 서있는 어린이 중 한 명을 선택하여 제일 앞이나 제일 뒤로 보낸다. 
 * 
 * 오름차순으로 정렬하기 위해서는 최소 몇 번의 과정이 필요한가
 * 
 * 연속된 숫자의 최대 길이를 계산해야한다?
 */