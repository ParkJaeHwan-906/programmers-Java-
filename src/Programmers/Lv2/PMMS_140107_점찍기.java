package Programmers.Lv2;

import java.util.*;

public class PMMS_140107_점찍기 {
    public static void main(String[] args) {
        int k = 2;
        int d = 4;
        System.out.println(new PMMS_140107_점찍기().solution(k, d));
    }

    /*
        원점 (0,0) 으로부터 x 축 방향으로 a*k
        y 축 방향으로 b*k 떨어진 위치에 점을 찍는다.

        원점과 거리가 d 를 넘는 위치에는 점을 찍지 않는다.
     */
    public long solution(int k, int d) {
        /*
            1. 각 점마다 확인한다.
                최악 O(N^2) -> 시간 초과
            2. 규칙? 범위가 있지 않을까
                - 무조건 (d,0), (0,d) 안에 존재한다.
                - 경계를 구해야한다.
         */
        long answer = 0;

        for(int y=0; y<d+1; y+=k) {
            long x = (long)d*d - (long)y*y;
            x = (long)Math.sqrt(x);
            answer += x/k+1;
        }
        return answer;
    }
}
