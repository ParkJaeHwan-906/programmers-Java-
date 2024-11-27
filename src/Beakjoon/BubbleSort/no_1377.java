package Beakjoon.BubbleSort;

import java.util.*;
import java.io.*;

// BAEKJOON
// 버블 소트
// https://www.acmicpc.net/problem/1377
public class no_1377 {
    static int[][] arr;
    static int[][] arrCopy;
    static int n = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        arrCopy = new int[n][2];

        for(int i=0; i<n; i++){
            arr[i][0] = i;
            arr[i][1] = Integer.parseInt(br.readLine());

            arrCopy[i][0] = i;
            arrCopy[i][1] = arr[i][1];
        }

        // ⚠️ 핵심 개념
        // 내부 for 문은 데이터를 왼쪽으로 한칸씩 이동시킴
        // 정렬 후 인덱스를 비교해 왼쪽으로 얼마나 움직였는지 비교
        Arrays.sort(arrCopy, (a ,b) -> a[1] - b[1]);

        int answer = 0;
        for(int i=0; i<n; i++){
            answer = Math.max(answer, arrCopy[i][0] - arr[i][0]);
        }
        System.out.println(answer+1);

//        bubbleSort();
    }
    // 시간 초과
//    public static void bubbleSort(){
//        for(int i=0; i<n-1; i++){
//            boolean b = false;
//            for(int j=0; j < n-1-i; j++){
//                if(arr[j] > arr[j+1]){
//                    int tmp = arr[j];
//                    arr[j] = arr[j+1];
//                    arr[j+1] = tmp;
//                    b = true;
//                }
//            }
//            if(!b) {
//                System.out.println(i+1);
//                return;
//            }
//        }
//    }
}
