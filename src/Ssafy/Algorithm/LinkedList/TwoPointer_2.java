package Ssafy.Algorithm.LinkedList;

import java.util.Scanner;

public class TwoPointer_2 {
    static int n, x;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 1 <= n <= 100,000
        // 1 <= x <= 2,000,000
        n = sc.nextInt();
        x = sc.nextInt();
        arr = new int[n];

        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        sc.close();

        System.out.println(twoPointer());
    }

    private static int twoPointer() {
        int l = 0;
        int r = 0;

        int ans = 0;
        int sum = arr[0];
        while(l < n &&  r < n-1) {
            if(sum == x) ans++;

            if(sum > x) {
                sum -= arr[l++];
            } else {
                sum += arr[++r];
            }
        }

        return ans;
    }
}

/*
수열의 크기 n 과 목표합이 주어진다.
각 수열이 주어진다.

a[i] ... a[j] 가 x 가 되는 경우
 */