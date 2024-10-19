package Level_1;

import java.util.*;

// 프로그래머스 Lv.1
// 같은 숫자는 싫어
// https://school.programmers.co.kr/learn/courses/30/lessons/12906
public class no_12906 {
    public int[] solution(int[] arr){
        Stack<Integer> st = new Stack<>();
        // 배열에서 연속적으로 나타나는 숫자 제거
        for(int i : arr){
            // 큐가 비어있지 않고, 연속된 수라면 넘어간다.
            if(!st.isEmpty() && st.peek() == i){
                continue;
            }
            st.push(i);
        }
        return st.stream().mapToInt(i->i).toArray();
    }
    
    //  ⚠️ 이게 더 빠름 list 가 속도는 젤 빠름
    public int[] solution2(int[] arr){
        List<Integer> list = new ArrayList<>();
        // 배열에서 연속적으로 나타나는 숫자 제거
        for(int i : arr){
            // 큐가 비어있지 않고, 연속된 수라면 넘어간다.
//            if(!list.isEmpty() && list.getLast() == i){
            if(!list.isEmpty() && list.get(list.size()-1) == i){
                continue;
            }
            list.add(i);
        }
        return list.stream().mapToInt(i->i).toArray();
    }

    // 배열 전체 중복 없게
//        Set<Integer> set = new HashSet<>();
//        Arrays.stream(arr).forEach(e -> {
//            set.add(e);
//        });
//        return set.stream().mapToInt(i -> i).toArray();

    public static void main(String[] args){
        int[] arr = new int[] {1,1,3,3,0,1,1};

        no_12906 problem = new no_12906();
        int[] answer = problem.solution2(arr);

        Arrays.stream(answer).forEach(e -> {
            System.out.print(e+" ");
        });
    }
}
