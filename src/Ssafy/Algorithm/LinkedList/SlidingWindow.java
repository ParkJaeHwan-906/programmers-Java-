package Ssafy.Algorithm.LinkedList;

import java.util.*;

public class SlidingWindow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); int M = sc.nextInt();
        int[] arr = new int[N];
        for(int idx=0; idx<N; idx++) {
            arr[idx] = sc.nextInt();
        }

        System.out.println(slidingWindow(arr, M));
    }

    private static int slidingWindow(int[] arr, int m) {
        int sum = 0;
        // 초기값
        for(int idx=0; idx<m; idx++) {
            sum += arr[idx];
        }

        int maxValue = sum;

        // 슬라이딩 윈도우
        for(int idx=m; idx < arr.length; idx++) {
            sum -= arr[idx-m];
            sum += arr[idx];

            maxValue = Math.max(sum, maxValue);
        }

        return maxValue;
    }
}

/*
공지된 일수 N, 일하고자 하는 연속 일수 M 이 주어진다.
N 개의 각 일자별 값이 주어진다.

M 일동안 일해 받을 수 있는 최대 금액은?
 */