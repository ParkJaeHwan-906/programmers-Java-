package Level_2;

// 프로그래머스 Lv.2
// 괄호 변환
// https://school.programmers.co.kr/learn/courses/30/lessons/60058
public class no_60058 {
    public String solution(String p){
        // 1, 입력 문자가 빈 문자열이라면, 빈 문자열 반환
        if(p.isEmpty()) return "";

        // 2. 문자열 p 를 균형잡힌 문자열 u 와 v 로 분리
        int left = 0;
        int right = 0;
        int idx = 0;
        for(int i = 0; i < p.length(); i++){
            if(p.charAt(i) == '(') left++;
            else  right++;

            if(left == right){
                idx = i;
                break;
            }
        }
        String u = p.substring(0, idx+1);
        String v = p.substring(idx+1);

        // 4. u 가 올바른 괄호 문자열이 아닌경우
        if(!isCorrect(u)){
            // 4-1. 빈 문자열에 "(" 를 붙인다.
            // 4-2. 문자열 v에 대해 1단계부터 다시 수행한 후 이어붙인다.
            // 4-3. ")" 를 다시 붙인다.
            // 4-4. u 의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙인다.
            return "(" + solution(v) + ")" + reverse(u);
        }
        return u + solution(v);
    }

    // 4-4. u 의 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙인다.
    public String reverse(String u) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < u.length()-1; i++){
            if(u.charAt(i) == '('){
                sb.append(')');
            }else{
                sb.append('(');
            }
        }
        return sb.toString();
    }

    // 3. u 가 올바른 괄호 문자열인지 확인
    public boolean isCorrect(String u){
        int left = 0;
        int right = 0;
        for(int i = 0; i < u.length(); i++){
            if(u.charAt(i) == '(') left++;
            else right++;

            // 닫는 괄호가 더 많아지는 경우 올바른 괄호 문자열이 아니다.
            if(left < right) return false;
        }
        return true;
    }
    public static void main(String[] args){
//        String p = "(()())()";
        String p = ")(";
//        String p = "()))((()";
        no_60058 problem = new no_60058();
        System.out.println(problem.solution(p));
    }

}
