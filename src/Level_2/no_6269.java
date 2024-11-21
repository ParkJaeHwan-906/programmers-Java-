package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Softeer Lv.2
// [21년 재직자 대회 예선] 비밀 메뉴
// https://www.softeer.ai/practice/6269
public class no_6269 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 비밀 메뉴 조작법은 M 개의 버튼 조작으로 이루어짐
        int m = Integer.parseInt(st.nextToken());
        // 사용자 조작
        int n = Integer.parseInt(st.nextToken());
        // 자판기의 총 버튼 수 ( 1 ~ k )
        int k = Integer.parseInt(st.nextToken());

        // 비밀 메뉴 키
        String secret = br.readLine();
        // 사용자 조작 키
        String input = br.readLine();

        System.out.println(input.indexOf(secret) > -1 ? "secret" : "normal");
    }
}
