package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 소프티어 Lv.2
// 연탄의 크기
// https://softeer.ai/practice/7628
public class no_7628 {
    /*
    난로의 반지름의 길이와 연탄의 반지름의 길이의 배수인 집에서만 사용 가능

    n개의 집에 각각 놓어있는 난로의 반지름 길이가 주어짐
    연탄의 반지름 길이를 잘 설정하여 최대한 많은 집에서 연탄을 사용할 수 있도록 함

    ⚠️ 반지름은 항상 1보다 크다
     */
    public int solution(int[] arr){
        int count = 0;
        for(int i = 2; i <= 100; i++){
            int cnt = 0;
            for(int z = 0; z < arr.length; z++){
                if(arr[z]%i == 0) cnt++;
            }
            count = Math.max(count, cnt);
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        no_7628 problem = new no_7628();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 집의 수
        System.out.print("n : ");
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        // 난로의 반지름 배열
        System.out.print("str : ");
        String[] str = br.readLine().split(" ");
        for(int i=0; i< str.length; i++){
            arr[i] = Integer.parseInt(str[i]);
        }

        System.out.println(problem.solution(arr));
    }
}
