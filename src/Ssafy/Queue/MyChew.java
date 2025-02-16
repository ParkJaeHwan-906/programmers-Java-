package Ssafy.Queue;

import java.util.*;

public class MyChew {
    public static void main(String[] args) {
        int maxChew = 20;   // 나눠줄 수 있는 마이쮸의 수

        Queue<People> statndingLine = new ArrayDeque<>();
        int peopleIdx = 1;  // 사람들 번호를 기록
        while(true) {
            if(!statndingLine.isEmpty()) {
                People nowPeople = statndingLine.poll();
                maxChew -= nowPeople.chew;  // 현재 사람한테 마이쮸를 준다.
                nowPeople.chew = nowPeople.chew+1;  // 또 서면 하나 더 받는다.

                if(maxChew <= 0) {  // 마지막 마이쮸를 가져감
                    System.out.println(nowPeople.idx);
                    return;
                }

                statndingLine.offer(nowPeople);
            }

            statndingLine.offer(new People(peopleIdx++));   // 사람을 줄세움

            // 검증 출력
//            for(People p : statndingLine) {
//                System.out.println(p.toString());
//            }
//
//            System.out.println();
        }
    }
}

class People {
    int idx;    // 사람 번호
    int chew;   // 받을 마이쮸의 수

    public People(int idx) {
        this.idx = idx;
        this.chew = 1;  // 처음 받을 마이쮸의 개수를 초기화한다.
    }
//
//    @Override
//    public String toString() {
//        return idx+" : "+chew;
//    }
}
/*
1번이 줄을 선다.
1번이 한 개의 마이쮸를 받는다.

1번이 다시 줄을 선다.
새로 2번이 들어와 줄을 선다.

1번이 2개의 마이쮸를 받는다.
1번이 다시 줄을 선다.
새로 3번이 들어와 줄을 선다.

... 반복 ...

줄을 새로 설 때마다 마이쮸를 1개씩 더 받는다.
 */