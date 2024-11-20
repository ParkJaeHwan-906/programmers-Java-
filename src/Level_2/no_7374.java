package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// Softeer Lv.2
// 진정한 효도
// https://www.softeer.ai/practice/7374
public class no_7374 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 할당된 땅은 3 x 3
        int[][] maps = new int[3][3];

        // maps 입력
        for(int i=0; i<3; i++){
            String[] arr = br.readLine().split(" ");
            for(int j=0; j<3; j++){
                maps[i][j] = Integer.parseInt(arr[j]);
            }
        }

        no_7374 problem = new no_7374();
        System.out.println(problem.solution(maps));

//        // 지도 확인
//        for(int[] arr : maps){
//            for(int a : arr){
//                System.out.print(a+" ");
//            }
//            System.out.println();
//        }
    }

    // 가로, 세로 따로 확인?
    public int solution(int[][] maps) {
        int answer = 27;
        for (int i = 0; i < 3; i++) {
            // 해당 인덱스의 가로와 세로 정보를 확인함
            int[][] arr = new int[2][3];

            // 가로 중간값
            int[] mid = new int[2];
            for (int j = 0; j < 3; j++) {
                // 가로
                arr[0][j] = maps[i][j];
                mid[0] += maps[i][j];
                // 세로
                arr[1][j] = maps[j][i];
                mid[1] += maps[j][i];
            }

            // ⚠️ 이거 때문에 틀렸었음
            mid[0] = mid[0] % 3 == 0 ? mid[0]/3 : mid[0]/3+1;
            mid[1] = mid[1] % 3 == 0 ? mid[1]/3 : mid[1]/3+1;

            System.out.printf("가로, (중간값 : %d)\n", mid[0]);
            for(int j=0; j<3; j++){
                System.out.print(arr[0][j]+" ");
            }
            System.out.println();

            System.out.printf("세로, (중간값 : %d)\n", mid[1]);
            for(int j=0; j<3; j++){
                System.out.print(arr[1][i]+" ");
            }
            System.out.println();

            answer = Math.min(answer, toFlat(arr, mid));
        }

        return answer;
    }

    public int toFlat(int[][] arr, int[] mid){
        int count1 = 0;
        int count2 = 0;
        for(int i=0; i<3; i++){
            count1 += Math.abs(mid[0] - arr[0][i]);
            count2 += Math.abs(mid[1] - arr[1][i]);
        }
        return Math.min(count1, count2);
    }
}
