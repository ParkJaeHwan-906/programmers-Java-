package Level_2;

import java.util.LinkedList;
import java.util.Queue;

// 프로그래머스 Lv.2
// 게임 맵 최단거리
// https://school.programmers.co.kr/learn/courses/30/lessons/1844?language=java
public class no_1844 {
    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    public int solution(int[][] maps){
        // 최단 거리 -> BFS
        int answer = -1;

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1));

        while(!q.isEmpty()){
            Node node = q.poll();

            // 도착지에 도달
            if(node.x == maps.length-1 && node.y == maps[0].length-1){
                answer = node.count;
                break;
            }

            for(int i = 0; i < 4; i++){
                int x = node.x + dx[i];
                int y = node.y + dy[i];

                if(x >= 0 && y >= 0 && x < maps.length && y < maps[0].length && maps[x][y] == 1){
                    q.add(new Node(x, y, node.count +1));
                    maps[x][y] = 0;
                }
            }
        }

        return answer;
    }

    class Node{
        int x;
        int y;
        int count;

        public Node(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args){
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};

        no_1844 problem = new no_1844();
        System.out.println(problem.solution(maps));
    }
}
