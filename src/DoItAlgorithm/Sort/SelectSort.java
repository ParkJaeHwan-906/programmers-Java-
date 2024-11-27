package DoItAlgorithm.Sort;

import java.util.*;
import java.io.*;

// 선택 정렬 ( 많이 사용 X )
// O(n^2)
public class SelectSort {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        selectSort();
        for(int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void selectSort() {
        for(int i=0; i<arr.length; i++){
            int[] min = {-1,987654321};
            for(int j=i; j<arr.length; j++){
                if(min[1] > arr[j]){
                    min[0] = j;
                    min[1] = arr[j];
                }
            }
            arr[min[0]] = arr[i];
            arr[i] = min[1];
        }
    }
}
