package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_7367_효도음식 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int ingredientCnt;       // 재료의 개수
    static int[] ingredients;       // 재료
    static void init() throws IOException {
        ingredientCnt = Integer.parseInt(br.readLine().trim());

        ingredients = new int[ingredientCnt];
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<ingredientCnt; i++) {
            ingredients[i] = Integer.parseInt(st.nextToken());
        }

        makeComb();
        getMaxSum();
    }

    static void getMaxSum() {
        int max = Integer.MIN_VALUE;
        for(int i=0; i<ingredientCnt-2; i++) {
            max = Math.max(max, dpArr[i]+reverseDpArr[i+2]);
        }
        System.out.println(max);
    }

    /*
        📌 카데인 알고리즘
        => 주어진 배열에서 연속된 부분 배열 중 합이 최대인 구간을 찾는 알고리즘
        => O(n)
        => 배열에서 가장 큰 합을 갖는 연속 구간을 찾는다.
     */
    static int[] dpArr;             //  순방향으로 현 위치까지의 최대 합
    static int[] reverseDpArr;      //  역방향으로 현 위치까지의 최대 값
    static void makeComb() {
        dpArr = new int[ingredientCnt];
        reverseDpArr = new int[ingredientCnt];

        /*
            1. 0 ~ i 까지의 최대 합을 구한다.
            2. end ~ i 까지의 최대 합을 구한다.
         */
        int maxSum;     // 현 위치까지의 최대 연속된 누적합을 저장할 변수
        // 1. 순방향 최대 값 구하기
        dpArr[0] = ingredients[0];  // 초기 값 설정
        maxSum = dpArr[0];
        for(int i=1; i<ingredientCnt; i++) {
            // 현재까지의 최대합을 갱신한다.
            // 이때 누적합이 큰지, 현재 재료의 값이 큰지 비교한다.
            maxSum = Math.max(maxSum+ingredients[i], ingredients[i]);
            // 현재까지의 최대합과, 이전까지의 누적합 중 큰 값을 갖는다.
            dpArr[i] = Math.max(dpArr[i-1], maxSum);
        }
//        System.out.println(Arrays.toString(dpArr));
        // 2. 역방향 최대 값 구하기
        reverseDpArr[ingredientCnt-1] = ingredients[ingredientCnt-1];  // 초기 값 설정
        maxSum = reverseDpArr[ingredientCnt-1];
        for(int i=ingredientCnt-2; i>-1; i--) {
            maxSum = Math.max(maxSum+ingredients[i], ingredients[i]);
            reverseDpArr[i] = Math.max(reverseDpArr[i+1], maxSum);
        }
//        System.out.println(Arrays.toString(reverseDpArr));
    }
}

/*
    요리를 만들 수 있는 조건
    1. 연속한 재료들로만 만들 수 있다.
        이때 최소 1개 이상의 재료를 선택한다.
    2. 재료는 겹쳐서도, 인접해서도 안된다.

    6
    4 -6 1 2 -2 3
    => 8

    3
    -10 25 -10
    => -20
 */