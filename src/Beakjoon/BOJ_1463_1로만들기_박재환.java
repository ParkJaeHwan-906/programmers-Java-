package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1463_1로만들기_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static int num;
	static void init() throws IOException {
		num = Integer.parseInt(br.readLine().trim());
		getMinComm();
	}
	
	/*
	 * 1 로 만드는 최소 횟수를 구한다. 
	 * DP 를 사용한다. 
	 * 바텀업 방식으로 1 ~ N 까지 올라가며 이전의 해를 사용한다. -> 연산을 거꾸로 한다.
	 */
	static void getMinComm() {
		// 1 ~ N 까지의 수 표현
		// 각 수를 만들기 위한 연산의 최소 횟수를 저장하고 있음 
		int[] arr = new int[num+1];
		
		for(int i=2; i<num+1; i++) {
			// 1. 이전의 수에서 1을 빼는 연산 
			arr[i] = arr[i-1]+1;
			
			// 2. 2로 나누어 떨어지는 수라면
			if(i%2 == 0) {
				arr[i] = Math.min(arr[i],  arr[i/2]+1);
			}
			
			// 3. 3으로 나누어 떨어지는 수라면 
			if(i%3 == 0) {
				arr[i] = Math.min(arr[i],  arr[i/3]+1);
			}
		}
		
		System.out.println(arr[num]);
	}
}

/* 
 * X 에 사용할 수 있는 연산은 다음과 같다
 * 1. X 가 3으로 나누어 떨어지면 3으로 나눈다.
 * 2. X 가 2로 나누어 떨어지면 2로 나눈다.
 * 3. 1 을 뺀다.
 */