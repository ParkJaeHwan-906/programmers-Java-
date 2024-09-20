package Level_2;

// 프로그래머스 Lv.2
// 멀쩡한 사각형
// https://school.programmers.co.kr/learn/courses/30/lessons/62048?language=java
public class no_62048 {
/*
대각선으로 잘랐을 때, 영역이 나누어지는 부분을 체크

체크 되지 않은 부분의 개수를 반환
 */
    public long solution(int w, int h){
        long answer = (long) w * h;
        int gcd = gcd(w,h);
        return answer - (gcd * ((w/gcd) + (h/gcd) -1));
    }

    private int gcd(int n, int m){  // 최대 공약수를 구한다.
        int max = Math.max(n,m);
        int min = Math.min(n,m);

        int r = 0;
        while(min > 0){
            r = max % min;
            max = min;
            min = r;
        }
        return max;
    }

    public static void main(String[] args){
        // 가로
        int W = 8;
        // 세로
        int H = 12;
        // result = 80 (w + w) => 96 - 16 = 80
        no_62048 problem = new no_62048();
        System.out.println(problem.solution(W,H));
    }
}
