package Level_2;
import java.util.*;

// 프로그래머스 Lv.2
// 주식 가격
// https://school.programmers.co.kr/learn/courses/30/lessons/42584?language=java
public class no_42584 {
    public int[] solution(int[] prices){
        int[] answer = new int[prices.length];
        for(int i = 0; i < prices.length; i++){
            int sec = 0;
            for(int z = i+1; z < prices.length; z++){
                sec++;
                if(prices[i] > prices[z]){  // 가격이 떨어지면 이를 기록하고 종료
                    break;
                }
            }
            answer[i] = sec;
        }

        return answer;
    }

    public static void main(String[] args){
        int[] prices = new int[] {1,2,3,2,3};
        no_42584 problem = new no_42584();
        int[] answer = problem.solution(prices);
        for(int i : answer){
            System.out.print(i+" ");
        }
    }
}
