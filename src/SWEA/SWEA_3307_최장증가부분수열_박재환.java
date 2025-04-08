package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_3307_최장증가부분수열_박재환 {
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
	static int arrLen;		// 배열의 길이
	static int[] arr;		// 배열 
	static List<Integer> lisList;	// 최장 증가 부분 수열
	static void init() throws IOException {
		lisList = new ArrayList<>();
		
		arrLen = Integer.parseInt(br.readLine().trim());
		arr = new int[arrLen];
		
		st = new StringTokenizer(br.readLine().trim());
		for(int idx=0; idx<arrLen; idx++) {
			arr[idx] = Integer.parseInt(st.nextToken());
			// 입력과 동시에 최장 증가 부분 수열 계산  -> 최적화
			if(idx > 0) {
				// 현재 LIS 의 마지막 값보다 작다면 -> 바로 추가 
				if(lisList.get(lisList.size()-1) < arr[idx]) {
					lisList.add(arr[idx]);
				}
				// LIS 의 중간에 삽입되어야하는 경우
				else {
					int insertIdx = findInsertIdx(lisList, arr[idx]);
					lisList.set(insertIdx, arr[idx]);
				}
				continue;
			}
			
			lisList.add(arr[idx]);
		}
		
		// 최장 증가 부분 수열을 구한다. 
//		getLisLen_DP();
//		getLisLen_BinarySearch();
		sb.append(lisList.size());
	}
	
	// 1. DP 사용 
	static void getLisLen_DP() {
		// 각 위치에서 LIS 의 최대 길이를 저장할 배열 
		int[] dpArr = new int[arrLen];
		Arrays.fill(dpArr, 1);	// 자기 자신을 포함하는 길이로 초기화
		for(int idx1=0; idx1<arrLen; idx1++) {
			for(int idx2=0; idx2<idx1; idx2++) {
				if(arr[idx1] > arr[idx2]) {	// 증가 부분 수열이라면
					dpArr[idx1] = Math.max(dpArr[idx1], dpArr[idx2]+1);
 				}
			}
		}
		
		findMaxLen(dpArr);
	} 
	
	static void findMaxLen(int[] dpArr) {
		int max = -1;
		for(int len : dpArr) {
			max = Math.max(max, len);
		}
		sb.append(max);
	}
	
	static void getLisLen_BinarySearch() {
		List<Integer> list = new ArrayList<Integer>();	// 최장 증가 수열의 최대 길이를 저장할 리스트
		
		list.add(arr[0]);
		// 각 수를 순차적으로 처리한다. 
		for(int idx=1; idx<arr.length; idx++) {
			// 1.현재 원소가 이전까지의 LIS 의 마지막 원소보다 큰 경우 -> LIS 길이 증가
			if(list.get(list.size()-1) < arr[idx]) {
				list.add(arr[idx]);
			} else {	// 2. 중간에 삽입해야하는 경우 
				// 2-1. 현재 숫자가 삽입될 위치를 찾는다.
				int insertIdx = findInsertIdx(list, arr[idx]);
				
				list.set(insertIdx, arr[idx]);
			}
		}
		
		sb.append(list.size());
	}
	
	static int findInsertIdx(List<Integer> list, int targetNum) {
		int l = 0, r = list.size()-1;
		
		// 찾고자하는 수보다 큰 가장 작은 값 
		while(l < r) {
			int mid = l + (r-l)/2;
			
			if(list.get(mid) < targetNum) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		
		return l;
	}
}
