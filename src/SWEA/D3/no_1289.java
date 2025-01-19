package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 1289. 원재의 메모리 복구하기
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV19AcoKI9sCFAZN
public class no_1289 {
    static String originData;
    public static void main(String args[]) throws IOException
    {
        no_1289 problem = new no_1289();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        // tc
        int tc = Integer.parseInt(br.readLine());
        for(int no = 1; no <= tc; no++)
        {
            // 문제 번호
            sb.append("#").append(no).append(" ");

            // 기존 데이터 입력 받음
            originData = br.readLine().trim();
            sb.append(problem.solution()).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.close();
    }

    // 기존 데이터로 복구하기 위하여 필요한 횟수 반환
    private int solution () {
        int answer = 0;

        // 기준
        char pre = '0';

        for(int i=0; i<originData.length(); i++) {
            // 변환할 필요가 없음
            if(originData.charAt(i) == pre) continue;
            // 변환해야함
            pre = originData.charAt(i);
            answer++;
        }
        return answer;
    }
}
