package Programmers.Lv2;

import java.util.*;
import java.io.*;

public class PMMS_181187_두원사이의정수쌍 {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        int r1 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());

        System.out.println(new PMMS_181187_두원사이의정수쌍().solution(r1, r2));
    }

    /*
        1 : 5
        2 : 13 ( -8 )
        3 : 29 ( -16 )
        4 : 49 ( -20 )
        5 : 81 ( -32 )

        => 2(13) - 1(5) + 4 = 12
        => 3(29) - 2(13) + 4 = 20
        => 4(49) - 3(29) + 4 = 24
        => 5(81) - 4(49) + 4 = 36

     */
    public long solution(int r1, int r2) {
        long answer = 0;

        for (int x = 1; x <= r2; x++) {
            long maxY = (long) Math.floor(Math.sqrt((long) r2 * r2 - (long) x * x));
            long minY = 0;
            if (x < r1) {
                minY = (long) Math.ceil(Math.sqrt((long) r1 * r1 - (long) x * x));
            }

            answer += (maxY - minY + 1);
        }

        return answer * 4; // 4분면 대칭
    }
}

/*
    서로 다른 원 두 개가 주어진다.
    r1, r2 는 각 원의 반지름이다. 
    
    두 원 사이에 존재하는 (x,y) 점의 개수를 반환하라
 */