package SWEA.D5;

import java.io.*;

public class SWEA_6782_현주가좋아하는제곱근놀이_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase<=TC; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();

        br.close();
    }

    static long number; // 2 로 만들고자하는 수
    static long cnt;    // 연산 횟수
    static void init() throws IOException {
        cnt = 0;
        number = Long.parseLong(br.readLine().trim());
        sqrtGame();
        sb.append(cnt);
    }

    static void sqrtGame() {
        while(number != 2) {    // 2 가 될때까지 연산
            long sqrt = (long) Math.sqrt(number);

            if(number == sqrt*sqrt) {   // 딱 떨어지는 경우
                cnt++;
                number = sqrt;
            } else {    // 딱 떨어지지 않음
                // 이럴때는 딱 떨어지도록 올려줘야함 -> 여기서 연산을 한번에 줄일 수 있음
                // 다음으로 가장 큰 제곱근으로 만들어줘야함
                long nextNumber = (sqrt+1) * (sqrt+1);
                // number 에서 nextNumber 로 변환하기 위한 연산과, 이를 제곱근으로 변환해주는 연산이 필요
                cnt+= (nextNumber - number + 1);
                number = sqrt+1;
            }
        }
    }
}

/*
    2 이상의 어떤 정수 N 이 있다.
    N 을 N+1 로 바꿀 수 있다. -> 옹 이걸로 제곱근으로 만들어버리면 될듯
    제곱근이 정수라면 N 을 제곱근으로 바꿀 수 있다.

    게임의 목표는 N을 2로 만드는 것
    N 을 2로 만들기 위해 조작해야하는 횟수의 최솟값은?

    N 이 10^12 ???????? ㅋㅋ 엥 1조?

    그냥 돌리면 터질듯
    최대한 제곱근 연산을 많이하는게 좋을거같음
    타입도 long 으로 해야할듯


 */