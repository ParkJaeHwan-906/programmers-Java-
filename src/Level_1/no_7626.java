package Level_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소프티어 Lv.1
// 연탄 배달의 시작
// https://softeer.ai/practice/7626
public class no_7626 {
    static int n;
    static int[] villge;
    static int minDist = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        villge = new int[n];

        String[] arr = br.readLine().split(" ");
        for(int i=0; i<n; i++){
            villge[i] = Integer.parseInt(arr[i]);
            if(i == 0) continue;
            minDist = Math.min(villge[i] - villge[i-1], minDist);
        }

        int count = 0;
        for(int i=1; i<n; i++){
            if(villge[i] - villge[i-1] == minDist) count++;
        }
        System.out.println(count);
    }
}
