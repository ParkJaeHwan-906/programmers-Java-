package Level_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 프로그래머스 Lv.1
// 덧칠하기
// https://school.programmers.co.kr/learn/courses/30/lessons/161989
public class no_161989 {
    /*
    벽 1미터 길이의 구역 n 개로 나눔, 왼쪽부터 1번 ~ n 번
    벽에 페인트를 칭하는 롤러의 길이는 m 미터

    <주의>
    롤러가 벽을 벗어나면 안됨
    한 번 칠할 때 구역을 모두 포함해야함 (일부만 채우기 불가)

    페인트를 다시 칠해야하는 구역 section
     */
    public int solution(int n, int m, int[] section){
        // section 은 오름차순 정렬
        int sections = section[section.length-1] - section[0] + 1;
        // 최대치
        int answer = 0;
        // 자바 8
//        List<Integer> list = Arrays.stream(section).boxed().collect(Collectors.toList());
//        List<Integer> list = new ArrayList<>();
//        Arrays.stream(section).forEach((e) -> {
//            list.add(e);
//        });

        // 자바 17
        List<Integer> list = Arrays.stream(section).boxed().toList();
        try{
            // 자바 17
            for(int i = list.getFirst(); i <= list.getLast(); i++){
//            for(int i = list.get(0); i <= list.get(list.size()-1); i++){
                if(list.contains(i)) {
                    i += (m-1);
                    answer++;
                }
            }
        } catch (Exception e){
            answer++;
        }
        return answer;
    }

    public static void main(String[] args){
        int n = 8;
        int m = 4;
        int[] section = new int[] {2, 3, 6};

        no_161989 problem = new no_161989();
        System.out.println(problem.solution(n, m, section));
    }
}
