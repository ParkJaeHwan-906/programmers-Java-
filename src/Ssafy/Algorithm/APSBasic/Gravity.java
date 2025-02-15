package Ssafy.Algorithm.APSBasic;

import java.util.*;
import java.io.*;

public class Gravity {
    static BufferedReader br;
    static BufferedWriter bw;
    static int n;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int ans = 0;

        int cnt = 0;    // 현재까지 떨어진 낙차를 기록한다
        int max = -1;   // 현재 가장 긴 블록 ( 떨어지는 블럭 ) 을 기록
        for(int idx=0; idx<n; idx++) {
            int nowHeight = Integer.parseInt(st.nextToken());

            if(max <= nowHeight) {  // 현재 떨어지고 있던 블록보다 더 크거나 같은 블록을 만난다
                // 낙차를 새로 계산해준다. -> 떨어지고 있던 놈은 멈출테니
                ans += cnt; // 현재까지 떨어진 높이를 기록
                cnt = 0;    // 새로운 블록이 추가로 떨어지는 높이를 기록하기 위함
                max = nowHeight;
                continue;
            }

            cnt++;
        }

        System.out.println(ans+cnt);
    }
}
/*
상자들이 쌓여있음 -> 방을 오른쪽으로 90도 회전시켜 상자가 떨어짐
낙차가 가장 큰 상자를 구해라

>>입력
9
7 4 2 0 0 6 0 7 0
>>출력
 7

>>입력
9
7 4 2 0 0 6 7 7 7
>>출력
5

>>입력
9
0 0 0 0 0 0 0 0 0
>>출력
0
 */