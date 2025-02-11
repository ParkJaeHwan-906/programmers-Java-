package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 징검다리
// https://softeer.ai/practice/6293
public class no_6293 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int stoneNum;
    static long[] stones;
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        stoneNum = Integer.parseInt(br.readLine().trim());
        stones = new long[stoneNum];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int idx=0; idx < stoneNum; idx++) {

            stones[idx] = Long.parseLong(st.nextToken());
        }
        br.close();

        no_6293 problem = new no_6293();
        bw.write(String.valueOf(problem.getMaxStone()));
        bw.flush();
        bw.close();
    }

    private int[] maxStone;
    private int max;
    private int getMaxStone() {
        maxStone = new int[stoneNum];
        Arrays.fill(maxStone, 1);    // 초기값 설정 ( 자기 자신 )
        max = Integer.MIN_VALUE;
        for(int i=0; i<stoneNum; i++) {
           for(int j=0; j<i; j++) {
               if(stones[i] > stones[j]) {  // 기준 값(i) 가 현재값(j) 보다 크다
                   // 최장 증가 부분 수열의 값을 갱신한다.
                   maxStone[i] = Math.max(maxStone[i], maxStone[j]+1);
               }
           }
       }
        return maxValue();
    }

    private int maxValue(){
        for(int value : maxStone) {
            max = Math.max(max, value);
        }
        return max;
    }
}
/*
징검다리의 돌은 높이가 모두 다르다
서쪽에서 동쪽으로 놓이가 점점 높은 돌을 밟으며 개울을 기나간다. 0 -> N

최장 증가 부분 수열(LIS)을 이용하여 문제 해결 가능할듯 ( DP )
 */