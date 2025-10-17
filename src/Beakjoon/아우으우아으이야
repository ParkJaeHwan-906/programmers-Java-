package Beakjoon;

import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws IOException {
		init();
	}
	static StringTokenizer st;
	static BufferedReader br;
	static int x, y;
	static int totalLen;
	static void init() throws IOException {
	    totalLen = 0;
	    br = new BufferedReader(new InputStreamReader(System.in));
	    int inputCnt = Integer.parseInt(br.readLine().trim());
	    for(int i=0; i<inputCnt; i++) {
	        st = new StringTokenizer(br.readLine().trim());
	        int nx = Integer.parseInt(st.nextToken());
	        int ny = Integer.parseInt(st.nextToken());
	        // 첫 번째 입력 제외
	        if(i == 0) {
	            x = nx; y = ny;
	            continue;
	        }
	       
	        if(nx <= y) {
	            y = Math.max(y, ny);
	        } else {
	            totalLen += Math.abs(x-y);
	            x = nx; y = ny;
	        }
	    }
	    br.close();
	    System.out.print(totalLen+Math.abs(x-y));
	}
}
