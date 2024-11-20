package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

// Softeer Lv.2
// 8단 변속기
// https://www.softeer.ai/practice/6283
public class no_6283 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dct = new int[st.countTokens()];
        for(int i=0; i<dct.length; i++){
            dct[i] = Integer.parseInt(st.nextToken());
        }
        no_6283 problem = new no_6283();
        System.out.println(problem.solution(dct));
    }

    public String solution(int[] dct) {
        // 음수 : ascending, 양수 : descending
        int next = dct[0] - dct[1];

        for(int i=1; i<dct.length; i++){
            if(dct[i-1] - dct[i] == next) continue;
            return "mixed";
        }
        return next > 0 ? "descending" : "ascending";
    }
}
