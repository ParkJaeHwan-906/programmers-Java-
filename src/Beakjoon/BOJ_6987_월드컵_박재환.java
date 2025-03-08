package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_6987_월드컵_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        init();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    static int[][] articles;    // 기자가 보내온 각 나라의 승 무 패 의 결과 저장
    static boolean isPossible;
    static void init() throws IOException{
        for(int testCase=0; testCase<4; testCase++) {
            articles = new int[6][3];
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            isPossible = false;
            int totalMatches = 0;   // 한 개의 조에서 나올 수 있는 모든 경기의 수를 계산한다. (30 을 넘을 수 없다)
            for(int team=0; team<6; team++) {
                for(int result=0; result<3; result++) {
                    articles[team][result] = Integer.parseInt(st.nextToken());
                    totalMatches += articles[team][result];
                }
            }

            // 한 조에 대한 입력을 마쳤을 때
            if(totalMatches != 30) {    // 절대 나올 수 없는 경우 (15 경기씩 * 2)
                sb.append(0).append(' ');
                continue;
            }

            playGame(0);

            sb.append(isPossible ? '1' : '0').append(' ');
        }
        br.close();
    }

    // 경기의 모든 경우를 구해놓음
    static int[][] teamMatches = {
            {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
            {1, 2}, {1, 3}, {1, 4}, {1, 5},
            {2, 3}, {2, 4}, {2, 5},
            {3, 4}, {3, 5},
            {4, 5}
    };
    static void playGame(int stageIdx) {
        if(stageIdx == 15) {    // 모든 경기를 끝냄
            isPossible = true;
            return;
        }
        // 현재 진행되는 게임에 팀 정보
        int teamA = teamMatches[stageIdx][0];
        int teamB = teamMatches[stageIdx][1];

        // [승, 무, 패]
        // A 승
        if(articles[teamA][0] > 0 && articles[teamB][2] > 0) {
            articles[teamA][0]--;
            articles[teamB][2]--;
            playGame(stageIdx+1);
            articles[teamA][0]++;
            articles[teamB][2]++;
        }
        // 무승부
        if(articles[teamA][1] > 0 && articles[teamB][1] > 0) {
            articles[teamA][1]--;
            articles[teamB][1]--;
            playGame(stageIdx+1);
            articles[teamA][1]++;
            articles[teamB][1]++;
        }
        // B 승
        if(articles[teamA][2] > 0 && articles[teamB][0] > 0) {
            articles[teamA][2]--;
            articles[teamB][0]--;
            playGame(stageIdx+1);
            articles[teamA][2]++;
            articles[teamB][0]++;
        }
    }
}

/*
    6개국으로 구성된 각 조별로, 동일한 조에 소속된 국가들과 한번씩 -> 각 국가별로 총 5번의 경기를 치른다.

    각 나라의 [승, 무승부, 패] 의 수가 가능한 결과인지 판별한다.

    [입력]
    5 0 0  3 0 2  2 0 3  0 0 5  4 0 1  1 0 4
    4 1 0  3 0 2  4 1 0  1 1 3  0 0 5  1 1 3
    5 0 0  4 0 1  2 2 1  2 0 3  1 0 4  0 0 5
    5 0 0  3 1 1  2 1 2  2 0 3  0 0 5  1 0 4
    각 줄은 각 나라별루 승 무 패 를 나열한 것 -> 임의로 공백으로 구분

    모든 경우를 구한다면?
    각 나라별로 승 무 패 -> 3가지의 경우를 가질 수 있음, 총 15번의 경기가 있음 3^15 인가?

    한 번 경기한 팀과는 다시 경기하지 않는다.

    1. 모든 경기의 경우의 수를 다 구하고 대입한다?
    2. 백트래킹을 이용하여 나올 수 있는 경우인지 탐색한다.
        - 모든 경기에 대한 결과를 재귀적으로 탐색함
        - 승 무 패 의 경우를 하나하나 대입시켜가며 탐색한다
        - boolean 타입의 변수를 주어 조합이 가능한지 불가한지 탐색 후 boolean 타입 값을 바탕으로 1 또는 0을 출력한다.
 */