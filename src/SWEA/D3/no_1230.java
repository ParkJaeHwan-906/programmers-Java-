package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 1230. [S/W 문제해결 기본] 8일차 - 암호문3
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14zIwqAHwCFAYD
public class no_1230 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        no_1230 problem = new no_1230();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int tc=1; tc<=10; tc++) {
            sb.append('#').append(tc).append(' ');

            int cryptoL = Integer.parseInt(br.readLine().trim());
            List<String> crypto = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int idx=0; idx<(cryptoL > 10 ? 10 : cryptoL); idx++) {  // 앞 뭉치 10개만 출력하므로, 필요없는 부분 X
                crypto.add(st.nextToken());
            }



        }
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}
/*
0 ~ 999999 사이 표현
해당 표현을 N 개 모아놓은 암호 뭉치

I(x,y,s) : 앞에서 x 번째 암호문 바로 다음에 y 개의 암호문을 삽입한다. s 는 덧 붙일 암호문들이다.
D(x,y) : 앞에서 x 번째 암호문 바로 다음부터 y 개의 암호문을 삭제한다
A(y,s) : 암호문 뭉치 맨 뒤에 y 개의 암호문을 덧붙인다. s 는 덧붙일 암호문들
 */