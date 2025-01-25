package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 택배마스터 광우
// https://softeer.ai/practice/6273
public class no_6273_2 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int railNum, limitWeight, work;
    static int[] rail;
    public static void main(String[] args) throws IOException {
        no_6273_2 problem = new no_6273_2();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        railNum = Integer.parseInt(st.nextToken()); // 레일의 개수
        limitWeight = Integer.parseInt(st.nextToken()); // 최대 무게
        work = Integer.parseInt(st.nextToken());    // 일의 횟수
        rail = new int[railNum];    // 배열 초기화

        st = new StringTokenizer(br.readLine().trim());
        for(int idx=0; idx<railNum; idx++) {    // 배열 입력
            rail[idx] = Integer.parseInt(st.nextToken());
        }
        br.close();

        bw.write(String.valueOf(problem.minWork()));
        bw.flush();
        bw.close();
    }

    int[] seqRail;
    boolean[] visited;
    int minWeight;
    public int minWork() {
        seqRail = new int[railNum]; // 순서를 기록할 배열
        visited = new boolean[railNum]; // 완전탐색에서 방문처리를 위한 배열

        minWeight = Integer.MAX_VALUE;  // Integer 의 최대값으로 초기화

        findSeq(0);

        return minWeight;
    }

    // 완전 탐색으로 모든 순서의 경우를 구한다
    private void findSeq(int depth) {
        if(depth == railNum) { // 모든 경우를 다 탐색했다면
            // 해당 순서로 일을 했을 경우 일의 양을 구한다
            int totalWeight = getTotalWeight();
            // 작은 값으로 갱신한다
            minWeight = Math.min(minWeight, totalWeight);
            return;
        }

        for(int idx=0; idx<railNum; idx++) {
            if(visited[idx]) continue;  // 이미 방문한 레일이라면 넘어간다

            // 백트래킹
            visited[idx] = true;
            seqRail[depth] = rail[idx];

            findSeq(depth+1);
            visited[idx] = false;
        }
    }

    // 현재 순서로 일을 하면 하게 되는 총 일의 양을 반환한다
    private int getTotalWeight() {
        int totalweight = 0;   // 총 일의 양
        int idx = 0;    // 인덱싱을 위한 변수

        // 할당량을 채운다
         for(int workTime = 0; workTime < work; workTime++){
              int bucket = 0;    // 현재 바구니의 무게
              while(bucket + seqRail[idx%railNum] <= limitWeight) {  // 바구니에 가장 많이 담을 수 있을때 까지
                  bucket += seqRail[idx++%railNum];
              }
              totalweight += bucket;
         }

         return totalweight;
    }
}
