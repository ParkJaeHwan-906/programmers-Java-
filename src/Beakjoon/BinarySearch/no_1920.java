package Beakjoon.BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 백준 - 이분탐색
// 수 찾기
// https://www.acmicpc.net/problem/1920
public class no_1920 {
    static long n, m;
    static long[] nArr;
    static long[] mArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Long.parseLong(br.readLine());
        nArr = new long[(int) n];
        String[] nStr = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            nArr[i] = Long.parseLong(nStr[i]);
        }

        m = Long.parseLong(br.readLine());
        mArr = new long[(int) m];
        String[] mStr = br.readLine().split(" ");
        for(int i=0; i<m; i++){
            mArr[i] = Long.parseLong(mStr[i]);
        }

        no_1920 problem = new no_1920();
        problem.solution();
    }

    public void solution(){
        Arrays.sort(nArr);
        StringBuilder sb = new StringBuilder();
        for(long i : mArr){
            if(binarySearch(i)) sb.append(1);
            else sb.append(0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public boolean binarySearch(long target){
        long left = 0;
        long right = n-1;

        while(left <= right){
            long mid = (left + right) / 2;

            if(nArr[(int)mid] > target){    // 찾으려는 값보다 크면 범위를 줄인다.
                right = mid - 1;
            } else if (nArr[(int)mid] < target) {
                left = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
