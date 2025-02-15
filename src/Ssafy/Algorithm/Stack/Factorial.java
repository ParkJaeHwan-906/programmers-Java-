package Ssafy.Algorithm.Stack;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(factorial(n));
    }

    private static int factorial(int n) {
        if(n < 2) return 1;

        return n * factorial(n-1);
    }
}

/*
재귀함수를 사용하여 팩토리얼을 구현한다
 */