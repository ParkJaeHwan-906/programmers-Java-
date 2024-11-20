package Level_1;

import java.util.Scanner;

// Softeer Lv.1
// A + B
// https://www.softeer.ai/practice/6295
public class no_6295 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i=0; i<n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.printf("Case #%d: %d\n", i+1, (a+b));
        }
    }
}
