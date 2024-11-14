package Level_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 프로그래머스 Lv.0
// 진료 순서 정하기
// https://school.programmers.co.kr/learn/courses/30/lessons/120835
public class no_120835 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");
        int[] emergency = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            emergency[i] = Integer.parseInt(arr[i]);
        }

        no_120835 problem = new no_120835();
        int[] result = problem.solution(emergency);
        System.out.println();
        for(int i : result){
            System.out.print(i + " ");
        }
    }

    public int[] solution(int[] emergency){
        int[] arrCopy = new int[emergency.length];
        for(int i=0; i<emergency.length; i++){
            arrCopy[i] = emergency[i];
        }
        Arrays.sort(arrCopy);

        int[] result = new int[arrCopy.length];
        for(int i=arrCopy.length-1; i>-1; i--){
            int cur = arrCopy[i];
            System.out.println("현재값 : "+ cur);
            System.out.println("우선 순위 : " + (emergency.length-i));
            for(int j=0; j<emergency.length; j++){
                if(cur == emergency[j]){ // 같은 값(위치)를 찾았다면
                    System.out.println("매칭값 : " + emergency[j]);
                    System.out.println("기존 위치 : "+j);
                    result[j] = emergency.length - i;
                    // 바로 종료
                    break;
                }
            }
            for(int z : result){
                System.out.print(z + " ");
            }
            System.out.println();
        }

        return result;
    }
}
