package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_사탕분배 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase <= TC; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int candyA, candyB, doChangeNums;    // A 의 사탕 개수, B의 사탕 개수, 작업 횟수
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        candyA = Integer.parseInt(st.nextToken());
        candyB = Integer.parseInt(st.nextToken());
        doChangeNums = Integer.parseInt(st.nextToken());

//        donateCandy();
    }

    /*
        증명
        A < B

        A.
        (A*2)%T
        (A*2)
        => A*2

        B.
        (B*2)%T
        (B-A)
        => T < B*2 < T*2
        B*2/T = 1 (몫) => B의 두배에 전체 사탕 개수를 뺀 것 -> B*2 - (A+B) = B-A
     */
//    static void donateCandy() {
//        long totalCandys = (long)candyA + (long)candyB;
//
//        candyA =
//
//        sb.append(Math.min(candyA, candyB));
//    }
}

/*
    A개의 사탕, B개의 사탕

    K번의 작업 -> 10^9 약 10억
    사탕의 개수가 더 적은 사람이 가지고 있는 사탕의 개수만큼, 더 많은 사탕을 가지고 있는 사람이
    사탕을 준다.

    작업이 끝난 이후, 사탕의 개수 중 더 작은 개수를 출력하고자 한다.

    📌 사탕의 개수가 음수가 되는 경우가 있는지 ?

    1. 그대로 작업을 반복해서 구현하면 안돼?
    2. 규칙성이 있을거같다. -> K 가 10억이면 너무 크다.

    1.
    4 9 2
    1 > 4 9 > 8 5
    2 > 8 5 > 3 10

    4 9 3
    1 > 4 9 > 8 5
    2 > 8 5 > 3 10
    3 > 3 1 > 6 7

    2.
    사탕의 총 개수를 이용한다.
    4 9 3
    사탕의 총 합 -> 13
    1. 2^1 => 8 18 > 13으로 나눔 > 8 5
    2. 2^2 => 16 36 > 13 으로 나눔 > 3 10
    ...
 */