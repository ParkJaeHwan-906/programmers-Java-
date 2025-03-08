package SWEA.D4;

import java.util.*;
import java.io.*;

public class SWEA_1861_정사각형방_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase<=TC; testCase++) {
            sb.append('#').append(testCase).append(' ');

            init();

            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int roomNums;    // 방의 개수
    static int[][] rooms;   // 방의 정보
    static void init() throws IOException {
        roomNums = Integer.parseInt(br.readLine().trim());
        rooms = new int[roomNums][roomNums];

        for(int row=0; row<roomNums; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int col=0; col<roomNums; col++) {
                rooms[row][col] = Integer.parseInt(st.nextToken());
            }
        }
        findMaxMove();
    }

    // 이동 방향 : 상 하 좌 우
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int startRoomIdx, maxMoves;    // 탐색을 시작할 방 번호, 최대로 이동한 방 개수
    static int[][] accMoves;    // 현재 방에서 이동가능한 최대 방의 개수
    static void findMaxMove() {
        startRoomIdx = Integer.MAX_VALUE;
        maxMoves = Integer.MIN_VALUE;
        accMoves = new int[roomNums][roomNums];
        // 모든 방에서 출발하여 탐색할 수 있는 모든 탐색을 한다.
        for (int x = 0; x < roomNums; x++) {
            for (int y = 0; y < roomNums; y++) {
                int accMove = moves(x,y);

                // 현재 누적 값이 최대 이동 횟수보다 크거나
                // 같지만 시작방의 수가 더 작은 경우
                if (accMove > maxMoves || (accMove == maxMoves && rooms[x][y] < startRoomIdx)) {
                    maxMoves = accMove;
                    startRoomIdx = rooms[x][y];
                }


            }
        }
        sb.append(startRoomIdx).append(' ').append(maxMoves);
    }

    static int moves(int x, int y) {
        if(accMoves[x][y] != 0) {    // 이미 계산이 완료된 방이라면
            return accMoves[x][y];
        }

        accMoves[x][y] = 1; // 자기 자신의 개수를 초기값으로

        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 범위를 벗어나면 패스한다.
            if(nx<0 || ny <0 || nx >= roomNums || ny >= roomNums) continue;
            // 1 이 큰 곳이 아니라면 패스한다.
            if(rooms[x][y]+1 != rooms[nx][ny]) continue;

            // 현재 칸의 최대값을 갱신한다.
            // 추가적인 이동이 가능하다면 최대로 이동 가능한 경우를 구해와서 갱신한다. -> 바텀업
            accMoves[x][y] = Math.max(accMoves[x][y], moves(nx,ny)+1);
        }

        return accMoves[x][y];
    }
}
/*
   N^2 의 방이 N x N 의 형태로 늘어서있다.
   모든 방에는 1 이상 N^2 이하의 수가 적혀있다. -> 숫자는 모든 방이 다르다

   이동은 상하좌우 가능하다 -> 이동은 현재 방에 적힌 숫자보다 정확하게 1 커야한다.

   첫 시작을 어디서 해야 가장 많은 개수의 방을 이동할 수 있는지 구해라
   N 의 최대값 -> 1000 -> N^2 => 1,000,000
   O(N^2) ? => 오,,, 큰데...? -> DFS 는 힘들거같다는 생각

   DFS 와 DP 를 활용
   1. 기본적으로 탐색은 DFS 를 기반으로 한다.
    1-1. 각 격자에서 움직일 수 있는 모든 칸을 세어준다.
        a. 이때 DP 를 활용하여 각 격자에서 움직일 수 있는 최대 칸을 기록해준다. (바텀업)
    1-2. 이미 계산이 완료된 칸이라면 넘어간다. -> 가지치키
*/