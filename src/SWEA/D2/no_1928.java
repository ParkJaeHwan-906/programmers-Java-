package SWEA.D2;

import java.lang.reflect.GenericDeclaration;
import java.util.*;
import java.io.*;

// SWEA D2
// 1928. Base64 Decoder
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PR4DKAG0DFAUq&categoryId=AV5PR4DKAG0DFAUq&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_1928 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        no_1928 problem = new no_1928();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();

        // 테스트 케이스 개수
        int tc = Integer.parseInt(br.readLine().trim());

        for(int no = 1; no <= tc; no++) {
            sb.append('#').append(no).append(' ');

            String encoded = br.readLine().trim();
            sb.append(problem.decodeString(encoded)).append('\n');
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    String decodePw = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private String decodeString(String encoded) {
        StringBuilder decode = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        String binaryString = toASCII(encoded);

        // 8비트씩 나누어 ASCII 코드 값으로 변경한다.
        for(char c : binaryString.toCharArray()) {
            if(sb.length() == 8) {
                int idx = Integer.parseInt(sb.toString(), 2);  // 2진수를 10진수로 변환
                decode.append((char)idx);
                sb.setLength(0);
            }
            sb.append(c);
        }

        int idx = Integer.parseInt(sb.toString(), 2);  // 2진수를 10진수로 변환
        decode.append((char)idx);
        sb.setLength(0);

        return decode.toString();
    }

    // 각 자리수를 이진수로 변경한다.
    private String toASCII(String encoded) {
        StringBuilder sb = new StringBuilder();

        for(char c : encoded.toCharArray()){
            // 각 자리수를 표에 존재하는 값으로 대치한다.
            int idx = decodePw.indexOf(c);
            sb.append(toBinary(idx));
        }

        return sb.toString();
    }

    // 10진수를 2진수로 변경한다.
    private String toBinary(int number) {
        StringBuilder sb = new StringBuilder();

        while(number > 0) {
            sb.insert(0, number%2);
            number /= 2;
        }

        while(sb.length() < 6){
            sb.insert(0, 0);
        }

        return sb.toString();
    }
}

/*
Encoding 순서
1. 24비트 버퍼 위쪽부터 한 byte 씩 3 byte 의 문자를 집어넣는다
2. 버퍼의 위쪽부터 6비트씩 잘라 그 값을 읽고, 각각의 값을 표의 문자로 Encoding 한다.

ex)
TGlmZSBpdHNlbGYgaXMgYSBxdW90YXRpb24u

Life itself is q quatation
해당 알파벳을 아스키코드 숫자로 변환 후 2친수로 변경하여 합친다.
이를 6비트씩 나누어 다시 10진수로 변환하여, 이를 표에 대치한다.
 */