package Level_3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 소프티어 Lv.3
// 루돌프 월드컵
// https://softeer.ai/practice/7721
public class no_7721 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int[] deers = new int[4];    // 루돌프는 4마리
    public static void main(String[] args) throws IOException {
        no_7721 problem = new no_7721();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 루돌프 정보 입력 (힘)
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        for(int idx=0; idx<4; idx++) {
            deers[idx] = Integer.parseInt(st.nextToken());
        }

        br.close();
        bw.write(problem.winPercent());
        bw.flush();
        bw.close();
    }

    double total;
    int[] points;
    public String winPercent(){
        total = 0.0;
        points = new int[4];    // 루돌프들의 승점을 기록

        calcPercent();
        match(0,1.0);

        // 퍼센트 출력을 위해 * 100 
        return String.format("%.3f", total*100);
    }

    int[][] matches = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}}; // 모든 경기 조합
    public void match(int round, double percent) {  // 모든 라운드에서의 승점을 구해준다 ( 완전 탐색 )
        if(round == 6) {    // 모든 라운드가 끝남
            // 2등 안에 드는지 확인한다.
            if(isContainDeer1()){
                total += percent;
            }
            return;
        }

        int deerA = matches[round][0];
        int deerB = matches[round][1];

        // 완전 탐색
        // A 번 사슴이 우승
        points[deerA] += 3;
        match(round+1, percent * win[deerA][deerB]);
        points[deerA] -= 3;

        // B 번 사슴이 우승
        points[deerB] += 3;
        match(round+1, percent * win[deerB][deerA]);
        points[deerB] -= 3;

        // 비기는 경우
        points[deerA] += 1;
        points[deerB] += 1;
        match(round+1, percent * draw[deerA][deerB]);
        points[deerA] -= 1;
        points[deerB] -= 1;
    }

    public boolean isContainDeer1() {
        int[][] rank = new int[4][2];   // [번호, 점수]
        for(int idx=0; idx < 4; idx++) {
            rank[idx][0] = idx;
            rank[idx][1] = points[idx];
        }

        Arrays.sort(rank, (a,b) -> {
            // 승점이 같다면
            if(a[1] == b[1]) {  // 번호가 낮은 루돌프가 우선
                return a[0] - b[0];
            }

            // 점수 내림차순
            return b[1] - a[1];
        });

        // 2 등 내에 1번 사슴이 있는지
        return rank[0][0] == 0 || rank[1][0] == 0;
    }

    double[][] win = new double[4][4];
    double[][] draw = new double[4][4];
    double[][] lose = new double[4][4];
    public void calcPercent() { // 각 경기의 확률을 계산한다
        for(int i=0; i<4; i++) {
            for(int j=i+1; j<4; j++) {  // 역방향도 채워준다 
                double total = 5.0 * deers[i] + 5.0 * deers[j];
                win[i][j] = (4.0 * deers[i]) / total;
                win[j][i] = (4.0 * deers[j]) / total;
                draw[i][j] = draw[j][i] = (deers[i] + deers[j]) / total;
                lose[i][j] = win[j][i];
                lose[j][i] = win[i][j];

            }
        }
    }
}

/*
* 루돌프 후보는 4마리
* 상위 2마리를 선발한다 -> 1번 루돌프가 선택될 확률을 구한다
*
* 입력
* 각 루돌프의 힘 정보가 주어진다
*
* i 번 루돌프와 j 번 루돌프의 결과
* i 번 루돌프가 이길 확률 5i + 5j*4i
* j 번 루돌츠가 이길 확률 5i + 5j*4j
* 비길 확률 5i + 5j*i + j
*
* 모든 루돌프가 정확하게 1번씩 경기를 진행한다
* 승리 : 3, 비김 : 1, 패배 : 0
* 최종 점수가 동일하다면 번호가 작은 루돌프가 더 높은 순위를 얻는다
*
* 1번 루돌프가 2등 안으로만 들어오면 된다
*
* 경기
* 1 : 2
* 1 : 3
* 1 : 4
* 2 : 3
* 2 : 4
* 3 : 4
* -> 총 6 회의 경기
*
* 각 경기마다 이김, 비김, 짐 3가지의 경우가 있음
*/