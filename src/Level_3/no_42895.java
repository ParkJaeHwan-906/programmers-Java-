package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// ⚠️ 다시 풀어보기
// 프로그래머스 Lv.3
// N 으로 표현
// https://school.programmers.co.kr/learn/courses/30/lessons/42895?language=java
public class no_42895 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int number = Integer.parseInt(br.readLine());

        no_42895 problem = new no_42895();
        System.out.println(problem.solution(N, number));
    }

    public int solution(int N, int number){
        if(N == number) return 1;

        List<Set<Integer>> dp = new ArrayList<>();
        // 8번까지 나올 수 있으므로
        for(int i=0; i<=8; i++){
            dp.add(new HashSet<>());
        }

        // 숫자를 한번만 써서 만들 수 있는 것은 자기 자신 뿐
        dp.get(1).add(N);

        // 숫자를 2개 쓰는 수부터, 최대 8개까지
        for(int i=2; i<=8; i++){

            StringBuilder sb = new StringBuilder();
            // N 을 i 번만큼 사용하기 위해 설정
            for(int j=1; j<=i; j++){
                sb.append(N);
            }

            // N 을 i 번만큼 생으로 사용
            dp.get(i).add(Integer.parseInt(sb.toString()));

            // 사칙연산을 사용해 숫자를 구성한다.
            for(int j=1; j<i; j++){
                // 나올 수 있는 경우의 수를 구함
                int k = i - j;
                for(int num1 : dp.get(j)){
                    for(int num2 : dp.get(k)){
                        dp.get(i).add(num1 + num2);
                        dp.get(i).add(num1 - num2);
                        dp.get(i).add(num1 * num2);
                        if(num2 != 0){
                            dp.get(i).add(num1/ num2);
                        }
                    }
                }
            }
            System.out.println(i);
            System.out.println(dp.get(i));
            System.out.println();
            if(dp.get(i).contains(number)){
                return i;
            }
        }

        return -1;
    }
}
