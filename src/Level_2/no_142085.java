package Level_2;

import java.security.Permission;
import java.util.Arrays;
import java.util.PriorityQueue;

// 프로그래머스 Lv.2
// 디펜스 게임
// https://school.programmers.co.kr/learn/courses/30/lessons/142085
public class no_142085 {
    /*
    보유병사 n 명
    매 라운드 ememy[i] 명의 적이 등장
    1 : 1 로 막을 수 있음

    무적권 이라는 스킬이 있음. 사용 시 병사 소모 없이 라운드 클리어 최대 k 번 사용 가능
     */
    private class Node implements Comparable<Node>{
        private int index;
        private int value;

        public Node(int index, int value){
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return o.value - this.value;
        }
    }
    public int solution(int n, int k, int[] enemy){
        int answer = enemy.length;

//        int totalEnemy = Arrays.stream(enemy).sum();
//        if(n >= totalEnemy) return enemy.length;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int nCopy = n;
        for(int i = 0; i<enemy.length; i++){
            n -= enemy[i];
            pq.add(new Node(i, enemy[i]));

            if(n < 0){
                if(k > 0 && !pq.isEmpty()){
                    Node node = pq.poll();
                    k--;
                    n += node.value;
                }else{
                    answer = i;
                    break;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args){
        int n = 7;
//        int n = 2;
        int k = 3;
//        int k = 4;
        int[] enemy = new int[] {4,2,4,5,3,3,1};
//        int[] enemy = new int[] {3,3,3,3};

        no_142085 problem = new no_142085();

        System.out.println(problem.solution(n,k,enemy));
    }
}
