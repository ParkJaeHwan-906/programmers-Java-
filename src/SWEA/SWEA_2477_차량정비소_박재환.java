package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_2477_차량정비소_박재환 {
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
	
	/*
	 * 고객 관리를 위한 클래스 
	 */
	static class Customer  {
		int no;		// 고객 번호
		int inTime;	// 처리 시간 or 도착 시간
		int csNo;	// 접수 창구
		int msNo;	// 정비 창구

		int csEndTime; // 접수 창구 종료 시간 (정비 창구 삽입 시 정렬)

		public Customer (int no, int inTime) {
			this.no = no;
			this.inTime = inTime;
			this.csNo = -1;
			this.msNo = -1;
			this.csEndTime = -1;
		}
		
//		void printInfo() {
//			System.out.println("고객 번호 : " + no);
//			System.out.println("시간  : "+ inTime);
//			System.out.println("접수 창구 : " + csNo);
//			System.out.println("정비 창구 : " +  msNo);
//		}
		
		// 정렬
		/*
		 * 접수 창구의 우선순위 
		 * 1. 고객 번호가 낮은 순서
		 * 2. 빈 창구가 여러 곳 -> 접수 창구 번호가 작은 곳으로
		 * 
		 * 정비 창구의 우선 순위 
		 * 1. 먼저 기다리는 고객이 우선 
		 * 2. 동시에 들어왔을 때는, 접수 창구 번호가 작은 고객 우선
		 * 3. 빈 창구가 여러 곳, 정비 창구 번호가 작은 곳으로
		 */
	}
	
	static StringTokenizer st;
	static int csCnt, msCnt, curTime;			// 접수 창구, 정비 창구 의 개수 
	static int[] cs, ms;						// 접수 창구, 정비 창구  
	static int customerCnt;						// 고객 수 
	static PriorityQueue<Customer> customers;			// 고객
	static int targetCs, targetMs;				// 대상 접수 창구, 대상 정비 창구
	static void init() throws IOException {
		curTime = 1000;
		st = new StringTokenizer(br.readLine().trim());
		
		csCnt = Integer.parseInt(st.nextToken());
		msCnt = Integer.parseInt(st.nextToken());
		customerCnt = Integer.parseInt(st.nextToken());
		
		cs = new int[csCnt];
		ms = new int[msCnt];
		
		targetCs = Integer.parseInt(st.nextToken());
		targetMs = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<csCnt; idx++) {
			cs[idx] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<msCnt; idx++) {
			ms[idx] = Integer.parseInt(st.nextToken());
		}
		
		customers = new PriorityQueue<>((c1, c2) -> {
			if(c1.inTime == c2.inTime) return c1.no - c2.no;

			return c1.inTime - c2.inTime;
		});
		st = new StringTokenizer(br.readLine().trim());
		for(int no=1; no<customerCnt+1; no++) {
			int inTime = Integer.parseInt(st.nextToken());
			curTime = Math.min(curTime, inTime);
			customers.offer(new Customer(no, inTime));
		}
		
		findCustomers();
	}
	
	
	/*
	 * 접수 창구와, 정비 창구 모두 들어온 순으로 처리한다. 
	 * 동시 접근 시, 창구 번호를 기준으로 해야하므로, 주의
	 */
	static Queue<Customer>[] csPq;
	static Queue<Customer>[] msPq;
	static PriorityQueue<Customer> waitPq;	
	static Queue<Customer> doneQueue;	// 작업을 모두 마친 고객을 저장 
	static void findCustomers() {
		doneQueue = new LinkedList<>();
		waitPq = new PriorityQueue<>((c1, c2) -> {	// 접수 창구 끝낸 순 비교 + 고객 번호 비교
			if (c1.csEndTime == c2.csEndTime) return c1.csNo - c2.csNo;

			return c1.csEndTime - c2.csEndTime;
		});		// 접수 -> 정비 의 중간 대기 공간


		// 접수 창구와 정비 창구를 구현한다.
		// 진입 시간을 우선으로
		// 같다면 각 창구의 우선 순위를 적용 
		// 각 창구를 우선순위 큐로 정의한다. 
		csPq = new LinkedList[csCnt];
		msPq = new LinkedList[msCnt];
		
		for(int i=0; i<csCnt; i++) {
			csPq[i] = new LinkedList<>();
		}
		
		for(int i=0; i<msCnt; i++) {
			msPq[i] = new LinkedList<>();
		}
		
		//---------------------------------------------------
		
		while(doneQueue.size() < customerCnt) {	// 모든 고객의 작업이 끝나는 경우  
			// 1. 정비 창구에서 끝낼 수 있는 인원이 있다면 끝낸다.
			finishMs(curTime);
			// 2. 정비 창구로 갈 수 있는 인원이 있다면, 정비 창구로 보낸다. 
			//	2-1. 접수 창구에서 작업이 끝나는 인원을 추가한다. 
			finishCs(curTime);
			//	2-2. waitQ 에서 대기중인 인원을 정비 창구로 보낸다. 
			moveMs(curTime);
			// 3. 접수 창구로 갈 수 있는 인원이 있다면, 접수 창구로 보낸다. 
			moveCs(curTime);
			
			// 현재 시간에 할 수 있는 작업 완료
			curTime++;
			
			
		}
		
		int result = 0;
		
		for(Customer customer : doneQueue) {
			if(customer.csNo == targetCs-1 && customer.msNo == targetMs-1) {
				result += customer.no;
			}
		}
		
		sb.append(result == 0 ? -1 : result);
	}
	
	// 고객이 접수 창구에서 작업을 마치고, 정비 창구로 넘어갈 때 작업 시간을 정확히 반영
	static void finishCs(int curTime) {
	    for (int no = 0; no < csCnt; no++) {
	        if (!csPq[no].isEmpty() && csPq[no].peek().inTime == curTime) {
	            Customer customer = csPq[no].poll();
				customer.csEndTime = curTime;	// 종료 시간 갱신
	            waitPq.add(customer); // 접수 창구에서 작업을 마친 후 대기 큐로 이동
	        }
	    }
	}

	// 정비 창구에서 끝낼 수 있는 고객이 있으면 마무리
	static void finishMs(int curTime) {
	    for (int no = 0; no < msCnt; no++) {
	        if (!msPq[no].isEmpty() && msPq[no].peek().inTime == curTime) {
	            Customer customer = msPq[no].poll();
	            doneQueue.add(customer); // 작업을 마친 고객을 완료 큐에 추가
	        }
	    }
	}

	// 대기 큐에서 처리할 고객을 정비 창구로 보내는 로직
	static void moveMs(int curTime) {
	    for (int no = 0; no < msCnt; no++) {
	        if (msPq[no].isEmpty() && !waitPq.isEmpty()) {
	            Customer customer = waitPq.poll();
	            customer.msNo = no;
	            customer.inTime = curTime+ms[no]; // 정비 창구 처리 시간 추가
	            msPq[no].offer(customer);
	        }
	    }
	}

	// 대기 큐에서 처리할 고객을 접수 창구로 보내는 로직
	static void moveCs(int curTime) {
	    for (int no = 0; no < csCnt; no++) {
	        if (csPq[no].isEmpty() && !customers.isEmpty() && customers.peek().inTime <= curTime) {
	            Customer customer = customers.poll();
	            customer.csNo = no;
	            customer.inTime = curTime+cs[no]; // 접수 창구 처리 시간 추가
	            csPq[no].offer(customer);
	        }
	    }
	}

}

