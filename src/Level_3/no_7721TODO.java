package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 소프티어 Lv.3
// 루돌프 월드컵
// https://softeer.ai/practice/7721
public class no_7721TODO {
    static List<Integer> deer = new ArrayList<>();
//    static int[][] vs;
    static List<int[][]> worldCup = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 루돌프 정보 입력 (힘)
        String[] deerArr = br.readLine().split(" ");
        for(String s : deerArr){
            deer.add(Integer.parseInt(s));
        }

//        vs = new int[deer.size()][deer.size()];


    }

    // 나올 수 있는 모든 경우의 수 찾기
    public void makeWorldCup() {

    }
}

/*
각 루돌프는 힘 Fi 정보가 주어졌을 때, i번 루돌프와 j번 루돌프의 축구 경기 결과는 다음과 같이 예측해볼 수 있습니다.

- i번 루돌프가 이길 확률은 5Fi+5Fj4Fi 입니다.

- j번 루돌프가 이길 확률은 5Fi+5Fj4Fj 입니다.

- i번 루돌프와 j번 루돌프 경기가 비길 확률은 5Fi+5FjFi+Fj 입니다.
 */
