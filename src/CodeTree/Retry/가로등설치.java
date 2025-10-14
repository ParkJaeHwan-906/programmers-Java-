package CodeTree.Retry;

import java.sql.Array;
import java.util.*;
import java.io.*;

public class 가로등설치 {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(sb);
    }

    static BufferedReader br;
    static StringTokenizer st;
    static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        // ------------------------------
        int commandCnt = Integer.parseInt(br.readLine().trim());
        while(commandCnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int commandType = Integer.parseInt(st.nextToken());
            switch(commandType) {
                case 100:
                    initVillage();
                    break;
                case 200:
                    break;
                case 300:
                    break;
                case 400:
                    break;
            }
        }

        // ------------------------------
        br.close();
    }

    /**
     * 주어지는 명령어에 따라 가로등을 설치한다.
     * 1. 가로등의 개수 M 이 주어진다.
     * 2. 가로등의 위치정보가 주어진다.
     *
     * 📌필요한 정보
     * 1. 거리에 대한 정보
     *      - 시작 가로등
     *      - 끝 가로등
     *      - 사이 거리 ( 젓 번째 우선 순위 )
     *      - 시작 좌표 ( 두 번째 우선 순위 )
     * 2. 기로등에 대한 정보
     *      - id -> 어차피 Index 로 접근
     *      - 위치
     *      - 제거 여부
     *      - 왼쪽 가로등
     *      - 오른쪽 가로등
     */
    static class Road {
        int leftLampId;     // 시작 가로등 위치
        int rightLampId;    // 끝 가로등 위치
        int length;         // 가로등 사이의 거리   ( 우선 순위 1 )
        int startPoint;     // 해당 거리의 시작 좌표 ( 우선 순위 2 )

        public Road(int leftLampId, int rightLampId, int length, int startPoint) {
            this.leftLampId = leftLampId;
            this.rightLampId = rightLampId;
            this.length = length;
            this.startPoint = startPoint;
        }
    }
    static class Lamp {
        int loc;            // 가로등 위치
        boolean removed;    // 제거 여부
        int leftLampId;     // 현재 가로등의 왼쪽에 위치한 가로등 id
        int rightLampId;    // 현재 가로등의 오른쪽에 위치한 가로등 id

        public Lamp(int loc, int leftLampId, int rightLampId) {
            this.loc = loc;
            this.leftLampId = leftLampId;
            this.rightLampId = rightLampId;
            this.removed = false;       // 가로등의 초기 상태는 false;
        }
    }
    static List<Lamp> lamps;                    // 가로등 정보를 저장
    static PriorityQueue<Road> roads;           // 인접한 가로등 사이의 거리에 대한 정보 저장
    static PriorityQueue<int[]> minLampHeap;    // 가장 작은 위치에 있는 가로등 정보를 저장  [id, loc]
    static PriorityQueue<int[]> maxLampHeap;    // 가장 큰 위치에 있는 가로등 정보를 저장   [id, loc]
    static void initVillage() {
        lamps = new ArrayList<>();
        roads = new PriorityQueue<>((a, b) -> {
            // 2. 거리가 같다면, 더 작은 좌표가 우선 순위를 가짐
            if(a.length == b.length) return Integer.compare(a.startPoint, b.startPoint);
            // 1. 거리가 큰 도로가 우선 순위를 가짐
            return Integer.compare(b.length, a.length);
        });
        minLampHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        maxLampHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        // ----------------------------------
        int lampCnt = Integer.parseInt(st.nextToken());
        for(int id=0; id<lampCnt; id++) {
            int loc = Integer.parseInt(st.nextToken());
            // 단순하게 현재 가로등의 이전, 이후 id 값을 제공
            lamps.add(new Lamp(loc, id-1, id+1));

            if(id > 0) {    // 가로등이 2개 이상 존재할 때
                int prevLampId = id-1;
                int prevLampLoc = lamps.get(prevLampId).loc;
                int length = loc - prevLampLoc;
                roads.offer(new Road(prevLampId, id, length, prevLampLoc));
            }

            minLampHeap.offer(new int[] {id, loc});
            maxLampHeap.offer(new int[] {id, loc});
        }
    }

    /**
     * 가로등을 추가한다.
     * 1. 인접 가로등 사이의 거리가 가장 먼 곳의 가운데 가로등을 설치한다.
     * 2. 거리가 같은 경우 좌표 정보가 작은 위치가 우선순위를 갖는다.
     *
     * 📌가로등이 추가된다면, 하나의 거리가 사라지고, 두 개의 새로운 거리가 생긴다.
     *   중간 지점이 2로 나누어 떨어지지 않는다면, 올림처리한다.
     */
    static void addLamp() {

    }
    /**
     * 가로등을 제거한다.
     * 1. D 번 가로등을 제거한다.
     *
     * 📌가로등을 제거했을 때, 하나의 거리가, 두 개의 새로운 거리로 나누어진다.
     */
    static void removeLamp() {

    }

    static void calcMinCost() {

    }
}
