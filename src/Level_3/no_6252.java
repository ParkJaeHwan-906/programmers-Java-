package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 4회 정기 코딩 인증평가 기출] 슈퍼컴퓨터 클러스터
// https://softeer.ai/practice/6252
public class no_6252 {
    static int n;
    static long b;
    static long[] computer;
    public static void main(String[] args) throws IOException {
        no_6252 problem = new no_6252();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());

        computer = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            computer[i] = Long.parseLong(st.nextToken());
        }
        br.close();

        // 오름차순 정렬
        Arrays.sort(computer);

        bw.write(String.valueOf(problem.solution()));
        bw.flush();
        bw.close();
    }

    // 5 5 6 1 =? 1 5 5 6
    public long solution() {
       long answer = 0;

       long l = computer[0];    // 최소값 초기화
       long r = computer[n-1] + (long) + Math.sqrt(b);  // 최대값 + B 원으로 최대 업그레이트 할 수 있는 성능

        while(l <= r) {
            long mid = l + (r-l) / 2;   // 오버플로우 방지

            if(powerUp(mid)) {
                l = mid + 1;
                answer = mid;
            } else {
                r = mid - 1;
            }
        }

        return answer;
    }

    private boolean powerUp(long mid) {
        long cost = 0;  // mid 를 만들기 위한 비용 계산

        for(long c : computer) {
            if(c < mid) {   // 최소가 되야할 값보다 작은 수는 업그레이트 비용 계산해줌
                cost += (long) Math.pow((mid - c), 2);


                if(cost > b) return false;
            }
        }

        return true;
    }
}
