package CodingTest;

import java.io.*;

public class SofteerAutoEver241102_2 {
    static int n,m,l,t;
    static int[][][] board;
    static boolean[][][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nmlt = br.readLine().split(" ");
        n = Integer.parseInt(nmlt[0]);
        m = Integer.parseInt(nmlt[1]);
        l = Integer.parseInt(nmlt[2]);
        t = Integer.parseInt(nmlt[3]);

        board = new int[n][m][l];
        visited = new boolean[n][m][l];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                String[] arr = br.readLine().split(" ");
                for(int k = 0; k < l; k++){
                    board[i][j][k] = Integer.parseInt(arr[k]);
                }
            }
        }

        SofteerAutoEver241102_2 problem = new SofteerAutoEver241102_2();
        visited[0][0][0] = true;
        int answer = problem.findMax(0,0,0, board[0][0][0],0);
        System.out.println(answer);

    }

    int[][] move = new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    public int findMax(int startX, int startY, int startZ, int sum, int depth){
        // 목적지에 도착하고, 장애물을 t 번 이하로 이동했을 경우
        if(startX == n-1 && startY == m-1 && startZ == l-1 && depth <= t) return sum;

        for(int i = 0; i < 3; i++){
            int newX = startX + move[i][0];
            int newY = startY + move[i][1];
            int newZ = startZ + move[i][2];

            // 주어진 범위를 벗어나면 패스
            if(newX < 0 || newY < 0 || newZ < 0 || newX >= n || newY >= m || newZ >= l ) continue;
            // 이미 방문했다면 패스
            if(visited[newX][newY][newZ]) continue;

            visited[newX][newY][newZ] = true;
            // 장애물인경우
            if(board[newX][newY][newZ] == -1){
                findMax(newX, newY, newZ, sum, depth+1);
            } else{
                findMax(newX, newY, newZ, sum + board[newX][newY][newZ], depth);
            }
            visited[newX][newY][newZ] = false;
        }

        return -1;
    }
}
/*
n,m,l 크기의 3차원 배열이 주어진다
장애물은 -1, 빈 공간은 0, 보상은 양의 정수로 주어진다.

장애물은 최대 t번까지 이동 가능하며
1,1,1에서 출발하여 n,m,l까지 이동한다 했을때 구할 수 있는 최대 보상의 수를 구하여라.

현 위치가 (i,j,k) 라고할 때 이동할 수 있는 방향은 제한되어있다.
(i+1, j, k)
(i, j+1, k)
(i, j, k+1)

목적지에 도달할 수 없는 경우 -1을 출력한다
 */