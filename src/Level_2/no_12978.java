package Level_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 프로그래머스 Lv.2
// Summer/Winter Coding(~2018) 배달
// https://school.programmers.co.kr/learn/courses/30/lessons/12978?language=java
class Node implements Comparable<Node> {
    int idx;
    int distance;
    public Node(int idx, int distance){
        this.idx = idx;
        this.distance = distance;
    }

    // 거리가 짧은 것이 높은 우선순위를 갖도록
    @Override
    public int compareTo(Node other){
        if(this.distance < other.distance) return -1;

        return 1;
    }
}

public class no_12978 {
    /*
    1 번 마을에서 출발해 경로가 K 이하인 곳의 개수
    road 순서 (a, b, c) => a b 는 연결된 마을, c는 거리
    다익스트라 사용
     */
    ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    int[] distance;

    public void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;
        while(!pq.isEmpty()){
            Node node = pq.poll();
            int dist = node.distance;
            int now = node.idx;

            if(distance[now] < dist) continue;

            for(int i = 0; i < graph.get(now).size(); i++){
                int cost = distance[now] + graph.get(now).get(i).distance;
                if(cost < distance[graph.get(now).get(i).idx]){
                    distance[graph.get(now).get(i).idx] = cost;
                    pq.offer(new Node(graph.get(now).get(i).idx, cost));
                }
            }
        }
    }

    public int solution(int N, int[][] road, int K){
        int answer = 0;
        distance = new int[N+1];

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<Node>());
        }

        for(int[] arr : road){
            int a = arr[0];
            int b = arr[1];
            int c = arr[2];
            // 양방향 통행
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        Arrays.fill(distance, 999999999);

        dijkstra(1);

        for(int i = 1; i<=N; i++){
            System.out.println(distance[i]);
            if(distance[i] <= K){
                answer++;
            }
        }
        return answer;
    }
    public static void main(String[] args){
        no_12978 problem = new no_12978();
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;
        int result = problem.solution(N, road, K);
        System.out.println(result);
    }
}
