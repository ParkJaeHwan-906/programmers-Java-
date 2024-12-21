package Beakjoon.DFS;

import java.util.*;
import java.io.*;

// BAEKJOON
// 신기한 소수
// https://www.acmicpc.net/problem/2023
public class no_2023 {
    static int n;
    static ArrayList<Integer> answer = new ArrayList<>(); // 정답을 저장할 배열
    public static void main(String[] args) throws IOException {
        no_2023 problem = new no_2023();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        br.close();

        problem.solution();
        Collections.sort(answer);

        for(int i : answer){
            bw.write(i+"\n");
        }
        bw.flush();
        bw.close();
    }

    int[] prime = new int[] {2,3,5,7};
    public void solution() {
        // 소수를 생성한다.
        for(int i : prime) {
            makePrime(i, 1);
        }
    }

    // 소수를 생성하고, 길이 ( length ) 가 n 일때 최종 소수 판별하여 저장한다.
    private void makePrime(int num, int length) {
        if(length == n) {   // 목표 길이에 도달했을 경우
            // 소수 판별 후 저장
            if(isPrime(num)) answer.add(num);
            return;
        }

        for(int i = 1; i < 10; i+=2) {  // 짝수가 뒤에 오는 경우 2로 나누어 지므로, 홀수만 붙인다.
            int newNum = num*10 + i;

            if(isPrime(newNum)){    // 소수인지 판별 후, 다시 소수로 만든다.
                makePrime(newNum, length+1);
            }
        }
    }

    private boolean isPrime(int num) {
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}
