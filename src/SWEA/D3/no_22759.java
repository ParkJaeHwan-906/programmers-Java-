package SWEA.D3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ⚠️ 모르겠음
// SWEA D3
// 묶음 판매
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AZK3fpuaBJwDFAXk&categoryId=AZK3fpuaBJwDFAXk&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_22759 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            String[] LR = br.readLine().split(" ");
            long l = Long.parseLong(LR[0]);
            long r = Long.parseLong(LR[1]);

            System.out.println((r+1)%l >= (r+1)/2.0);
        }
    }
}
