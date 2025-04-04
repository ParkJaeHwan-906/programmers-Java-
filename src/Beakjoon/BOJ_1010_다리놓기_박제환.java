package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1010_다리놓기_박제환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 0; testCase < TC; testCase++) {
			init();
		}
		br.close();
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int left, right;
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		left = Integer.parseInt(st.nextToken());
		right = Integer.parseInt(st.nextToken());
		
		// 조합의 개수를 구한다. 
		getComb(right, left);
	}
	
	static void getComb(int n, int r) {
		int[][] arr = new int[n+1][r+1];
		
		for(int x=0; x<n+1; x++) {
			for(int y=0; y<Math.min(x, r)+1; y++) {
				if(y==0 || x==y) arr[x][y] = 1;
				else arr[x][y] = arr[x-1][y-1] + arr[x-1][y];
			}
		}
		
		sb.append(arr[n][r]).append('\n');
	}
}

/* 
 * 서쪽엔 N 개의 사이트
 * 동쪽엔 M 개의 사이트가 있다. 
 * N <= M
 * 
 * 각 사이트에는 한 개의 다리만 연결 가능하다. 
 * 서로 겹칠 수 없다. 
 * 
 * 조합구하기?
 */