package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 4회 정기 코딩 인증평가 기출] 통근버스 출발 순서 검증하기
// https://softeer.ai/practice/6257
public class no_6257 {
    static int n;
    static int[] bus;
    public static void main(String[] args) throws IOException {
        no_6257 problem = new no_6257();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        bus = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            bus[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(problem.solution());
    }

    Queue<Integer> depart = new LinkedList<>();
    public long solution() {
        Stack<Integer> parkingLot = new Stack<>();

        for(int i : bus) {
            System.out.println("현재 버스 : " + i);
            System.out.println("주차장 : " + parkingLot);
            System.out.println("출발 : " + depart);
            while(!parkingLot.isEmpty()) {
                System.out.println("비교 : " + parkingLot.peek());
                if(parkingLot.peek() > i) break;
                depart.offer(parkingLot.pop());
            }
            parkingLot.push(i);
        }


        System.out.println("마지막 주차장 : " + parkingLot);
        while(!parkingLot.isEmpty()){
            depart.offer(parkingLot.pop());
        }
        System.out.println("마지막 출발 : " + depart);
        return isSeq();
    }

    public long isSeq(){
        int pre = depart.poll();

        while(!depart.isEmpty()) {
            System.out.println("이전 : " + pre);

            int now = depart.peek();

            System.out.println("현재 : " + now);
            System.out.println("차 : " + (now - pre));
            if(now - pre != 1) break;
            depart.poll();
            pre = now;
        }


        System.out.println("chlchlchlchlchlchlwhd : "+depart);
        return depart.isEmpty() ? 0 : depart.size() <= 3 ? 1 : combination();
    }

    public long combination(){
        // depart 의 원소 중 순서쌍 (i,j,k) 3개를 뽑는 경우
        int size = depart.size();
        int peek = 3;

        // size C peek
        return factorial(size) / (factorial(peek) * factorial(size-peek));
    }

    public long factorial(int n) {
        if(n == 0 || n == 1) return 1;

        long result = 1;
        for(int i = 2; i <= n; i++){
            result *= i;
        }

        return result;
    }
}
