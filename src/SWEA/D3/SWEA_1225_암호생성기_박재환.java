package SWEA.D3;

import java.util.*;
import java.io.*;

public class SWEA_1225_암호생성기_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        sb = new StringBuilder();
        for(int testCase=0; testCase < 10; testCase++) {
            int tc = Integer.parseInt(br.readLine().trim());
            sb.append('#').append(tc).append(' ');

            // 입력 받기
            init();

            // 암호를 생성한다.
            makeCrypto();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int[] cryptoStr;    // 암호문 생성을 위한 입력을 저장할 배열
    private static void init() throws IOException{
        cryptoStr = new int[8]; //  8개의 입력은 고정

        int minValue = Integer.MAX_VALUE;   // 입력받은 숫자 중 가장 작은 값을 찾는다.
        /*
        0 이 되면 프로그램이 종료되므로 -> 가장 작은 값을 기준으로한다.
         */
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for(int idx=0; idx<8; idx++) {
            cryptoStr[idx] = Integer.parseInt(st.nextToken());
            minValue = Math.min(minValue, cryptoStr[idx]);
        }

        if(minValue > 15) { // 최솟값이 15보다 크다면, 15를 나눠서 연산의 횟수를 줄이자
            int count = minValue/15 - 1;    // 사이클의 횟수를 구한다.

            // 모든 값에 사이클을 적용
            for(int idx=0; idx<8; idx++) {
                cryptoStr[idx] -= count*15;
            }
        }
    }

    private static void makeCrypto() {
        // 사이클 작업을 위한 큐 생성
        Queue<Integer> q = new ArrayDeque<>();
        for(int number : cryptoStr) {   // 큐에 값을 넣어준다.
            q.offer(number);
        }

        // 사이클을 순회한다.
        int diff = 1;   // 사이클을 순회하면 값을 빼줄 변수 (1~5)
        while(true){
            int number = q.poll() - diff;  // 첫 번째 숫자를 가져와, diff 를 빼준다

            if(number < 0) number = 0;  // 0 보다 작아지는 경우 0을 유지한다.

            q.offer(number);    // 다시 값을 맨 뒤에 넣어준다.

            if(number == 0) break;  // 종료 조건 충족

            // 사이클을 더 돌아야한다면 diff 값 갱신
            diff = diff%5 + 1;
        }


        // 값을 출력한다.
        while(!q.isEmpty()) {
            sb.append(q.poll()).append(' ');
        }
        sb.append('\n');
    }
}
/*
    N개의 수를 처리하여 8자리의 암호를 생성한다.
    8개의 숫자를 입력 받는다
    첫 숫자를 1 감소한 뒤 맨 뒤로 보낸다
    다음 첫 숫자를 2 감소한 뒤 맨 뒤로 보낸다.
    ... 반복한다

    이때 사이클은 5 간격

    숫자가 감소할 때 0 보다 작아지는 경우는 0으로 유지된다 -> 프로그램 종료
    -> 감소 시키고 맨 뒤로 보내니까 마지막 자리가 0

    감소되는 배열
    [1 2 3 4 5 1 2 3]

    먼저 생각나는 방법
    1. Queue 를 사용해서 직접 쭉 구현한다
    => 주어지는 수의 범위는 int 를 넘지 않는다 -> 최악의 경우 모든 자리수가 21억
    => O(N)

    2. 사이클을 이용해 계산식을 사용한다
    [1,2,3,4,5] => 한 사이클에 15만큼 수가 감소한다. -> 8 사이클을 돌면 초기와 같은 상태가 됨
    =>8사이클을 돌면 각 자리당 15씩 빼주면 될듯?
 */