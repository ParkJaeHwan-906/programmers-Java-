package CodingTest;

import java.util.*;
import java.io.*;

public class HSAT241206_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 상품의 수
        int n = Integer.parseInt(st.nextToken());
        int[] item = new int[n];
        // 사람의 수 ?
        int people = Integer.parseInt(st.nextToken());
        while(people-- > 0) {
            st = new StringTokenizer(br.readLine());

            int item1 = Integer.parseInt(st.nextToken());
            int item2 = Integer.parseInt(st.nextToken());

            item[item1]++;
            item[item2]++;
        }

        int answer = 0;
        for(int i : item) {
            if(i == 0) answer++;
        }

        System.out.println(answer);
    }
}
