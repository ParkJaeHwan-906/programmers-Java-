package CodeTree;

import java.util.*;
import java.io.*;

public class 코드트리등산게임 {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(sb);
    }
    /**
     * 산들이 연속적으로 있다.
     * 각 산은 높이를 가진다.
     *
     * [등산가의 이동 조건]
     * - 매 등산 시뮬레이션마다 등산을 통한 산 사이 이동은
     * 현재 위치보다 오른쪽에 위치한 산으로만 이동할 수 있다.
     * - 등산을 통한 산과 산 사이 이동에 있어서 항상 현재 산의 높이보다
     * 더 높은 산으로만 이동이 가능하다.
     *
     * -> 현 위치보다 오른쪽에 있고, 높은 산으로만 이동 가능하다.
     *
     * [케이블 카]
     * - 케이블 카는 특정 산에서만 탈 수 있다.
     * - 케이블 카를 타면 현재 위치를 포함한 임의의 산으로 이동할 수 있다.
     * 이때 산의 높낮이는 상관없다.
     *
     * [등산 시뮬레이션]
     * - 등산가는 시뮬레이션을 시작할 산을 자유롭게 선택할 수 있다.
     * - 등산가가 현재 위치보다 오른쪽 산으로 오르막 이동에 성공할 때마다
     * 1_000_000 점을 얻는다.
     * - 케이블 카를 탈 수 있는 산에 도착한다면 케이블 카를 이용한다.
     * 이때 케이블 카 이용에 성공하면 1_000_000 점을 얻는다.
     * - 3에서 케이블 카 이용에 성공했다면, 이후 다시 등산을 시작한다.
     * 이 또한 성공할때마다 1_000_000 점을 얻는다.
     * - 최종적으로 위치한 산의 높이 만큼 점수를 얻는다.
     *
     * ---
     *
     * [명령의 종류]
     * - 빅뱅
     * - 우공이산
     * - 지진
     * - 등산 시뮬레이션
     */
    static BufferedReader br;
    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        br.close();
    }
}