/* 
 * 고객 만족도 설문지에는 고객이 이용했던 접수 창구번호와 정비 창구번호가 있다. 
 * N 개의 접수 창구와 M 개의 정비 창구가 있다. 
 * 
 * 접수 창구는 1 ~ N 개의 번호가 있다. 
 * 정비 창구는 1 ~ M 개의 번호가 있다. 
 * 
 * 순서 
 * 접수 창구 -> 정비 창구 
 * 접수 창구 i 에서 고객 한 명을 처리하는 시간 a[i]
 * 정비 창구 j 에서 고객 한 명을 처리하는 시간 b[j]
 * 
 * 고객 수는 K ( 1 ~ K )
 * 고객이 정비소에 도착하는 시간 t[k]
 * 
 * 빈 창구가 있다면, 고장 접수, 없다면 빈 창구 생길 때 까지 대기 
 * 
 * 접수 창구의 우선순위 
 * 1. 고객 번호가 낮은 순서
 * 2. 빈 창구가 여러 곳 -> 접수 창구 번호가 작은 곳으로
 * 
 * 정비 창구의 우선 순위 
 * 1. 먼저 기다리는 고객이 우선 
 * 2. 동시에 들어왔을 때는, 접수 창구 번호가 작은 고객 우선
 * 3. 빈 창구가 여러 곳, 정비 창구 번호가 작은 곳으로 
 * 
 * 고객의 도착시간 t[k], 접수 창구 처리시간 a[i], 정비 창구 처리 시간 b[j] 가 주어졌을때, 
 * 분실 고객과 같은 접수 창구와, 같은 정비 창구를 이용한 고객번호들을 찾아 합을 출력해라
 * 
 * 없다면 -1 을 출력해라 
 */