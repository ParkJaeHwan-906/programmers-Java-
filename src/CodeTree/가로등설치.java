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
    static StringTokenizer st;
    static void init() throws IOException {
        int commandCnt = Integer.parseInt(br.readLine().trim());

        while(commandCnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int commandType = Integer.parseInt(st.nextToken());
            switch(commandType) {
                case 100:       // 단 한번만 주어진다.
                    // [마을 상태 확인]
                    initVillage();
                    break;
                case 200:
                    // [가로등 추가]
                    setLamp();
                    break;
                case 300:
                    // [가로등 제거]
                    removeLamp();
                    break;
                case 400:
                    // [최적 위치 계산]
                    sb.append(calcMinCost()).append('\n');
                    break;
            }
        }
    }
    static class Road {
        int leftLampId;     // 왼쪽 가로등 ID
        int rightLampId;    // 오른쪽 가로등 ID
        int length;         // 도로의 길이
        int startPos;          // 도로의 시작 위치 (왼쪽 가로등 위치)

        public Road(int leftLampId, int rightLampId, int length, int startPos) {
            this.leftLampId = leftLampId;
            this.rightLampId = rightLampId;
            this.length = length;
            this.startPos = startPos;
        }
    }

    /**
     * [마을 상태 확인]
     */
    static ArrayList<Integer> lampPos;          // 각 가로등의 위치 (index를 ID 값으로 사용)
    static ArrayList<Integer> nextLampId;       // 각 가로등의 다음 가로등의 ID
    static ArrayList<Integer> prevLampId;       // 각 가로등의 이전 가로등의 ID
    static PriorityQueue<Road> roads;           // 도로 정보를 저장할 최대 힙
    static PriorityQueue<int[]> lampPosMinHeap; // 가로등 위치를 저장할 최소 힙 [id, pos] (가장 왼쪽 가로등 탐색)
    static PriorityQueue<int[]> lampPosMaxHeap; // 가로등 위치를 저장할 최대 힙 [id, pos] (가장 오른쪽 가로등 탐색)
    static int roadSize;                               // 거리의 크기
    static void initVillage() {
        // 자료구조 초기화
        lampPos = new ArrayList<>();
        nextLampId = new ArrayList<>();
        prevLampId = new ArrayList<>();
        // 1-based 처리
        lampPos.add(-1);
        nextLampId.add(-1);
        prevLampId.add(-1);
        // 인접한 가로등 사이의 거리가 가장 먼 곳의 가운데에 새로운 가로등을 설치한다.
        // 거리가 먼 도로가 우선 순위를 가짐
        roads = new PriorityQueue<>((a, b) -> {
            // 거리가 같다면, 직선상 좌표 위치가 작은 가로등 쌍을 선택한다.
            if(a.length == b.length) return Integer.compare(a.startPos, b.startPos);
            return Integer.compare(b.length, a.length);
        });
        lampPosMinHeap = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
        lampPosMaxHeap = new PriorityQueue<>((a,b)->Integer.compare(b[1], a[1]));
        // -----
        roadSize = Integer.parseInt(st.nextToken());
        int lampSize = Integer.parseInt(st.nextToken());
        for(int id=1; id<=lampSize; id++) {
            int pos = Integer.parseInt(st.nextToken());
            lampPos.add(pos);
            prevLampId.add(id-1);
            nextLampId.add(id+1);

            lampPosMinHeap.add(new int[] {id, pos});
            lampPosMaxHeap.add(new int[] {id, pos});

            // 가로등 사이의 거리 정보 저장
            // id 가 2 이상인 항목들에 대해서 저장
            // id 가 1 인 가로등은 이전 가로등이 없기 때문
            if(id > 1) {
                int prevPos = lampPos.get(id-1);
                int length = pos - prevPos;
                roads.offer(new Road(id-1, id, length, prevPos));
            }
        }
        // 가장 왼쪽 가로등과, 오른쪽 가로등 처리
        prevLampId.set(1, -1);
        nextLampId.set(lampSize, -1);
    }

    /**
     * [가로등 설치]
     */
    static void setLamp() {
        // 1. 가장 긴 도로를 찾는다.
        Road road = getMaxLengthRoad();
        roads.poll();
        // 2. 중간 위치 계산
        // +1 : 홀수/짝수 모두 동일한 값을 내기 위해 -> 정확한 가운데 값을 내기 위해
        int midPos = road.startPos + (road.length+1)/2;
        int newLampId = lampPos.size();

        // 3. 새로운 가로등 추가
        lampPos.add(midPos);
        prevLampId.add(road.leftLampId);
        nextLampId.add(road.rightLampId);
        nextLampId.set(road.leftLampId, newLampId);
        prevLampId.set(road.rightLampId, newLampId);
        lampPosMinHeap.add(new int[] {newLampId, midPos});
        lampPosMaxHeap.add(new int[] {newLampId, midPos});

        // 4. 새로운 가로등과의 거리를 추가
        // 새로 2 개의 도로가 추가됨
        int lengthLeft = midPos - road.startPos;
        int lengthRight = road.startPos + road.length - midPos;
        roads.add(new Road(road.leftLampId, newLampId, lengthLeft, road.startPos));
        roads.add(new Road(newLampId, road.rightLampId, lengthRight, midPos));
    }
    /**
     * [가로등 제거]
     */
    static void removeLamp() {
        int removeTargetId = Integer.parseInt(st.nextToken());
        lampPos.set(removeTargetId, -1);
        // 제거된 위치의 왼쪽 오른쪽 가로등 id 를 가져온다.
        int leftLampId = prevLampId.get(removeTargetId);
        int rightLampId = nextLampId.get(removeTargetId);
        // 왼족 가로등이 존재한다면
        // 오른쪽 가로등과 바로 연결시킨다.
        if(leftLampId != -1) {
            nextLampId.set(leftLampId, rightLampId);
        }
        // 오른쪽 가로등이 존재한다면
        // 왼족 가로등과 바로 연결시킨다.
        if(rightLampId != -1) {
            prevLampId.set(rightLampId, leftLampId);
        }

        // 제거된 가로등의 각 왼쪽과 오른쪽에 있는 가로등의 거리를 추가
        if(leftLampId != -1 && rightLampId != -1) {
            int leftPos = lampPos.get(leftLampId);
            int rightPos = lampPos.get(rightLampId);
            int length = rightPos - leftPos;
            roads.add(new Road(leftLampId, rightLampId, length, leftPos));
        }
    }
    /**
     * 최소 전력 계산
     */
    static long calcMinCost() {
        // 1. 가장 왼쪽 가로등
        int minPos = getMinPos();
        int maxPos = getMaxPos();
        Road road = getMaxLengthRoad();

        long r1 = 2L * (minPos-1);
        long r2 = 2L * (roadSize-maxPos);
        long r3 = road == null ? 0 : road.length;
        // maxRoad 는 그 자체가 2r 이기 때문
        return Math.max(r1, Math.max(r2, r3));
    }
    /**
     * [공통 함수]
     */
    static Road getMaxLengthRoad() {        // 200, 400 명령을 위해 최대 간격의
        while(!roads.isEmpty()) {
            Road road = roads.peek();
            int leftLampId = road.leftLampId;
            int rightLampId = road.rightLampId;
            int length = road.length;
            int startPos = road.startPos;

            // 지연 갱신 로직 반영
            // 최신 정보가 맞는지 확인
            if(lampPos.get(leftLampId) == startPos && lampPos.get(rightLampId) == startPos+length) {
                break;
            }
            roads.poll();
        }
        return roads.peek();    // 200 번에서는 poll(), 400 번에서는 peek() 해야하기 때문
    }

    static int getMinPos() {
        while(!lampPosMinHeap.isEmpty()) {
            int[] lamp = lampPosMinHeap.peek();
            int id = lamp[0];
            int pos = lamp[1];
            if (lampPos.get(id) == pos) break;

            lampPosMinHeap.poll();
        }
        return lampPosMinHeap.peek()[1];
    }

    static int getMaxPos() {
        while(!lampPosMaxHeap.isEmpty()) {
            int[] lamp = lampPosMaxHeap.peek();
            int id = lamp[0];
            int pos = lamp[1];
            if (lampPos.get(id) == pos) break;

            lampPosMaxHeap.poll();
        }
        return lampPosMaxHeap.peek()[1];
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