package Level_3;

import java.io.*;
import java.util.*;

// Softeer Lv.3
// [한양대 HCPC 2023] Pipelined
// https://softeer.ai/practice/9496
public class no_9496_2 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int targetNum;   // 목표 차량의 수
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
         br = new BufferedReader(new InputStreamReader(System.in));
         bw = new BufferedWriter(new OutputStreamWriter(System.out));

        targetNum = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for(int idx=0; idx<targetNum; idx++) {
            max = Math.max(max, Integer.parseInt(st.nextToken()));
        }
        br.close();
        bw.write(String.valueOf(max + targetNum - 1));
        bw.flush();
        bw.close();
    }
}

/*
각 자동차는 생선하기 위해 Si 단계의 프로세스를 거친다

ex)
5 7 8 11
-> 14

최대 값 + 목표 개수 - 1?
 */