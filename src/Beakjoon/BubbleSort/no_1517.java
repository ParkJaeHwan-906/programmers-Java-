package Beakjoon.BubbleSort;

import java.util.*;
import java.io.*;

// BAEKJOON
// 버블 소트
// https://www.acmicpc.net/problem/1517
public class no_1517 {
    static int[] arr;
    static int n;
    static long result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        no_1517 problem = new no_1517();
        problem.mergeSort(0, n);

        System.out.println(result);
    }


    private void mergeSort(int s, int e) {
        if(e-s <= 1) return;

        int m = s + (e-s)/2;

        mergeSort(s,m);
        mergeSort(m,e);
        merge(s, m, e);
    }

    private void merge(int s, int m, int e){
        int[] tmp = new int[e-s];
        int t = 0;
        int low = s;
        int high = m;

        while(low < m && high < e) {
            if(arr[low] > arr[high]){
                tmp[t++] = arr[high++];
                result += (m-low);
            } else{ // 이부분은 swap 부분
                tmp[t++] = arr[low++];
            }
        }

        while(low < m) {
            tmp[t++] = arr[low++];
        }

        while(high < e) {
            tmp[t++] = arr[high++];
        }

        for(int i = s; i < e; i++){
            arr[i] = tmp[i - s];
        }
    }
}
