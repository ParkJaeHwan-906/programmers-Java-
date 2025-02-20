package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 6회 정기 코딩 인증평가 기출] 출퇴근길
// https://softeer.ai/practice/6248
public class no_6248_2 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    static int nodeNum, edgeNum;    // 정점 개수, 간선 개수
    private static void init() {

    }
}
/*
출퇴근길을 단방향 그래프이다.

집 S, 회사 T

목적지의 정점을 방문하고 나면, 더이상 움직이지 않음
-> 출근길에 T 는 마지막에 한 번 등장
-> 퇴근길에 S 또한 마지막에 한 번 등장

출퇴근길에 모두 방문하는 노드의 개수는 ?
 */