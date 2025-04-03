package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_K번째문자열 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());

        for (int testCase = 1; testCase < TC+1; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }
        br.close();
        System.out.println(sb);
    }

    static int seq;
    static String str;
    static Set<String> dic;
    static void init() throws IOException {
        dic = new TreeSet<>();

        seq = Integer.parseInt(br.readLine().trim());
        str = br.readLine();

        makeDic();

        if(dic.size() < seq) sb.append("none");
        else {
            Iterator<String> word = dic.iterator();
            for(int i=0; i<seq-1; i++) word.next();
            sb.append(word.next());
        }
    }

    static void makeDic() {
        for (int i = 0; i < str.length(); i++) {
            StringBuilder word = new StringBuilder();
            for (int j = i; j < str.length(); j++) {
                word.append(str.charAt(j));

                dic.add(word.toString());

                if(dic.size() == seq);
            }
        }
    }
}

/*
    영어 소문자로 된 문자열이 있다.
    두 위치가 같을 때는 길이가 1인 부분 문자열이 된다.

    ex)
    love
    l o v e
    lo ov ve
    lov ove
    love

    food
    f o d -> o 는 중복이기에 제거
    fo oo od
    foo ood
    food

    문자열에 대해 사전 순서로 정렬하는 것을 고려

    두 문자열을 왼쪽부터 오른쪽으로 비교해나가며,
    처음으로 다른 글자가 나왔을 때, 알파벳 순으로 먼저 나오는 문자가 있는 쪽이 순서가 앞
 */