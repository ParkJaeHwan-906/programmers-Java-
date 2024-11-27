package Level_2;

import java.util.*;
import java.io.*;

// Softeer Lv.2
// GPT식 숫자 비교
// https://www.softeer.ai/practice/11001
public class no_11001 {
    static String[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // [정수부, 소수부]
        arr = new String[n][2];
        for(int i=0; i<n; i++){
            String s = br.readLine();
            int dotIdx = s.indexOf(".");

            if(dotIdx > -1) {
                arr[i][0] = s.substring(0, dotIdx);
                arr[i][1] = s.substring(dotIdx+1);
            } else {
                arr[i][0] = s;
                arr[i][1] = "-1";
            }
        }
        br.close();


        Arrays.sort(arr, (a, b) -> {
            int aOne = Integer.parseInt(a[0]);
            int bOne = Integer.parseInt(b[0]);

            int aTwo = Integer.parseInt(a[1]);
            int bTwo = Integer.parseInt(b[1]);

            if(aOne == bOne) return aTwo - bTwo;

            return aOne - bOne;
        });

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(String[] sArr : arr) {
            StringBuilder sb = new StringBuilder();

            sb.append(sArr[0]);

            if(sArr[1].charAt(0) != '-'){
                sb.append(".");
                sb.append(sArr[1]);
            }
            bw.write(sb.toString()+"\n");

        }

        bw.flush();
        bw.close();
    }
}
