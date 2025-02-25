package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_17281_야구_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        init();

        // 타순 구하기
        allSimulation();

        bw.write(String.valueOf(maxScore));
        bw.flush();
        bw.close();
    }

    static int inning;    // 이닝 수
    static int[][] playerResult;    // 각 선수가 이닝별 얻을 수 있는 결과
    private static void init() throws IOException {
        inning = Integer.parseInt(br.readLine().trim());
        playerResult = new int[inning][10];

        for(int inningIdx=0; inningIdx < inning; inningIdx++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int playerIdx=1; playerIdx<=9; playerIdx++) {
                playerResult[inningIdx][playerIdx] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }

    static int[] playerSeq;    // 타순을 기록한다.
    static boolean[] isUsedPlayer;    // 이미 타순이 정해진 선수인지 확인한다.
    static int maxScore;
    private static void allSimulation() {
        maxScore = Integer.MIN_VALUE;
        playerSeq = new int[10];
        isUsedPlayer = new boolean[10];

        // 4번째 타석은 1번 타자
        playerSeq[4] = 1;
        isUsedPlayer[1] = true;

        makeAllSeq(1);
    }
    // 모든 순열을 구한다.
    // 최대 점수를 찾는다.
    private static void makeAllSeq(int seqIdx) {
        if(seqIdx == 10) {    // 모든 선수의 타순을 결정했다면
            // 점수를 구하고 종료한다.
//            System.out.println(Arrays.toString(playerSeq));
            scorePredict();
            return;
        }

        if(seqIdx == 4) {    // 4번 타석이라면 이미 고정되어있으니 넘어간다.
            makeAllSeq(seqIdx+1);
            return;
        }

        for(int player=2; player<=9; player++) {    // 1번 타자의 타순은 정해져있기 때문
            // 현재 선수의 타순이 이미 정해져 있다면
            if(isUsedPlayer[player]) continue;

            // 아직 정해져있지 않다면
            isUsedPlayer[player] = true;
            playerSeq[seqIdx] = player;    // 타순을 배정해준다.
            makeAllSeq(seqIdx+1);
            isUsedPlayer[player] = false;
        }
    }

    // 현재의 타순으로 점수를 예측한다.
    private static void scorePredict() {
        int score = 0; // 총 점수
        int order = 1; // 1-base 배열로 인덱스 맞춰줌

        for (int i = 0; i < inning; i++) {  // 이닝을 진행한다.
            int[] inningResults = playerResult[i];  // 현재 이닝에서 얻을 수 있는 타수의 점수..?
            int out = 0;    // 아웃
            int[] base = new int[4];    // 베이스를 표현 1-base

            while (out < 3) {
                int batter = playerSeq[order];
                int hit = inningResults[batter];
                order = (order % 9) + 1;

                switch (hit) {
                    case 0:
                        out++;
                        break;
                    case 1:
                        score += base[3];
                        base[3] = base[2];
                        base[2] = base[1];
                        base[1] = 1;
                        break;
                    case 2:
                        score += base[3] + base[2];
                        base[3] = base[1];
                        base[2] = 1;
                        base[1] = 0;
                        break;
                    case 3:
                        score += base[3] + base[2] + base[1];
                        base[3] = 1;
                        base[2] = base[1] = 0;
                        break;
                    case 4:
                        score += base[3] + base[2] + base[1] + 1;
                        Arrays.fill(base, 0);
                        break;
                }
            }
        }
        maxScore = Math.max(maxScore, score);
    }
}
/*
 * N 개의 이닝동안 개임을 진행한다.
 * 한 이닝에 3아웃이 발생하면 이닝 종료 이후 두 팀이 공격과 수비를 바꾼다.
 *
 * 경기 시작전까지 타순을 정해야한다. -> 타순은 이닝이 변경되어도 순서를 유지한다.
 *
 * 선수는 총 9명이 있다. 1번 선수를 4번 타자로 고정한다. 9P8?
 * 각 선수가 각 이닝에서 어떤 결과를 얻는지 알고 있다. 가장 많은 득점을 하는 타순을 찾고 그 때의 득점을 구해보자
 *
 * 순서를 기록할 순열이 필요하다.
 *
 * [Next Permutation 안써도 되..나....ㅎㅎ]
 *
 * 타순의 모든 순열을 계산한다.
 *     a. 1번 타자는 4번 타석이 고정이다.
 *         타순이 배정된 선수를 기록하는 배열과, 타순을 기록하는 배열에 값을 고정시킨다.
 *         순열을 찾으며 해당 차례가 되면 다음으로 넘어간다.
 * => 순열이 생성되면 현재의 순열로 나올 수 있는 점수를 계산한다
 */