package Programmers.Lv1;

import java.util.*;

public class PMMS_258712_가장많이받은선물 {
	public static void main(String[] args) {
		String[] friends = {"muzi", "ryan", "frodo", "neo"};
		String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
		
		System.out.println(new PMMS_258712_가장많이받은선물().solution(friends, gifts));
	}
	
	Map<String, Map<String, Integer>> map;
	public int solution(String[] friends, String[] gifts) {
        map = new HashMap<>();
        
        // map 초기 세팅
        for(String name : friends) {
        	map.put(name, new HashMap<>());
        }
		
        // 선물 현황 
        for(String gift : gifts) {
        	String[] arr = gift.split(" ");
        	map.get(arr[0]).getOrDefault(arr[1], 0) 
        }
		
		return 0;
    }
}
/*
 * 두 사람이 선물을 주고 받은 기록이 있다면
 * 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받는다.
 * 선물을 주고 받은 기록이 없거나, 같다면 선물 지수가 더 큰 사람이 하나 받는다.
 * - 선물 지수 : 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값
*/