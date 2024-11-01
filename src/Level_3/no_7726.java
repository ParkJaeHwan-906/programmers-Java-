package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 소프티어 Lv.3
// 나무 섭지
// https://softeer.ai/practice/7726
public class no_7726 {
    static List<int[]> ghost = new ArrayList<>();
    static int[] goal;
    static int[] start;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // n,m 입력
        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        // map 입력
        char[][] map = new char[n][m];
        for(int i=0; i<n; i++){
            // map 을 한줄씩 입력받음
            String s = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = s.charAt(j);
                // 유령, 목적지, 남수의 좌표를 기록
                if(s.charAt(j) == 'G'){
                    ghost.add(new int[] {i,j});
                }
                else if(s.charAt(j) == 'D'){
                    goal = new int[]{i,j};
                }
                else if(s.charAt(j) == 'N'){
                    start = new int[] {i,j};
                }
            }
        }

        no_7726 problem = new no_7726();
        problem.solution(map);
    }

    int[][] ghostMap;
    public boolean solution(char[][] map){
        // 유령이 각 칸으로 갈 수 있는 시간 구하기
        ghostMap = new int[map.length][map[0].length];

        // 각 행을 최대 시간으로 채우기
        for (int i = 0; i < ghostMap.length; i++) {
            Arrays.fill(ghostMap[i], 987654321);
        }

        for(int[] ghostPoint : ghost){
            boolean[][] visited = new boolean[map.length][map[0].length];
            ghostMove(ghostPoint[0], ghostPoint[1], visited);
        }

        for(int[] arr : ghostMap){
            for(int i : arr){
                System.out.print(i+" ");
            }
            System.out.println();
        }

        return true;
    }

    int[] dx = new int[] {0, 1, 0, -1}; // x좌표 이동
    int[] dy = new int[] {1, 0, -1, 0}; // y좌표 이동

    public void ghostMove(int startX, int startY, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY, 0});

        while (!q.isEmpty()) {
            int[] node = q.poll();
            ghostMap[node[0]][node[1]] = Math.min(ghostMap[node[0]][node[1]], node[2]);
            visited[node[0]][node[1]] = true; // 방문 처리

            for (int i = 0; i < 4; i++) {
                int ny = node[0] + dy[i]; // y좌표 계산
                int nx = node[1] + dx[i]; // x좌표 계산

                if (nx >= 0 && nx < ghostMap[0].length && ny >= 0 && ny < ghostMap.length && !visited[ny][nx]) {
                    q.offer(new int[]{ny, nx, node[2] + 1}); // 순서 주의
                }
            }
        }
    }
}

//    public class Node{
//        int x;
//        int y;
//        int cnt;
//
//        public Node(int x, int y, int cnt){
//            this.x = x;
//            this.y = y;
//            this.cnt = cnt;
//        }
//    }


/*
미로는 n x m 크기
각 격자 칸은 남우(N), 출구(D), 유령(G), 빈 공간(.), 벽(#) 으로 이루어짐

남우 : 1초에 상하좌우 한칸이동(벽이 아닌곳)
유령 : 1초에 상하좌우 아무곳이나 한칸 이동 (벽도 가능)
 */