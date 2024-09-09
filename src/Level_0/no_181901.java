package Level_0;

// 프로그래머스 Lv.0
// 배열 만들기 1
// https://school.programmers.co.kr/learn/courses/30/lessons/181901
public class no_181901 {
    public int[] solution(int n, int k){
        int[] answer = new int[n/k];
        int idx = 0;
        for(int i=k; i<=n; i+=k){
            answer[idx++] = i;
        }
        return answer;
    }
    public static void main(String[] args){
        int n = 10;
        int k = 3;
        no_181901 problem = new no_181901();
        int[] result = problem.solution(n,k);
        for(int i : result){
            System.out.print(i + " ");
        }
    }
}
