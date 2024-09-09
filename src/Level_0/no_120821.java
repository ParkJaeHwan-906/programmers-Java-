package Level_0;

// 프로그래머스 Lv.0
// 배열 뒤집기
// https://school.programmers.co.kr/learn/courses/30/lessons/120821
public class no_120821 {
    public int[] solution(int[] num_list){
        int[] result = new int[num_list.length];
        for(int i=0; i<num_list.length; i++){
            result[i] = num_list[num_list.length-1-i];
        }
        return result;
    }

    public static void main(String[] args){
        int[] num_list = {1, 2, 3, 4, 5};
        no_120821 problem = new no_120821();
        int[] result = problem.solution(num_list);
        for(int i : result){
            System.out.print(i+" ");
        }
    }
}
