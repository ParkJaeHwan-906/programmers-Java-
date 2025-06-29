package Programmers.Lv2;

import java.util.*;

public class PMMS_178870_연속된부분수열의합 {
    public static void main(String[] args) {
        int[] sequence = {1,2,3,4,5};
//        int[] sequence = {1,1,1,2,3,4,5};
        int k = 7;
//        int k = 5;
        System.out.println(Arrays.toString(new PMMS_178870_연속된부분수열의합().solution(sequence, k)));
    }

    int[] sequence;
    int k;
    public int[] solution(int[] sequence, int k) {
        // 💡 sequence 는 비내림차순으로 정렬되어있다.
        //      => 오름차순 정렬이디.
        this.sequence = sequence;
        this.k = k;


        return findIdx();
    }

    // 투포인터로 범위 찾기
    int[] findIdx() {
        int[] result = null;
        int l = 0, r = 0;
        // 초기값
        long acc = sequence[0];
        while(r < sequence.length) {
            if(acc == k && (result == null || (result[1] - result[0] > r - l))) {
                result = new int[] {l, r};
                acc -= sequence[l++];
//                r = l;
                continue;
            }

            if(acc < k) {
                if(++r < sequence.length) {
                    acc += sequence[r];
                }
            }
            else {
                acc -= sequence[l++];
            }
        }

        return result;
    }
}
