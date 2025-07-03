package Programmers.Lv3;

import java.util.*;

public class PMMS_150367_표현가능한이진트리 {
    public static void main(String[] args) {
        long[] numbers = {7,42,5};
//        long[] numbers = {63 ,111,95};
        System.out.println("result : " + Arrays.toString(new PMMS_150367_표현가능한이진트리().solution(numbers)));
    }

    /*
        리프노드가 아닌 노드는
        자신의 왼쪽 자식이 루트인 서브트리의 노드들 보다 오른쪽에 있다.
        자신의 오른쪽 자식이 루트인 서브트리의 노드들보다 왼쪽에 있다.

        더미 노드를 추가하여 포화 이진트리로 만든다.
        루트 노드는 그대로 유지한다.
        => 포화이진트리의 노드 개수 -> 2n-1 ( n 은 트리의 높이 ( 레벨 ) )
     */
    public int[] solution(long[] numbers) { // 이진트리로 만들고 싶은 수를 담은 1차원 정수 배열
        int[] answer = new int[numbers.length];
        /*
            순차적으로 이진수로 변환 후, 이진트리로 만들 수 있는지 판별
         */
        for(int i=0; i<numbers.length; i++) {
//            System.out.printf("case%d : %d\n", i, numbers[i]);
            answer[i] = convertBinaryTree(Long.toBinaryString(numbers[i]))
                    ? 1 : 0;
        }

        return answer;
    }

    /*
        전달받은 이진수로 이진 트리를 만들 수 있는지 판별
     */
    boolean convertBinaryTree(String binary) {
//        System.out.println("이진수 : " +  binary);
        /*
            수의 앞에 0을 붙이는 것은 십진수 변환에 영향이 없음
            즉, 앞에 0 이 얼마나 붙어야, 포화이진트리로 만들 수 있는지 확인 가능
         */
        String fullBinary = makeFullBinaryTree(binary);
//        System.out.println("포화이진트리 : " + fullBinary);
        /*
            절반식 분할하며, 각 서브트리의 루트가 더미노드(0) 이 아닌지 판별한다.
            0 이 나오는 즉시 false 반환 -> 이진트리 XXX
         */

        if(!isVaild(fullBinary)) return false;

        return true;
    }

    boolean isVaild(String binary) {
        // 리프 노드인 경우 -> 항상 참
        if(binary.length() == 1) return true;

        // 1. 문자를 절반으로 나눈다 -> 루트 노드 구하기
        int mid = binary.length()/2;
        char root = binary.charAt(mid);
        // 1-1. 루트노드를 기준, 왼쪽 자식과 오른쪽 자신을 나눈다.
        String left = binary.substring(0, mid);
        String right = binary.substring(mid+1);
        // 2. 루트 노드가 0 ( 더미 노드 ) 인데, 자식을 갖는 경우 이는 거짓임
        if(root == '0' && (left.contains("1") || right.contains("1"))) return false;

        // 문제 없다면, 다음 서브트리를 탐색
        return isVaild(left) && isVaild(right);
    }

    String makeFullBinaryTree(String binary) {
        // 주어진 수의 이진수 길이
        int nowLen = binary.length();
        int fullLen = 1;        // 초기 0 Level 트리의 노드 개수
        while(nowLen > fullLen) {
             fullLen = fullLen*2 + 1;       // Level 을 1 씩 증가 시킨다.
        }

        // 앞쪽에 0 을 채워 포화이진트리를 만든다.
        StringBuilder sb = new StringBuilder(binary);
        while(fullLen-- > nowLen) sb.insert(0, 0);

        return sb.toString();
    }
}
/*
    이진트리를 수로 표현하는 방법
    1. 이진수를 저장할 빈 문자열을 생성
    2. 주어진 이진트리에 더미 노드를 추가하여 포화 이진트리로 만든다.
        - 루트 노드는 그대로 유지
    3. 만들어진 포화 이진트리의 노드들을 가장 왼쪽 노드부터 오른쪽 노드까지
    4. 살펴본 노드가 더미 노드 -> 문자열 뒤에 0 을 추가.
        더미 노드가 아니라면 -> 문자열 뒤에 1 을 추가
    5. 문자열에 저장된 이진수를 십진수로 변환
 */