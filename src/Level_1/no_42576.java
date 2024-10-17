package Level_1;

import java.util.*;
import java.io.*;

// 프로그래머스 LV.1
// 완주하지 못한 선수
// https://school.programmers.co.kr/learn/courses/30/lessons/42576
public class no_42576 {
    public String solution(String[] participant, String[] completion){
        Map<String, Integer> map = new HashMap<>();
        for(String s : participant){
            map.put(s, map.getOrDefault(s,0)+1);
        }

        for(String s : completion){
            map.put(s, map.get(s)-1);
        }

        for(String s : map.keySet()){
            if(map.get(s) != 0) return s;
        }
        return "";
    }


    // 이게 더 느림
    public String solution2(String[] participant, String[] completion){
        Arrays.sort(participant);
        Arrays.sort(completion);

        for(int i=0; i<completion.length; i++){
            if(!participant[i].equals(completion[i])) return participant[i];
        }

        return participant[participant.length-1];
    }

    public static void main(String[] args){
        String[] participant = new String[] {"leo", "kiki", "eden"};
        String[] completion = new String[] {"eden", "kiki"};

        no_42576 problem = new no_42576();
        System.out.println(problem.solution(participant, completion));
        System.out.println(problem.solution2(participant, completion));
    }
}
