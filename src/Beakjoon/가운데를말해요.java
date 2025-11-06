package Beakjoon;

import java.util.*;
import java.io.*;

public class 가운데를말해요 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }
    static StringBuilder sb;
    static int command;
    // 중간 값을 기준으로
    static PriorityQueue<Integer> left;     // 왼쪽 영역
    static PriorityQueue<Integer> right;    // 오른쪽 영역
    static void init() throws IOException {
        sb = new StringBuilder();
        left = new PriorityQueue<>((a, b) -> Integer.compare(b, a));       // maxHeap -> peek() 값이 중간값이 될 수 있도록
        right = new PriorityQueue<>();                                                   // minHeap
        command = Integer.parseInt(br.readLine().trim());

        while(command --> 0) {
            int num = Integer.parseInt(br.readLine().trim());

            // 1. left 가 비어있다면, 우선적으로 left 에 먼저 삽입
            // 2. 중간값 (left.peek()) 보다 작다면 -> 왼쪽 영역
            if(left.isEmpty() || left.peek() >= num) left.offer(num);
            // 2-1. 중간값보다 크다면 -> 오른쪽 영역
            else right.offer(num);

            // 3. 중간값 보정
            // left 의 크기가, right 보다 항상 크거나 같아야한다.
            // -> 외친 수의 개수가 홀 수 -> 중간값, 외친 수의 개수가 짝수 -> 중간의 두 개의 수 중 작은 수
            if(left.size() < right.size()) {
                int tempNum = right.poll();
                left.offer(tempNum);
            } else if(left.size() > right.size() + 1) {    // left 의 크기가 너무 크면 left.peek() 의 값이 중간 값이 아니게 된다. -> 크기를 일정하게 유지해줘야한다.
                // 너무 전부 다 left 에 몰리는 경우
                int tempNum = left.poll();
                right.offer(tempNum);
            }
//            System.out.println("left : " + left);
//            System.out.println("right : " + right);
//            System.out.println();

            sb.append(left.peek()).append('\n');
        }

        System.out.println(sb);
    }
}
