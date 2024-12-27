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

        System.out.println(problem.solution());
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

    // ⚠️ 풀이 참고
    // A < B 조건은 무시하자
    // 📌 A > C 가 핵심

    // ???? 구간합? (DP?)  ????

    /*
    arr[x][j] => j 번째 이후에 있는 것들 중, x 보다 작은 것들의 수

    => 버스 번호가 담긴 리스트를 끝에서부터 순회하면 O(1) ( 효율적 ✅ )
    => 총 O(n^n) 시간 소요
     */
    public long solution() {
        // ✅ i < j < k
        long answer = 0;

        // 첫 번째 인덱스부터 n-3 까지 순회
        for(int i=0; i<n-2; i++){   // i 는 첫 번째 값 인덱스
            long stack = 0; // 조건을 만족하는 중간 값 누적
            List<Integer> list = new ArrayList<>(); // i 를 기준으로 j 와 k 의 조건을 기록

            // i+1 에서 n-1 까지 순회
            for(int j=i+1; j<n; j++){   // j 는 두 번째 값 인덱스
                if(bus[i] < bus[j]) {   // i < j 인 경우 ( 📌 bus[i] < bus[j] )
                    list.add(1);    // 만족하면 1 추기
                } else{ // ( 📌 bus[i] > bus[k] )
                    list.add(0);    // 아니면 0 추가
                }
            }

            // 리스트를 기반으로 조합 계산
            for(int count : list) {
                if(count == 1) {
                    stack++;    // 조건을 만족하는 값의 개수를 누적 ( bus[i] < bus[j] )
                } else {
                    answer += stack;    // 현재까지 누적된 조건 만족 개수를 더함 ( bus[i] > bus[k] )
                }
            }
        }

        return answer;
    }
}
