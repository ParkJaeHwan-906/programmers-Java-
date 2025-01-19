package SWEA.D1;

import java.util.*;
import java.io.*;

// SWEA D1
// 2056. 연월일 달력
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5QLkdKAz4DFAUq&categoryId=AV5QLkdKAz4DFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_2056 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for(int i=1; i<=tc; i++) {
            sb.append('#').append(i).append(' ');
            String s = br.readLine();

            String year = s.substring(0, 4);
            String month = s.substring(4, 6);
            String day = s.substring(6);

            if(proveDate(year,month, day)){
                sb.append(year).append('/').append(month).append('/').append(day).append('\n');
            } else {
                sb.append(-1).append('\n');
            }
        }
        System.out.print(sb.toString());
    }

    private static int[] mDay = new int[] {31,28,31,30,31,30,31,31,30,31,30,31};
    private static boolean proveDate(String year, String month, String day) {
        int m = Integer.parseInt(month);
        int d = Integer.parseInt(day);

        return m > 0 && m <= 12 && d > 0 && d <= mDay[m-1];
    }
}
