package DoItAlgorithm.Sort;

import java.util.*;
import java.io.*;

// 버블 정렬
// 인접한 데이터의 크기를 비교하여 정렬
// O(n^2) 시간 복잡도
public class BubbleSort {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 배열의 크기 설정
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        // 배열 원소를 공백을 기준으로 입력 받음
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        bubbleSort();
        for(int i : arr){
            bw.write(i+" ");
        }
        bw.flush();
        bw.close();
    }

    public static void bubbleSort() {
        System.out.println("Bubble Sort");
        for(int i=0; i<arr.length-1; i++){
            for(int j=0; j<arr.length-i-1; j++){
                System.out.println("비교 : "+arr[j]+", "+arr[j+1]);
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
            showArr();
        }
    }

    public static void showArr(){
        System.out.print("[");
        for(int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println("]");
    }
}
