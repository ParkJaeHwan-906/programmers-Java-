package Level_2;

import java.util.*;

// 프로그래머스 Lv.2
// 다리를 지나는 트럭
// https://school.programmers.co.kr/learn/courses/30/lessons/42583
public class no_42583 {
    public int solution(int bridge_length, int weight, int[] truck_weights){
        // 다리
        Queue<Integer> bridge = new LinkedList<>();
        int sec = 0;
        int seq = 0;

        while(seq < truck_weights.length){
            if(bridge.isEmpty()){   // 다리에 아무것도 올라가있지 않음 -> 트럭 바로 추가
                bridge.add(truck_weights[seq]);
                weight -= truck_weights[seq++];
            }else{  // 한 개 이상의 트럭이 올라가있는 경우
                if(bridge.size() == bridge_length){ // 다리가 가득 차 있다면
                    // ⚠️ 다리에 있는 차를 통과 시킨 뒤, 다음 차가 올라와야함 ( 시간 카운트 X )
                    weight += bridge.poll();
                    continue;
                }else if(weight - truck_weights[seq] >= 0){ // 무게가 넘지 않는다면 다리에 올라감
                    bridge.add(truck_weights[seq]);
                    weight -= truck_weights[seq++];
                }else{  // 무게가 넘는다면 대기
                    bridge.add(0);
                }
            }
            sec++;
        }

        // 마지막 차가 올라간 뒤 반복문이 종료되기 때문에 다리 길이만큼 시간 필요
        return sec + bridge_length;
    }

    public static void main(String[] args){
        // 트럭이 최대로 올라갈 수 있는 수
        int bridge_length = 2;
        // 다리의 최대 하중
        int weight = 10;
        // 트럭의 무게 리스트
        int[] truck_weights = new int[] {7,4,5,6};

        no_42583 problem = new no_42583();
        System.out.println(problem.solution(bridge_length, weight, truck_weights));
    }
}
