package CodeTree.Retry;

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
                    addLamp();
                    break;
                case 300:
                    removeLamp();
                    break;
                case 400:
                    sb.append(calcMinCost()).append('\n');
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
    static List<Integer> lamps;                 // 가로등의 위치 정보를 저장
    static List<Integer> leftLamps;             // 가로등의 이전 위치 가로등 ID를 저장
    static List<Integer> rightLamps;             // 가로등의 이전 위치 가로등 ID를 저장
    static PriorityQueue<Road> roads;           // 인접한 가로등 사이의 거리에 대한 정보 저장
    static PriorityQueue<int[]> minLampHeap;    // 가장 작은 위치에 있는 가로등 정보를 저장  [id, loc]
    static PriorityQueue<int[]> maxLampHeap;    // 가장 큰 위치에 있는 가로등 정보를 저장   [id, loc]
    static int roadLength;                      // 주어지는 도로의 길이
    static void initVillage() {
        lamps = new ArrayList<>();
        leftLamps = new ArrayList<>();
        rightLamps = new ArrayList<>();
        lamps.add(-1);              // 1-based
        leftLamps.add(-1);          // 1-based
        rightLamps.add(-1);         // 1-based
        roads = new PriorityQueue<>((a, b) -> {
            // 2. 거리가 같다면, 더 작은 좌표가 우선 순위를 가짐
            if(a.length == b.length) return Integer.compare(a.startPoint, b.startPoint);
            // 1. 거리가 큰 도로가 우선 순위를 가짐
            return Integer.compare(b.length, a.length);
        });
        minLampHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        maxLampHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        // ----------------------------------
        roadLength = Integer.parseInt(st.nextToken());
        int lampCnt = Integer.parseInt(st.nextToken());
        for(int id=1; id<=lampCnt; id++) {
            int loc = Integer.parseInt(st.nextToken());
            // 단순하게 현재 가로등의 이전, 이후 id 값을 제공
            lamps.add(loc);
            leftLamps.add(id-1);
            rightLamps.add(id+1);
            if(id > 0) {    // 가로등이 2개 이상 존재할 때
                int prevLampId = id-1;
                int prevLampLoc = lamps.get(prevLampId);
                int length = loc - prevLampLoc;
                roads.offer(new Road(prevLampId, id, length, prevLampLoc));
            }

            minLampHeap.offer(new int[] {id, loc});
            maxLampHeap.offer(new int[] {id, loc});
        }
        // 가장 왼쪽 오른쪽 가로등에 대하여 다음 가로등 위치를 -1 로 표시
        leftLamps.set(1, -1);
        rightLamps.set(lampCnt, -1);
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
        // 1. 가장 긴 도로를 선택한다.
        // 가로등이 추가되고, 제거되며 새로운 도로가 생기거나, 사라질 수 있음
        Road road = peekLongestRoad();
        roads.poll();           // 현재 도로는 사라진다.

        int leftLampId = road.leftLampId;
        int rightLampId = road.rightLampId;
        int length = road.length;
        int startPoint = road.startPoint;

        // 2. 중간 지점에 새로운 가로등을 추가한다.
        // 이때 2로 나누어지지 않는 경우 올림처리한다.
        int midLoc = startPoint + (length+1)/2;
        // 2-1. 가로등을 추가한다.
        lamps.add(midLoc);
        int newLampId = lamps.size()-1;
        minLampHeap.add(new int[] {newLampId, midLoc});
        maxLampHeap.add(new int[] {newLampId, midLoc});
        // 2-2. 새로운 도로가 추가된다.
        // leftLamp -> newLamp
        int newLength1 = midLoc - lamps.get(leftLampId);
        roads.offer(new Road(leftLampId, newLampId, newLength1, lamps.get(leftLampId)));
        // newLamp -> rightLamp
        int newLength2 = lamps.get(rightLampId) - midLoc;
        roads.offer(new Road(newLampId, rightLampId, newLength2, midLoc));
        // 2-3. 연결 정보 업데이트
        // 2-3-1. 기존에 있던 가로등 정보 업데이트
        rightLamps.set(leftLampId, newLampId);
        leftLamps.set(rightLampId, newLampId);
        // 2-3-2. 새로운 가로등 정보 업데이트
        leftLamps.add(leftLampId);
        rightLamps.add(rightLampId);
    }
    /**
     * 가로등을 제거한다.
     * 1. D 번 가로등을 제거한다.
     *
     * 📌가로등을 제거했을 때, 하나의 거리가, 두 개의 새로운 거리로 나누어진다.
     */
    static void removeLamp() {
        int removeTargetLampId = Integer.parseInt(st.nextToken());
        // 삭제 처리
        lamps.set(removeTargetLampId, -1);
        // 이전에 있는 가로등들과 연결 해제 처리
        int leftLampId = leftLamps.get(removeTargetLampId);
        int rightLampId = rightLamps.get(removeTargetLampId);
        if(leftLampId != -1) {
            rightLamps.set(leftLampId, rightLampId);
        }
        if(rightLampId != -1) {
            leftLamps.set(rightLampId, leftLampId);
        }
        // 두 개의 거리가 사리자고, 한 개의 거리가 새로 추가된다.
        // 단, 왼쪽과 오른쪽 가로등이 모두 존재한다면!
        if(leftLampId != -1 && rightLampId != -1) {
            int leftLoc = lamps.get(leftLampId);
            int rightLoc = lamps.get(rightLampId);
            int length = rightLoc - leftLoc;
            roads.add(new Road(leftLampId, rightLampId, length, leftLoc));
        }
    }
    /**
     * 최소 전력을 구한다.
     * 1. 가장 왼쪽 가로등의 최소 위치
     * => 1 <= min-r
     * => r <= min-1
     *
     * 2. 가장 오른쪽 가로등의 최대 위치
     * => N >= max + r
     * => r <= N-max
     */
    static long calcMinCost() {
        // 1. 가장 왼쪽 가로등을 구한다.
        int minLampLoc = minLampLoc();
        // 2. 가장 오른쪽 가로등을 구한다.
        int maxLampLoc = maxLampLoc();
        // 3. 가장 긴 도로를 구한다.
        Road road = peekLongestRoad();
        
        long candidateR1 = 2L * (minLampLoc-1);
        long candidateR2 = 2L * (roadLength-maxLampLoc);
        long candidateR3 = road == null ? 0 : road.length;
        
        return Math.max(candidateR1, Math.max(candidateR2, candidateR3));
    }

    /**
     * 공통 함수
     */

    /**
     * 가장 긴 도로를 고른다.
     *
     * 가로등이 추가될 때, 한 개의 도로가 사라지고, 두 개의 도로가 생겨난다.
     * 가로등이 제거 될 때는 두 개의 도로가 사라지고, 한 개의 도로가 생겨난다.
     *
     * 지연 갱신을 사용해서 매번 삽입 삭제 연산을 하지 않고, 필요할 때 필요한 부분만 정리한다.
     */
    static Road peekLongestRoad() {
        while(!roads.isEmpty()) {
            Road road = roads.peek();

            int leftLampId = road.leftLampId;
            int rightLampId = road.rightLampId;
            int length = road.length;
            int startPoint = road.startPoint;
            // 현재 도로가 유효한 도로인지 확인한다.
            if(lamps.get(leftLampId) == -1 || lamps.get(rightLampId) == -1) {
                roads.poll();
                continue;
            }
            break;
        }
        return roads.peek();
    }
    /**
     * 가장 왼쪽의 가로등을 구한다.
     *
     * 이때 lamps 에 remove = true 가 아닌, 첫 번째 가로등을 구한다.
     */
    static int minLampLoc() {
        while(!minLampHeap.isEmpty()) {
            if(lamps.get(minLampHeap.peek()[0]) == -1) {
                minLampHeap.poll();
                continue;
            }
            break;
        }
        return minLampHeap.peek()[1];
    }

    /**
     * 가장 오른쪽 가로등을 구한다.
     *
     * 상동
     */
    static int maxLampLoc() {
        while(!maxLampHeap.isEmpty()) {
            if(lamps.get(maxLampHeap.peek()[0]) == -1) {
                maxLampHeap.poll();
                continue;
            }
            break;
        }
        return maxLampHeap.peek()[1];
    }
}
