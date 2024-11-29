package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 3회 정기 코딩 인증평가 기출] 교차로
// https://www.softeer.ai/practice/6256
public class no_6256 {
    static Queue<Integer> inTime = new LinkedList<>();
    static Queue<String> inLoc = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            // 진입 시점, 위치
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String loc = st.nextToken();

            inTime.offer(time);
            inLoc.offer(loc);
        }

        br.close();



        bw.flush();
        bw.close();
    }

    public int[] solution(int n) {
        while(!inLoc.isEmpty()){
            int time = inTime.poll();
            String loc = inLoc.poll();

            // 동시간 진입차량 전체 확인
            while(!inLoc.isEmpty() && inTime.peek() == time) {

            }
        }
    }
}
