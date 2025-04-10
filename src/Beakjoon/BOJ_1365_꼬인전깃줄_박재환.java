package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1365_꼬인전깃줄_박재환 {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	/*
	 * 꼬여있는 전선을 제거한다. 
	 * 전봇대의 번호가 1,2,3,4..., n 증가한다. 
	 * 번호가 꾸준히 증가하는게 아니라 역행하는 원소의 개수를 구한다. LIS 사용
	 */
	static StringTokenizer st;
	static int arrLen;		// 전봇대의 수
	static void init() throws IOException {
		arrLen = Integer.parseInt(br.readLine().trim());
		
		// LIS 의 길이를 저장할 List 
		List<Integer> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine().trim());
		// 첫 원소 저장 
		list.add(Integer.parseInt(st.nextToken()));
		for(int idx=1; idx<arrLen; idx++) {
			int num = Integer.parseInt(st.nextToken());	// 현재 삽입할 숫자
			
			if(list.get(list.size()-1) < num) {	// 현재 LIS 수열의 마지막 원소보다 크다면
				list.add(num);
				continue;
			}
			
			// 새로운 LIS 수열을 생성해야한다. 
			int insertIdx = findInsertIdx(list, num);
			// 해당 위치에 원소를 삽입한다.
			list.set(insertIdx, num);
		}
		
		System.out.println(arrLen-list.size());
	}
	
	/*
	 * num 을 삽입할 위치를 찾는다.
	 * num 보다 큰 가장 왼쪽의 원소를 찾는다. 
	 */
	static int findInsertIdx(List<Integer> list, int num) {
		int l = 0, r = list.size()-1;
		
		while(l < r) {
			int mid = l + (r-l)/2;
			
			if(list.get(mid) < num) l = mid+1;
			else r = mid;
		}
		return l;
	}
}

/* 
 * 각 전봇대는 1:1 로 연결된다. 
 * 
 * 입력으로 전봇대의 개수가 주어지고
 * 다음으로 각 연결 정보를 준다.
 * 
 * 제거하는 연결을 최소로 하여, 꼬여있는 연결을 없애고싶다. 
 */