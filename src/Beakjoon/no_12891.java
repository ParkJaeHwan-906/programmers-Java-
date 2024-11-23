package Beakjoon;

import java.util.*;
import java.io.*;

// BAEKJOON
// DNA 비밀번호
// https://www.acmicpc.net/problem/12891
public class no_12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        String s = br.readLine();
        int[] key = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            key[i] = Integer.parseInt(st.nextToken());
        }

        no_12891 problem = new no_12891();
        System.out.println(problem.solution(s, p, key));
    }

    int[] compare = new int[4];
    public long solution(String s, int p, int[] key){
        int left = 0;
        int right = p;
        long count = 0;

        while(right <= s.length()) {
            if (left == 0) { // 초기 설정
                for (int i = left; i < right; i++) {
                    char c = s.charAt(i);
                    change(c, 1);
                }
            } else {
                char sub = s.charAt(left-1);
                char add = s.charAt(right-1);
                change(sub, -1);
                change(add, 1);
            }

            if(isOk(key)) count++;
            left++;
            right = left+p;
        }

        return count;
    }

    public void change(char c, int flag){
        switch(c) {
            case 'A' :
                compare[0] += flag;
                break;
            case 'C' :
                compare[1] += flag;
                break;
            case 'G' :
                compare[2] += flag;
                break;
            case 'T' :
                compare[3] += flag;
                break;
        }
    }

    public boolean isOk(int[] key){
        for(int i = 0; i < 4; i++){
            if(key[i] > compare[i]) return false;
        }
        return true;
    }
}
