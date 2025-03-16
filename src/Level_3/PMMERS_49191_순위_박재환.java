package Level_3;

import java.util.*;

public class PMMERS_49191_순위_박재환 {
    public static void main(String[] args) {
        int n = 5;
        int[][] result = {
                {4,3},
                {4,2},
                {3,2},
                {1,2},
                {2,5}
        };

        System.out.println(new PMMERS_49191_순위_박재환().solution(n, result));
    }

    List<Integer>[] graph;  // 선수 간의 연결 ( 정방항 )
    List<Integer>[] reverseGraph;  // 선수 간의 연결 ( 역방항 )
//    int[] inEdge;           // 진입 차수를 기록
    public int solution(int n, int[][] results) {
//        inEdge = new int[n+1];
        graph = new ArrayList[n+1];
        reverseGraph = new ArrayList[n+1];
        for(int player=0; player<=n; player++) {
            graph[player] = new ArrayList<>();
            reverseGraph[player] = new ArrayList<>();
        }

        addEdges(results);

        // 그래프 연결 정보 확인
//        for(int player=1; player <= n; player++) {
//            System.out.print(player + " : ");
//            for(int win : graph[player]) {
//                System.out.print(win+" ");
//            }
//            System.out.println();
//        }

        // 진입 차수 확인
//        for(int player=1; player <= n; player++) {
//            System.out.printf("%d 의 진입 차수 : %d\n", player, inEdge[player]);
//        }

        return calcRank(n);
    }

    boolean[] matched;
    int calcRank(int n) {
        int correctPlayer = 0;

        // 모든 선수의 연결 관계를 확인한다.
        for(int player=1; player<=n; player++) {
            matched = new boolean[n+1];
            matched[player] = true;     // 자기 자신의 순위를 구할 필요 없음

            search(player, false);
            search(player, true);

            if(allMatch(n)) correctPlayer++;
        }

        return correctPlayer;
    }

    boolean allMatch(int n) {
        for(int player=1; player <= n; player++) {
            if(!matched[player]) return false;
        }
        return true;
    }

    /*
        정방향 그래프를 탐색한다.
     */
    void search(int curPlayer, boolean isReverse) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(curPlayer);

        while(!q.isEmpty()) {
            int nowPlayer = q.poll();
            for(int player : isReverse ? reverseGraph[nowPlayer] : graph[nowPlayer]) {
                // 이미 선행 관계가 있는 선수라면 패스
                if(matched[player]) continue;

                matched[player] = true;
                q.offer(player);
            }
        }
    }

    /*
        그래프의 연결 정보와, 각 노드의 진입 차수를 구한다. ( 정방향, 역방향 )
     */
    void addEdges(int[][] results) {
        for(int[] match : results) {
            int winner = match[0];
            int loser = match[1];

            graph[winner].add(loser);
            reverseGraph[loser].add(winner);
//            inEdge[loser]++;
        }
    }
}
/*
    1~n 번의 선우가 있음
    권투는 1:1 방식
    A 선수가 B 선수보다 실력이 좋다면 A 선수는 B 선수를 항상 이긴다.

    경기 결과가 몇 개 주어지는데, 순위를 매길 수 있는 선수의 수를 구해라

    즉 그래프는 유향 그래프이다.
    노드들 간의 선행 관계를 찾아야한다. -> 위상 정렬??

    범위가 크지 않아서
 */