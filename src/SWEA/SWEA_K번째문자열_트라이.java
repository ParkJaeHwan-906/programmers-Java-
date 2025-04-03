package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SWEA_K번째문자열_트라이 {
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
    static List<String> dic;
    static void init() throws IOException {
        dic = new ArrayList<>();

        seq = Integer.parseInt(br.readLine().trim());
        str = br.readLine();



        Collections.sort(dic, (a, b) -> {
            if(a.length() == b.length()) return a.compareTo(b);

            return a.length() - b.length();
        });

        sb.append(dic.get(seq));
    }
}
