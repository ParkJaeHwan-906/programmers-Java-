package Level_2;

// 프로그래머스 Level 2
// 마법의 엘리베이터
// https://school.programmers.co.kr/learn/courses/30/lessons/148653
public class no_148653 {
    public int solution(int storey) {
        int answer = 0;
        while(storey > 0){
            int mod = storey % 10;
            storey/= 10;
            if(mod > 5){
                answer += 10 - mod;
                storey++;
            }else{
                answer += mod;
                if(mod == 5 && storey%10+1 > 5){
                    storey++;
                }
            }

//            System.out.println(storey + "      "+ mod);
        }
        return answer;
    }

    /*
    엘리버에터는 절대값이 10^n 의 수로만 움직인다.
    현재 있는 층수에 값을 더한 층으로 이동한다.
    결과가 0보다 작다면 움직이지 않는다.
    0층이 가장 아래층이다.
     */

    /*
    접근 방법
    1의 자리 숫자부터 비교하여 각 자리수가 5를 기준으로 5보다 크면 반올림, 작으면 그냥 내려가기

    ⚠️ mod 값이 5일 경우 그 다음의 mod 값을 보고 5 보다 클 경우 +1 을 더
     */

    public static void main(String[] args){
        no_148653 problem = new no_148653();
        int result = problem.solution(485);
        System.out.println(result);
    }
}
