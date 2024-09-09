package Exhaustive_Search;

import java.util.LinkedList;
import java.util.Queue;

public class exhaustive_search {
    // 프로그래머스 Lv.2 전력망을 둘로 나누기
    static boolean[][] arr;
    public int solution(int n, int[][] wires) {
        int answer = n;
        // 인접 행렬 만들기
        arr = new boolean[n+1][n+1];
        for(int i=0; i<wires.length; i++){
            arr[wires[i][0]][wires[i][1]] = true;
            arr[wires[i][1]][wires[i][0]] = true;
        }
        // 선을 하나씩 끊기
        for(int i=0; i<wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];

            arr[a][b] = false;
            arr[b][a] = false;

            answer = Math.min(answer, bfs(n, a));

            arr[a][b] = true;
            arr[b][a] = true;
        }

        return answer;
    }

    public int bfs(int n, int start) {
        boolean[] visited = new boolean[n+1];
        int cnt = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while(!q.isEmpty()){
            int point = q.poll();
            visited[point] = true;

            for(int i=1; i<visited.length; i++){
                if(visited[i]) continue;
                if(arr[point][i]){
                    q.add(i);
                    cnt++;
                }
            }
        }
        return (int)Math.abs((n-2*cnt));
    }

    // 실행 함수
    public static void main(String[] args){
        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        exhaustive_search e1 = new exhaustive_search();
        int result = e1.solution(n, wires);
        System.out.println(result);
    }
}
