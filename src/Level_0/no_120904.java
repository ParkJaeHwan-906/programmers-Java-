package Level_0;

// 프로그래머스 Level 0
// 숫자 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/120904
public class no_120904 {
    static int solution(int num, int k) {
        return (num+"").indexOf(k+"") >= 0 ? (num+"").indexOf(k+"")+1 : -1;
    }

    public static void main(String[] args){
        int result = solution(29183, 1);
        System.out.println(result);
    }

}
