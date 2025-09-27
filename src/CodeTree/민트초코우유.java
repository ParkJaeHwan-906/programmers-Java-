package CodeTree;

import java.util.*;
import java.io.*;
public class 민트초코우유 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }

    /**
     * N x N 크기의 격자 (1,1) -> (N, N)
     * 음식의 종류
     * T : 민트
     * C : 초코
     * C : 우유
     * 초기에는 셋 중 하나만을 신봉
     * 다른 사람에게 영향을 받아 바뀔 수 있음
     * -> 민트 초코 우유가 생길수도 있음
     *
     * 1. 아침
     * 신앙심을 1 씩 얻는다
     *
     * 2. 점심
     * 인접한 학생들과 신봉하는 음식이 완전하게 같은 경우 그룹을 형성
     * 인접 -> 상 하 좌 우
     *
     * 그룹의 대표
     * - 신앙심이 가장 큰 사람
     * - 동일한 경우 x 위치가 가장 작은 사람
     * - 그것조차 같으면 y 위치가 가장 작은 사람
     *
     * 그룹 대표에게 신앙심 1씩 양도
     *
     * 3. 저녁
     * 그룹 1
     * 단일 음식 : 민트, 초코, 우유
     * 그룹 2
     * 이중 조합 : 초코 우유, 민트 우유, 민트 초코
     * 그룹 3
     * 삼중 조합 : 민트 초코 우유
     *
     * 모든 그룹 대표가 신앙을 전파
     * - 대표자의 신앙심이 높은 순
     * - 동일한 경우 행 번호가 작은 순
     * - 열 번호가 작은 순
     *
     * 전파자는 신앙심 중 1 만 남기고, 나머지를 간절함으로 바꿔 전파에 사용
     * 전파 방향은 B 를 4로 나눈 나머지
     * 방향 [0 : 위, 1 : 아래, 2 : 왼쪽, 3 : 오른쪽]
     *
     * 전파자는 전파 방향으로 한 칸씩 이동하며 전파를 시도
     * 격자 밖으로 나가거나, 간절함이 0 이 되면 전파는 종료
     *
     * 전파 대상과 신봉음식이 같은 경우, 패스
     * 다른 경우 전파 진행, 전파 대상의 신앙심 y 일대, 간절함이 크면 전파 성공 ( x > y )
     * 신봉 음식 변경
     * 간절함은 y+1 줄어들고, 대상의 신앙심은 1 증가
     *
     * (x >= y )
     * 신봉 음식 결합
     *
     * 전파를 당한 대상은 방어 상태가 되어, 당일에는 전파를 하지 않는다.
     * 하지만 추가 전파를 받는 것은 가능하다.
     *
     * => 각 날의 저녁 이후
     * [민트초코우유, 민트초코, 민트우유, 초코우유, 우유, 초코, 민트]
     * 신앙심 총 합을 출력하라
     */
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};
    static StringTokenizer st;
    static int n, t;
    static Student[][] stMap;       // 학생들 정보 map
    static int[][] grMap;           // 그룹 map
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        stMap = new Student[n+1][n+1];
        grMap = new int[n+1][n+1];

        for(int x=1; x<n+1; x++) {
            String str = br.readLine().trim();
            for(int y=1; y<n+1; y++) {
                stMap[x][y] = new Student(String.valueOf(str.charAt(y-1)));
            }
        }
        for(int x=1; x<n+1; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=1; y<n+1; y++) {
                stMap[x][y].trust = Integer.parseInt(st.nextToken());
            }
        }
        // 하루하루 이동
        while(t-- > 0) {

        }
    }

    /**
     * 학생의 정보
     * - 신봉 음식
     * - 신앙심
     * - 방어 상태
     */
    static class Student {
        /**
         * T : 민트
         * C : 초코
         * M : 우유
         * TC : 민트초코
         * CM : 초코우유
         * MU : 민트우유
         * TCM : 민트초코우유
         */
        String food;
        int trust;
        boolean depend;

        public Student(String food) {
            this.food = food;
            this.depend = false;
        }
    }
    /**
     * 신봉 그룹 정보
     * - id
     * - 대표
     * - 소속 학생들
     */
    static class Group {
        int id;
        Student repSt;
        PriorityQueue<Student> member;

        public Group (int id, List<Student> member) {
            this.id = id;
            this.member = new PriorityQueue<>((a, b) -> Integer.compare(b.trust, a.trust));
            this.member.addAll(member);
            repSt = this.member.poll();
        }
    }

    /**
     * 1. 아침
     * 모든 학생의 신앙심을 + 1
     */
    static void morining() {
        for(int x=1; x<n+1; x++) {
            for(int y=1; y<n+1; y++) {
                stMap[x][y].trust++;
            }
        }
    }

    /**
     * 2. 점심
     * 그룹을 묶는다.
     */
}
