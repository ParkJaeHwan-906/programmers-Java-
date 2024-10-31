package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalInt;

// 소프티어 Lv.3
// [한양대 HCPC 2023] Pipelined
// https://softeer.ai/practice/9496
public class no_9496 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 자동차의 개수
        int n = Integer.parseInt(br.readLine());

        // 자동차의 생산 프로세스에 대한 각각의 단계 수
        String[] sArr = br.readLine().split(" ");
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(sArr[i]);
        }
        Arrays.sort(arr);
        System.out.println((arr.length - 1) + arr[arr.length-1]);
    }
}
