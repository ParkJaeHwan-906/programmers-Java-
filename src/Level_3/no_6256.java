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
        // loc : 도로 위치 지정
        // carIdx : 입력 순서 -> 정답 배열 반환 순서
        // time : 차량 진입 시점
        // flag : Deque 의 삽입, 삭제 위치 (첫번째/마지막)
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

        int nowTime = -1;
        // 교차로에 모든 차량이 통과할 때까지 반복
        while (!roadA.isEmpty() || !roadB.isEmpty() || !roadC.isEmpty() || !roadD.isEmpty()) {
            System.out.println("answer : ");
            for(int i : answer){
                System.out.print(i+" ");
            }
            System.out.println();
            // 각 도로의 첫 번째 차량의 진입시간
            int timeA = !roadA.isEmpty() ? roadA.peekFirst().time : Integer.MAX_VALUE;
            int timeB = !roadB.isEmpty() ? roadB.peekFirst().time : Integer.MAX_VALUE;
            int timeC = !roadC.isEmpty() ? roadC.peekFirst().time : Integer.MAX_VALUE;
            int timeD = !roadD.isEmpty() ? roadD.peekFirst().time : Integer.MAX_VALUE;

            // 현 시점(nowTime) 에 진입하는 차량을 기록해둔다.
            if(nowTime == timeA) {
                Car c = roadA.pollFirst();
                pushRoad(c.loc, c.no, nowTime+1, -1);
                continue;
            }
            if(nowTime == timeB) {
                Car c = roadB.pollFirst();
                pushRoad(c.loc, c.no, nowTime+1, -1);
                continue;
            }
            if(nowTime == timeC) {
                Car c = roadC.pollFirst();
                pushRoad(c.loc, c.no, nowTime+1, -1);
                continue;
            }
            if(nowTime == timeD) {
                Car c = roadD.pollFirst();
                pushRoad(c.loc, c.no, nowTime+1, -1);
                continue;
            }

            // 가장 빠른 시점 찾기
            nowTime = findMinTime(timeA, timeB, timeC, timeD);

            // 현재 시간에 진입 가능한 차량을 저장할 리스트
            Deque<Car> nowCar = new ArrayDeque<>();

            // 현 시점(nowTime) 에 진입하는 차량을 기록해둔다.
            if(nowTime == timeA) {
                nowCar.offerFirst(roadA.pollFirst());
            }
            if(nowTime == timeB) {
                nowCar.offerFirst(roadB.pollFirst());
            }
            if(nowTime == timeC) {
                nowCar.offerFirst(roadC.pollFirst());
            }
            if(nowTime == timeD) {
                nowCar.offerFirst(roadD.pollFirst());
            }

            System.out.println("현시간 : "+nowTime);
            System.out.println("nowCar : ");
            for(Car c : nowCar){
                System.out.printf("%s : %d번 차량\n", c.loc, c.no);
            }

            if(nowCar.size() == 4){ // 교착상태
                while(!nowCar.isEmpty()){
                    Car c = nowCar.pollFirst();

                    answer[c.no] = -1;
                }
            } else {    // 차량 통행이 가능하다면
                // 1. 각 우측을 확인해야한다.
                while(!nowCar.isEmpty()) {
                    Car c = nowCar.pollFirst();
                    boolean isOk = true;

                    String right = map.get(c.loc);
                    for(Car car : nowCar) {
//                        ⚠️ 우측에 존재하는 차량을 선행시킨다.

                        String reverseRight = map.get(car.loc);

                        if (car.loc.equals(right)) { // 현재 차량(c)의 우측에 다른 차량(car)이 있는 경우
                            isOk = false;
                            // 현재 차량(c)을 다시 대기열로 이동
                            pushRoad(c.loc, c.no, nowTime + 1, -1);
                            break;
                        } else if (c.loc.equals(reverseRight)) { // 현재 차량(c)이 특정 차량(car)의 우측일 경우
                            nowCar.remove(car); // nowCar에서 제거
                            pushRoad(car.loc, car.no, nowTime + 1, -1); // 특정 차량(car)을 다시 대기열로 이동
                            break;
                        }
                    }

                    // 현재 차량이 다시 대기열로 이동하는 경우
                    if(!isOk){
                        continue;
                    }

                    answer[c.no] = nowTime;
                }
            }

        }

        return answer;
    }


    Map<String, String> map = new HashMap<>();
    public void makeMap() {
        map.put("A", "D");
        map.put("B", "A");
        map.put("C", "B");
        map.put("D", "C");
    }
}
