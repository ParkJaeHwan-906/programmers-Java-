package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 4회 정기 코딩 인증평가 기출] 슈퍼컴퓨터 클러스터
// https://softeer.ai/practice/6252
public class no_6252 {

    static class Computer implements Comparable<Computer> {
        int idx;
        long power;

        public Computer(int idx, long power) {
            this.idx = idx;
            this.power = power;
        }

        @Override
        public int compareTo(Computer c){
            return (int) (this.power - c.power);
        }
    }

    static int n;
    static long b;
//    static PriorityQueue<Computer> computer = new PriorityQueue<>();
    static long[] computer
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());

        computer = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
//            computer.offer(new Computer(i, Long.parseLong(st.nextToken())));
            computer[i] = Long.parseLong(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(computer);
    }

    // 5 5 6 1 =? 1 5 5 6
    public int solution() {
        // 가장 성능이 낮은 컴퓨터
        long lowPower = computer[0];

        // 차례로 성능을 비교함
        for(int i=1; i<n; i++){

        }
    }

    private boolean powerUp(int idx){
        long maxPower = computer[idx];

        for(int i=idx-1; i>-1; i--){

        }
    }
}
