package Programmers.Lv3;

import java.sql.Array;
import java.util.*;

public class PMMS_118669_등산코스정하기 {
    public static void main(String[] args) {
        int n = 6;
        int[][] paths = {
                {1,2,3},
                {2,3,5},
                {2,4,2},
                {2,5,4},
                {3,4,4},
                {4,5,3},
                {4,6,1},
                {5,6,1}
        };
        int[] gates = {1,3};
        int[] summits = {5};
        System.out.println(Arrays.toString(new PMMS_118669_등산코스정하기().solution(n,paths,gates,summits)));
    }

    /*
        1 ~ n 번의 노드가 있다 ( 출입구, 쉼터, 산봉우리 )
        각 노드는 양방향 통행이 가능한 통로로 연결되어 있다.

        등산코스는 방문할 지점 번호들을 순서대로 나열하여 표현한다.
        휴식은 쉼터 혹은 산봉우리를 방문할 때마다 취할 수 있다.
        휴식 없이 이동해야하는 가장 긴 등산코스 시간을 itensity 라고 부른다.

        itensity 가 최소가 되도록 등산코스를 정한다.
        -> 원래 출입구로 돌아와야한다.
        -> 산 봉우리를 한 곳만 방문한다.
     */
    int n;
    int[] answer;
    int[] gates;
    int[] summits;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.gates = gates;
        this.summits = summits;
        this.n = n;
        // n 의 최대 값은 50,000 -> TSP 는 불가능
        // 가장 빠르게 올라가면 가장 빠르게 내려온다?
        // 대칭 형태?
        init(paths);

        // 출발지를 기준으로, 봉우리간의 최단거리만 구한다
        findBestRoute();

        // itensity 가 최소가 되는 산봉우리, 최소값
        return answer;
    }

    List<int[]>[] graph;
    void init(int[][] paths) {
        answer = new int[] {-1, Integer.MAX_VALUE};
        graph = new ArrayList[n+1];
        for(int i=0; i<n+1; i++) graph[i] = new ArrayList<>();

        for(int[] conn : paths) {
            int from = conn[0];
            int to = conn[1];
            int time = conn[2];
            graph[from].add(new int[] {to, time});
            graph[to].add(new int[] {from, time});
        }
    }

    // 최소 itensity 를 찾는다.
    // 출입구는 시작과 끝 한 번씩만 방문한다.
    // 봉우리는 딱 한 개의 봉우리만 방문한다.
    Set<Integer> summitSet;
    Set<Integer> gateSet;
    void findBestRoute() {
        summitSet = new HashSet<>();
        gateSet = new HashSet<>();
        for(int i : summits) summitSet.add(i);
        for(int i : gates) gateSet.add(i);
        searchRoute();
    }

    void searchRoute() {
        int[] summitArrived = new int[n+1];
        Arrays.fill(summitArrived, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[1], b[1]));
        for(int gate : gates) {
            pq.offer(new int[] {gate, 0});
            summitArrived[gate] = 0;
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curTime = cur[1];

            if(curTime > summitArrived[curNode]) continue;
            if(summitSet.contains(curNode)) continue;

            for(int[] conn : graph[curNode]) {
                int connNode = conn[0];
                int conntime = conn[1];

                if(gateSet.contains(connNode)) continue;
                int newIntensity = Math.max(curTime, conntime);
                if(newIntensity >= summitArrived[connNode]) continue;
                summitArrived[connNode] = newIntensity;
                pq.offer(new int[] {connNode, newIntensity});
            }
        }

        for(int summit : summits) {
            if(answer[1] > summitArrived[summit]) answer = new int[] {summit, summitArrived[summit]};
            else if(answer[1] == summitArrived[summit]) answer[0] = Math.min(summit, answer[0]);
        }
    }
}
