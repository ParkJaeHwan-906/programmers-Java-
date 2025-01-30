package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [21년 재직자 대회 본선] 코딩 테스트 세트
// https://softeer.ai/practice/6261
public class no_6261 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int level, scenarios;
    static long[] fixed;
    static long[] notFixed;
    static long sum;
    public static void main(String[] args) throws IOException {
        no_6261 problem = new no_6261();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 출력을 위해 사용
        StringBuilder sb = new StringBuilder();

        // 레벨과 시나리오 수를 입력 받는다.
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        level = Integer.parseInt(st.nextToken());
        scenarios = Integer.parseInt(st.nextToken());

        for(int no=0; no<scenarios; no++) {
            sum = 0;

            fixed = new long[level];
            notFixed = new long[level];

            // 시나리오 입력 받음
            st = new StringTokenizer(br.readLine().trim());
            for(int idx=0; idx<level-1; idx++) {
                fixed[idx] = Long.parseLong(st.nextToken());
                notFixed[idx] = Long.parseLong(st.nextToken());
                sum += fixed[idx] + notFixed[idx];  // 모든 문제의 개수를 더해준다
            }
            fixed[level-1] = Long.parseLong(st.nextToken());
            sum += fixed[level-1];

            sb.append(problem.getMaxSet()).append('\n');
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private long getMaxSet() {
        long l = 0;
        long r = sum/level; // 나올 수 있는 최대 문제 세트의 개수

        //  값으로 가능한지 체크
        if(isPossible(fixed, notFixed, r)) {
            return r;
        }

        while(l < r) {
            long mid = (l + r) / 2;

            if(l == mid) break;

            if(isPossible(fixed, notFixed, mid)) {
                l = mid;
            }else{
                r = mid;
            }
        }

        return l;
    }

    // mid 개의 세트를 만들 수 있는지 확인
    private boolean isPossible(long[] fixed, long[] notFixed, long mid){
        // 이전 레벨에서 넘겨 줄 수 있는 문제 개수
        long prev = 0;

        for(int i=0; i<level; i++) {
            // 문제가 충분
            if(fixed[i] >= mid){
                prev = notFixed[i]; // 여분 문제를 저장
                continue;
            }

            long need = mid - fixed[i]; // 부족한 문제 개수
            long available = notFixed[i] + prev;    // 현제 레벨과 이전 레벨의 남은 문제 합

            if(need > available) return false;

            // notFixed 와 사용 값을 비교
            // notFixed 보다 available-need 가 작을 수가 없음
            prev =  Math.min(notFixed[i], available - need);    // 다음 레벨로 넘어 갈 수 있는 문제 개수 갱신
        }

        return true;    // 모든 레벨을 채울 수 있다면
    }
}


/*
문제의 난이도는 1~N 레벨

난이도가 정확히 i 레벨로 평가된 문제는 총 C 개 있음
난이도가 애매한 문제는 D 개 있음 ( i 레벨 또는 i+1 레벨로 표기 )

하나의 문제 세트는 1~N 사이의 문제를 N 개 모은 것
난이도가 애매한 문제들은 현호가 임의로 가능한 난이도를 적절히 매겨 넣음
서로 같은 문제 포함 X, 최대로 나올 수 있는 코딩 테스트 세트의 개수는 ?

입력
각 줄마다 시나리오가 주어진다.
3 3
2 2 1 1 3
39 31 97 95 24
1000 1000 1000 1000 1000

각 시나리오는 문제 개수를 나타내는 2N-1 개의 정수로 이루어져 있음 c,d 나열
C : 2(1) 1(2) 3(3) [A,B,C]
D : 2(1~2) 1(2~3) [Q, W]

A,B,C
A,Q,C
Q,B,C
*/

/*
⚠️ 문제 해설
3 3
2 2 1 1 3
C : 2(1) 1(2) 3(3)
D : 2(1~2) 1(2~3)

1. 2(1) 1(2) 3(3)
2. 2(1) 1(2~3) 3(3)
3. 2(1~2) 1(2) 3(3)
 */