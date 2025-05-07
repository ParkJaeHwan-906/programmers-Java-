package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_6273_택배마스터광우 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int railCnt;         // 레일의 개수
    static int limitW;          // 바구니 무게
    static int workCnt;         // 일 횟수
    static int[] rails;         // 각 레일 무게들
    static boolean[] checked;   // 레일을 사용했는지 확인
    static int minW;            // 최소 일 하는 무게
    static void init() throws IOException {
        minW = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine().trim());
        railCnt = Integer.parseInt(st.nextToken());
        limitW = Integer.parseInt(st.nextToken());
        workCnt = Integer.parseInt(st.nextToken());

        rails = new int[railCnt];
        checked = new boolean[railCnt];
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<railCnt; i++) {
            rails[i] = Integer.parseInt(st.nextToken());
        }

        allSeq(0, new int[railCnt]);

        System.out.println(minW);
    }

    /*
        1. 순열을 사용한다.
        근데 안될거같음 -> 레일의 개수가 최대 10 개
        -> 그럼 순열의 최대 경우는 10!
     */
    static void allSeq(int railIdx, int[] railSeq) {
        if(railIdx == railCnt) {
//            System.out.println(Arrays.toString(railSeq));
            getTotalW(railSeq);
            return;
        }

        for(int i=0; i<railCnt; i++) {
            if(checked[i]) continue;    // 이미 이전에 순서를 정한 레일은 넘어간다.

            checked[i] = true;
            railSeq[railIdx] = rails[i];
            allSeq(railIdx+1, railSeq);
            checked[i] = false;
        }
    }

    /*
        한 번 일을 할때마다, 바구니에 담을 수 있는 최대 무게까지만 담는다.
     */
    static void getTotalW(int[] railSeq) {
        int railIdx = 0;
        int total = 0;
        for(int w=0; w<workCnt; w++) {
            int bag = 0;    // 초기 바구니 무게

            // 최대 무게를 넘지 않을때까지 물건을 담는다.
            while(bag + railSeq[railIdx] <= limitW) {
                bag+=railSeq[railIdx++];

                // 인덱스 보정
                railIdx%=railCnt;
            }
            total += bag;
//            System.out.println(bag);
        }
//        System.out.println(total);
        minW = Math.min(minW, total);
    }
}

/*
    N 개의 레일이 존재한다.
    각 레일은 N[i] 무게 전용 레일로 주어진다.
    레일의 순서가 정해지면 택배 바구나 무게 M 을 넘어가기 전까지 물건을 담아 들고 옮겨야한다.

    레일 순서대로 택배를 담되, 바구니 무게를 초과하지 않은 만큼 담아서 이동하게 되면 1번 일한 것으로 친다.
    총 K 번 일하는데 최소한의 무게로 일을 할 수 있게 광우를 도와주는 프로그램을 만들어보자
 */
