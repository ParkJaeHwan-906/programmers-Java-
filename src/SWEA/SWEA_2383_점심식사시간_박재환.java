package SWEA;

import java.io.*;
import java.util.*;

public class SWEA_2383_점심식사시간_박재환 {
    static StringTokenizer st;
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase <= TC; testCase++) {
            sb.append('#').append(testCase).append(' ');

            init();

            sb.append('\n');
        }
        System.out.println(sb);
    }

    /*
        계단 정보를 저장한다.
        위치와, 길이
     */
    static class Stair {
        int x, y, len;

        public Stair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.len = time;
        }
    }

    static int mapSize, minTime;
    static Stair[] stairs;
    static List<int[]> people;
    static List<Integer> stair1, stair2;
    public static void init() throws Exception {
        mapSize = Integer.parseInt(br.readLine());

        stairs = new Stair[2];
        people = new ArrayList<>();

        int stairIdx = 0;
        for (int i = 0; i < mapSize; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < mapSize; j++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 1) people.add(new int[]{i, j});
                if (value > 1) stairs[stairIdx++] = new Stair(i, j, value);
            }
        }

        stair1 = new ArrayList<>();
        stair2 = new ArrayList<>();
        minTime = Integer.MAX_VALUE;

        selectStair(0);

        sb.append(minTime);
    }

    public static int getMinTime() {
        Collections.sort(stair1);
        Collections.sort(stair2);

        int stairATime = 0;
        int stairBTime = 0;

        Queue<Integer> q = new ArrayDeque<>();
        for (Integer time : stair1) {
            if (q.size() == 3) {
                if (time < q.peek()) {
                    int firstOutTime = q.poll();
                    q.offer(firstOutTime + stairs[0].len);
                } else {
                    q.offer(time + stairs[0].len + 1);
                }
            } else {
                q.offer(time + stairs[0].len + 1);
            }
        }
        while (!q.isEmpty()) stairATime = q.poll();

        q = new ArrayDeque<>();
        for (Integer time : stair2) {
            if (q.size() == 3) {
                if (time < q.peek()) {
                    int firstOutTime = q.poll();
                    q.offer(firstOutTime + stairs[1].len);
                } else {
                    q.offer(time + stairs[1].len + 1);
                }
            } else {
                q.offer(time + stairs[1].len + 1);
            }
        }
        while (!q.isEmpty()) stairBTime = q.poll();

        return Math.max(stairATime, stairBTime);
    }

    /*
        각 계단을 이용할 사람들의 조합을 구한다.

        1 번 계단 이용
        2 번 계단 이용

        2가지
     */
    public static void selectStair(int cnt) {
        if (cnt >= people.size()) {
            minTime = Math.min(minTime, getMinTime());
            return;
        }

        int[] pos = people.get(cnt);

        int weight = Math.abs(pos[0] - stairs[0].x) + Math.abs(pos[1] - stairs[0].y);
        stair1.add(weight);
        selectStair(cnt + 1);
        stair1.remove((Integer)weight);

        weight = Math.abs(pos[0] - stairs[1].x) + Math.abs(pos[1] - stairs[1].y);
        stair2.add(weight);
        selectStair(cnt + 1);
        stair2.remove((Integer)weight);
    }

}
/*
 * 사람 P, 계단 입구 S
 * 이동 완료 : 모든 사람들이 내려감
 *
 * 계단 입구에 도착 후 1분 후에 아래 도착한다.
 * 동시 최대 3명까지 계단에 있을 수 있다.
 *
 * 격자 크기는 N x N 이다.
 * 계단은 반드시 두개이다.
 *
 * 최적 : 인원을 반반 나눠서 보내버리기
 *
 * 3초
 *
 * 계단을 도착한 순으로 내려갈 수 있다. ? 큐 사용 ?
 * wait / stair
 */