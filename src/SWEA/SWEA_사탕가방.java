package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_사탕가방 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase<=TC; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int kindOf;  // 사탕의 종류
    static long bagSize, maxCandies;  // 가방에 넣어야하는 사탕의 개수, 사탕의 최대 개수
    static long[] candiese; // 각 사탕의 개수
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        kindOf = Integer.parseInt(st.nextToken());
        bagSize = Long.parseLong(st.nextToken());

        candiese = new long[kindOf];
        maxCandies = 0;
        st = new StringTokenizer(br.readLine().trim());
        for(int candyIdx=0; candyIdx<kindOf; candyIdx++) {
            candiese[candyIdx] = Long.parseLong(st.nextToken());
            maxCandies = Math.max(candiese[candyIdx],maxCandies);
        }

        sb.append(getMaxBags());
    }

    static long getMaxBags() {
        long l = 0;
        long r = maxCandies;    //

        long maxBags = 0;
        // 가방의 개수를 범위로 하여 탐색한다.
        while(l <= r) {
            long mid = l + (r-l)/2;

            // 해당 가방만큼 사탕을 담을 수 있다.
            if(canDistibute(mid)) {
                maxBags = Math.max(maxBags, mid);

                l = mid+1;
            } else {
                r = mid-1;
            }
        }
        return maxBags;
    }

    // 각 가방에 사탕을 모두 분배할 수 있는지 확인한다.
    static boolean canDistibute(long bagCnt) {
        if(bagCnt == 0) return true;    // 가방의 크기가 0 일때 -> 무조건 만들 수 있음

        long putCandies = 0;

        // 특정 사탕이 모든 가방에 균등하게 들어갈 수 있는지 확인한다.
        for(long candy : candiese) {
            putCandies += candy / bagCnt;
        }
        return putCandies >= bagSize ? true : false;
    }
}

/*
    사탕의 종류가 N 개 있다.

    가방 안에 M 개의 사탕이 들어있어야한다.
    모든 가방에 있는 사탕의 종류 구성이 같아야한다.

    최대 몇 개의 사탕 가방을 만들 수 있는가

 */