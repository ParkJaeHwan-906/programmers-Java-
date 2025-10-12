package CodeTree;

import java.util.*;
import java.io.*;

public class 가로등설치 {
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
     * 거리에 가로등을 추가, 조정하려 한다.
     * 1 ~ N 의 직선 좌표로 표현한다.
     * 모든 가로등은 동일한 소비 전력 r 을 사용한다.
     * 각 가로등은 설치된 위치 x 를 기준으로 [x-r, x+r] 거리를 밝힌다.
     *
     * [마을 상태 확인]
     * 거리의 크기 N, 현재 존재하는 가로등의 개수 M과 각 가로등의 위치가 주어진다.
     * 가로등은 좌표 오름차순순으로 주어지며, 주어진 순서대로 1, 2, ..., M 의 번호가 주어진다.
     *
     * [가로등 추가]
     * M+K 번 가로등을 설치하려한다. ( K 는 가로등 추가 명령이 주어진 횟수 )
     * 인접한 가로등 사이의 거리가 가장 먼 곳의 가운데에 새로운 가로등을 설치한다.
     *  - 인접한 가로등의 거리가 가장 먼 곳이 여러개 존재한다면, 좌표값이 작은 가로등을 선택한다.
     * 가운데는 /2 (나누어 떨어지는 경우)
     *
     * [가로등 제거]
     * D 번 가로등을 제거한다.
     * 가로들이 2개 이하일 경우 해당 명령은 주어지지 않는다.
     *
     * [최적 위치 계산]
     * 마을의 거리를 전부 밝히기 위한 최소 소비 전력을 구한다.
     *
     * => 최소 전력 계산 명령이 주어질 때마다 최소 소비 전력 값이 2를 곱해 출력한다.
     */
    static void init() throws IOException {

    }
}
/**
9
100 100 3 3 10 90
400
200
200
400
300 3
400
200
400

80
40
100
100
 */