package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 2948. 문자열교집합
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV-Un3G64SUDFAXr
public class SWEA_2948_문자열교집합 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase <= TC; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();;
    }

    static int groupASize, groupBSize;
    static void init() throws IOException {
        int duplicateNum = 0;   // 중복 문자열의 개수
        Set<String> set = new HashSet<>();  // 문자열의 중복 확인을 위한 작업

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        groupASize = Integer.parseInt(st.nextToken());
        groupBSize = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        for(int idx=0; idx<groupASize; idx++) {
            if(set.add(st.nextToken())) continue;
            duplicateNum++;
        }

        st = new StringTokenizer(br.readLine().trim());
        for(int idx=0; idx<groupBSize; idx++) {
            if (set.add(st.nextToken())) continue;
            duplicateNum++;
        }

        sb.append(duplicateNum);
    }
}

/*
    문자열을 알파벳 소문자로만 이루어져 있다.
    {"aba", "cdefasad", "wefawef"}은 문자열 3개로 구성된 한 개의 문자열 집합
    2개의 무자열 집합이 주어졌을 때, 두 집합에 모두 속하는 문자열 원소의 개수를 출력하라

    각 집합의 문자열간 월드컵 형식으로 구현한다?
    두 집합의 원소의 개수는 10^5
    문자열 길이는 50
    완탐하면 시간 초과 날듯. 10^10
    ------------------------------------------------------------------------------------
    Set 을 사용하여 문자열의 중복처리를 해준다.
 */