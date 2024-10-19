package Level_2;

// 프로그래머스 Lv.2
// 카펫
// https://school.programmers.co.kr/learn/courses/30/lessons/42842
public class no_42842 {
    // 카펫의 가로 길이는 세로 길이와 같거나 길다
    public int[] solution(int brown, int yellow){
        int[] answer = {};

        for(int w = 3; w < brown; w++){
            for(int h = 3; h <= w; h++){
                int countBrown = (w*2)+(h-2)*2;
                int countYellow = (w-2)*(h-2);
                if(countYellow == yellow && countBrown == brown){
                    return new int[] {w,h};
                }
            }
        }
        return answer;
    }

    public static void main(String[] args){
        int brown = 10;
        int yellow = 2;

        no_42842 problem = new no_42842();
        int[] answer = problem.solution(brown, yellow);
        for(int i : answer){
            System.out.print(i + " ");
        }
    }
}
