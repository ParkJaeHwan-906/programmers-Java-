package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 프로그래머스 Lv.2
// 구명보트
// https://school.programmers.co.kr/learn/courses/30/lessons/42885?language=java
public class no_42885 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int[] people = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            people[i] = Integer.parseInt(arr[i]);
        }
        int limit = Integer.parseInt(br.readLine());

        no_42885 problem = new no_42885();
        System.out.println(problem.solution(people, limit));
    }

    // ⚠️ 무거운 사람과 가벼운 사람을 짝지어 보내는 것이 가장 효율적임
    public int solution(int[] people, int limit){
        // 사람들을 무게가 가벼운 순으로 오름차순 정렬한다.
        Arrays.sort(people);

        // 투포인터 사용
        // 가장 무게가 적은 사람
        int left = 0;
        // 가장 무게가 큰 사람
        int right = people.length - 1;

        // 횟수 저장
        int count = 0;

        // 무게가 가장 적은 사람과, 큰 사람을 묶어본다.
        // 제한이 넘지 않으면 둘을 짝지어 보낸다.
        // 넘으면 무거운 사람만 보낸다.
        while(left <= right){
            // 최대 두 사람까지만 태워 보낼 수 있으므로 두명만 계산
            int weight = people[left] + people[right];

            if(weight <= limit){    // 무게가 최대 제한을 넘지 않는 경우
                left++; right--;
                count++;
            }else { // 무게가 최대 제한을 넘는 경우 -> ⚠️ 무거운 사람만 태워 보낸다.
                right--;
                count++;
            }
        }
        return count;
    }
}
