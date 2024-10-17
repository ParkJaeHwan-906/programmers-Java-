package Level_1;

import java.util.*;
import java.io.*;
// 프로그래머스 Lv.1
// 폰켓몬
// https://school.programmers.co.kr/learn/courses/30/lessons/1845
public class no_1845 {
    /*
    N 마리 중 N/2 마리 가져간다.
    같은 종류는 같은 번호를 가지고 있다.
     */
    public int solution(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }
//        return nums.length/2 > set.size() ? set.size() : nums.length/2;
        return Math.min(nums.length/2, set.size());
    }

    public static void main(String[] args){
        int[] nums = new int[] {3,1,2,3};

        no_1845 problem = new no_1845();
        System.out.println(problem.solution(nums));
    }
}
