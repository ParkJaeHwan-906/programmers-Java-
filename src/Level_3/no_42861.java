package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 프로그래머스 Lv.3
// 섬 연결하기
// https://school.programmers.co.kr/learn/courses/30/lessons/42861
public class no_42861 {
    public static void main(String[] args)throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
        int n = 4;
        int[][] costs = new int[][] {{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}};

        no_42861 problem = new no_42861();
        System.out.println(problem.solution(n, costs));
    }


    // 📌 Prim 알고리즘 
    int[][] conn;
    boolean[] visited;
    public int solution(int n, int[][] costs){
        conn = new int[n][n];
        visited = new boolean[n];
        // 연결망 구현
        for(int[] i : costs){
            int a = i[0];
            int b = i[1];
            int cost = i[2];
            conn[a][b] = cost;
            conn[b][a] = cost;
        }
        int result = bfs(costs);
        return result;
    }

    private int bfs(int[][] costs){
        // 비용을 기준으로 오름차순 우선순위 큐
        PriorityQueue<int[]>  pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);

        // 시작 위치 설정 (0에서 0으로 가는 비용 0)
        pq.add(new int[] {0,0,0});
        int price = 0;
        int count = 0;

        while(!pq.isEmpty()){
            // 현재 위치
            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];
            int cost = cur[2];
            if(visited[b]) continue;

            visited[b] = true;

            price += cost;
            count++;
            for(int i=0; i< visited.length; i++){
                // 현재의 도착지에서 새로운 목적지까지 연결되어있는 경우
                // 아직 방문하지 않은 곳의 경우
                if(conn[b][i] != 0 && !visited[i]){
                    pq.add(new int[] {b, i, conn[b][i]});
                }
            }
        }
        return price;
    }
}
