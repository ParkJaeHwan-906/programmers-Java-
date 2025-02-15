package Ssafy.Algorithm.Stack;

/*
n 개 중 k 개를 뽑는 조합의 경우의 수 계산을 재귀로 작성해보자
 */
public class nCombinationK {
    static int answer = 0;
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        int k = 3;
        combination(0, new int[k], arr, 0, k);
        System.out.println(answer);
    }

    private static void combination(int idx, int[] comb,int[] arr, int depth, int k) {
        if(idx == k) {  // 조합을 모두 완성한 경우
            for(int i : comb) {
                System.out.print(i+" ");
            }
            answer++;
            System.out.println();
            return;
        }

        if(depth == arr.length) return; // 모든 탐색이 끝난 경우

        comb[idx] = arr[depth];
        combination(idx+1, comb, arr, depth+1, k);
        comb[idx] = 0;
        combination(idx, comb, arr, depth+1, k);
    }
}

/*
팩토리얼 (!)
: 서로 다른 n 개의 수를 나열하는 경우
-> n! = n(n-1)(n-2)(n-3)...1

순열 ( nPr )
순열이란 서로 다른 n개중에 r개를 선택하는 경우의 수를 의미(순서 상관 있음)
->nPr = n! / (n-r)!

조합 ( nCr )
조합이란 서로 다른 n개중에 r개를 선택하는 경우의 수를 의미(순서 상관 없음)
-> nCr = n!/(n-r)!r!

...
[참고]
https://coding-factory.tistory.com/606
 */