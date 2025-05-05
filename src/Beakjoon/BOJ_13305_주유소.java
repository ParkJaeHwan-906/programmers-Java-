package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_13305_주유소 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int nodeCnt;     // 도시의 수
    static int[] oils;      // 도시 별 기름 값
    static long[] dists;    // 도시 간 거리
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        nodeCnt = Integer.parseInt(st.nextToken());

        oils = new int[nodeCnt];
        dists = new long[nodeCnt];

        st = new StringTokenizer(br.readLine().trim());
        for(int i=1; i<nodeCnt; i++) {
            dists[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i<nodeCnt; i++) {
            oils[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;
        int minOil = oils[0];   // 처음 기름은 무조건 0 번에서 넣어야함

        for (int i = 1; i < nodeCnt; i++) {
            // 현재까지의 최소 기름값 사용
            minOil = Math.min(minOil, oils[i - 1]);
            result += minOil * dists[i];
        }

        System.out.println(result);
    }
}
/*
    N 개의 도시가 있다.
    제일 왼쪽의 도시 -> 제일 오른쪽의 도시로 이동한다.
    각 도시를 연결하는 도로의 길이는 서로 다를 수 있다.

    처음 출발 시에 기름을 넣어야한다.
    기름은 무제한으로 넣을 수 있다.
    1Km 마다 1 리터의 기름을 사용한다.
 */