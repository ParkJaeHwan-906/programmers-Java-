package Level_2;

import java.util.Arrays;
import java.util.PriorityQueue;

// 프로그래머스 Lv.2
// 호텔 대실
// https://school.programmers.co.kr/learn/courses/30/lessons/155651
public class no_155651 {
    public int solution(String[][] book_time){
        // 예약 내역 배열을 정수형 배열로 복사
        int[][] book_time_copy = new int[book_time.length][2];
        for(int i=0; i<book_time.length; i++){
            int start = Integer.parseInt(book_time[i][0].replace(":",""));
            int end = Integer.parseInt(book_time[i][1].replace(":",""));

            end += 10;
            if(end % 100 >= 60){
                end = ((end/100)+1)*100 + (end%100)%60;
            }
            book_time_copy[i][0] = start;
            book_time_copy[i][1] = end;
        }
        // 예약시간을 오름차순으로 정렬
        Arrays.sort(book_time_copy, (e1, e2) -> {
            return e1[0] - e2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int[] arr : book_time_copy){
            int start = arr[0];
            int end = arr[1];
            if(pq.isEmpty()){
                pq.add(end);
            }else{
                if(pq.peek() > start){
                    pq.add(end);
                }else{
                    pq.poll();
                    pq.add(end);
                }
            }
        }
        return pq.size();
    }
    public static void main(String[] args){
        String[][] book_time = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        no_155651 problem = new no_155651();
        int result = problem.solution(book_time);
        System.out.println(result);
    }
}
