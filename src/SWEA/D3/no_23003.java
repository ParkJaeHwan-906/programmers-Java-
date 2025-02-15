package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 23003. 색상환
public class no_23003 {
    static BufferedReader br;
    static BufferedWriter bw;
    static final String[] colors = {"red", "orange", "yellow", "green", "blue", "purple"};
    static final int[][] nearIdx = {{5,1},{0,2},{1,3},{2,4},{3,5},{5,0}};
    static final int[] opposite = {3,4,5,0,1,2};
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for(int tc=0; tc<TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            String color1 = st.nextToken(); // 비교 색상 1
            String color2 = st.nextToken(); // 비교 색상 2

            int color1Idx = -1;
            int color2Idx = -1;
            for(int idx=0; idx<6; idx++) {
                if(color1.equals(colors[idx])) color1Idx = idx;
                if(color2.equals(colors[idx])) color2Idx = idx;
            }

            if(color1Idx == color2Idx) {    // 동일 색상
                sb.append('E');
            } else if(color2Idx == opposite[color1Idx]) {   // 반대 색상
                sb.append('C');
            } else if(color2Idx == nearIdx[color1Idx][0] || color2Idx == nearIdx[color1Idx][1]) {   // 인접 색상
                sb.append('A');
            } else {
                sb.append('X');
            }
            sb.append('\n');
        }
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
/*
    인접   반대
0   [5,1]  [3]
1   [0,2]  [4]
2   [1,3]  [5]
3   [2,4]  [0]
4   [3,5]  [1]
5   [5,0]  [2]
 */