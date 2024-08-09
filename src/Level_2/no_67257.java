package Level_2;

import java.util.ArrayList;
import java.util.Arrays;

// 프로그래머스 Lv.2
// 수식 최대화
// https://school.programmers.co.kr/learn/courses/30/lessons/67257
public class no_67257 {
    /*
    3 가지의 연산 문자 (+,-,*) 가 있다.
    같은 우선 순위를 갖지 못한다.
     */
    public long solution(String expression) {
        long answer = 0;
        // 나올 수 있는 연산자의 모든 경우의 수
        String[][] arr = new String[][] {{"+","-","*"},{"+","*","-"},
                                         {"-","+","*"},{"-","*","+"},
                                         {"*","-","+"}, {"*","+","-"}};
        // 수식을 저장하기 위한 리스트 (값, 연산자)
        ArrayList<String> list = new ArrayList<>();
        int start = 0;
        for(int i=0; i < expression.length(); i++){
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*'){
                list.add(expression.substring(start, i));
                list.add(expression.charAt(i)+"");
                start = i+1;
            }
        }
        // 마지막 남은 숫자를 list 에 추가
        list.add(expression.substring(start));

        // 연산자의 우선 순위를 모두 순회하며 비교
        for(int i=0; i<arr.length; i++){
            ArrayList<String> listCopy = new ArrayList<>(list);
            // 연산자는 3개
            for(int j=0; j<3; j++){
                for(int z=0; z< listCopy.size(); z++){
                    if(arr[i][j].equals(listCopy.get(z))){
                        listCopy.set(z-1, calc(listCopy.get(z), listCopy.get(z-1), listCopy.get(z+1)));
                        listCopy.remove(z);
                        listCopy.remove(z);
                        z--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(listCopy.get(0))));
        }
        return answer;
    }

    public String calc(String s, String num1, String num2){
        Long num1L = Long.parseLong(num1);
        Long num2L = Long.parseLong(num2);

        if(s.equals("-")){
            return num1L - num2L+"";
        }else if(s.equals("+")){
            return num1L + num2L+"";
        }
        return num1L * num2L+"";
    }
    public static void main(String[] args){
        no_67257 problem = new no_67257();
        String expression = "100-200*300-500+20";
        System.out.println(problem.solution(expression));
    }
}
