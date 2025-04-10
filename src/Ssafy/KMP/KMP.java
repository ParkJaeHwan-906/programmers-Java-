package Ssafy.KMP;

import java.util.*;
import java.io.*;
/*
 * 본문에서 패턴이 몇 번 등장하는지 횟수와 등장한 위치를 출력
 */
public class KMP {
	static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		br.close();
	}
	
	static char[] origin, pattern;	// 본문, 패턴
	static int originLen, patternLen;	// 본문, 패턴 길이
	static int[] pi;		// 부분 일치 테이블 ( 실패 함수 ) : 패턴에서 불일치 발생 시, 활용해서 패턴 포인터 이동 목적
	static void init() throws IOException {
		origin = br.readLine().trim().toCharArray();
		pattern = br.readLine().trim().toCharArray();
		
		originLen = origin.length;
		patternLen = pattern.length;
		
		pi = new int[patternLen];
		for (int i = 1, j = 0; i < patternLen; i++) {
			/*
			 * 패턴에서 패턴을 찾는 원리를 이용
			 * i : 패턴의 접미사 ( 꼬리 )
			 * j : 패턴의 접두사 ( 머리 )
			 * 
			 * 두 포인터의 위치에서 불일치가 발생하면 
			 * 맞은 직전 위치의 정보를 활용해서 불필요한 비교를 줄임
			 */
			
			while(j > 0 && pattern[i] != pattern[j]) {
				j = pi[j-1];
			}
			
			// 현재 i 위치까지의 부분문자열의 접미사가 접두사와 일치하는 패턴의 최장길이 저장 
			if(pattern[i] == pattern[j]) {	// j 위치까지 일치하는 경우
				pi[i] = ++j;	// 길이는 j+1, 이후 j 뒤로 이동 
			}
			else {	// 일치하는게 없음 + j 가 0 임
				pi[i] = 0;	// 없어도 되긴함. 명확한 의미를 위해  
			}
		}
		
		// 부분 일치 테이블 
//		System.out.println(Arrays.toString(pi));
		
		// 패턴 일치 횟수
		int cnt = 0;
		// 패턴 시작 인덱스
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i = 0, j = 0; i < originLen; i++) {
			while(j > 0 && origin[i] != pattern[j]) {
				j = pi[j-1];
			}
			
			// 둘이 일치했거나, j 가 0 인경우
			
			if(origin[i] == pattern[j]) {
				if(j == patternLen-1) {	// 패턴이 완벽하게 일치하는 경우 
					++cnt;	// 패턴 찾음
					list.add(i-j);	// 패턴의 시작 위치
					
					j = pi[j];
				} else {
					++j;
				}
			}
		}
		
		System.out.println(cnt);
		if(cnt > 0) System.out.println(list);
	}
}

/*
ababababcababaca
ababaca
==>
1
[9]


==>
4
[0, 1, 2, 3]

ababababcababaca
abacabab
==>
0
*/