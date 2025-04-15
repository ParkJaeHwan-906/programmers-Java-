package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_조합_페르마의소정리_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<TC+1; testCase++) {
			sb.append('#').append(testCase).append(' ');
			init();
			sb.append('\n');
		}
		br.close();
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int n, r;	// 원소의 개수, 뽑고자 하는 개수
	static long[] fac;	// 팩토리얼 메모이제이션
	static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		fac = new long[n+1];
		sb.append(nCr(n,r,1234567891));
	}
	
	static long nCr(int n, int r, int p) {
		if(r == 0) return 1L;
		
		// 팩토리얼 생성 
		fac[0] = 1;
		for(int i=1; i<n+1; i++) {
			fac[i] = fac[i-1]*i % p;
		}
		
		return (fac[n]*power(fac[r], p-2, p)
				% p * power(fac[n-r], p-2, p) % p) % p;
	}
	
	static long power(long x, long y, long p) {
		long res = 1L;
		x = x%p;
		
		while(y>0) {
			if(y%2==1) {
				res = res*x%p;
			}
			
			y = y >> 1;
			x = (x*x)%p;
		}
		
		return res;
	}
}

/* 
 * 조합의 개수를 구한다.
 * 1. 이항계수
 * 		최대 입력 1,000,000  -> N^2 시간 초과 
 * 2. 페르마의 소정리
 * 		a 가 정수이고 p 가 소수이고 a 가  p 의 배수가 아닐때,
 * 		a ^ p = a (mod p) 이며 a != 0 일 때,
 * 		a^p-1 = 1 (mod p) 이다. 
 */