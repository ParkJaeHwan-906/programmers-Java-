package SWEA.D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// SWEA D2
// 백만 장자 프로젝트
// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LrsUaDxcDFAXc&categoryId=AV5LrsUaDxcDFAXc&categoryType=CODE&problemTitle=&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
public class no_1859 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        no_1859 problem = new no_1859();
        for(int i=0; i<t; i++){
            int n = Integer.parseInt(br.readLine());
            int[] dayPrice = new int[n];
            String[] arr = br.readLine().split(" ");
            for(int j=0; j<n; j++){
                dayPrice[j] = Integer.parseInt(arr[j]);
            }

            System.out.printf("#%d %d\n", i+1, problem.solution(dayPrice));
        }
    }

    public long solution(int[] dayPrice){
        long result = 0;
        int max = 0;
        for(int i=dayPrice.length-1; i > -1; i--){
            if(max < dayPrice[i]){
                max = dayPrice[i];
                continue;
            }
            result += (max - dayPrice[i]);
        }
        return result;
    }
}
