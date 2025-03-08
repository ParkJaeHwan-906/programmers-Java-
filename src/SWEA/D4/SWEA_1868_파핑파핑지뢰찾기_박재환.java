package SWEA.D4;

import java.util.*;
import java.io.*;

public class SWEA_1868_파핑파핑지뢰찾기_박재환{
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase <= TC; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

        br.close();
    }

    static int mapSize;
    static char[][] map;
    static boolean[][] isChecked;
    static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new char[mapSize][mapSize];
        isChecked = new boolean[mapSize][mapSize];

        for(int row=0; row<mapSize; row++) {
            String mapInput = br.readLine().trim();
            for(int col=0; col<mapSize; col++) {
                map[row][col] = mapInput.charAt(col);
            }
        }

        getMinClick();
    }

    // 모든 칸에 수를 표시하기 위한 최소 클릭 횟수를 구한다.
    static void getMinClick() {
        int clickCnt = 0;
        // 전체 맵의 지뢰 현황을 체크한다.
        checkBomb();

//		// 지뢰 체크 맵 확인
//		for(char[] arr : map) {
//			System.out.println(Arrays.toString(arr));
//		}
        //-------------------------------------------------------
        // 전체 맵을 체크했다면
        // 최소 클릭 횟수를 구한다.
        for(int row=0; row<mapSize; row++) {
            for(int col=0; col<mapSize; col++) {
                // 인접지역에 지뢰가 없고, 아직 체크하지 않은 곳이면 탐색 ㄱㄱ
                if(map[row][col] == '0' && !isChecked[row][col]) {
                    clickGrid(row, col);
                    clickCnt++;
                }
            }
        }

        // 남은 칸 ( 인접한 구역에 지뢰가 있는 칸을 처리한다 )
        for(int row=0; row<mapSize; row++) {
            for(int col=0; col<mapSize; col++) {
                if(!isChecked[row][col]) {
                    clickCnt++;
                }
            }
        }
        sb.append(clickCnt);
    }

    // 1. 지뢰가 있는 칸은 클릭하지 않는다.
    // 지뢰가 있는 칸을 방문하지 않도록 미리 처리해둔다
    static void checkBomb() {
        for(int row=0; row<mapSize; row++) {
            for(int col=0; col<mapSize; col++) {
                if(map[row][col] == '*') {	// 절대 클릭 X
                    isChecked[row][col] = true;
                } else {	// 지뢰가 없음
                    // 주변 지뢰의 개수를 파악한다.
                    findBomb(row, col);
                }
            }
        }
    }


    // 주변을 탐색한다.
    // 현재 위치를 기준으로 8방향을 탐색하여 주변의 지뢰 개수를 파악한다.
    // 파악한 지뢰 개수를 map 에 표시해둔다.
    static int[] dx = {0,1,0,-1,1,1,-1,-1};
    static int[] dy = {1,0,-1,0,1,-1,1,-1};
    static void findBomb(int x, int y) {
        int cnt = 0;
        for(int i=0; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위를 벗어나거나, 방문한 이력이 있는 곳이면
            if (nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;

            if(map[nx][ny] == '*') cnt++;
        }

        map[x][y] = (char) (cnt+'0');
    }

    // 2. 클릭한 위치 인접 칸을 체크처리한다.
    // 0 인 칸을 우선적으로 클릭하므로
    static void clickGrid(int x, int y) {
        // 수정 : 재귀호출 대신 Queue 를 사용하여 실행시간 단축
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x,y});
        isChecked[x][y] = true;	// 클릭 처리

        while(!q.isEmpty()) {
            // 현재 처리하려는 칸
            int[] curPoint = q.poll();

            for(int dir=0; dir<8; dir++) {
                int nx = curPoint[0] + dx[dir];
                int ny = curPoint[1] + dy[dir];

                // 범위를 벗어나는 경우
                if (nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) continue;

                // 이미 체크한 구역인 경우
                if(isChecked[nx][ny]) continue;

                // 체크할 수 있음
                isChecked[nx][ny] = true;
                // 만약 인접한 칸도 0이라면 연쇄적으로 터뜨릴수 있음
                if(map[nx][ny] == '0') q.offer(new int[] {nx,ny});
            }
        }

    }

}

/*
 * R * C 크기의 표
 * 각 칸에는 지뢰가 있을수도 없을수도 있음
 *
 * 칸을 클릭했을 때 지뢰가 있다면 게임 끝
 * 지뢰가 없다면 8방향에 대해 몇 개의 지뢰가 있는지 0~8 사이의 숫자로 칸에 표시됨
 *
 * '*' : 지뢰
 * '.' : 아무것도 아님
 *
 * 지뢰가 있는 칸을 제외한 다른 모든 칸의 숫자들이 표시되려면 최소 몇 번의 클릭을 해야하는지
 *
 * 주변에 지뢰가 없다면, 8방향 모두 자동으로 숫자를 표시해준다.
 *
 * 1. 주변에 지뢰가 없는 칸을 우선적으로 클릭해서 연쇄적으로 찾아두는게 제일 최적일거같음
 * 		1-1. 주변에 지뢰가 없지만, 해당 영역이 경계면에 있다면, 다른 칸이 더 최적의 해가 될 수 있음
 *
 *  최대 N 은 300 즉 최대 map 의 크기는 90000
 */
