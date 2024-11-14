package Level_2;

import java.util.*;
import java.io.*;
// 프로그래머스 Lv.2
// 조이스틱
// https://school.programmers.co.kr/learn/courses/30/lessons/42860
public class no_42860 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();

        no_42860 problem = new no_42860();
        System.out.println(problem.solution(name));
    }

    /*
    조이스틱을 움직여 이름을 완성한다.
    이름의 초기값은 A
    ↑ : 다음 알파벳
    ↓ : 이전 알파벳
    ← : 커서를 왼쪽으로 이동
    → : 커서를 오른쪽으로 이동
     */
    public int solution(String name){
        int leftRight = moveLR(name);
        int upDown = moveUD(name);

        return leftRight + upDown;
    }

    private int moveLR(String name){
        int result = 0;

        for(char c : name.toCharArray()){
            int left = 'Z' - c + 1;
            int right = c - 'A';

            result += Math.min(left, right);
        }
        return result;
    }

    private int moveUD(String name){
        int result = name.length() - 1; // 정방향으로 끝까지 이동한 경우

        for(int i=0; i<name.length(); i++){
            int idx = i + 1;

            while(idx < name.length() && name.charAt(idx) == 'A'){
                idx++;
            }

            // 정방향 -> 역방향
            int rightMove = (i*2) + name.length() - idx;
            // 역방향 -> 정방향
            int leftMove = (name.length() - idx) * 2 + i;

            result = Math.min(result, Math.min(rightMove, leftMove));
        }

        return result;
    }
}

// a : 97 - z : 122
// A : 65 - Z : 90