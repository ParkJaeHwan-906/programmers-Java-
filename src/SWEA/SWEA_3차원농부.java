package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_3차원농부 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase<=TC; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int cowCnt, horseCnt;
    static int[] cows;
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        cowCnt = Integer.parseInt(st.nextToken());
        horseCnt = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        int xDist = Math.abs(Integer.parseInt(st.nextToken())-Integer.parseInt(st.nextToken()));

        cows = new int[cowCnt];
        st = new StringTokenizer(br.readLine().trim());
        for(int cowIdx=0; cowIdx<cowCnt; cowIdx++) {
            cows[cowIdx] = Integer.parseInt(st.nextToken());
        }
        // 입력받은 소들을 오름차순으로 정렬한다.
        Arrays.sort(cows);

        int minDist = Integer.MAX_VALUE;
        int count = 0;

        st = new StringTokenizer(br.readLine().trim());
        for(int horseIdx=0; horseIdx<horseCnt; horseIdx++) {
            int nowHorse = Integer.parseInt(st.nextToken());

            int idx = getIdx(nowHorse); // 현재 말보다 더 큰 위치에 있는 가장 작은 위치의 소
            // 각 말과 소의 거리를 구한다.
            if(idx < cows.length) {
                int dist = cows[idx] - nowHorse;

                if(minDist == dist) count++;
                else if(minDist > dist){
                    minDist = dist;
                    count = 1;
                }
            }

            if(idx-1 >= 0) {    // 왼쪽에도 있다면
                int dist = nowHorse - cows[idx-1] ;

                if(minDist == dist) count++;
                else if(minDist > dist) {
                    minDist = dist;
                    count = 1;
                }
            }
        }

        sb.append(minDist+xDist).append(' ').append(count);
    }

    static int getIdx(int targetValue) {
        int l = 0;
        int r = cows.length-1;
        int idx = cows.length;

        while(l <= r) {
            int mid = l + (r-l)/2;

            if(cows[mid] >= targetValue) {
                idx = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return idx;
    }
}

/*
    가축들이 3차원 공간에서 길러진다.
    N 마리의 소와, M 마리의 말을 키운다.

    각 가축들은 3차원 좌표 평면 상의 점으로 표현된다.
    모든 소는 x=c1, y=0 직선 상에 존재하고
    모든 말은 x=c2, y-0 직선 상에 존재한다.

    두 소와 말이 가까이 있을 경우, 이산화탄소가 발생한다.
    두 동물 간의 거리는 dist (P, Q) = | x2-x1 | + | y2-y1 | + | z2-z1 | 이다.

    모든 소와 말 쌍 중에서 가장 가까운 쌍의 거리와, 이러한 거리를 갖는 쌍의 개수를 알고싶어한다.

    모든 쌍의 거리를 미리 구한다?
    각 소와 말의 최대 수는 50만, 모든 쌍을 구하는 것은 힘듬 500,000 ^ 2
 */