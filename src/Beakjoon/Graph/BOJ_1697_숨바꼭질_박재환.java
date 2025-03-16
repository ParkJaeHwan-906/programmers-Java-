package Beakjoon.Graph;

import java.util.*;
import java.io.*;

public class BOJ_1697_숨바꼭질_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        bw.flush();
        bw.close();
    }

    static int subin, sister;   // 수빈, 동생 초기 위치
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        subin = Integer.parseInt(st.nextToken());
        sister = Integer.parseInt(st.nextToken());

        bw.write(String.valueOf(findSister()));
    }

    static int findSister() {
        int[] arr = new int[100001];
        Arrays.fill(arr, 100000);   // 최악의 경우 언니는 0, 동생은 100000의 위치에 있음

        if(subin >= sister) {   // 수빈이가 동생보다 앞에 있다면
            // 이동은 걸어서 +1 -1
            // 순간이동 *2
            // 즉 뒤로는 걸어서 밖에 못간다.
            return subin-sister;
        }

        for(int loc=0; loc<subin; loc++) {  // 뒤로 이동하는 경우 시간을 구해둠
            arr[loc] = subin-loc;
        }

        arr[subin] = 0; // 수빈이의 초기 위치 설정
        for(int start = subin+1; start <= sister; start++) {    // 동생의 위치까지 이동
            if(start % 2 == 0) {    // 순간 이동으로 올 수 있는 위치인 경우
                arr[start] = Math.min(arr[start-1]+1, arr[start/2]+1);
            } else {    //  순간이동으로 올 수 없는 경우
                arr[start] = Math.min(arr[start-1]+1, arr[(start+1)/2]+2);
            }
        }

        return arr[sister];
    }
}
/*
    수빈이의 위치는 n
    동생의 위치는 k

    수빈이는 걷거나 순간이동이 가능하다.

    수빈이의 위치가 X
    걷는다면 X-1, X+1 로 이동 가능
    순간이동한다면 2X 위치로 이동 가능

 */