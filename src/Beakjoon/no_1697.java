package Beakjoon;

import java.util.*;
import java.io.*;

// Baekjoon
// 숨바꼭질
// https://www.acmicpc.net/problem/1697
public class no_1697 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int start, target;
    static int answer;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        answer = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        br.close();
        bw.write(String.valueOf(findSister()));
        bw.flush();
        bw.close();
    }

    static final int MAX = 100000;
    public static int findSister() {
        int[] arr = new int[MAX+1];
        Arrays.fill(arr, MAX);

        if(start >= target) {   // 언니가 동생 보다 앞에 있다면 -> 뒤로밖에 못감
            return start - target;
        }

        for(int i=0; i<start; i++) {    // 언니가 시작점에서 뒤로 가는 경우
            arr[i] = start - i;
        }

        arr[start] = 0;
        for(int i=start+1; i<=target; i++) {
            if(i%2 == 0) {
                arr[i] = Math.min(arr[i/2]+1, arr[i-1]+1);
            } else {
                arr[i] = Math.min(arr[i-1]+1, arr[(i+1)/2] + 2);
            }
        }
        return arr[target];
    }
}
