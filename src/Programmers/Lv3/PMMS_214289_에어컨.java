package Programmers.Lv3;

import java.util.*;

public class PMMS_214289_에어컨 {
    public static void main(String[] args) {
        int temperature = -10;
        int t1 = -5;
        int t2 = 5;
        int a = 5;
        int b = 1;
        int[] onboard = {0,0,0,0,0,1,0};
//        int[] onboard = {0,0,0,0,0,0,0};
        System.out.println(new PMMS_214289_에어컨().solution(temperature, t1, t2, a, b, onboard));
    }


    int lowLimit, highLimit;        // 최저 희망 온도, 최고 희망 온도
    int aPrice, bPrice;             // 온도를 상/하 시키는데 소모되는 비용, 온도를 유지하는데 소모되는 비용
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        lowLimit = t1+10; highLimit = t2+10;		// 온도의 범위가 -10 ~ 40 -> 0 ~ 50 으로 변환
        aPrice = a; bPrice = b;

        return getMinCost(temperature+10, onboard);
    }

    /*
        DP[][] => [현재시간][현재온도] = 최소 비용
        temperature : 외부 기온 
        onboard		: 승객 탑승 여부
     */
    int getMinCost(int temperature, int[] onboard) {
    	int[][] dp = new int[onboard.length][51];
    	for(int[] arr : dp) {
    		Arrays.fill(arr, 100*1000+1);
    	}
    	
    	// 초기값 설정
    	// 0 분에 외부온도 비용은 0
    	dp[0][temperature]=0; 
    	
    	for(int time=0; time<onboard.length-1; time++) {
    		/*
    		 * 에어컨을 킨다
    		 * - 온도 증가
    		 * - 온도 감소
    		 * - 온도 유지
    		 * 
    		 * 에어컨을 끈다
    		 * - 온도 증가
    		 * - 온도 감소
    		 */
    		for(int temp=0; temp<51; temp++) {
    			// 손님이 타있지만, 희망온도 범주 내에 있지 않는 경우 
    			if(onboard[time] == 1 && (lowLimit > temp || highLimit < temp)) continue;
    			
    			// 에어컨을 킨다.
    			// - 온도를 증가시킨다.
    			if(temp < 50) dp[time+1][temp+1] = Math.min(dp[time+1][temp+1], dp[time][temp]+aPrice);
    			// - 온도를 감소시킨다.
    			if(temp > 0) dp[time+1][temp-1] = Math.min(dp[time+1][temp-1], dp[time][temp]+aPrice);
    			// -현 온도 유지
    			dp[time+1][temp] = Math.min(dp[time+1][temp], dp[time][temp]+bPrice);
    			
    			// 에어컨을 끈다.
    			// + 현재 온도와 외부 기온이 같다면
    			if(temp == temperature) dp[time+1][temp] = Math.min(dp[time+1][temp], dp[time][temp]);
    			// - 온도를 증가시킨다.
    			else if(temp < 50 && temp < temperature) dp[time+1][temp+1] = Math.min(dp[time+1][temp+1], dp[time][temp]);
    			// - 온도를 감소시킨다.
    			else if(temp > 0 && temp > temperature) dp[time+1][temp-1] = Math.min(dp[time+1][temp-1], dp[time][temp]);
    		}
    	}
    	
    	// 마지막 시간에 최대값을 반환
    	return findMin(dp[onboard.length-1], onboard);
    }
    
    int findMin(int[] dp, int[] onboard) {
    	int min = Integer.MAX_VALUE;

    	/*
    	 * 마지막 시간에 손님이 탈 수도, 타지 않을 수도 있음
    	 */
    	for(int temp=0; temp<51; temp++) {
    		// 손님이 타게된다면, 희망 온도 내에 있어야함
    		if(onboard[onboard.length-1] == 1 && (lowLimit > temp || highLimit < temp)) continue;
    		min = Math.min(min, dp[temp]);
    	}
    	return min;
    }
}

/*
    온도 : t1 ~ t2 를 유지할 수 있도록한다.
    현재 ( 0 분 ) 실내온도는 실외온도와 같다.

    희망온도는 에어컨의 전원이 켜져 있는 동안 원하는 값으로 변경할 수 있다.
    1분마다 희망온도로 1도씩 상승 / 하강 한다.
    온도가 같다면, 에어컨이 켜져있는 동안 온도가 변하지 않늗다.

    에어컨을 끄면 실외 온도와 같아지는 방향으로 1씩 상승 / 하강 한다.
    온도가 같다면 변하지 않는다.

    에어컨 소비전력은 희망온도와 실내 온도가 다르다면 1분에 a 만큼 소모,
    같다면 b 만큼 소모한다. 
    
  [시도 1]  
     나올 수 있는 케이스,
        1. 희망온도까지 올린 다음, 유지하기
        2. 손님이 아예 타지 않는 경우 희망온도까지 올릴 필요가 없음

        [!! 영하의 온도를 가질 수 있음 !!]
        조합? XX
        1. 에어컨을 가동한다.
        2. 에어컨을 가동하지 않는다.
 */