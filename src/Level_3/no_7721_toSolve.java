package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// ⚠️ ??????????????????????????????????????????????????????????????????????????
// 소프티어 Lv.3
// 루돌프 월드컵
// https://softeer.ai/practice/7721
public class no_7721_toSolve {
    static List<Integer> deer = new ArrayList<>();
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 루돌프 정보 입력 (힘)
        String[] deerArr = br.readLine().split(" ");
        for(String s : deerArr){
            deer.add(Integer.parseInt(s));
        }
        n = deer.size();

        no_7721_toSolve problem = new no_7721_toSolve();
        System.out.println(problem.solution());
    }

    public double solution(){
        return 0.0;
    }
}

/*
* 루돌프끼리 경기해서 나올 수 있는 경우의 수를 모두 구한다.
*/