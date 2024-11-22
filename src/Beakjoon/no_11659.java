package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 구간 합 구하기 4
// https://www.acmicpc.net/problem/11659
public class no_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] sumRange = new int[m][2];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sumRange[i][0] = a;
            sumRange[i][1] = b;
        }

        no_11659 problem = new no_11659();
        long[] sumArr = problem.sumArr(arr);

        for(int[] range : sumRange){
            int a = range[0];
            int b = range[1];
            System.out.println(sumArr[b] - sumArr[a-1]);
        }
    }

    public long[] sumArr(int[] arr) {
        long[] sumArr = new long[arr.length];

        for(int i=1; i<sumArr.length; i++){
            sumArr[i] = sumArr[i-1] + arr[i];
        }
        return sumArr;
    }
}
