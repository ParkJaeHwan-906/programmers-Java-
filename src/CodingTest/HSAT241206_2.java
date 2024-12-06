package CodingTest;

import java.util.*;
import java.io.*;

public class HSAT241206_2 {

    // 신호등
    // R G Y 가 나오는 경우의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String light = br.readLine();

        int answer = 0;
        for(int a = 0; a < light.length(); a++) {
            if(light.charAt(a) != 'R') continue;
            for(int b = a+1; b < light.length(); b++){
                if(light.charAt(b) != 'G') continue;
                for(int c = b+1; c < light.length(); c++){
                    if(light.charAt(c) != 'Y') continue;
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}
