package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 구간 합 구하기 5
// https://www.acmicpc.net/problem/11660
public class no_11660 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] commands = new int[m][4];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                commands[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        no_11660 problem = new no_11660();
        long[][] sumArr = problem.solution(arr);

        for(int[] command : commands) {
            int x1 = command[0];
            int y1 = command[1];

            int x2 = command[2];
            int y2 = command[3];

            long sum = sumArr[x2][y2] - sumArr[x1-1][y2] - sumArr[x2][y1-1] + sumArr[x1-1][y1-1];
            System.out.println(sum);
        }
    }

    public long[][] solution(int[][] arr){
        int length = arr.length;

        long[][] sumArr = new long[length][length];
        for(int i=1; i<length; i++){
            for(int j=1; j<length; j++){
                sumArr[i][j] = sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1] + arr[i][j];
            }
        }

        return sumArr;
    }
}
