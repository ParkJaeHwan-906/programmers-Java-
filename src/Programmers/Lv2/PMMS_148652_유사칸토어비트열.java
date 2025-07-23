package Programmers.Lv2;

import java.util.*;
import java.io.*;

public class PMMS_148652_유사칸토어비트열 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
    }

    static void init() throws IOException {
        int n = Integer.parseInt(br.readLine());
        long l = Long.parseLong(br.readLine());
        long r = Long.parseLong(br.readLine());

        System.out.println(new PMMS_148652_유사칸토어비트열().solution(n, l, r));
    }

    /*
        칸토어 집합
        0 과 1 사이의 실수로 이루어진 집합
        [0,1] 부터 시작하여 각 구간을 3등분하여 가운데 구간을 반복적으로 제외하는 방식

        유사 칸토어 비트열
        0 번째 유사 칸토어 비트열 = 1
        n 번째 유사 칸토어 비트열은 n-1 번째 유사 칸토어 비트열에서의
        1을 11011로 치환하고, 0 을 00000 로 치환한다.

        n 번째 유사 칸토어 비트열에서 특정 구간 내 1의 개수가 몇 개인지 반환
     */
    // 1. 문자열을 r 까지 반복 생성하여 해결 ❌
    //      -> 메모리 초과
    public int solution(int n, long l, long r) {
        /*
            n 은 1~20
            l,r 은 5^n

            0 : 1
            1 : 11011
            2 : 1101111011000001101111011
            
            문자열의 길이도 5의 제곱으로 늘어남
         */
        // 0 번째 유사 칸토열은 1
        // 바로 반환
        if(n==0) return 1;

        int answer = 0;
        for(long i=l-1; i<r; i++) {
            /*
                규칙이 존재
                0 인 위치의 인덱스를 생각
                0 : 1 [0]
                1 : 11011 [2]
                2 : 1101111011000001101111011 [2,7,10,11,12,13,14,17,22]
                => 위치를 2진수로 바꿔본다.
                [0]
                [002]
                [002,012,020,021,022,023,023,032,042]
                => 해당 위치를 5진수로 바꿔, 2가 포함되어 있으면 0 이다.
             */
            if(!Long.toString(i, 5).contains("2")) answer++;
        }

        return answer;
    }
}
