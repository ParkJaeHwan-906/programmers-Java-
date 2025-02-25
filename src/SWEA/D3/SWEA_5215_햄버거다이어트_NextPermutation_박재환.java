package SWEA.D3;

import java.util.*;
import java.io.*;

public class SWEA_5215_햄버거다이어트_NextPermutation_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int itemNum, limitK; // 재료의 수, 제한 칼로리
    static int[][] items; // 재료를 저장할 배열 [점수, 칼로리]
    static int[] isSelected; // 선택 여부 배열
    static long maxScore; // 최대 선호도

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            sb.append('#').append(tc).append(' ');
            init();
            makeHamburgerCombination(); // 햄버거 조합 생성
            sb.append(maxScore).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        maxScore = Long.MIN_VALUE; // 최소값으로 초기화

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        itemNum = Integer.parseInt(st.nextToken());
        limitK = Integer.parseInt(st.nextToken());

        items = new int[itemNum][2];
        isSelected = new int[itemNum]; // 선택 여부 배열 초기화

        for (int item = 0; item < itemNum; item++) {
            st = new StringTokenizer(br.readLine().trim());
            items[item][0] = Integer.parseInt(st.nextToken()); // 점수
            items[item][1] = Integer.parseInt(st.nextToken()); // 칼로리
        }
    }

    /*
    모든 조합을 구하기 위하여
    재료의 개수를 1~N 개 선택하는 경우를 구한다.
     */
    private static void makeHamburgerCombination() {
        // 사용할 재료의 개수를 설정한다.
        for (int selectCnt = 1; selectCnt <= itemNum; selectCnt++) {
            initIsSelected(selectCnt); // 선택할 재료 개수에 따라 초기화

            // next permutation 사용
            do {
                calculateMaxScore();    // 순열을 생성한 경우 -> 해당 조합의 최대 값을 구한다.
            } while (nextPermutation());
        }
    }
    /*
      비트 마스킹 개념을 사용하여 사용하는 재료의 초기 세팅을 한다.
     */
    private static void initIsSelected(int selectCnt) {
        Arrays.fill(isSelected, 0); // 배열 초기화
        for (int i = itemNum - selectCnt; i < itemNum; i++) {
            isSelected[i] = 1; // 선택된 재료에 대해 1로 설정
        }
    }

    private static void calculateMaxScore() {
        int klsum = 0;  // 현재 조합의 칼로리 합
        int scoreSum = 0; // 현재 조합의 점수 합

        for (int i = 0; i < itemNum; i++) {
            if (isSelected[i] == 1) { // 선택된 재료만 계산
                klsum += items[i][1];
                scoreSum += items[i][0];
                if (klsum > limitK) return; // 제한 칼로리 초과 시 중단
            }
        }

        maxScore = Math.max(maxScore, scoreSum);
    }

    private static boolean nextPermutation() {
        // 꼭대기 값을 찾는다.
        int highestIdx = itemNum - 1;
        while (highestIdx > 0 && isSelected[highestIdx - 1] >= isSelected[highestIdx]) highestIdx--;
        // 현재 순열이 마지막 순열이라면 더이상 탐색이 불가하다
        if (highestIdx == 0) return false;

        // 교환한 값을 찾고 교환한다.
        int swapIdx = itemNum - 1;
        while (isSelected[highestIdx - 1] >= isSelected[swapIdx]) swapIdx--;
        swap(highestIdx - 1, swapIdx);

        // 꼭대기값 다음 원소들을 재정렬한다.
        int behind = itemNum - 1;
        while (highestIdx < behind) swap(highestIdx++, behind--);

        return true;
    }

    private static void swap(int left, int right) {
        int temp = isSelected[left];
        isSelected[left] = isSelected[right];
        isSelected[right] = temp;
    }
}

/*
 * 햄버거의 맛은 최대한 유지하면서 정해진 칼로리를 넘지않는다
 * 고객이 원하는 조합으로 햄버거를 만들어서 준다.
 *
 * 정해진 칼로리 이하의 조합 중 가장 선호하는 햄버거(모든 원소의 맛의 합)를 조합해주는 프로그램을 만들자
 *
 *  제한 칼로리를 나타내는 N, L(1 ≤ N ≤ 20, 1 ≤ L ≤ 10^4)
 *  점수와 칼로리를 나타내는 Ti, Ki(1 ≤ Ti ≤ 10^3, 1 ≤ Ki ≤ 10^3)
 *
 *  재료의 수는 20 개가 최대
 *  -> 가장 최악의 경우 2^20 (넣는 경우, 안넣는 경우)
 *  -> 제한 시간 8초 > 최악의 경우로 400만번 가능 -> 부분집합으로 해결 가능
 *
 *  Next Permutation 사용
 *
 */