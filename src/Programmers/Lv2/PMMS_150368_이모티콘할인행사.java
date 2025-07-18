package Programmers.Lv2;

import java.util.*;

public class PMMS_150368_이모티콘할인행사 {
    public static void main(String[] arg) {
        int[][] users = {{40,10000},{25,10000}};
        int[] emoticons = {7000,9000};

        System.out.println(Arrays.toString(new PMMS_150368_이모티콘할인행사().solution(users, emoticons)));
    }

    /*
        목표
        1. 이모티콘 플러스 서비스 가입자 최대한 늘리기
        2. 이모티콘 판매액 최대한 늘리기

        할인행사
        - n 명의 카카오톡 사용자들에게 이모티콘 m 개를 할인하여 판매한다.
        - 이모티콘마다 할인율은 다를 수 있다.
            10%, 20%, 30%, 40% 중 하나

        사용자들을 다음과 같은 기준을 따라 이모티콘을 사거나 이모티콘 플러스 서비스에 가입한다.
        - 각 사용자들은 자신의 기준에 따라 일정 비율 이상 할인하는 이모티콘을 모두 구매한다.
        - 각 사용자들은 자신의 기준에 따라 이모티콘 구매 비용의 합이 일정 가격 이상 된다면,
            구매를 모두 취소하고, 이모티콘 플러스 서비스에 가입한다.

        반환
        [이모티콘 플러스 가입자 수, 이모티콘 매출액]
     */
    int[] sales = {10,20,30,40};    // 할인율
    double[][] emoticonsPrice;
    int[] result;
    int[][] users;
    int[] emoticons;
    public int[] solution(int[][] users, int[] emoticons) {
        result = new int[2];
        this.users = users;
        this.emoticons = emoticons;
        // user -> 최대 100 명
        // [n% 이상 할인하는 이모티콘 구매, 마지노선 금액]
        // emoticons  -> 최대 7개
        // 각 이모티콘의 가격
        // 최종 목표는, 이모티콘 플러스 가입자 최대, 그 다음이 매출액 최대
        // 완전탐색 시, 각 이모티콘은 총 4 개의 할인율을 가질 수 있음 -> 사람수*4^n (n : 이모티콘 수)

        // 1. 각 할인을 적용한 이모티콘의 가격 전처리
        emoticonsPrice = new double[emoticons.length][4];
        for(int emoticonIdx=0; emoticonIdx<emoticons.length; emoticonIdx++) {
            for(int saleIdx=0; saleIdx<4; saleIdx++) {
                emoticonsPrice[emoticonIdx][saleIdx] =
                        emoticons[emoticonIdx] * ((double)(100 - sales[saleIdx])/100);
            }
//            System.out.println(Arrays.toString(emoticonsPrice[emoticonIdx]));
        }

        // 2. 최대 조합 찾기
        findBestSaleRate(0, new double[users.length]);


        return result;
    }

    void findBestSaleRate(int emoticonIdx, double[] userTotalPrice) {
        if(emoticonIdx == emoticons.length) {  // 모든 이모티콘의 조건 비교
            int emoticonPlus, totalPrice;
            emoticonPlus = totalPrice = 0;
            for(int userIdx=0; userIdx < users.length; userIdx++) {
                if(userTotalPrice[userIdx] >= users[userIdx][1]) {  // 제한 금액 넘으면 플러스로
                    emoticonPlus++;
                } else {
                    totalPrice += userTotalPrice[userIdx];
                }
            }

            // 이전 값과 비교
            if(emoticonPlus > result[0]) {
                result = new int[]{emoticonPlus, totalPrice};
            } else if(emoticonPlus == result[0]) {
                result[1] = Math.max(result[1], totalPrice);
            }
            return;
        }

        // 조합 구하기
        // 현재 이모티콘의 할인율 정하기
        for(int saleRate=0; saleRate<4; saleRate++) {
            int rate = sales[saleRate];
            for(int uIdx=0; uIdx< users.length; uIdx++) {   // 현재 사용자가 살만한 할인율인가?
                if(rate >= users[uIdx][0]) userTotalPrice[uIdx] += emoticonsPrice[emoticonIdx][saleRate];
            }
            findBestSaleRate(emoticonIdx+1, userTotalPrice);
            for(int uIdx=0; uIdx< users.length; uIdx++) {   // 되돌리기
                if(rate >= users[uIdx][0]) userTotalPrice[uIdx] -= emoticonsPrice[emoticonIdx][saleRate];
            }
        }
    }
}
