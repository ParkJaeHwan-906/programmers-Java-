package Level_2;

import java.util.LinkedList;
import java.util.Queue;

// 프로그래머스 Lv.2
// 미로 탈출
// https://school.programmers.co.kr/learn/courses/30/lessons/159993
public class no_159993 {
    public int solution(String[] maps){
        int answer = 0;
        // 시작 위치와 레버의 위치 좌표를 저장
        int[] start = new int[2];
        int[] labor = new int[2];

        for(int i = 0; i < maps.length; i++){
            if(maps[i].indexOf("S") > -1){
                start[0] = i;
                start[1] = maps[i].indexOf("S");
            }
            if(maps[i].indexOf("L") > -1){
                labor[0] = i;
                labor[1] = maps[i].indexOf("L");
            }
        }
//        System.out.println(start[0] + ", " + start[1]);
//        System.out.println(labor[0] + ", " + labor[1]);

        int result1 = bfs(maps, start, 'L');
        int result2 = bfs(maps, labor, 'E');
        return (result1 > -1 && result2 > -1) ? result1 + result2 : -1;
    }

    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    public int bfs(String[] maps, int[] start, char target){
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        // 큐 내부에 x, y, 거리 저장
        q.add(new int[] {start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()){
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];
            int dist = arr[2];

            if(maps[x].charAt(y) == target) return dist;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && ny >= 0 && nx < maps.length && ny < maps[0].length() && !visited[nx][ny] && maps[nx].charAt(ny) != 'X'){
                    visited[nx][ny] = true;
                    q.add(new int[] {nx, ny, dist+1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        no_159993 problem = new no_159993();
        String[] maps = {"SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"};
//        String[] maps = {"LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"};
        System.out.println(problem.solution(maps));
    }
}
