package Softeer;

import java.util.*;
import java.io.*;
public class Softeer_6256_교차로 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    /*
        각 차량의 진입 위치와 시점이 입력으로 온다.
     */
    static StringTokenizer st;
    static int carCnt;                  // 주어지는 자동차의 수
    static Queue<int[]>[] roads;      // 각 도로별 진입 시점을 기록, 차량 번호화 진입 시간을 기록
    static void init() throws IOException {
        carCnt = Integer.parseInt(br.readLine());
        // 도로는 4개 (A,B,C,D)
        roads = new Queue[4];
        for(int i=0; i<4; i++) {
            roads[i] = new LinkedList<>();
        }

        for(int i=0; i<carCnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int inTime = Integer.parseInt(st.nextToken());
            int roadType = st.nextToken().charAt(0) - 'A';      // A : 0 / B : 1 / C : 2 / D : 3

            roads[roadType].offer(new int[] {i, inTime});
        }

        doSimulation();
    }

    /*
        우측에 차량이 있는지 구분 -> 뒤로 한칸씩 당긴다.
        0 : 3
        1 : 0
        2 : 1
        3 : 2

        -1 + 4 % 4 => + 3 % 4
     */
    static void doSimulation() {
        int[] cars = new int[carCnt];   // 각 차량들이 교차로를 통과하는 시간을 기록한다.
        Arrays.fill(cars, -1);      // 모든 차량이 교착상태를 초기 값으로

        /*
            각 교차로를 순차적으로 탐색하며, 확인한다.
         */
        int time = 0;
        while(!allQueueIsEmpty()) {
            int[] waitCars = new int[4];    // 도로는 4개 -> 한번에 대기할 수 있는 최대 차량의 수는 4대
            // 그 중 가장 빠른 진입 시간 구하기
            int minTime = Integer.MAX_VALUE;
            // 교착상태인가 확인 => 모든 도로에 차량이 대기중인지
            int waitCarCnt = 0;

            for(int i=0; i<4; i++) {
                if(roads[i].isEmpty()) continue;

                // 대기중인 차량이 있다면
                int[] waitCar = roads[i].peek();
                int inTime = waitCar[1];

                minTime = Math.min(minTime, inTime);
                if(time >= inTime) {    // 현 시간 이전 ( 현 시간 포함 ) 에 교차로에 진입한다면
                    waitCars[i] = 1;    // 단순히 차량이 있다는 표시
                    waitCarCnt++;
                }
            }
            // 교착 상태 -> 더 이상 탐색할 필요 없음!
            if(waitCarCnt == 4) break;

            // 차량이 움직일 수 있다.
            if(waitCarCnt == 0) {   // 현 시간에 움직일 수 있는 차량이 한 대도 없음
                time = minTime;     // 시간 점프 -> 탐색 수 줄이기
            } else {
                // 차량이 이동 가능하다.
                // 각 차량의 우측 도로를 확인
                for(int i=0; i<4; i++) {
                    if(waitCars[i] == 0) continue;

                    // 현재 도로에 대기중인 차가 있음
                    // 오른쪽 확인
                    if(waitCars[(i+3)%4] == 0) {    // 오른쪽에 차량이 없다 -> 통과가 가능하다.
                        int[] car = roads[i].poll();

                        cars[car[0]] = time;
                    }
                }

                time++;
            }
        }

        printTrafficLog(cars);
    }

    static void printTrafficLog(int[] cars){
        StringBuilder sb = new StringBuilder();

        for(int i : cars) {
            sb.append(i).append('\n');
        }

        System.out.println(sb);
    }

    // 모든 큐가 비어있는지 확인한다.
    // 모든 큐가 비어있다면 true
    static boolean allQueueIsEmpty() {
        for(int i=0; i<4; i++) {
            if(!roads[i].isEmpty()) return false;
        }

        return true;
    }
}

/*
    교차로에서는 직진만 가능하다.
    효울적인 흐름을 위해 자신의 기준 오른쪽 도로에 차량이 있다면 1초 있다가 출발한다.
    A -> D
    B -> A
    C -> B
    D -> C
 */