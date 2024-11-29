package Level_3;

import org.w3c.dom.html.HTMLImageElement;

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

    static Deque<Car> roadA = new ArrayDeque<>();
    static Deque<Car> roadB = new ArrayDeque<>();
    static Deque<Car> roadC = new ArrayDeque<>();
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
        // -1 로 초기화
        Arrays.fill(answer, -1);

        // 현재 시간
        int curTime = 0;

        while(!roadA.isEmpty() || !roadB.isEmpty() || !roadC.isEmpty() || !roadD.isEmpty()) {
            // 각 도로의 첫 번째 차량의 진입시간
            int timeA = roadA.isEmpty() ? Integer.MAX_VALUE : roadA.peek().time;
            int timeB = roadB.isEmpty() ? Integer.MAX_VALUE : roadB.peek().time;
            int timeC = roadC.isEmpty() ? Integer.MAX_VALUE : roadC.peek().time;
            int timeD = roadD.isEmpty() ? Integer.MAX_VALUE : roadD.peek().time;

            Deque<Car> deque = new ArrayDeque<>();
            // 현재 시간에 진입한 차들을 deque에 삽입
            if(curTime == timeA) {
                deque.offerLast(roadA.poll());
            }
            if(curTime == timeB) {
                deque.offerLast(roadB.poll());
            }
            if(curTime == timeC) {
                deque.offerLast(roadC.poll());
            }
            if(curTime == timeD) {
                deque.offerLast(roadD.poll());
            }

            // 현 시간에 아무도 없다면
            if(deque.isEmpty()) {
                curTime = findMinTime(timeA, timeB, timeC, timeD);
                continue;
            } else if(deque.size() == 4) {  // 교착상태라면
                continue;
            }

            // 현 시간에 교차로에 대기중인 차들이 있다면
            // 우측에 차가 있는지 확인
            while(!deque.isEmpty()) {
                // 현재 차량 [번호, 시간, 위치]
                Car car = deque.pollFirst();
                String right = map.get(car.loc);
                String reverseRight = map.get(right);
                boolean b = false;
                for(Car c : deque) {
                    if(c.loc.equals(right) || reverseRight.equals(car.loc)){    // 우측에 차가 있다면
                        b = true;
                        break;
                    }
                }

                if(b){
                    pushRoad(car.loc, car.no, car.time, -1);
                    continue;
                }
                answer[car.no] = car.time;
                curTime++;
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
