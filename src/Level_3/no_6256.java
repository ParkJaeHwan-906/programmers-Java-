package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 3회 정기 코딩 인증평가 기출] 교차로
// https://www.softeer.ai/practice/6256
public class no_6256 {
    static class Car {
        int no;
        int time;
        String loc;

        public Car(int no, int time, String loc) {
            this.no = no;
            this.time = time;
            this.loc = loc;
        }
    }

    // 도로 A
    static Deque<Car> roadA = new ArrayDeque<>();
    // 도로 B
    static Deque<Car> roadB = new ArrayDeque<>();
    // 도로 C
    static Deque<Car> roadC = new ArrayDeque<>();
    // 도로 D
    static Deque<Car> roadD = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        no_6256 problem = new no_6256();

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            // 진입 시점, 위치
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String loc = st.nextToken();
            problem.pushRoad(loc, i, time, 1);
        }
        br.close();
        // 우측 구분
        problem.makeMap();
        int[] answer = problem.solution(n);
        for(int i :answer){
            bw.write(i+"\n");
        }
        bw.flush();
        bw.close();
    }

    // 도로별 차량 진입 기록 [차량 번호, 진입 시점]
    private void pushRoad(String loc, int carIdx, int time, int flag) {
        switch (loc) {
            case "A" :
                if(flag == 1 ) roadA.offerLast(new Car(carIdx, time, loc));
                else roadA.offerFirst(new Car(carIdx, time, loc));
                break;
            case "B" :
                if(flag == 1 ) roadB.offerLast(new Car(carIdx, time, loc));
                else roadB.offerFirst(new Car(carIdx, time, loc));
                break;
            case "C" :
                if(flag == 1 ) roadC.offerLast(new Car(carIdx, time, loc));
                else roadC.offerFirst(new Car(carIdx, time, loc));
                break;
            case "D" :
                if(flag == 1 ) roadD.offerLast(new Car(carIdx, time, loc));
                else roadD.offerFirst(new Car(carIdx, time, loc));
                break;
        }
    }

    private int findMinTime(int timeA, int timeB, int timeC, int timeD){
        return Math.min(timeA, Math.min(timeB, Math.min(timeC, timeD)));
    }


    private int[] solution(int n) {
        int[] answer = new int[n];
        Arrays.fill(answer, -1); // 초기값 -1로 설정

        int curTime = 0; // 현재 시간

        // 교차로에 모든 차량이 통과할 때까지 반복
        while (!roadA.isEmpty() || !roadB.isEmpty() || !roadC.isEmpty() || !roadD.isEmpty()) {
            // 각 도로의 첫 번째 차량의 진입시간
            int timeA = !roadA.isEmpty() ? roadA.peek().time : Integer.MAX_VALUE;
            int timeB = !roadB.isEmpty() ? roadB.peek().time : Integer.MAX_VALUE;
            int timeC = !roadC.isEmpty() ? roadC.peek().time : Integer.MAX_VALUE;
            int timeD = !roadD.isEmpty() ? roadD.peek().time : Integer.MAX_VALUE;

            // 현재 시간에 진입 가능한 차량을 저장할 리스트
            Deque<Car> nowCar = new ArrayDeque<>();

            // 현재 시간에 진입 가능한 차량을 확인
            if (curTime == timeA) nowCar.offerLast(roadA.poll());
            if (curTime == timeB) nowCar.offerLast(roadB.poll());
            if (curTime == timeC) nowCar.offerLast(roadC.poll());
            if (curTime == timeD) nowCar.offerLast(roadD.poll());

            // 현재 시간에 진입 가능한 차량이 없으면 다음 시간으로
            if (nowCar.isEmpty()) {
                curTime = findMinTime(timeA, timeB, timeC, timeD);
                continue;
            }

            // 우측 차량 여부 확인 및 처리
            while (!nowCar.isEmpty()) {
                Car car = nowCar.pollFirst(); // 현재 차량
                String right = map.get(car.loc); // 우측 도로
                Deque<Car> rightQueue = getQueue(right); // 우측 도로 큐

                // 우측 도로에 차량이 없다면 통과
                if (rightQueue.isEmpty() || rightQueue.peek().time > curTime) {
                    answer[car.no] = curTime; // 통과 시간 기록
                    curTime++; // 시간 증가
                } else {
                    // 우측 차량 때문에 대기, 다시 해당 도로로 삽입
                    pushRoad(car.loc, car.no, car.time, -1);
                }
            }
        }

        return answer;
    }

    // 도로 이름으로 큐를 반환하는 메서드
    private Deque<Car> getQueue(String road) {
        switch (road) {
            case "A": return roadA;
            case "B": return roadB;
            case "C": return roadC;
            case "D": return roadD;
        }
        return null;
    }


    Map<String, String> map = new HashMap<>();
    public void makeMap() {
        map.put("A", "D");
        map.put("B", "A");
        map.put("C", "B");
        map.put("D", "C");
    }
}
