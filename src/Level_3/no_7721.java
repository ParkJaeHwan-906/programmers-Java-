package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 소프티어 Lv.3
// 루돌프 월드컵
// https://softeer.ai/practice/7721
public class no_7721 {
    static List<Integer> deer = new ArrayList<>();
    static int[][] vs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 루돌프 정보 입력 (힘)
        String[] deerArr = br.readLine().split(" ");
        for(String s : deerArr){
            deer.add(Integer.parseInt(s));
        }

        vs = new int[deer.size()][deer.size()];


    }
}
