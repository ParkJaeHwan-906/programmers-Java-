package SWEA.D2;

import java.util.*;
import java.io.*;

// SWEA D2
// 2001.파리 퇴치
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq&categoryId=AV5PzOCKAigDFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_2001 {
    public static void main(String[] args) throws IOException {
        no_2001 problem = new no_2001();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int i=0; i<tc; i++){
            int[][] maps;

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // maps
            int m = Integer.parseInt(st.nextToken());   // 파리채

            maps = new int[n][n];
            for(int x=0; x<n; x++){
                st = new StringTokenizer(br.readLine());
                for(int y=0; y<n; y++){
                    maps[x][y] = Integer.parseInt(st.nextToken());
                }
            }

            int result = problem.solution(maps, m);
            sb.append("#").append(i + 1).append(" ").append(result).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    private int solution(int[][] maps, int m) {
        int answer = 0;
        int n = maps.length;
        for(int i=0; i<n-m+1; i++){
            for(int j=0; j<n-m+1; j++){
                answer = Math.max(findMax(i, j, m, maps), answer);
            }
        }
        return answer;
    }

    // 현재 좌표 기준 파리채 범위만 확인
    private int findMax(int x, int y, int m, int[][] maps) {
        int result = 0;

        for(int i=x; i<x+m; i++){
            for(int j=y; j<y+m; j++){
                result += maps[i][j];
            }
        }
        return result;
    }
}
