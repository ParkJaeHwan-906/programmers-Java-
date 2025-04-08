package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_4013_특이한자석_박재환 {
	static BufferedReader br;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase < TC+1; testCase++) {
			sb.append('#').append(testCase).append(' ');
			init();
			sb.append('\n');
		}
		
		br.close();
		System.out.println(sb);
	}
	
	static StringTokenizer st;
	static int rotateCnt;	// 회전 횟수
	static Deque<Integer>[] gears;	// 톱니바퀴
	static int[][] eachScore = {{},{0,1},{0,2},{0,4},{0,8}};
	static void init() throws IOException {
		rotateCnt = Integer.parseInt(br.readLine().trim());
		
		// 자석은 총 4개
		gears = new ArrayDeque[5];	// 1-base
		for(int gear=0; gear<5; gear++) {
			gears[gear] = new ArrayDeque<>();
		}
		
		/*
		 * 톱니 수를  Deque 에 저장하자 
		 */
		for(int gear=1; gear<5; gear++) {
		    st = new StringTokenizer(br.readLine().trim());
		    for(int i=0; i<8; i++) {
		        gears[gear].offer(Integer.parseInt(st.nextToken()));
		    }
		}

		
		
		// 회전 명령을 받는다. 
		while(rotateCnt-- > 0) {
			st = new StringTokenizer(br.readLine().trim());
			
			int targetGear = Integer.parseInt(st.nextToken());
			int rotateDir = Integer.parseInt(st.nextToken());	// -1 : 반시계, 1 : 시계
			
			rotateGears(targetGear, rotateDir);

			// 회전 확인 
//			for(Deque<Integer> dq : gears) {
//				for(int i : dq) {
//					System.out.print(i+" ");
//				}
//				System.out.println();
//			}
		}
		
		int score = 0;
		// 8 번째 Head 의 값에 따른 합을 구한다. 
		for(int gearIdx=1; gearIdx<5; gearIdx++) {
			score += eachScore[gearIdx][gears[gearIdx].pollFirst()];
		}
		
		// 입력 확인 
//		for(Deque<Integer> dq : gears) {
//			for(int i : dq) {
//				System.out.print(i+" ");
//			}
//			System.out.println();
//		}
		sb.append(score);
	}
	
	/*
	 * 회전하는 자석을 기준
	 * 1. 시계 방향
	 * 		a. 회전 자석 왼쪽 : 반시계방향
	 * 		b. 회전 자석 오른쪽 : 반시계방향
	 * 2. 반시계 방향 
	 * 		a. 회전 자석 왼쪽 : 시계방향
	 * 		b. 회전 자석 오른쪽 : 시계방향
	 */
	static void rotateGears(int targetGear, int rotateDir) {
		// 각 기어의 회전 방향을 기록
		int[] dir = new int[5]; // 1-base, 0: 회전 안함, 1: 시계, -1: 반시계
	    dir[targetGear] = rotateDir;

	    // 왼쪽 방향 체크
	    for (int i = targetGear - 1; i >= 1; i--) {
	        // 기준은 오른쪽(i+1)의 6번과 현재(i)의 2번을 비교
	        int rightGearTooth = getNthElement(gears[i+1], 6);
	        int currentGearTooth = getNthElement(gears[i], 2);
	        if (rightGearTooth != currentGearTooth) {
	            dir[i] = -dir[i+1];
	        } else {
	            break;
	        }
	    }

	    // 오른쪽 방향 체크
	    for (int i = targetGear + 1; i <= 4; i++) {
	        // 기준은 왼쪽(i-1)의 2번과 현재(i)의 6번을 비교
	        int leftGearTooth = getNthElement(gears[i-1], 2);
	        int currentGearTooth = getNthElement(gears[i], 6);
	        if (leftGearTooth != currentGearTooth) {
	            dir[i] = -dir[i-1];
	        } else {
	            break;
	        }
	    }

	    // 실제 회전 적용
	    for (int i = 1; i <= 4; i++) {
	        if (dir[i] != 0) rotateGear(i, dir[i]);
	    }
	}

	/*
	 * 각 자석의 마주보는 날을 찾기위한 메서드
	 */
	static int getNthElement(Deque<Integer> dq, int n) {
	    Iterator<Integer> it = dq.iterator();
	    int cnt = 0;
	    while (it.hasNext()) {
	        int val = it.next();
	        if (cnt == n) return val;
	        cnt++;
	    }
	    return -1;
	}

	
	/*
	 * 각 기어를 회전시킨다. 
	 * 
	 * 시계 : 뒤 -> 앞
	 * 반시계 : 앞 -> 뒤
	 */
	static void rotateGear(int gearIdx, int rotateDir) {
		if(rotateDir == 1) {	// 시계방향
			int temp = gears[gearIdx].pollLast();
			gears[gearIdx].offerFirst(temp);
		} else if (rotateDir == -1) {	// 반시계 방향 
			int temp = gears[gearIdx].pollFirst();
			gears[gearIdx].offerLast(temp);
		}
	}
}
/* 
 * 4 개의 자석이 놓여져있다. 
 * 각 자석은 8 개의 날을 가지고 있다. -> 각 날은 N, S 자성을 갖고 있다. 
 * 
 * 자석을 1칸 회전시킬 때, 불어있는 자석의 날이 다은 경우, 반대 방향으로 1 움직인다. 
 * 
 * K 번 자석을 회전 시킨 후, 획득하는 점수의 총 합은 ?
 * 
 * 점수 계산 방법 
 * 1 번 자석에서 12시방향의 자성이 N 이면 0, S 면 1
 * 2 번 자석에서 12시방향의 자성이 N 이면 0, S 면 2
 * 3 번 자석에서 12시방향의 자성이 N 이면 0, S 면 4
 * 4 번 자석에서 12시방향의 자성이 N 이면 0, S 면 8
 */