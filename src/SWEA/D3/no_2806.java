package SWEA.D3;

import java.util.*;
import java.io.*;

// SWEA D3
// 2806. N-Queen
public class no_2806 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int queenNum;
    static boolean[][] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int TC = Integer.parseInt(br.readLine().trim());

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= TC; tc++) {
            sb.append('#').append(tc).append(' ');
            queenNum = Integer.parseInt(br.readLine().trim());

            visited = new boolean[queenNum][queenNum];
            answer = 0;

            putQueen(0);  // 0번째 행부터 시작
            sb.append(answer).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void putQueen(int row) {
        if (row == queenNum) { // 전부 배치함
            answer++;
            return;
        }

        for (int col = 0; col < queenNum; col++) {
            if (isSafe(row, col)) {
                visited[row][col] = true;
                putQueen(row + 1);  // 다음 행
                visited[row][col] = false;
            }
        }
    }

    // 아래쪽은 확인할 필요 없음 -> 즉 왼쪽위, 위, 오른쪽위 3방향
    static int[][] dir = {
            {-1,0},
            {-1,1},
            {-1,-1}
    };
    private static boolean isSafe(int x, int y) {
        for(int d=0; d<3; d++) {
            for(int i=1; i<=x; i++) {
                int nx = x + dir[d][0] * i;
                int ny = y + dir[d][1] * i;

                if(nx < 0 || ny < 0 || nx >= queenNum || ny >= queenNum) break;   // 범위 벗어남
                if(visited[nx][ny]) return false;
            }
        }

        return true;
    }

}

/*
DFS 로 모든 칸을 탐색하는 경우 8^9 -> 1억이상
최대 10개 테스트케이스 -> 10초 => 시간 초과

1차원 배열로 생각해보자 O(n) 정도로 줄일수 있을 듯?
인덱스를 행으로 생각하고, 값을 퀸의 위치로
 */