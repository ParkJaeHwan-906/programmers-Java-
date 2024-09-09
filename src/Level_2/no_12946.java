package Level_2;

import java.util.ArrayList;
import java.util.List;

// 프로그래머스 Lv.2
// 하노이의 탑
// https://school.programmers.co.kr/learn/courses/30/lessons/12946
public class no_12946 {
    /*
    하노이의 탑
    ✅ 하노이의 탑을 옮기혀면 원반을 모두 2^n - 1 만큼 옮겨야한다.

    <문제 조건>
    3개의 기둥이 존재한다.
    한 번에 하나의 원판만 이동한다.
    큰 원판이 작은 원판 위에 있을 수 없다.
     */
    List<int[]> list = new ArrayList<>();
    public int[][] solution(int n){
        hanoi(n,1,3,2);

        int[][] answer = new int[list.size()][2];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
    private int count = 0;
    private int hanoi(int n, int from, int to, int sub){
        if(n == 0) return 0;
        hanoi(n-1, from, sub, to);
        System.out.printf("%d 번 원판을 %d 에서 %d 로 옮긴다.\n", n,from,to);
        list.add(new int[] {from,to});
        count++;
        hanoi(n-1, sub, to, from);
        return count;
    }
    public static void main(String[] args){
        int n = 2;
        no_12946 problem = new no_12946();
//        System.out.println(problem.hanoi(n,1,3,2));
        int[][] answer = problem.solution(n);
        for(int[] i : answer){
            System.out.println(i[0]+","+i[1]);
        }
    }
}
