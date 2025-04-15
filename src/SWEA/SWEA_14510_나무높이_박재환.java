package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_14510_나무높이_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase=1; testCase<TC+1; testCase++) {
			sb.append('#').append(testCase).append(' ');
			init();
			sb.append('\n');
		}
		br.close();
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int[] trees;
	static int treeCnt;
	static int maxH;		// 나무 중 가장 큰 나무 ( 기준 )
	static void init() throws IOException {
		maxH = -1;
		
		treeCnt = Integer.parseInt(br.readLine().trim());
		trees = new int[treeCnt];
		
		st = new StringTokenizer(br.readLine().trim());
		for(int i=0; i<treeCnt; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			maxH = Math.max(maxH, trees[i]);
		}
		
		findMinDay();
	}
	
	// 나무의 높이가 달라지는데 걸리는 최소 날짜를 구한다. 
	static void findMinDay() {
		/*
		 * 홀수 날에는 +1
		 * 짝수 날에는 +2
		 * => 즉 2일 동안 자랄 수 있는 나무의 높이는 총 3 이다. 
		 */
		
		// 가장 큰 나무를 기준으로 각 나무가 자라야하는 높이를 구한다.
		// 이때 해당 높이를 다 자라기 위해서 필요한 날짜 수를 구한다.
		int odd = 0;	// 홀수
		int total = 0;
		for(int tree : trees) {
			int needH = maxH - tree;
			total += needH;
			odd += needH%2;
		}
		
		int days = total/3*2 + total%3;	// 가장 이상적인 날짜
		int one = days/2 + days%2;

		int result;
		if(odd <= one) {
			result = days;
		} else {
			result = 2 * odd - 1;
		}
		
		sb.append(result);
	}
}

/* 
 * N 개의 나무 
 * 각 나무의 키가 주어짐 
 * 물을 주면 키가 자람 -> 하루에 한 나무만 물을 줄 수 있음 
 * 	첫 날에 물 준 나무는 1 자라고
 * 	둘째 날에 물 준 나무는 2 자라고
 * 	셋째 날에 물 준 나무는 1 자란다
 * => 짝수 날에는 2
 * => 홀수 날에는 1 
 * 
 * 모든 나무의 높이가 같아질 수 있는 최소 날짜 
 */