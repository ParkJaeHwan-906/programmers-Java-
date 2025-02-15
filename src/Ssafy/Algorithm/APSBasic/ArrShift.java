package Ssafy.Algorithm.APSBasic;

public class ArrShift {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};

        // 오른쪽으로 한 칸
        for(int i=arr.length-1; i > 0; i--) {
            int tmp = arr[i];
            arr[i] = arr[i-1];
            arr[i-1] = tmp;
        }

        for(int i : arr) {
            System.out.print(i+" ");
        }

        System.out.println();

        // 왼쪽으로 한 칸
        for(int i=1; i<arr.length; i++) {
            int tmp = arr[i];
            arr[i] = arr[i-1];
            arr[i-1] = tmp;
        }

        for(int i : arr) {
            System.out.print(i+" ");
        }
    }
}
