package SWEA.D1;

import java.util.*;
import java.io.*;

// SWEA D1
// 1936. 1대1 가위바위보
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PjKXKALcDFAUq
public class no_1936 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if(a == 1) {    // A : 가위
            if (b== 2) {    // B : 바위
                bw.write("B");
            } else {    // B : 보
                bw.write("A");
            }
        } else if(a == 2){  // A : 바위
            if(b == 1) {    // B : 가위
                bw.write("A");
            } else {    // B : 보
                bw.write("B");
            }
        } else {    // A : 보
            if(b == 1) {    // B : 가위
                bw.write("B");
            } else {    // B : 바위
                bw.write("A");
            }
        }

        bw.flush();
        bw.close();
    }
}
