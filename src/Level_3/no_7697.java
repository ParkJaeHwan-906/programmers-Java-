package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Softeer Lv.3
// [한양대 HCPC 2023] Phi Squared
// https://softeer.ai/practice/7697
public class no_7697 {
    static class Node{
        int idx;
        long value;

        public Node(int idx, long value){
            this.idx = idx;
            this.value = value;
        }

        public void addValue(long other){
            this.value += other;
        }

        public String toString() {
            return this.idx + " : " + this.value;
        }
    }

//    static List<Node> list = new ArrayList<>();
    static int n;
    static Queue<long[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        n = Integer.parseInt(br.readLine());
//        String[] listArr = br.readLine().split(" ");
//        for(int i=0; i<listArr.length; i++){
//            list.add(new Node(i+1, Long.parseLong(listArr[i])));
//        }
        no_7697 problem = new no_7697();
//        Node node = problem.solution();
//        System.out.println(node.toString());

//        System.out.println("solution2");
        n = Integer.parseInt(br.readLine());
        String[] listArr2 = br.readLine().split(" ");
        for(int i=0; i<listArr2.length; i++){
            q.offer(new long[] {i+1, Long.parseLong(listArr2[i])});
        }

        long[] answer = problem.solution2();
        for(long l : answer){
            System.out.println(l);
        }

    }

//    public Node solution(){
//        while(list.size() > 1){ // 마지막 미생물 하나 남은 경우
//            // 미생물들을 차례도 흡수한다.
//            int idx = 0;
//            while(idx < list.size()){   // idx 가 list 의 크기보다 작을때까지 (리스트 순회)
//                if(idx == 0){   // 맨 앞의 값, 뒤의 항목과만 비교
//                    if(list.get(idx).value >= list.get(idx+1).value){
//                        list.get(idx).addValue(list.get(idx+1).value);
//                        list.remove(idx+1);
//                    }
//                } else if(idx == list.size()-1){    // 마지막 값, 앞의 항목과만 비교
//                    if(list.get(idx).value >= list.get(idx-1).value){
//                        list.get(idx).addValue(list.get(idx-1).value);
//                        list.remove(idx-1);
//                        idx--;
//                    }
//                } else {    // 중간 값, ⚠ 앞 뒤 둘 다 비교, 한 번에 비교 ㄱㄱ
//                    if(list.get(idx).value >= list.get(idx+1).value){   // 뒤의 값과 비교
//
//                        if(list.get(idx).value >= list.get(idx-1).value){   // 앞의 값과 비교
//                            list.get(idx).addValue(list.get(idx-1).value);
//                            list.remove(idx-1);
//                            idx--;
//                        }
//
//                        list.get(idx).addValue(list.get(idx+1).value);
//                        list.remove(idx+1);
//                    } else if(list.get(idx).value >= list.get(idx-1).value) {   // 앞의 값과 비교
//
//                        if (list.get(idx).value >= list.get(idx + 1).value) {   // 뒤의 값과 비교
//                            list.get(idx).addValue(list.get(idx + 1).value);
//                            list.remove(idx + 1);
//                        }
//
//                        list.get(idx).addValue(list.get(idx - 1).value);
//                        list.remove(idx - 1);
//                        idx--;
//                    }
//
//                }
//                idx++;
//            }
//        }
//        return list.get(0);
//    }

    // ✔ 정답 소스코드
    public long[] solution2() {
        while(q.size() > 1){ // 미생물이 하나 남을때까지
            // 📌 Deque 를 쓰는 이유 Stack 처럼 동작할 수 있지만 마지막에 Queue 의 값에 덮어써야하기 때문
            Deque<long[]> nextDays = new ArrayDeque<>();

            while(!q.isEmpty()){
                long[] cur = q.poll();
                // 미생물의 크기
                long value = cur[1];

                if(!nextDays.isEmpty()){
                    long[] last = nextDays.peekLast();  // 가장 마지막 작업 결과를 가져옴
                    // 이전값과 비교
                    if(last[1] <= value){   // 현재의 값이 이전 값보다 크거나 같다면
                        value += last[1];
                        nextDays.pollLast();
                    }
                }

                // 큐가 비어있지 않다면 뒤의 값과 비교 ( 중간값일 경우 )
                if(!q.isEmpty()){
                    long[] next = q.peek();
                    if(cur[1] >= next[1]){  // 다음값과 비교를 해서 연산
                        value += next[1];
                        q.poll();
                    }
                }
                cur[1] = value;
                nextDays.offer(cur);
            }
            q = nextDays;
        }

        return new long[] {q.peek()[1], q.peek()[0]};
    }
}