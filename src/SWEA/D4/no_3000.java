package SWEA.D4;

import java.util.*;
import java.io.*;

// SWEA D4
// 3000. 중간값 구하기
public class no_3000 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int command;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for(int tc=1; tc<=TC; tc++) {
            sb.append('#').append(tc).append(' ');
            sb.append(init()).append('\n');
        }
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static long init() throws IOException {
        PriorityQueue<Integer> minH = new PriorityQueue<>();    // 오름차순 -> 최우선순위값이 중간값이다.
        PriorityQueue<Integer> maxH = new PriorityQueue<>((a,b) -> b-a);    // 내림차순

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        command = Integer.parseInt(st.nextToken());
        int initNum = Integer.parseInt(st.nextToken());

        minH.offer(initNum);    // minH 의 크기가 maxH 보다 커야함
        long answer = 0;

        for(int i=0; i<command; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a > b) {
                minH.offer(a);
                maxH.offer(b);
            } else {
                minH.offer(b);
                maxH.offer(a);
            }

            while(minH.peek() < maxH.peek()) {  // 정확한 정렬상태를 만들기 위함
                // 서로 교환
                maxH.offer(minH.poll());
                minH.offer(maxH.poll());
            }

            answer = (answer + minH.peek())%20171109;
        }

        return answer;
    }
}
