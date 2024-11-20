package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Softeer Lv.2
// 금고털이
// https://www.softeer.ai/practice/6288
public class no_6288 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] wn = br.readLine().split(" ");
        // 배낭의 무게
        int w = Integer.parseInt(wn[0]);
        // 금속의 종류
        int n = Integer.parseInt(wn[1]);

        int[][] items = new int[n][2];
        for(int i=0; i<n; i++) {
            String[] item = br.readLine().split(" ");
            // 무게
            items[i][0] = Integer.parseInt(item[0]);
            // 무게당 가격
            items[i][1] = Integer.parseInt(item[1]);
        }

        no_6288 problem = new no_6288();
        System.out.println(problem.solution(items, w));
    }

    public int solution(int[][] items, int w){
        // 무게당 가격을 기준으로 내림차순 정렬
        Arrays.sort(items, (e1, e2) -> e2[1] - e1[1]);
        int idx = 0;

        int answer = 0;
        while(w > 0){
            // 현재 상품의 무게보다 가방에 담을 수 있는 무게가 더 여유있을 경우
            if(w >= items[idx][0]){
                answer += (items[idx][0] * items[idx][1]);
                w -= items[idx][0];
                idx++;
            } else{ // 쪼개서 담어야하는 경우
                answer += items[idx][1] * w;
                w = 0;
            }
        }
        return answer;
    }
}
