package Level_3;

import java.util.*;
import java.io.*;

// softeer Lv.3 교차로
// https://chatgpt.com/c/674d183c-88cc-800e-afd1-9017d39dbf32
// 해설 풀이
public class no_6256_answer {
    static class Car {
        int idx;
        int time;

        public Car(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }
    }

    static int n;
    static Queue<Car>[] road = new Queue[4];
    public static void main(String[] args) throws IOException {
        no_6256_answer problem = new no_6256_answer();

        // Queue 배열 초기화
        problem.setDefault();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 도로 진입 시점
            int time = Integer.parseInt(st.nextToken());
            // 도로 위치
            int loc = st.nextToken().charAt(0) - 'A';

            //  ⚠️ 12 시 방향부터 시계방향으로 0, 1, 2, 3 순으로 부여한다.
            //  => (i+3) % 4 로 우측을 확인할 수 있다.
            road[loc].offer(new Car(i, time));
        }
        br.close();

        int[] answer = problem.solution(n);
        for(int i : answer) {
            bw.write(i+"\n");
        }

        bw.flush();
        bw.close();
    }

    public void setDefault() {
        for(int i = 0; i < 4; i++){
            road[i] = new LinkedList<>();
        }
    }

    public int[] solution (int n) {
        int[] answer = new int[n];
        Arrays.fill(answer, -1);

        // 현재 시간을 기록하는 변수
        int cur = -1;

        // 모든 도로의 차량이 통과할 때 까지
        while(!road[0].isEmpty() || !road[1].isEmpty() || !road[2].isEmpty() || !road[3].isEmpty()) {
            int[] state = new int[4];

            int minTime = Integer.MAX_VALUE;
            for(int i = 0; i < 4; i++) {
                // 해당 도로에 차량이 있다면
                if(!road[i].isEmpty()){
                    // 차량이 도로에 진입하는 시간
                    int time = road[i].peek().time;

                    // 차량이 가장 빨리 들어오는 시점을 구한다.
                    minTime = Math.min(minTime, time);

                    if(time <= cur) {
                        state[i] = 1;
                    }
                }
            }

            int count = 0;
            for(int i : state){
                count += i;
            }

            if(count == 0) {
                // 아직 어떤 차량도 교차로에 진입하지 않음
                // 현재 시간을 가장 빨리 들어오는 차량의 시간으로 점프한다.
                cur = minTime;
            } else if(count == 4) {
                // 교착상태
                break;
            } else {
                for(int i = 0; i < 4; i++) {
                    // 현재 방향에서 진행 가능, 오른쪽은 불가능
                    if(state[i] == 1 && state[(i+3)%4] == 0) {
                        answer[road[i].poll().idx] = cur;
                    }
                }
                cur++;
            }
        }

        return answer;
    }
}
