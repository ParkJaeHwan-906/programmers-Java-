package Level_2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 프로그래머스 Lv.2
// 전력망을 둘로 나누기
// https://school.programmers.co.kr/learn/courses/30/lessons/86971
public class no_86971 {
    boolean[][] connection;
    public int solution(int n , int[][] wires){
        int answer = n;

        connection = new boolean[n+1][n+1];
        for(int[] arr : wires){
            connection[arr[0]][arr[1]] = true;
            connection[arr[1]][arr[0]] = true;
        }

        // 하나씩 끊어보며 연결되어 있는 송신탑을 계산?
        for(int[] arr : wires){
            connection[arr[0]][arr[1]] = false;
            connection[arr[1]][arr[0]] = false;

            answer = Math.min(answer, bfs(n, arr[0]));

            connection[arr[0]][arr[1]] = true;
            connection[arr[1]][arr[0]] = true;
        }

        return answer;
    }

    public int bfs(int n, int start){
        boolean[] visited = new boolean[n+1];
        int cnt = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()){
            int point = q.poll();
            visited[point] = true;

            for(int i = 1; i < visited.length; i++){
                if(visited[i]) continue;

                if(connection[point][i]){
                    q.add(i);
                    cnt++;
                }
            }
        }
        return (int)Math.abs((n-cnt) - cnt);
    }

    public static void main(String[] args){
        int n = 9;
        int[][] wires = new int[][]{{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};

        no_86971 problem = new no_86971();
        System.out.println(problem.solution(n, wires));
    }
}
