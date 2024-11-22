package DoItAlgorithm.RangeSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구간합 알고리즘
// 2차원 배열
public class RangeSum2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("n : ");
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            System.out.printf("%d 번째 배열 : ", i);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println("\n기존 배열");
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

        RangeSum2 problem = new RangeSum2();
        int[][] arrSum = problem.arrSum(arr);

        System.out.println("\n구간합 배열");
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                System.out.print(arrSum[i][j]+" ");
            }
            System.out.println();
        }
    }

    public int[][] arrSum(int[][] arr){
        int[][] arrSum = new int[arr.length][arr[0].length];

        for(int i=1; i<arr.length; i++){
            for(int j=1; j< arr[0].length; j++){
                arrSum[i][j] = arrSum[i-1][j] + arrSum[i][j-1] - arrSum[i-1][j-1] + arr[i][j];
            }
        }
        return arrSum;
    }
}
