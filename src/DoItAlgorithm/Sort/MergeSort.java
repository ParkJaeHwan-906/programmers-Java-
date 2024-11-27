package DoItAlgorithm.Sort;

import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeSort {
    static int n;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        for(int i = 0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        MergeSort(0, n);

        for(int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void MergeSort(int s, int e) {
        if(e-s <= 1) return;   // 배열의 크기가 1 이하인 경우

//        int m = (s+e) / 2;
        int m = s + (e-s) / 2;  // 위의 식과 같으나 오버플로우를 막기 위해 이렇게 연산

        // 분할
        // 재귀함수 호출
        MergeSort(s, m);
        MergeSort(m, e);

        // 정복 (이때 정렬)
        merge(s, m, e);

    }

    private static void merge(int low, int mid, int high) {
        int[] temp = new int[high - low];
        int t = 0, l = low, h = mid;

        while (l < mid && h < high) {
            if (arr[l] < arr[h]) {
                temp[t++] = arr[l++];
            } else {
                temp[t++] = arr[h++];
            }
        }

        while (l < mid) {
            temp[t++] = arr[l++];
        }

        while (h < high) {
            temp[t++] = arr[h++];
        }

        for (int i = low; i < high; i++) {
            arr[i] = temp[i - low];
        }
    }
}
