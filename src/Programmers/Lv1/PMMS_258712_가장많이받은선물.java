package Programmers.Lv1;

import java.util.*;

public class PMMS_258712_가장많이받은선물 {
	public static void main(String[] args) {
		String[] friends = {"muzi", "ryan", "frodo", "neo"};
		String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
		
		System.out.println(new PMMS_258712_가장많이받은선물().solution(friends, gifts));
	}

	/*
		2 x 2 크기로 각각 선물을 주고받은 현황을 기록한다.
	 */
	Map<String, Integer> nameMap;		// 이름으로 인덱스 값을 기록하기 위한 Map
	int maxGift;						// 가장 많은 선물
	public int solution(String[] friends, String[] gifts) {
		maxGift = Integer.MIN_VALUE;

		// 선물을 주고받은 기록을 정리한다.
		makeGiftBoard(friends, gifts);

		// 가장 많은 선물의 개수를 구한다.
		getMaxGift();

		return maxGift;
	}

	void getMaxGift() {
		// 선물 지수를 계산
		int[] giftIdx = new int[giftBoard.length];
		/*
			선물 지수
			-> 내가 준 선물 - 받은 선물
		 */
		for(int from=0; from < giftBoard.length; from++) {
			int give = 0, receive = 0;
			for(int to=0; to < giftBoard.length; to++) {
				if(from == to) continue;

				give += giftBoard[from][to];
				receive += giftBoard[to][from];
			}

			giftIdx[from] = give - receive;
		}
		// 선물 지수 확인
//		System.out.println(Arrays.toString(giftIdx));
		for(int from=0; from < giftBoard.length; from++) {
			int gift = 0;
			for(int to=0; to < giftBoard.length; to++) {
				// 1. 자기 자신인 경우 패스
				if(from == to) continue;

				// 2. 선물을 주고받은 이력이 있음
				if(giftBoard[from][to] > 0 && giftBoard[from][to] > giftBoard[to][from]) {
					// 내가 선물을 보낸 이력이 있음
					// 내가 더 많이 줬으면, 다음달에 하나를 받을 수 있음
					gift++;
				}
				// 3. 주고 받은 적이 없거나, 같은 양의 선물을 받음
				else if(giftBoard[from][to] == giftBoard[to][from] ||
						(giftBoard[from][to] == 0 && giftBoard[to][from] == 0)) {
					// 선물 지수를 비교한다.
					if(giftIdx[from] > giftIdx[to]) gift++;
				}
			}

			maxGift = Math.max(maxGift, gift);
		}
	}

	/*
		선물을 주고받은 현황판을 생성한다.
	 */
	int[][] giftBoard;			// 선물 현황판
	void makeGiftBoard(String[] friends, String[] gifts) {
		// 각 이름에 인덱스를 할당한다
		nameMap = new HashMap<>();
		for(int idx=0; idx< friends.length; idx++) {
			nameMap.put(friends[idx], idx);
		}

		giftBoard = new int[friends.length][friends.length];
		for(String gift : gifts) {
			String[] arr = gift.split(" ");

			int from = nameMap.get(arr[0]);
			int to = nameMap.get(arr[1]);

			giftBoard[from][to]++;
		}
		// 선물 현황 확인
//		for(int[] arr : giftBoard) {
//			System.out.println(Arrays.toString(arr));
//		}
	}
}
/*
 * 두 사람이 선물을 주고 받은 기록이 있다면
 * 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물을 하나 받는다.
 * 선물을 주고 받은 기록이 없거나, 같다면 선물 지수가 더 큰 사람이 하나 받는다.
 * - 선물 지수 : 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값
*/