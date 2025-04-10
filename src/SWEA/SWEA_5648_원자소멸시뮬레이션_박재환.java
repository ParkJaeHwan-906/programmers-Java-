package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_5648_원자소멸시뮬레이션_박재환 {
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
	static Queue<int[]> atoms;	// 원자들의 이동을 저장할 큐
	static int atomCnt;			// 원자들의 수
	static int minX, minY, maxX, maxY;	// 탐색 범위 설정 
	static void init() throws IOException {
		minX = minY = 1000;
		maxX = maxY = -1000;
		
		atomCnt = Integer.parseInt(br.readLine().trim());
		atoms = new LinkedList<>();
		
		for(int atom=0; atom<atomCnt; atom++) {
			st = new StringTokenizer(br.readLine().trim());
			
			// 중간에서 만나는 부분 처리  *2 
			// 양수 부분 처리  +2000
			int x = Integer.parseInt(st.nextToken())*2 + 2000;
			int y = Integer.parseInt(st.nextToken())*2 + 2000;
			int dir = Integer.parseInt(st.nextToken());
			int power = Integer.parseInt(st.nextToken());
			
			atoms.offer(new int[] {x,y,dir,power});
			
			maxX = Math.max(maxX, x);
			maxY = Math.max(maxY, y);
			minX = Math.min(minX, x);
			minY = Math.min(minY, y);
		}
		
//		System.out.println("입력 확인");
//		for(int[] arr : atoms) {
//			System.out.println(Arrays.toString(arr));
//		}
//		System.out.println();
		
		moveAtoms();
	}
	
	/*
	 * 원자들을 이동시킨다. 
	 * Queue 를 사용해, 매 초마다 원자들을 동시 이동 처리한다. 
	 * 0 : 상
	 * 1 : 하
	 * 2 : 좌
	 * 3 : 우
	 * 
	 * 원자가 중간에 충돌하는 경우
	 * 1. 0.5 씩 이동 처리
	 * 2. 좌표를 2배 처리 
	 * 3. 이전 좌표 기억
	 */
	static int[] dx = {0,0,-1,1};
	static int[] dy = {1,-1,0,0};
	static void moveAtoms() {
		int powerSum = 0;
		
		// 원자들을 1초 동안 움직인 뒤, 충돌한 원자들을 한 번에 처리한다. 
		while(!atoms.isEmpty()) {
			// 원자가 한개밖에 없다면 충돌할 수 없다.
			if(atoms.size() == 1) break;
			
			// 이동 위치를 기록할 큐
			Queue<int[]> temp = new LinkedList<>();
			// 같은 위치의 원자들을 구별하기 위한 맵 사용?
			Map<Integer, Integer> map = new HashMap<>();
			
//			System.out.println("원자 이동");
			// 원자들을 전부 이동처리한다. 
			while(!atoms.isEmpty()) {
				int[] cur = atoms.poll();
//				System.out.println("현재 원자 : " + Arrays.toString(cur));
				int x = cur[0];
				int y = cur[1];
				int dir = cur[2];
				int power = cur[3];
				
				// 원자들을 이동시킨다. 
				int nx = x + dx[dir];
				int ny = y + dy[dir];
//				System.out.println("이동 후 :" + nx + ", " + ny);
				// 더 이상 만날 수 없다면 ( 범위 벗어남 ) 
				if(nx < minX || ny < minY || nx > maxX || ny > maxY) continue;
				
				int key = nx * 4001 + ny;
				// 중복되는 위치가 있는지 확인한다.
				map.put(key , map.getOrDefault(key, 0)+1);
				temp.offer(new int[] {nx, ny, dir, power});
			}
			
//			// test
//			for(int[] arr : temp) {
//				System.out.println(Arrays.toString(arr));
//			}
//			System.out.println();
//			
//			System.out.println("중복 확인");
//			for(Integer key : map.keySet()) {
//				System.out.print(key+ " : ");
//				System.out.println(map.get(key));
//			}
			
			
			// 충돌한 원자들은 제외하고, 다음 이동 준비를 한다. 
			while(!temp.isEmpty()) {
				int[] cur = temp.poll();
				int x = cur[0];
				int y = cur[1];
				int dir = cur[2];
				int power = cur[3];
				
				int key = x * 4001 + y;
				// 중복되는 원자가 있는지 확인한다. 
				if (map.get(key) > 1) {
					powerSum += power;
					continue;
				}
				
				atoms.offer(new int[] {x,y,dir,power});
			}
		}
		
		sb.append(powerSum);
	}
}

/* 
 * 두 개 이상의 원자가 충돌 할 경우, 충돌한 원자들은 각자 보유한 에너지를 모두 방출하고 소멸된다. 
 * 상 : y 증가
 * 하 : y 감소
 * 좌 : x 감소
 * 우 : x 증가 
 * 
 * 원자들의 이동속도는 동일하다. 1초에 1만큼의 거리 이동 
 * 
 * 원자들이 소멸되면서 방출하는 에너지의 총 합을 구하라 
 */