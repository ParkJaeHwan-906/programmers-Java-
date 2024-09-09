package Level_2;

import java.util.LinkedList;
import java.util.Queue;

// 프로그래머스 Lv.2
// 리코쳇 로봇
// https://school.programmers.co.kr/learn/courses/30/lessons/169199
public class no_169199 {
    /*
    장애물 혹은 맵의 끝에 도달하기 전까지는 한방향으로만 이동한다.

    ⚠️ 최소로 움직이는 횟수를 구해야하므로 BFS 를 사용한다.
     */

    private final int[] dx = new int[] {1, 0, -1, 0};
    private final int[] dy = new int[] {0, 1, 0, -1};

    private final char ROBOT = 'R', DISABLE = 'D', GOAL = 'G';

    private int n, m;

    private class Moving{
        int x, y, depth;

        public Moving(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    public int solution(String[] board){
        int answer = 0;

        n = board.length;
        m = board[0].length();

        Moving robot = null;
        Moving goal = null;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                char c = board[i].charAt(j);

                if(c==ROBOT){
                    robot = new Moving(i,j,0);
                }else if(c == GOAL){
                    goal = new Moving(i,j,0);
                }
            }
        }

        answer = bfs(board, robot, goal);

        return answer;
    }

    private int bfs(String[] board, Moving robot, Moving goal){
        Queue<Moving> q = new LinkedList<>();
        q.add(robot);
        boolean[][] visited = new boolean[n][m];
        visited[robot.x][robot.y] = true;

        while(!q.isEmpty()){
            Moving moving = q.poll();

            if(moving.x == goal.x && moving.y == goal.y){
                return moving.depth;
            }

            for(int i=0; i<4; i++){
                int nx = moving.x;
                int ny = moving.y;

                while(inRange(nx, ny) && board[nx].charAt(ny) != DISABLE){
                    nx += dx[i];
                    ny += dy[i];
                }

                nx -= dx[i];
                ny -= dy[i];

                if(visited[nx][ny] || (moving.x == nx && moving.y == ny)) continue;

                visited[nx][ny] = true;
                q.add(new Moving(nx, ny, moving.depth+1));
            }
        }
        return -1;
    }

    private boolean inRange(int x, int y){
        return x >= 0 && y >= 0 && x < n && y < m;
    }
    public static void main(String[] args) {
        no_169199 problem = new no_169199();
        String[] board = new String[]
                {"...D..R", ".D.G...", "....D.D", "D....D.", "..D...."};
        System.out.println(problem.solution(board));
    }
}
