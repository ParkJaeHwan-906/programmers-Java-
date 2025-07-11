package Programmers.Lv3;

import java.util.Arrays;

public class PMMS_161988_연속펄스부분수열의합 {
    public static void main(String[] args) {
        int[] sequence = {2,3,-6,1,3,-1,2,4};
        System.out.println(new PMMS_161988_연속펄스부분수열의합().solution(sequence));
    }

    /*
        펄스 : 1 또는 -1 로 시작하면서 1 과 -1 이 번갈아 나오는 수열이다.

        어떤 수열의 연속 부분 수열에, 같은 길이의 펄스 수열을 각 원소끼리 곱하여
        연속 펄스 부분 수열을 만드려한다. -> 연속 펄스 부분 수열의 합 중 가장 큰 것을 골라라
     */
    public long solution(int[] sequence) {
        /*
            sequence 의 최대 길이는 50만
            펄스 -1 로 시작하는 배열 / 1로 시작하는 배열을 각각 만든다.
            => O(n)
            부분합 구하기?
         */
        makeArr(sequence);

        // 부분합 구하기
        // => 카데인 알고리즘 -> 현 위치까지의 최대값 구하기
        long plusCurMax, plusGlobalMax;
        long minusCurMax, minusGlobalMax;
        plusCurMax = plusGlobalMax = plusArr[0];
        minusCurMax = minusGlobalMax = minusArr[0];
        for(int i=1; i<sequence.length; i++) {
            // 1. 현재 원소를 포함할지, 새로 시작할지 선택
            plusCurMax = Math.max(plusCurMax+plusArr[i], plusArr[i]);
            minusCurMax = Math.max(minusCurMax+minusArr[i], minusArr[i]);
            // 2. 전체 최대값 갱신
            plusGlobalMax = Math.max(plusGlobalMax, plusCurMax);
            minusGlobalMax = Math.max(minusGlobalMax, minusCurMax);
        }

        return Math.max(minusGlobalMax, plusGlobalMax);
    }

    // 펄스 배열과 곱한 배열
    long[] plusArr;     // 1 로 시작
    long[] minusArr;    // -1 로 시작
    void makeArr(int[] sequence) {
        int flag = 1;
        plusArr = new long[sequence.length];
        minusArr = new long[sequence.length];
        for(int i=0; i<sequence.length; i++) {
            plusArr[i] = sequence[i] * flag;
            flag *= -1;
            minusArr[i] = sequence[i] * flag;
        }

        // 확인
//        System.out.println(Arrays.toString(plusArr));
//        System.out.println(Arrays.toString(minusArr));
    }
}
