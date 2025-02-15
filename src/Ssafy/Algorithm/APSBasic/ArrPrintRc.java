package Ssafy.Algorithm.APSBasic;

/*
1치원 배열의 모든 원소를 재귀로 작성해보자
 */
public class ArrPrintRc {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        printArr(0, arr);
    }

    private static void printArr(int idx, int[] arr) {
        if(idx == arr.length) return;
        System.out.println(arr[idx]);
        printArr(idx+1, arr);
    }
}
