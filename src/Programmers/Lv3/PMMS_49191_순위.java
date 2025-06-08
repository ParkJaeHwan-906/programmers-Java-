package Programmers.Lv3;

import java.util.*;

public class PMMS_49191_순위 {
    public static void main(String[] args) {
        int n = 5;
        int[][] results = {{4,3},{4,2},{3,2},{1,2},{2,5}};

        System.out.println(new PMMS_49191_순위().solution(n, results));
    }

    int[][] dist;              // 각 노드에서 모든 노드로의 거리
    int playerCnt;             // 순위가 명확한 선수의 수
    static final int INF = 987654321;
    public int solution(int n, int[][] results) {
        playerCnt = 0;
        dist = new int[n+1][n+1];
        init(n, results);
        return playerCnt;
    }

    /*
        그래프를 구성한다.
        자기 자신으로의 거리는 0
        연결되지 않는다면 INF 로 초기화한다.
     */
    void init(int n, int[][] results) {
        //      자기 자신으로의 거리는 0으로 초기화
        for(int from=1; from<n+1; from++) {
            for(int to=1; to<n+1; to++) {
                if(from == to) continue;
                dist[from][to] = INF;
            }
        }
        // 연결 정보를 갱신
        for(int[] conn : results) {
            int from = conn[0], to = conn[1];

            dist[from][to] = 1;
        }
        getAllDist();
    }

    void getAllDist() {
        // 플로이드 워셜 수행
        for(int mid=1; mid<dist.length; mid++) {
            for(int from=1; from<dist.length; from++) {
                for(int to=1; to<dist.length; to++) {
                    // 바로 가는것이 빠른지, 경유해서 가는것이 빠른지 비교
                    dist[from][to] = Math.min(dist[from][to], dist[from][mid] + dist[mid][to]);
                }
            }
        }

        countPlayers();
    }

    void countPlayers() {
        for(int i=1; i<dist.length; i++) {
            boolean b = true;
            for(int j=1; j<dist.length; j++) {
                if(i==j) continue;  // 자기 자신과의 경기는 제외
                if(dist[i][j] == INF && dist[j][i] == INF) { // 두 선수 사이의 경기 결과를 모를때
                    // 순위를 명확하게 알 수 없음
                    b = false;
                    break;
                }
            }
            if(b) playerCnt++;
        }
    }
}
/*
    n 명의 선수가 있고, 1대 1 방식으로 경기가 진행된다.
    [A, B] 의 입력은 -> A 가 B 를 이겼다는 의미이다.
    하지만 몇 경기 결과를 분실해서 정확하게 순위를 매길 수 없다.

    -> 각 선수들의 선행관계를 나타낸다.
    -> 특정 노드가 모든 노드와 연결되어 있다면 이는 순서를 정확하게 매길 수 있다.
    -> 플로이드워셜
 */