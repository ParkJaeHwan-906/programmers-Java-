package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 3회 정기 코딩 인증평가 기출] 플레이페어 암호
// https://softeer.ai/practice/6255
public class no_6255 {
    static char[][] playFair = new char[5][5];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 평문 입력
        String plainText = br.readLine();
        // 비밀키 입력
        String secretKey = br.readLine();
        no_6255 problem = new no_6255();

        System.out.println(problem.crypto(plainText, secretKey));
    }

    public String crypto(String plainText, String secretKey) {
        makeKeySet(secretKey);
        return pharse1(plainText);
    }

    private void makeKeySet(String secretKey) {
        boolean[] used = new boolean[26];
        StringBuilder key = new StringBuilder();

        // I와 J는 같은 문자로 처리
        secretKey = secretKey.replaceAll("J", "I");

        // 키 문자열로 격자 채우기
        for (char c : secretKey.toCharArray()) {
            if (!used[c - 'A']) {
                key.append(c);
                used[c - 'A'] = true;
            }
        }

        // 나머지 알파벳 추가
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue; // J는 I와 동일하게 처리됨
            if (!used[c - 'A']) {
                key.append(c);
                used[c - 'A'] = true;
            }
        }

        // 5x5 격자 채우기
        int index = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playFair[i][j] = key.charAt(index++);
            }
        }
    }

    private String pharse1(String plainText) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i < plainText.length(); i+=2){
            char left = plainText.charAt(i);
            char right = (i+1) < plainText.length() ? plainText.charAt(i+1) : '@';
            sb.append(left);
            if(left == right){  // 같은쌍이라면
                sb.append(right == 'X' ? 'Q' : 'X');
                --i;
                continue;
            }
            sb.append(right == '@' ? 'X' : right);
        }
        return pharse2(sb.toString());
    }

    private String pharse2(String pharseText) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i < pharseText.length(); i+=2){
            char a = pharseText.charAt(i);
            char b = pharseText.charAt(i+1);

            int[] aPoint = new int[2];
            int[] bPoint = new int[2];

            // 각 위치 찾기
            for(int x=0; x<5; x++){
                for(int y=0; y<5; y++){
                    if(playFair[x][y] == a){
                        aPoint[0] = x;
                        aPoint[1] = y;
                    }
                    if(playFair[x][y] == b){
                        bPoint[0] = x;
                        bPoint[1] = y;
                    }
                }
            }
            sb.append(pharse3(aPoint, bPoint));
        }
        return sb.toString();
    }

    private String pharse3(int[] aPoint, int[] bPoint) {
        StringBuilder sb = new StringBuilder();
        if(aPoint[0] == bPoint[0]){
            aPoint[1] = (aPoint[1]+1)%5;
            bPoint[1] = (bPoint[1]+1)%5;
        } else if(aPoint[1] == bPoint[1]) {
            aPoint[0] = (aPoint[0]+1)%5;
            bPoint[0] = (bPoint[0]+1)%5;
        } else {
            int tmp = aPoint[1];
            aPoint[1] = bPoint[1];
            bPoint[1] = tmp;
        }

        sb.append(playFair[aPoint[0]][aPoint[1]]);
        sb.append(playFair[bPoint[0]][bPoint[1]]);

        return sb.toString();
    }
}