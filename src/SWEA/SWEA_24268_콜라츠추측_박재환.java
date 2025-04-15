package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_24268_콜라츠추측_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase=1; testCase<TC+1; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }
        br.close();
        System.out.println(sb);
    }

    static void init() throws IOException {
        int num = Integer.parseInt(br.readLine().trim());
        sb.append(num).append(' ');
        int cnt = 0;
        while(num != 1) {
            if(num%2 == 0) num/=2;
            else num = num*3 + 1;

            cnt++;
        }

        sb.append(cnt);
    }
}

/*
    짝수이면, 절반
    홀수이면 3배 + 1
    => 1 이 될때까지 반복
 */