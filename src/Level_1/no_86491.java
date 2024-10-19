package Level_1;

// 프로그래머스 Lv.1
// 최소직사각형
// https://school.programmers.co.kr/learn/courses/30/lessons/86491
public class no_86491 {
    public int solution(int[][] sizes){
        int w = 0;
        int h = 0;

        for(int[] arr : sizes){
            int max = Math.max(arr[0], arr[1]);
            int min = Math.min(arr[0], arr[1]);
            w = Math.max(w, max);
            h = Math.max(h, min);
        }

        return w*h;
    }

    public static void main(String[] args){
        int[][] sizes = new int[][] {{60, 50},{30, 70},{60, 30},{80, 40}};
        no_86491 problem = new no_86491();
        System.out.println(problem.solution(sizes));
    }
}
/*
[[60, 50], [30, 70], [60, 30], [80, 40]]	4000
60 70 60 80
50 30 30 40
80 50

[[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]]	120
10 12 15 14 15
7 3 8 7 5
15 8

[[14, 4], [19, 6], [6, 16], [18, 7], [7, 11]]	133
14 19 16 18 11
4 6 6 7 7
19 7
 */