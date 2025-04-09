package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_5052_전화번호목록_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine().trim());
		while(TC-- > 0) {
			init();
		}
		br.close();
		System.out.println(sb);
	}
	
	static int telListCnt;		// 전화번호 개수 
	static String[] telList;		// 전화번호부
	static void init() throws IOException {
		telListCnt = Integer.parseInt(br.readLine().trim());
		telList = new String[telListCnt];
		
		for(int i=0; i<telListCnt; i++) {
			telList[i] = br.readLine().trim();
		}
		
		// 한 번호가 다른 번호의 접두어인 경우가 없어야한다. 
		// 번호를 길이 순으로 정렬한다. 
		Arrays.sort(telList, (a, b) -> a.length() - b.length());
		
		sb.append(isOk() ? "YES" : "NO").append('\n');
	}
	
	/*
	 * 전화번호 최대 10000 개
	 * 완탐시 1억 -> 딱 1초
	 */
	static boolean isOk() {
		for(int i=0; i<telListCnt; i++) {
			for(int j=i+1; j<telListCnt; j++) {
				if(telList[j].startsWith(telList[i])) return false;
			}
		}
		return true;
	}
}
