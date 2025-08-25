package Programmers.Lv3;

import java.util.*;

public class PMMS_72413_합승택시요금 {
    public static void main(String[] args) {
        int n = 6, s = 4, a = 6, b = 2;
        int[][] fares = {
                {4,1,10},
                {3,5,24},
                {5,6,2},
                {3,1,41},
                {5,1,24},
                {4,6,50},
                {2,4,66},
                {2,3,22},
                {1,6,25}
        };

        System.out.println(new PMMS_72413_합승택시요금().solution(n,s,a,b,fares));
    }

    int[][] distMap;
    int minCost;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int singleCostFinal = 0, multiCostFinal = 0;
        /*
            n : 3 ~ 200
            fares : (from, to, cost)
            ---
            고려사항
            1. 경유지가 존재한다.
            2. 노드의 개수가 300 개이다. ( 1 ~ n 번 )
            3. 합승을 하지 않고 이동하는 경우가 더 싸다면 합승을 하지 않는다.
            ---
            데려다주고 가는 것이 아닌, 중간 지점을 찾아서 내려준다.
            => 플로이드 워셜 O(n^3)
         */
        init(n, fares);     // 경로 연결 구성
        findMinCost(n);      // 플로이드 워셜
        calcPrice(n, s, a, b);
        return minCost;
    }

    final int INF = 20_000_001;
    void init(int n, int[][] fares) {
        minCost = Integer.MAX_VALUE;
        distMap = new int[n+1][n+1];
        for(int from=1; from<n+1; from++) {
            for(int to=1; to<n+1; to++) {
                if(from == to) distMap[from][to] = 0;
                else distMap[from][to] = INF;
            }
        }

        for(int[] conn : fares) {
            int from = conn[0];
            int to = conn[1];
            int cost = conn[2];
            distMap[from][to] = cost;
            distMap[to][from] = cost;
        }
    }

    void findMinCost(int n) {
        for(int mid=1; mid<n+1; mid++) {
            for(int from=1; from<n+1; from++) {
                for(int to=1; to<n+1; to++) {
                    if(from == to) continue;
                    distMap[from][to] = Math.min(distMap[from][to],
                            distMap[from][mid]+distMap[mid][to]);
                }
            }
        }
    }

    void calcPrice(int n, int s, int a, int b) {
        // 1. 각자 택시를 타는 경우
        minCost = Math.min(minCost, distMap[s][a]+distMap[s][b]);
        // 2. 중간지점에서 택시를 타는 경우
        for(int mid=1; mid<n+1; mid++) {
            minCost = Math.min(minCost,
                    distMap[s][mid]+distMap[mid][b]+distMap[mid][a]);
        }
    }
}
