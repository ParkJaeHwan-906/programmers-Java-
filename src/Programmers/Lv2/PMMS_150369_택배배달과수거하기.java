package Programmers.Lv2;

import java.util.*;

public class PMMS_150369_택배배달과수거하기 {
    public static void main(String[] args) {
        int cap = 4;
        int n = 5;
        int[] deliveries = {1,0,3,1,2};
        int[] pickups = {0,3,0,4,0};
        System.out.println(new PMMS_150369_택배배달과수거하기()
                .solution(cap, n, deliveries, pickups));
    }

    /*
        일렬로 나열된 n 개의 집에 배달
        배달하며 빈 재활용 상자들을 수거

        i 번째 집은 창고에서 i 거리만큼 떨어져있다.
        j 번째 집은 i 번째 집에서 j-i 만큼 떨어져있다.

        트럭에는 재활용 상자 cap 개를 실을 수 있다.
        배달할 상자로 실고 출발해 각 집에 배달하며,
        재활용 상자를 수거한다.

         배달할 상자와, 수거할 상자의 개수를 알고 있을 때
         트럭 하나로 모들 배달과 수거를 마치고 돌아오는 최소 이동 거리를 구하라
         각 집에 배달 및 수거 시 원하는 개수만큼 할 수 있다.
     */
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long totalDist = 0;      // 이동하는 총 거리
        /*
            최소 거리를 구하여라.
            -> 최대한 먼 거리에 있는 집은 적게 이동해야하지 않을까

            가장 멀리 있는 박스부터 수거하여 처리?
         */
        // 가장 멀리 있는것부터 처리하는 것이 목표
        int deliveryIdx, pickupIdx;
        deliveryIdx = pickupIdx = n-1;      // 마지막서부터 탐색
        // -> 멀리 있는 곳을 최대한 적게 가자

        // 모든 배송 / 수거를 마칠때까지
        while(deliveryIdx >= 0 || pickupIdx >= 0) {
            // 1. 인덱스 보정
            while(deliveryIdx >= 0 && deliveries[deliveryIdx] == 0) --deliveryIdx;
            while(pickupIdx >= 0 && pickups[pickupIdx] == 0) --pickupIdx;

            // 종료 조건 -> 모든 배송 / 수거를 마쳤을 경우
            if(deliveryIdx < 0 && pickupIdx < 0) break;

            // 배송 거리를 기록
            totalDist += (Math.max(deliveryIdx, pickupIdx)+1)*2;

            // 배송 / 수거 업무가 남음
            int deliveryBox, pickupBox;
            deliveryBox = pickupBox = cap;

            // 2. 배달
            // 모든 배송을 완료 / 최대 적재량을 넘김
            while(deliveryIdx >= 0 && deliveryBox > 0) {
                // 2 가지 경우가 있음
                // 2-1. 모두 처리할 수 있음
                if(deliveries[deliveryIdx] <= deliveryBox) {
                    deliveryBox -= deliveries[deliveryIdx];
                    deliveries[deliveryIdx--] = 0;
                } else {    // 2-2. 한 번에 처리하지 못해 나눠처리해야함
                    deliveries[deliveryIdx] -= deliveryBox;
                    deliveryBox = 0;
                }
            }
            // 3. 수거
            while(pickupIdx >= 0 && pickupBox > 0) {
                // 3 가지 경우가 있음
                // 3-1. 모두 처리할 수 있음
                if(pickups[pickupIdx] <= pickupBox) {
                    pickupBox -= pickups[pickupIdx];
                    pickups[pickupIdx--] = 0;
                } else {    // 3-2. 한 번에 처리하지 못해 나눠처리해야함
                    pickups[pickupIdx] -= pickupBox;
                    pickupBox = 0;
                }
            }
        }

        return totalDist;
    }
}
