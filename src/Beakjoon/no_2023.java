package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// 신기한 소수
// https://www.acmicpc.net/problem/2023
public class no_2023 {

    static int[] prime = {2,3,5,7};
    static ArrayList<Integer> list = new ArrayList<>();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        // ⚠️ 왼쪽부터 1자리, 2자리, 3자리, 4자리 수 모두 소수이다
        // 일의 자리는 모두 홀수이다. (짝수면 2로 나누어떨어지니 소수가 아님)
        no_2023 problem = new no_2023();

        for(int i : prime) {
            problem.makePrime(i,1);
        }

        Collections.sort(list);
        for(int i : list){
            bw.write(i+"\n");
        }
        bw.flush();
        bw.close();
    }

    public void makePrime(int num, int length){
        if(length == n){
            int answer = num;
            // 소수 판별
            while(num > 0) {
                if(!isPrime(num)){
                    return;
                }
                num /= 10;
                list.add(answer);
            }

        } else{
            for(int i=1; i<10; i+=2){
                makePrime(num*10+i, length+1);
            }
        }
    }

    public boolean isPrime(int num) {
        for(int i=2; i*i <= num; i++){
            if(num%i == 0) return false;
        }
        return true;
    }
}
