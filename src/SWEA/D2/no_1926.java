package SWEA.D2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class no_1926 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for(int i=1; i<=n; i++){
            String s = String.valueOf(i);
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for(int j=0; j<s.length(); j++){
                if(s.charAt(j) == '3' || s.charAt(j) == '6' || s.charAt(j) == '9') count++;
            }
            if(count == 0) System.out.print(i+" ");
            else{
                while(count-- > 0){
                    sb.append('-');
                }
                System.out.print(sb.toString()+" ");
            }
        }
    }
}
