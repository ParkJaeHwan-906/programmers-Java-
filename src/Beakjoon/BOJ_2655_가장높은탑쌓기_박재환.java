package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_2655_가장높은탑쌓기_박재환 {
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
	static int blockCnt;	// 벽돌의 개수
	static int[][] blocks;	// 벽돌 [인덱스, 밑면 넓이, 높이, 무게 ]
	static void init() throws IOException {
		blockCnt = Integer.parseInt(br.readLine().trim());
		blocks = new int[blockCnt][4];
		
		for(int i=0; i<blockCnt; i++) {
			st = new StringTokenizer(br.readLine().trim());
			
			blocks[i][0] = i+1;
			blocks[i][1] = Integer.parseInt(st.nextToken());
			blocks[i][2] = Integer.parseInt(st.nextToken());
			blocks[i][3] = Integer.parseInt(st.nextToken());
		}
		
		// 밑면을 기준 내림차순으로 정렬한다.
		Arrays.sort(blocks, (a,b) -> b[1] - a[1]);
		
		getMaxHeight();
	}
	
	/*
	 * 벽돌을 쌓을 수 있는 조건 
	 * 1. 이전의 벽돌보다 밑면이 작을것 
	 * 2. 이전의 벽돌보다 가벼울것
	 */
	static void getMaxHeight() {
		int[] dpArr = new int[blockCnt];	// 해당 벽돌을 사용할때까지 최대 높이 
		// 역추적을 위한 배열 
		int[] forTrace = new int[blockCnt];
		
		for(int blockIdx=0; blockIdx<blockCnt; blockIdx++) {
			dpArr[blockIdx] = blocks[blockIdx][2];
			forTrace[blockIdx] = -1;
			// 현재 블록 이전의 블록을 이용해 쌓을 수 있는 최대 높이 갱신 
			for(int prevBlockIdx=0; prevBlockIdx < blockIdx; prevBlockIdx++) {
				if(blocks[prevBlockIdx][3] > blocks[blockIdx][3]) {	// 이전 벽돌의 무게가 더 무거운 경우 -> 쌓을 수 있음 
					// 해당 경우가 더 높은 경우인지 확인 
					if(dpArr[blockIdx] < dpArr[prevBlockIdx] + blocks[blockIdx][2]) {
						dpArr[blockIdx] = dpArr[prevBlockIdx] + blocks[blockIdx][2];
						forTrace[blockIdx] = prevBlockIdx;
					}
				}
			}
		}
		// test
//		for(int[] block : blocks) {
//			System.out.println(Arrays.toString(block));
//		}
//		System.out.println(Arrays.toString(dpArr));
//		System.out.println(Arrays.toString(forTrace));
		
		
		// 최대 높이 찾기
		int max = 0, idx = -1;	// 역추적을 위한 인덱스를 찾음 
		for(int i=0; i<blockCnt; i++) {
			if(max < dpArr[i]) {
				max = dpArr[i];
				idx = i;
			}
		}
		
		// 최대 높이일때의 idx 를 이용해 사용한 블록 추적하기 
		// idx 를 따라가면 위에서 -> 아래로 사용한 블록을 따라갈 수 있음 
		Queue<Integer> q = new LinkedList<>();
		
		while(idx != -1) {
			q.offer(blocks[idx][0]); // 블록 번호
		    idx = forTrace[idx];     // 다음 추적할 인덱스
		}
		
		sb.append(q.size()).append('\n');
		while(!q.isEmpty()) {
			sb.append(q.poll()).append('\n');
		}
	}
}

/* 
 * 밑면이 정사각형인 직육면체 벽돌들을 사용하여 탑을 쌓고자한다. 
 * 벽돌을 아래에서 위로 쌓으면서 간다. 
 * 
 * 1. 벽돌은 회전시킬 수 없다.
 * 2. 밑면의 넓이가 같은 벽돌은 없다. 무게도 다 다르다.
 * 3. 높이는 같을 수 있다.
 * 4. 탑을 쌓을 때 아래가 더 넓어야한다. 
 * 5. 아래가 더 무거워야한다. 
 */