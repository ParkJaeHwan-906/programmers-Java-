package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [한양대 HCPC 2023] Hanyang Popularity Exceeding Competition
// https://softeer.ai/practice/9495
public class no_9495_2 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int peopleNum;
    static int[][] peoples; // [인기도, 친화력]
    public static void main(String[] args) throws IOException {
        no_9495_2 problem = new no_9495_2();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        peopleNum = Integer.parseInt(br.readLine().trim());
        peoples = new int[peopleNum][2];

        for(int idx=0; idx<peopleNum; idx++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            peoples[idx][0] = p;
            peoples[idx][1] = c;
        }
        br.close();
        bw.write(String.valueOf(problem.findMaxValue()));
        bw.flush();
        bw.close();
    }

    private int jjin;   // 철민이의 인기도
    public int findMaxValue() {
        jjin = 0;   // 철민이의 초기 인기도는 0

        // 순서대로 사람을 만남
        for(int[] people : peoples) {
            int p = people[0];
            int c = people[1];

            // 수식을 만족하면 인기도를 증가시킨다
            jjin = getPopular(p, c) ? jjin+1 : jjin;
        }

        return jjin;
    }



    private boolean getPopular(int p, int c) {
       return Math.abs(p-jjin) <= c;
    }
}
/*
철민이의 현재 인기도 X
i 번 유명인의 인기도 pi, 친화력 Ci

철민이의 인기도가 증가하는 경우
|pi - X| <= ci

나올 수 있는 경우는 만나는 경우, 만나지 않는 경우 총 2가지
N 의 최대 값은 20만

순서대로 만나니까 그냥 다 수식 대입?
 */