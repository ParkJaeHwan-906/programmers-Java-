package Level_2;

// 프로그래머스 Lv.2
// 타겟 넘버
// https://school.programmers.co.kr/learn/courses/30/lessons/43165?language=java
public class no_43165 {
    static int n;
    int answer = 0;
    public int solution(int[] numbers, int target){
        n = numbers.length;

        dfs(numbers, 0, target, 0);

        return answer;
    }

    // 순서를 바꾸지 않고, 부호만 바꿔야함
    public void dfs(int[] numbers, int sum, int target, int depth){
        if(depth == n){
            if(sum == target) answer++;
            return;
        }

        dfs(numbers, sum + numbers[depth], target, depth+1);
        dfs(numbers, sum - numbers[depth], target, depth+1);
    }

    public static void main(String[] args){
        int[] numbers = new int[] {1,1,1,1,1};
        int target = 3;

        no_43165 problem = new no_43165();
        System.out.println(problem.solution(numbers,target));
    }
}
