package Level_2;

import java.util.*;

// 프로그래머스 Lv.2
// 행렬 테두리 회전하기
// https://school.programmers.co.kr/learn/courses/30/lessons/77485
public class no_77485 {
    List<Integer> result = new ArrayList<>();
    public int[] solution(int rows, int columns, int[][] queries){
        int [] answer = {};

        // 배열 초기화
        int[][] arr = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j <columns; j++){
                arr[i][j] = (i*columns+(j+1));
//                System.out.print(arr[i][j]+"\t");
            }
//            System.out.println();
        }

        // 회전시키기
        for(int[] bound : queries){
            arr = rotation(bound, arr);
            for(int[] i : arr){
                for(int j : i){
                    System.out.print(j + "\t");
                }
                System.out.println();
            }
                System.out.println("------------------------");
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public int[][] rotation(int[] bound, int[][] arr){
        int x1 = bound[0] - 1;
        int y1 = bound[1] - 1;
        int x2 = bound[2] - 1;
        int y2 = bound[3] - 1;

        int prev = arr[x1][y1];
        int min = prev;

       // 상단 →
        for(int i = y1+1; i <= y2; i++){
            min = Math.min(min, arr[x1][i]);
            int tmp = arr[x1][i];
            arr[x1][i] = prev;
            prev = tmp;
        }

        // 우측 ↓
        for(int i = x1+1; i <= x2; i++){
            min = Math.min(min, arr[i][y2]);
            int tmp = arr[i][y2];
            arr[i][y2] = prev;
            prev = tmp;
        }

        // 하단 ←
        for(int i = y2-1; i >= y1; i--){
            min = Math.min(min, arr[x2][i]);
            int tmp = arr[x2][i];
            arr[x2][i] = prev;
            prev = tmp;
        }

        // 좌측 ↑
        for(int i = x2-1; i >= x1; i--){
            min = Math.min(min, arr[i][y1]);
            int tmp = arr[i][y1];
            arr[i][y1] = prev;
            prev = tmp;
        }

        result.add(min);
        return arr;
    }

    public static void main(String[] args){
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4}, {3,3,6,6}, {5,1,6,3}};

        // result : [8, 10, 25]
        no_77485 problem = new no_77485();
        int[] answer = problem.solution(rows, columns, queries);
        for(int i : answer){
            System.out.print(i+" ");
        }
    }
}
