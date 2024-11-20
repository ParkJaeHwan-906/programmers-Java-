package Level_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// Softer Lv.1
// 나무 심기
// https://www.softeer.ai/practice/7353
public class no_7353 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] f = new int[n];

        String[] arr = br.readLine().split(" ");

        for(int i=0; i<n; i++) {
            f[i] = Integer.parseInt(arr[i]);
        }

        Arrays.sort(f);

        int result1 = f[0]*f[1];
        int result2 = f[n-1]*f[n-2];
        System.out.println(Math.max(result1, result2));
    }


}
