package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 2회 정기 코딩 인증평가 기출] 사물인식 최소 면적 산출 프로그램
// https://softeer.ai/practice/6277
public class no_6277 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        init();

        
    }

    static int[][] map;  // 좌표를 2차원 배열로 표현
    static int dotNums, colorNums;  // 점의 개수, 색상의 종류
    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        dotNums = Integer.parseInt(st.nextToken());
        colorNums = Integer.parseInt(st.nextToken());

        map = new int[41][41];  // 여유롭게 좌표 설정 (-20,-20) -> (20,20)

        for(int color=0; color<dotNums; color++) {
            st = new StringTokenizer(br.readLine().trim());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int colorNo = Integer.parseInt(st.nextToken());

            map[y+20][x+20] = colorNo;
        }
    }
}

/*
    인식된 정도는 평면에 N 개의 점으로 주어진다
    각 점들을 K 개의 색 중 하나를 가진다.

    주어진 K 개의 색에 대해 {1,2,3,...,K}
    해당 색깔을 가지는 점들을 적어도 하나씩 포함하는 사물 중
    넓이가 가장 작은 것을 찾아 반환한다. ( 직사각형 )

    내부와 경계 모두 직사각형으로 포함
    가로와 세로가 0이되어 선분 혹은 점으로 나타나는 경우도 직사각형으로 인정 -> 넓이 0

    제약조건
    1 ≤ N ≤ 10
    1 ≤ K ≤ 20
    -1,000 ≤ x, y ≤ 1,000
    1 ≤ k ≤ K
  */
