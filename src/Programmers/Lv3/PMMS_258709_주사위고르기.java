package Programmers.Lv3;

import java.util.*;
import java.io.*;

public class PMMS_258709_주사위고르기 {
    public static void main(String[] args) {
        int[][] dice = {{1,2,3,4,5,6},{3,3,3,3,4,4},{1,3,3,4,4,4},{1,1,4,4,5,5}};
        System.out.println(Arrays.toString(new PMMS_258709_주사위고르기().solution(dice)));
    }

    int diceCnt;
    int[][] dice;
    int maxWin;     // 최대 승리 횟수
    int[] aDice;    // 그때의 조합
    public int[] solution(int[][] dice) {
        this.dice = dice;
        diceCnt = dice.length;
        maxWin = Integer.MIN_VALUE;
        aDice = new int[diceCnt/2];

        makeCase(0, 0, new int[diceCnt/2], 0, new int[diceCnt/2]);

        for(int i=0; i<aDice.length; i++) {
            aDice[i]++;
        }
        return aDice;
    }

    /*
        주사위를 고르는 경우의 수를 구한다.
        A 가 고르는 주사위, B 가 고르는 주사위
        => 조합으로 A 가 선택하는 경우, B 가 선택하는 경우로 구분
     */
    void makeCase(int diceIdx, int aIdx, int[] a, int bIdx, int[] b) {
        if(diceIdx == diceCnt) {    // 모든 조합을 구함
            // 현재 조합에서의 승률을 계산한다.
//            System.out.println("A : " + Arrays.toString(a));
//            System.out.println("B : " + Arrays.toString(b));
            rollDice(a, b);
            return;
        }

        // 조합을 만들 수 있음
        // 1. A 가 선택하는 경우
        if(aIdx < diceCnt/2) {
            a[aIdx] = diceIdx;
            makeCase(diceIdx + 1, aIdx + 1, a, bIdx, b);
        }
        // 2. B 가 선택하는 경우
        if(bIdx < diceCnt/2) {
            b[bIdx] = diceIdx;
            makeCase(diceIdx + 1, aIdx, a, bIdx + 1, b);
        }
    }

    /*
        사전에 구한 주사위 조합으로 뽑을 수 있는 점수를 구한다.
     */
    ArrayList<Integer> aDiceResult;
    ArrayList<Integer> bDiceResult;
    void rollDice(int[] a, int[] b) {
        aDiceResult = new ArrayList<>();
        bDiceResult = new ArrayList<>();

        // 1. A 의 주사위로 나올 수 있는 모든 점수 구하기
        calcDiceScore(0, a, 0, aDiceResult);
        // 2. B 의 주사위로 나올 수 있는 모든 점수 구하기
        calcDiceScore(0, b, 0, bDiceResult);

//        System.out.println(aDiceResult.size());
//        System.out.println(bDiceResult.size());
//        System.out.println(aDiceResult.size()*bDiceResult.size());

        // ❌ 완전 탐색 O(n^2) 으로 접근 시 시간초과 발생
        // ✅ 이진 탐색으로 탐색 줄이기 O(nlog n)
        int win = 0;
        Collections.sort(bDiceResult);      // 이진 탐색을 위해 정렬 처리
        for(int aScore : aDiceResult) {
//            for(int bScore : bDiceResult) {
//                if(aScore > bScore) win++;
//            }
            win += findIdx(bDiceResult, aScore);
        }
//        System.out.println(win);

        if(win > maxWin) {
//            System.out.println("최대승률 갱신 : "+ win);
//            System.out.println(Arrays.toString(a));
            maxWin = win;
            aDice = a.clone();  // 깊은 복사 해야함
        }
    }

    void calcDiceScore(int diceNumIdx, int[] diceNum, int sum, ArrayList<Integer> scoreList) {
        if(diceNumIdx == diceNum.length) {  // 탐색을 완료
            scoreList.add(sum);
            return;
        }

        // 탐색 가능
        int diceIdx = diceNum[diceNumIdx];  // 현재 주사위 번호
        for(int score : dice[diceIdx]) {
            calcDiceScore(diceNumIdx+1, diceNum, sum+score, scoreList);
        }
    }

    /*
        이진 탐색
        타겟 값을 기준으로, 타겟값보다 작은 원소의 개수 구하기
     */
    int findIdx(ArrayList<Integer> list, int targerNum) {
        int l = 0, r = list.size()-1;

        while(l < r) {
            int mid = l + (r-l) / 2;

            if(list.get(mid) < targerNum) l = mid+1;
            else r = mid;
        }
        return l;
    }
}

/*
    주사위의 개수 n 개, 각 면이 나올 확률은 동일
    각 주사위는 1~n 번호를 가지고 있다. 주사위에 쓰인 수의 구성은 모두 다르다.

    A 가 먼저 n/2 개의 주사위를 가져가면, B 가 남은 n/2 개의 주사위를 가져간다.
    주사위를 모두 굴린 뒤, 나온 수들을 모두 합해 점수를 계산한다.

    점수가 큰 쪽이 승리한다. 점수가 같다면 무승부이다.
 */