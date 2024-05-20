package Level_0;

// 프로그래머스 Level 0
// 다항식 더하기
// https://school.programmers.co.kr/learn/courses/30/lessons/120863
public class no_120863 {
    public String solution(String polynomial){
//        String answer = "";
        int x = 0; int num = 0;
        for(String s : polynomial.split("\\+")){
            String str = s.trim();
            if(str.indexOf("x") >= 0){
                // x
                str = str.replace("x","");
                if(str.isBlank()) x++;
                else x += Integer.parseInt(str);
            }else{
                num += Integer.parseInt(str);
            }
        }
        return (x > 0 ? x > 1 ? x+"x" : "x" : "") + (num > 0 ? x > 0 ? " + "+num : num : "");
    }
    /*
    접근 방법
    더하기만 함
    변수는 x만 존재함
     */
    public static void main(String[] args){
        no_120863 problem = new no_120863();
        String str = problem.solution("3x + 7 + x");
        System.out.println(str);
    }
}
