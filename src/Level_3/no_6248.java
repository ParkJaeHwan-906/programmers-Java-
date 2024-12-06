package Level_3;

import java.sql.Array;
import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 6회 정기 코딩 인증평가 기출] 출퇴근길
// https://softeer.ai/practice/6248
public class no_6248 {
    static ArrayList<Integer>[] road;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        road = new ArrayList[n];
        for(int i = 0; i < n; i++){
            road[i] = new ArrayList<>();
        }

        // 도로 정보 저장
        while(m > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            road[a].add(b);
            m--;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken())-1;


    }
}
