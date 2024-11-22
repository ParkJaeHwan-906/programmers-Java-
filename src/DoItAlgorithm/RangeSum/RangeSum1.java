package DoItAlgorithm.RangeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간합 알고리즘
// 1차원 배열
public class RangeSum1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("n : ");
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        System.out.print("arr : ");
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        RangeSum1 problem = new RangeSum1();
        int[] arrSum = problem.arrSum(arr);

        for(int i : arrSum){
            System.out.print(i+" ");
        }
    }

    public int[] arrSum(int[] arr){
        int[] arrSum = new int[arr.length];
        // 초기값 설정
        arrSum[0] = arr[0];

        for(int i=1; i<arr.length; i++){
            arrSum[i] = arrSum[i-1] + arr[i];
        }

        return arrSum;
    }
}
