package SWEA.D2;

import java.util.*;
import java.io.*;

// SWEA D2
// 달팽이 숫자
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq&categoryId=AV5PobmqAPoDFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_1954 {
    private static int t;   // TC 개수
    public static void main(String[] args) throws IOException {
        no_1954 problem = new no_1954();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());

            int[][] answer = problem.solution(n);
            bw.write(problem.printArr(answer, i+1));
        }

        bw.flush();
        bw.close();
    }

    private int[][] arr;
    private int[] dx = new int[] {0,1,0,-1};    // 위 아래
    private int[] dy = new int[] {1,0,-1,0};    // 좌 우
    private int[][] solution(int n) {
        arr = new int[n][n];

        int seq = 1;
        int i = 0;
        int x = 0;
        int y = 0;

        while(seq <= n*n) {
            arr[x][y] = seq++;

            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어남, 이미 방문처리함 -> 방향 바꿈
            if(nx < 0 || ny < 0 || nx >= n || ny >= n || arr[nx][ny] != 0) {
                i = (i+1)%4;

                // 값 새로 갱신
                nx = x + dx[i];
                ny = y + dy[i];
            }

            x = nx;
            y = ny;
        }

        return arr;
    }

    private String printArr(int[][] arr, int tc) throws IOException {
        StringBuilder sb = new StringBuilder();

        sb.append("#"+tc+"\n");

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[i].length; j++){
                sb.append(arr[i][j]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
