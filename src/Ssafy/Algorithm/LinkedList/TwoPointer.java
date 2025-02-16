package Ssafy.Algorithm.LinkedList;

import java.util.Arrays;
import java.util.Scanner;

public class TwoPointer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); int x = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(TwoPointer(arr, x));
    }

    private static int TwoPointer(int[] arr, int x) {
        Arrays.sort(arr);   // 배열을 정렬 후 투포인터 알고리즘을 사용한다.

        int ans = 0;

        int l = 0;
        int r = arr.length-1;
        while(l <= r) {
            int sum = arr[l] + arr[r];

            if(sum == x) ans++;

            if(sum < x) {
                l++;
            } else {
                r--;
            }
        }
        return ans;
    }
}

/*
첫 줄에 수열의 크기 n 과 목표합 x 가 주어진다.
둘째 줄에는 수열 n 개가 주어진다.

2개의 항을 선택하여 더한 값이 X 를 만족하는 쌍의 수는?
 */