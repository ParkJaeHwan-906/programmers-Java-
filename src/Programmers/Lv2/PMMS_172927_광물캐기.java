package Programmers.Lv2;

import java.util.*;

public class PMMS_172927_광물캐기 {
    public static void main(String[] args) {
//        int[] picks = {1,3,2};
        int[] picks = {0, 1, 1};
//        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        String[] minerals = {"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};

        System.out.println(new PMMS_172927_광물캐기().solution(picks, minerals));
    }

    int[] picks;
    String[] minerals;
    int picksCnt;   // 곡괭이의 개수
    int minHp;      // 최소 피로도
    public int solution(int[] picks, String[] minerals) {
        picksCnt = 0;
        minHp = Integer.MAX_VALUE;
        this.picks = picks;
        this.minerals = minerals;
        for(int i : picks) picksCnt += i;
        // picks -> [dia, iron, stone] : 각 곡괭이의 수
        mineCraft(0,-1,0,0);
        return minHp;
    }


    void mineCraft(int mineralIdx, int pickIdx, int hp, int cnt) {
        if(mineralIdx == minerals.length || mineralIdx == picksCnt*5) {
            /*
                종료 조건
                1. 더 이상 캘 광물이 없는 경우
                2. 더 이상 사용할 곡괭이가 없는 경우
            */
            System.out.println(hp);
            minHp = Math.min(minHp, hp);
            return;
        }

        if(minHp <= hp) return;

        /*
            광물을 캔다.
            - 한 개의 곡괭이는 5개의 광물을 캘 수 있다.
            - 각 곡괭이는 광물에 따라 얻는 피로도가 다르다.
         */
        // 1. 곡괭이가 이미 선택되어 있는 경우
        //      광물을 캘 수 있는 경우
        if(cnt > 0) {
            mineCraft(mineralIdx+1, pickIdx, hp+spendHp(pickIdx, minerals[mineralIdx]), cnt-1);
        }
        // 2. 곡괭이를 선택해야하는 경우
        //      광물을 캘 수 없는 경우
        else {
            for(int i=0; i<3; i++) {
                // 2-1. 해당 곡괭이를 이미 다 사용한 경우
                if(picks[i] == 0) continue;

                picks[i]--;
                // 현재 광물을 하나 캔 것이므로, 총 4 개를 더 캘 수 있음
                mineCraft(mineralIdx+1, i, hp+spendHp(i, minerals[mineralIdx]), 4);
                picks[i]++;
            }
        }
    }

    /*
        현재 곡괭이로, 현재 광물을 캐는데 사용되는 피로도를 반환한다.
     */
    int spendHp(int pickIdx, String mineral) {
        if(pickIdx == 0) return 1;

        int hp;
        if(pickIdx == 1) {      // 철 곡괭이
            switch (mineral) {
                case "diamond":
                    hp = 5;
                    break;
                default:
                    hp = 1;
            }
        } else {                // 돌 곡괭이
            switch (mineral) {
                case "diamond":
                    hp = 25;
                    break;
                case "iron":
                    hp = 5;
                    break;
                default:
                    hp = 1;
            }
        }

        return hp;
    }
}
