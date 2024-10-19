package Level_2;

import java.util.HashSet;
import java.util.Set;

// 프로그래머스 Lv.2
// 소수 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/42839
public class no_42839 {
    Set<Integer> set = new HashSet<>();
    public int solution(String numbers){
        boolean[] visited = new boolean[numbers.length()];

//        for(int i = 0; i < numbers.length(); i++){
//            makeEvery(numbers, i+1, "", 0, visited);
//        }

        dfs("", numbers, visited, 0);

        return set.size();
    }

    public void makeEvery(String numbers, int range, String now, int idx, boolean[] visited){
        // 자리수와 길이가 같을 때
        if(range == idx){
            int num = Integer.parseInt(now);
            if(isPrime(num)){
                set.add(num);
                return;
            }
        }

        for(int i = 0; i < numbers.length(); i++){
            if(visited[i]) continue;

            visited[i] = true;
            makeEvery(numbers, range, now+numbers.charAt(i), idx+1, visited);
            visited[i] = false;
        }
    }

    public boolean isPrime(int num){
        if(num == 0 || num == 1) return false;

        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0) return false;
        }
        return true;
    }

    public void dfs(String s, String numbers ,boolean[] visited, int depth){
        if(depth > numbers.length()) return;    // 끝까지 탐색 완료

        for(int i = 0; i < numbers.length(); i++){
            if(visited[i]) continue;

            visited[i] = true;
            int num = Integer.parseInt(s+numbers.charAt(i));
            if(isPrime(num)) set.add(num);
            dfs(s+numbers.charAt(i), numbers, visited, depth+1);
            visited[i] = false;
        }
    }

    public static void main(String[] args){
        String numbers = "17";

        no_42839 problem = new no_42839();
        System.out.println(problem.solution(numbers));
    }
}
