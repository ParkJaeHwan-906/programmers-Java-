package SWEA.D1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// SWEA D1
// 홀수만 더하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5QSEhaA5sDFAUq
public class no_2072 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; i++){
            String[] arr = br.readLine().split(" ");
            int[] numbers = new int[arr.length];
            for(int j=0; j<arr.length; j++){
                numbers[j] = Integer.parseInt(arr[j]);
            }
            System.out.printf("#%d %d\n", i+1, sumOdd(numbers));
        }

    }

    public static int sumOdd(int[] numbers){
        int result = 0;
        for(int i : numbers){
            result += (i%2 == 0 ? 0 : i);
        }
        return result;
    }
}
