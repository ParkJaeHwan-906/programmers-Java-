package CodingTest;

import java.util.*;
import java.io.*;

public class HSAT241206_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int test = Integer.parseInt(br.readLine());

        HSAT241206_3 problem = new HSAT241206_3();
        while(test-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String originPw = st.nextToken();
            String inputPw = st.nextToken();

            bw.write(problem.solution(originPw, inputPw)+"\n");

        }
        br.close();
        bw.flush();
        bw.close();
    }

    public int solution(String originPw, String inputPw){
        // 입력된 비밀번호안에 기존 비밀번호가 있는 경우
        if(inputPw.contains(originPw)) return 0;

        int originPwL = originPw.length();
        int inputPwL = inputPw.length();

        int same = 0;
        // 기존 비밀번호와 일치하는 부분이 얼마나 있는지 확인
        // 조건이 기억이 안나네, 입력된 비밀번호가 무조건 더 긴가?
        for(int i=inputPwL-originPwL >= 0 ? inputPwL-originPwL : 0; i < inputPwL; i++) {
            if(originPw.charAt(same) != inputPw.charAt(i)){ // 같지 않다면
                same = 0;   // 초기화
                continue;
            }
            // 같다면 다음것도 같은지 비교
            same++;
        }
        return originPwL - same;
    }
}
