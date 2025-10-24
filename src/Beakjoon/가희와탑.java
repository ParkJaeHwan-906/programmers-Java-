package Beakjoon;

import java.util.*;
import java.io.*;

public class 가희와탑 {
    public static void main(String[] args) throws IOException {
        init();
    }
    static BufferedReader br;
    static StringTokenizer st;
    static int[] arr;
    static int n, a, b;
    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        /**
         * 건물의 개수 N
         * 가희가 볼 수 있는 건물의 개수
         * 단비가 볼 수 있는 건물의 개수
         * 한 줄로 주어진다.
         */
        st = new StringTokenizer(br.readLine().trim());
        // 최장 증가 부분 수열을 이용하면 되지 않을까?
        n = Integer.parseInt(st.nextToken());   // 건물의 개수
        a = Integer.parseInt(st.nextToken());   // 가희가 볼 수 있는 건물의 개수
        b = Integer.parseInt(st.nextToken());   // 단비가 볼 수 있는 건물의 개수
        reverseLIS();
        br.close();
    }

    static void reverseLIS() {
        /**
         * 가장 높은 건물을 K 라고 했을 때,
         * 3 개의 구역으로 나눌 수 있다.
         *
         * [a-1][k][b-1]
         * 즉
         * a-1 + 1 + b-1 = N 이다.
         * => a + b - 1 = N
         */
        // 1. 문제의 조건에 맞는 건물들의 높이 정보가 존재하지 않는 경우
        if(a+b-1 > n) {
            System.out.println(-1);
            return;
        }

        List<Integer> lis = new ArrayList<>();
        // 2. 가희가 보는 건물 구하기
        // K ( 가장 높은 건물 ) 를 제외
        for(int h=1; h<a; h++) lis.add(h);
        /**
         * 가장 큰 건물 구하기
         * -> 가희가 보는 위치에서
         * -> 단비가 보는 위치에서
         *  둘 중 더 높은 위치로 정한다.
         */
        int highestBuilding = Math.max(a,b);
        lis.add(highestBuilding);

        // 3. 단비가 보는 건물 구하기
        // 마찬가지로 K 를 제외
        for(int h=b-1; h>0; h--) lis.add(h);

        // 이제 모든 건물은 완성
        // 문제 조건 중 사전 순 가장 앞선 것을 찾아야함
        // add(1, 1)로 계속 채워야함
        // 0 에 넣지 않는 이유 -> 0 번째 건물의 크기와 갖게해야함
        while(lis.size() < n) lis.add(1, 1);

        for(int i=0; i<n; i++) System.out.print(lis.get(i)+" ");
    }
}
