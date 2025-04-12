package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_5598_카이사르암호_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }

    static void init() throws IOException {
        String input = br.readLine().trim();

        for(char c : input.toCharArray()) {
            char convert = (char)((c - 'A' - 3 + 26) % 26 + 'A');
            sb.append(convert);
        }
    }
}
