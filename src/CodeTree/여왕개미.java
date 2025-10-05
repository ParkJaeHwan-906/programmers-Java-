package CodeTree;

import java.util.*;
import java.io.*;

public class 여왕개미 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }
    static StringTokenizer st;
    static int q;       // 주어지는 명령어의 수
    static List<AntHome> village;     // 마을       좌표의 정보가 0 ~ 10^9
    static int nextIdx;
    static void init() throws IOException {
        village = new ArrayList<>();
        nextIdx = 0;
        q = Integer.parseInt(br.readLine().trim());
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int commandType = Integer.parseInt(st.nextToken());
            switch (commandType) {
                case 100:
                    initVillage();
                    break;
                case 200:
                    buildAntHome();
                    break;
                case 300:
                    removeAntHome();
                    break;
                case 400:
                    getMinTime();
                    break;
            }
        }
    }

    static class AntHome {
        long loc;       // 좌표 0 ~ 10^9
        boolean deleted;   // 철거 여부

        public AntHome(long loc) {
            this.loc = loc;
            this.deleted = false;
        }
    }

    /**
     * [1]
     * 초기 마을 건설
     * - 여왕의 집은 0 위치에 지어진다.
     */
    static void initVillage() {
        int antHomeCnt = Integer.parseInt(st.nextToken());
        // 여왕 개미의 집은 0 위치에 있음
        village.add(new AntHome(0));
        while(antHomeCnt-- > 0) {
            long loc = Long.parseLong(st.nextToken());
            village.add(new AntHome(loc));
        }
    }
    /**
     * [2]
     * 개미집을 건설한다.
     * - 집을 건설하는 위치는 양의 정수 p
     * - p 는 지금까지 건설된 모든 개미집의 좌표보다 큰 값으로 주어진다.
     * - 무조건 뒤에 삽입 된다
     */
    static void buildAntHome() {
        long loc = Long.parseLong(st.nextToken());
        village.add(new AntHome(loc));
    }
    /**
     * [3]
     * 개미집을 철거한다.
     * - 주어지는 idx 의 개미집을 철거한다.
     * - 이미 철거된 상태이거나, 아직 지어지지 않은 경우는 입력으로 들어오지 않는다.
     */
    static void removeAntHome() {
        int idx = Integer.parseInt(st.nextToken());
        village.get(idx).deleted = true;        // 철거
    }
    /**
     * [4]
     * 개미집을 정찰한다.
     * - x 값이 증가하는 방향으로 1초에 1만큼 이동한다.
     * - 처음 만나는 개미집이 안전한 개미집인 경우 이동을 중지한다.
     * - 모든 개미집이 안전한 개미집이 되면 이동이 종료된다.
     *
     * => 처음 개미집을 선택할 때, 정찰에 걸리는 시간이 최소가 되도록 선택한다.
     */
    static void getMinTime() {
        int antCnt = Integer.parseInt(st.nextToken());      // 정찰 나갈 개미의 수

        // 시간을 기준으로 이진 탐색 진행
        long l = 0, r = 1_000_000_000;
        long minTime = 0;

        while(l <= r) {
            long mid = l + (r-l)/2;     // mid 시간 내에 antCnt 로 정찰이 가능한지

            int ant = 0;       // 필요한 개미 수를 0으로 시작
            long lastLoc = Long.MIN_VALUE/2;  // 임의의 작은 수 대입 -> 첫번째 위치에 어찌됐든 한 마리의 개미를 할당해야하기 때문
            for (int idx=1; idx<village.size(); idx++) {
                if(village.get(idx).deleted) continue;
                long loc = village.get(idx).loc;
                if(loc - lastLoc > mid) {
                    ant++;          // 새로운 개미 필요
                    lastLoc = loc;  // 커버 시작 위치 갱신
                }
            }

            if(ant <= antCnt) {     // antCnt 로 mid 시간 내에 정찰이 가능
                // 범위를 더 줄여본다.
                minTime = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        sb.append(minTime).append('\n');
    }
}
/**
7
100 5 2 4 7 8 15
400 1
400 2
200 50
400 2
300 5
400 3
 */