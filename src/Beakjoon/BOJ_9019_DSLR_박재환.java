package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_9019_DSLR_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        while(TC-- > 0) {
            init();
            sb.append('\n');
        }
        System.out.println(sb);
    }

    static StringTokenizer st;
    static int origin, target;
    static void init() throws IOException {
        st = new StringTokenizer(br.readLine().trim());
        origin = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        findBestCommand();
    }


    static class Number {
        int num;    // 현재 숫자
        String command; // 명령어
        Number prev;    // 이전 숫자 ( 명령어 기억 )

        public Number(int num, String command, Number prev) {
            this.num = num;
            this.command = command;
            this.prev = prev;
        }
    }
    /*
        origin 을 target 으로 변환하는 최적의 명령어를 찾는다.
        BFS 를 이용한다.

        이전 명령어 추적을 위해 클래스를 정의한다.
     */
    static boolean[] visited;
    static void findBestCommand() {
        visited = new boolean[10000];   // 수의 범위는 0 ~ 9999
        Queue<Number> q = new LinkedList<>();

        // 초기 설정
        q.offer(new Number(origin, "", null));
        visited[origin] = true;

        while(!q.isEmpty()) {
            Number now = q.poll();
            int num = now.num;

            // target 인지 확인
            if(num == target) {
                // 역추적하여 명령어를 확인한다.
                Stack<String> stack = new Stack<>();
                Number cur = now;
                while (cur.prev != null) {
                    stack.push(cur.command);
                    cur = cur.prev;
                }

                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                return;
            }

            // 적용 가능한 연산은 4가지
            int dNum = (num*2)%10000;
            if(!visited[dNum]) {
                q.offer(new Number(dNum, "D", now));
                visited[dNum] = true;
            }
            int sNum = (10000+num-1)%10000;
            if(!visited[sNum]) {
                q.offer(new Number(sNum, "S", now));
                visited[sNum] = true;
            }
            int lNum = (num/1000) + (num%1000)*10;
            if(!visited[lNum]) {
                q.offer(new Number(lNum, "L", now));
                visited[lNum] = true;
            }
            int rNum = (num/10)+(num%10)*1000;
            if(!visited[rNum]) {
                q.offer(new Number(rNum, "R", now));
                visited[rNum] = true;
            }
        }
    }
}

/*
    D : n 을 두 배로 바꾼다. 값이 9999 보다 크다면 10000 으로 나눈 나머지를 취한다.
    S : n 에서 1을 뺀다. 0 - 1 인 경우 9999 를 저장한다.
    L : 각 자릿수를 왼편으로 회전시킨다. 1234 -> 2341
    R : 각 자릿수를 오른쪽으로 회전시킨다. 1234 -> 4123
 */