package Ssafy.BasicGrammer;

import java.util.*;
import java.io.*;

public class EvenSum {
    static BufferedReader br;
    static int numRange;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        numRange = Integer.parseInt(br.readLine().trim());

        long sum = 0;
        for(int i=2; i<=numRange; i+=2){
            sum += i;
        }

        sb.append(sum);
        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
