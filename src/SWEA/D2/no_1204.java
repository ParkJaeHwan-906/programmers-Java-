package SWEA.D2;

import java.util.*;
import java.io.*;

// SWEA D2
// 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV13zo1KAAACFAYh&categoryId=AV13zo1KAAACFAYh&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_1204 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine().trim());
        for(int no=1; no <= tc; no++){
            sb.append('#').append(no).append(' ');
            br.readLine();
            int[] scores = new int[101];
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int student=0; student < 1000; student++){
                int idx = Integer.parseInt(st.nextToken());
                scores[idx]++;
            }

            sb.append(findMax(scores)).append('\n');
        }
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static int findMax(int[] scores) {
        int maxCount = Integer.MIN_VALUE;
        int score = -1;

        for(int num=0; num<101; num++){
            if(scores[num] >= maxCount) {
                score = num;
                maxCount = scores[num];
            }
        }

        return score;
    }
}
