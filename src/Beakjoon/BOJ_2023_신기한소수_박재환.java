package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_2023_신기한소수_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력
        init();

        // 소수의 조합 구하기
        makePrime();

        StringBuilder sb = new StringBuilder();
        for(int prime : pq) {
            sb.append(prime).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int targetLen;   // 목표로 하는 길이
    static PriorityQueue<Integer> pq;    // 소수를 저장한다. ( 오름차순 )
    private static void init() throws IOException {
        targetLen = Integer.parseInt(br.readLine().trim());
        pq = new PriorityQueue<>();
        br.close();
    }

    static final int[] PRIME = {2,3,5,7};
    private static void makePrime() {   // 한자리 소수를 기반으로 소수를 만든다
        for(int number : PRIME) {
            combinationPrime(number, 1);
        }
    }

    // 만들 수 있는 소수의 조합을 구한다.
    private static void combinationPrime(int number, int len) {
        // 기저조건
        if(len == targetLen) {  // 원하는 길이를 달성했다면
            // 소수인지 판별하고 반환한다.
            if(isPrime(number)) pq.offer(number);
            return;
        }

        // 홀수인 1,3,5,7,9 를 숫자 뒤에 붙여가며 소수인지 구별한다.
        // 소수라면 한자리를 더 붙여서 또 확인한다.
        // 홀수를 붙이는 이유는 홀수는 나누어 떨어지지 않기때문이다.
        for(int addNum=1; addNum < 10; addNum+=2) {
            int newNum = number*10 + addNum;

            if(isPrime(newNum)) {   // 해당 자릿수가 소수라면, 다음으로 생성할 수 있는 자릿수를 만든다
                combinationPrime(newNum, len+1);
            }
        }
    }

    /*
    주어진 숫자가 소수인지 판별한다.
    제곱근까지 나누어 떨어지는 수가 있는지 판별하면 보다 빠르게 판별할 수 있다.
     */
    private static boolean isPrime(int num) {
        for(int div=2; div<=Math.sqrt(num); div++) {
            if(num % div == 0) return false;
        }
        // 반복문을 빠져나왔다면 소수이다
        return true;
    }
}
/*
7331은 소수이다
733도 소수이다
73도 소수이다
7도 소수이다.

왼족부터 1,2,3,4 자리 수가 모두 소수이다.

문제 이해
각 자리수가 모두 소수여야하고, 전체 수도 소수여야한다
한자리 소수 : 2,3,5,7
조합을 이용하여 수를 생성하고 소수인지 판별한다
 */