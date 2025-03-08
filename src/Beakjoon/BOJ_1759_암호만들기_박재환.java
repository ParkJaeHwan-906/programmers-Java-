package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1759_암호만들기_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        init();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int passwordLen, alphabetNums;        // 목표 길이, 주어지는 알파벳 수
    static String[] alphabets;                   // 주어지는 알파벳
    static boolean[] isUsed;                    // 조합을 구하기 위한 방문 처리 배열
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        passwordLen = Integer.parseInt(st.nextToken());
        alphabetNums = Integer.parseInt(st.nextToken());

        isUsed = new boolean[alphabetNums];
        alphabets = new String[alphabetNums];
        st = new StringTokenizer(br.readLine().trim());
        for(int alphabet=0; alphabet<alphabetNums; alphabet++) {
            alphabets[alphabet] = st.nextToken();
        }

        // 사전순으로 출력을 위해 초기에 정렬
        Arrays.sort(alphabets);

        makeCrypto(0,0);
    }

    static void makeCrypto(int alphabetIdx, int len) {  // 현재 알파벳, 현재 암호 길이
        if(len == passwordLen) {    // 요구하는 길이를 충족
            StringBuilder crypto = new StringBuilder();

            int vowel = 0, consonant = 0;   // 모음, 자음
            for(int alphabet=0; alphabet<alphabetNums; alphabet++) {    // 암호를 만드는데 사용되는 문자열을 확인
                if(isUsed[alphabet]){   // 현재 문자를 사용
                    crypto.append(alphabets[alphabet]);

                    // 사용한 알파벳이 자음인지 모음인지 화인
                    if("aeiou".contains(alphabets[alphabet])) {
                        vowel++;
                    } else {
                        consonant++;
                    }
                }
            }

            // 문제의 조건을 만족하는지 확인
            // 1. 최소 1개의 모음을 사용한다.
            // 2. 최소 2개의 자음을 사용한다.
            if(vowel >= 1 && consonant >= 2) sb.append(crypto).append('\n');

            return;
        }

        // 이전의 알파벳은 사용하지 않는다.
        // 사용한 것 이전의 것 확인은 하지 않아도 됨
        for(int alphabet = alphabetIdx; alphabet < alphabetNums; alphabet++) {
            isUsed[alphabet] = true;
            makeCrypto(alphabet+1, len+1);
            isUsed[alphabet] = false;
        }
    }
}

/*
 * 암호는 서로 다른 L 개의 알파벳 소문자들로 구성되며, 최소 한 개의 모음 [a,e,i,o,u] 와 최소 두 개의 자음으로 구성되어 이싿.
 * 알파벳이 암호에서 증가하는 순서로 배열되어 있을것이라고 추측한다.
 *
 * 암호로 사용했을 법한 문자 C 개가 주어진다.
 *
 * 최소 한 개의 모음이 사용되어야한다.
 *
 * 1. 한 개의 모음을 꼭 포함하는 문자열 조합부터 구한다.
 *         a. 이때 알파벳의 순서는 오름차순이어야한다.
 * 자음과 모음을 분리하여 배열을 저장해둔다.
 */
