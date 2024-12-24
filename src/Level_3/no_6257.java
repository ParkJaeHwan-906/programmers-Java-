package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 4회 정기 코딩 인증평가 기출] 통근버스 출발 순서 검증하기
// https://softeer.ai/practice/6257
public class no_6257 {
    static int n;
    static int[] bus;
    public static void main(String[] args) throws IOException {
        no_6257 problem = new no_6257();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        bus = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            bus[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(problem.problemUnderStand());
    }

    // 문제 이해하기
    /*
    (i, j, k) 가 있을 때
    임의의 자연수 i < j < k 에 대하여 (1) a^i < a^j 이고 (2) a^i > a^k 인 경우가 있다면 정렬이 불가하다
     */
    public long problemUnderStand() {
        long answer = 0;

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int a = bus[i]; // a^i
                int b = bus[j]; // a^j

                if(a > b) continue;

                // (1) 번 조건 만족
                for(int k=j+1; k<n; k++){
                    int c = bus[k];

                    // (2) 번 조건 만족
                    if(a>c) {
                        // 정답 반영
                        answer++;
                    }

                }
            }
        }

        return answer;
    }

}
