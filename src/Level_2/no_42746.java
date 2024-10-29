package Level_2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 프로그래머스 Lv.2
// 가장 큰 수
// https://school.programmers.co.kr/learn/courses/30/lessons/42746
public class no_42746 {
    //  ⚠️ DFS 는 런타임에러
//    boolean[] visited;
//    long max = 0;
//    public String solution(int[] numbers){
//        visited = new boolean[numbers.length];
//        dfs(numbers, "0");
//        return max+"";
//    }

    // ⚠️ 성공 -> 속도 2등
//    public String solution(int[] numbers){
//        List<Integer> numbersList = new ArrayList<>();
//        for(int i : numbers){
//            numbersList.add(i);
//        }
//        Collections.sort(numbersList, (e1, e2) -> {
//            String a = String.valueOf(e1);
//            String b = String.valueOf(e2);
//
//            return Integer.parseInt(b+a) - Integer.parseInt(a+b);
//        });
//
//        StringBuilder sb = new StringBuilder();
//        for(Integer i : numbersList){
//            sb.append(i);
//        }
//        String answer = sb.toString();
//
//        return answer.charAt(0) == '0' ? "0" : answer;
//    }

//    public void dfs(int[] numbers, String s){
//        max = Math.max(max, Long.parseLong(s));
//        for(int i = 0; i < numbers.length; i++){
//            if(!visited[i]){
//                visited[i] = true;
//                dfs(numbers, s+String.valueOf(numbers[i]));
//                visited[i] = false;
//            }
//        }
//    }

    //  ✅ 성공 속도 1등
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            arr[i] = numbers[i]+"";
        }
        Arrays.sort(arr, (a,b) -> (b+a).compareTo(a+b));

//        StringBuilder sb = new StringBuilder();
//        for(String s : arr){
//            sb.append(s);
//        }
//
//        String answer = sb.toString();
//
//        return answer.charAt(0) == '0' ? "0" : answer;

        return arr[0].equals("0") ? "0" : String.join("",arr);
    }

    public static void main(String[] args){
        int[] numbers = new int[] {6, 10, 2};

        no_42746 problem = new no_42746();
        System.out.println(problem.solution(numbers));
    }
}
