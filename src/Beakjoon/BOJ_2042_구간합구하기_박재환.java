package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_2042_구간합구하기_박재환 {
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
	static int arrLen, changeCnt, rangeSumCnt;	// 수열의 길이, 변경 횟수, 구간합 구하는 횟수
	static long[] arr;							// 수열
	static long[] rangeSum;						// 수열의 구간합을 저장 
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		arrLen = Integer.parseInt(st.nextToken());
		changeCnt = Integer.parseInt(st.nextToken());
		rangeSumCnt = Integer.parseInt(st.nextToken());
		
		arr = new long[arrLen+1];			// 1-based
		rangeSum = new long[arrLen+1];		// 1-based
		for(int idx=1; idx<arrLen+1; idx++) {
			arr[idx] = Long.parseLong(br.readLine().trim());
			rangeSum[idx] = rangeSum[idx-1] + arr[idx];
		}
		
		// 명령어가 실행되는 총 횟수
		int totalCommand = changeCnt + rangeSumCnt;	
		while(totalCommand-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			
			/*
			 * 1 : 숫자 변경
			 * 2 : 구간합 구하기
			 */
			int command = Integer.parseInt(st.nextToken());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			switch (command) {
			case 1 :	// 수를 변경한다. 
				changeNum((int)a, b);
				break;

			case 2 :	// 구간합을 반환한다.
				sb.append(rangeSum[(int)b] - rangeSum[(int)a-1]).append('\n');
				break;
			}
		}
	}
	
	/*
	 * arr 의 changeIdx 위치의 값을 changeNum 으로 변경해준다.
	 * rangeSum 의 changeIdx 위치부터 마지막까지 값을 기존 값과 차이 만큼 더하거나 빼준다. 
	 */
	static void changeNum(int changeIdx, long changeNum) {
		// 기존값과, 바뀌는 수의 차이 
		long diff = changeNum - arr[changeIdx];
		
		arr[changeIdx] = changeNum;
		
		for(int idx=changeIdx; idx<arrLen+1; idx++) {
			rangeSum[idx] += diff;
		}
	}
}

/* 
 * N 개의 수가 있다. 
 * 중간 수의 변경이 빈번히 일어난다. 
 * 어떤 부분합을 구하려한다. 
 */