package CodingTest;

import java.util.*;
import java.io.*;

public class HSAT241206_4 {
    static int n, count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 패턴 길이
        n = Integer.parseInt(st.nextToken());
        // 반복 횟수
        count = Integer.parseInt(st.nextToken());

        String pw = br.readLine();

        HSAT241206_4 problem = new HSAT241206_4();
        System.out.println(problem.solution(pw));
    }

    public int solution(String pw) {
        for(int i=0; i<=pw.length()-n; i++){
            String slice = pw.substring(i,i+n);
            int cnt = 1;
            for(int j=i+1; j<=pw.length()-n; j++){
                String check = pw.substring(j, j+n);
                if(slice.equals(check)) cnt++;
            }
            System.out.println();
            if(cnt == count) return 1;
        }

        return 0;
    }
}
