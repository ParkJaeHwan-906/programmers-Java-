package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 소프티어 Lv.3
// 나무 섭지
// https://softeer.ai/practice/7726
public class no_7726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n,m 입력
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        // map 입력
        char[][] map = new char[n][m];
        for(int i=0; i<n; i++){
            map[i] = br.readLine().toCharArray();
        }

    }

}
/*
미로는 n x m 크기
각 격자 칸은 남우(N), 출구(D), 유령(G), 빈 공간(.), 벽(#) 으로 이루어짐

남우 : 1초에 상하좌우 한칸이동(벽이 아닌곳)
유령 : 1초에 상하좌우 아무곳이나 한칸 이동 (벽도 가능)
 */