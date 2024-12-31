package SWEA.D1;

import java.util.*;
import java.io.*;

// SWEA D1
// 2071.평균값 구하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5QRnJqA5cDFAUq&categoryId=AV5QRnJqA5cDFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_2071 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for(int i=0; i<tc; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int sum = 0;
            for(int j=0; j<10; j++){
                sum += Integer.parseInt(st.nextToken());
            }
            sb.append("#").append(i+1).append(" ").append((int)Math.round((double) sum / 10)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
