package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소프티어 Lv.3
// [한양대 HCPC 2023] Hanyang Popularity Exceeding Competition
// https://softeer.ai/practice/9495
public class no_9495 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[][] people = new int[n][2];

        for(int i = 0; i < n; i++){
            String[] arr = br.readLine().split(" ");
            // 인기도
            people[i][0] = Integer.parseInt(arr[0]);
            // 친화력
            people[i][1] = Integer.parseInt(arr[1]);
        }

        System.out.println(solution(people));
    }

    public static int solution(int[][] people){
        // 현재 인기도
        int answer = 0;

        for(int[] arr : people){    // 유명인들 순회
            int p = arr[0];
            int c = arr[1];
            if(Math.abs(p - answer) <= c){
                answer++;
            }
        }

        return  answer;
    }
}
/*
유명인 N 명과 만남
1 번부터 N 번까지 차례대로 만난다

현재 인기토 X, i 번 유명인 인기도 p[i], 친화력을 c[i]
|p[i] - X| <= c[i] 여야 인기도가 1 올라감

인기도 최대화
⚠️ 유명인은 순서대로 만남
 */