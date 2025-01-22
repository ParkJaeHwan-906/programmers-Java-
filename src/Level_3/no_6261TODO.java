package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [21년 재직자 대회 본선] 코딩 테스트 세트
// https://softeer.ai/practice/6261
public class no_6261TODO {
    static BufferedReader br;
    static BufferedWriter bw;
    static int level, scenario;
    static int[] problems;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 출력을 위해 사용
        StringBuilder sb = new StringBuilder();

        // 레벨과 시나리오 수를 입력 받는다.
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        level = Integer.parseInt(st.nextToken());
        scenario = Integer.parseInt(st.nextToken());

        for(int no=0; no<scenario; no++) {
            // 문제의 개수는 2*N - 1
            problems = new int[level*2 - 1];

            // 시나리오 입력 받음
            st = new StringTokenizer(br.readLine().trim());
            for(int idx=0; idx<problems.length; no++) {
                problems[idx] = Integer.parseInt(st.nextToken());
            }


        }
    }

//    // 이진 탐색 함수
//    public binarySearch(int start, int end) {
//
//    }
//
//    public boolean test()
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