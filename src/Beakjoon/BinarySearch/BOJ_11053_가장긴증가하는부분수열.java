package Beakjoon.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ_11053_가장긴증가하는부분수열 {
    static BufferedReader br;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static int n;
    static List<Integer> lis;
    static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        lis = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while(n-- > 0) {
            int num = Integer.parseInt(st.nextToken());

            // 1. 현재 리스트가 비어있거나, 마지막 원소보다 크다면 바로 뒤에 삽입한다.
            if(lis.isEmpty() || lis.get(lis.size()-1) < num) {
                lis.add(num);
                continue;
            }

            // 2. 원소를 중간에 삽입해야한다.
            int insertIdx = findInsertIdx(num);
            lis.set(insertIdx, num);
        }

        System.out.println(lis.size());
    }

    static int findInsertIdx(int targetNum) {
        int l = 0, r = lis.size();

        // 타겟 이상인 가장 첫 번째 윈소 -> lower bound
        while(l < r) {
            int m = l + (r-l) / 2;
            if(lis.get(m) < targetNum) l = m+1;
            else r = m;
        }

        return l;
    }
}
