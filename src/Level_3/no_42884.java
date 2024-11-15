package Level_3;

import java.util.Arrays;
import java.util.Stack;

// 프로그래머스 Lv.3
// 단속 카메라
// https://school.programmers.co.kr/learn/courses/30/lessons/42884
public class no_42884 {
    public static void main(String[] args){
        int[][] routes = new int[][]
                {{-20,-15},{-14,-5},{-18,-13},{-5,-3}};

        no_42884 problem = new no_42884();
        System.out.println(problem.solution(routes));
    }

    public int solution(int[][] routes){
        // 진출 지점이 가장 먼 순으로 오름차순 정렬
        Arrays.sort(routes, (a,b) -> a[1]- b[1]);
        /*
        ⚠ 진출을 기준으로 오름차순 정렬 이유 : 가장 빨리 나가는 지점 이전에 들어오는 차들을 한번에 묶을 수 있음, 이후 다음 진출 지점을 기준으로 반복
         */
        // 최솟값
        int camera = -30001;
        int result = 0;

        for(int[] arr : routes){
            int in = arr[0];
            int out = arr[1];

            if(in > camera){
                result++;
                camera = out;
            }
        }

        return result;
    }
}
