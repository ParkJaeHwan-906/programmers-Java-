package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_5656_벽돌깨기_박재환 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase < TC+1; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        br.close();
        System.out.println(sb);
    }

    static StringTokenizer st;
    static int tryCnt, width, height;   // 구슬을 던지는 횟수, 가로, 세로
    static int minBlock;
    static void init() throws IOException {
        minBlock = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine().trim());
        tryCnt = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        int[][] map = new int[height][width];
        for (int x = 0; x < height; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int y = 0; y < width; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        getMaxBreak(0, map);

        sb.append(minBlock);
    }

    /*
        구슬은 가로로만 움직인다.
        이때 가로의 최대 길이는 12 이다.

        구슬을 던지는 최대 횟구는 4 이다.
        O (12^4) 완전 탐색 가능
     */
    static void getMaxBreak(int tryIdx, int[][] map) {
        // 구슬 던지는 횟수를 모두 사용했다면
        if(tryIdx == tryCnt) {
            // 현재 남아있는 블록의 개수를 구한다.
            minBlock = Math.min(minBlock, countRemainBlock(map));

            return;
        }

        // 구슬을 던질 수 있다.
        // 0 ~ width 범위를 하나씩 탐색한다.
        // ?? 벽돌을 하나도 깨지 못하는 경우가 있나 ?? ( 일단 나중에 )
        boolean isBreak = false;        // 블록을 하나도 깨지 못하는 경우 체크
        for(int y=0; y<width; y++) {
            // 현재 열에 부술 수 있는 블록이 있는지 확인한다.
            // 행 인덱스를 반환한다.
            int targetIdx = isWorthIt(y, map);

            if(targetIdx == -1) continue;   // 부술 수 있는 블록이 없다. -> 다음 열 탐색

            // 1. 블록을 부순다.
            int[][] mapCopy = breakBlock(targetIdx, y, map);
            isBreak = true;
            // 2. 다음 탐색을 진행한다.
            getMaxBreak(tryIdx+1, mapCopy);
            // 3. 원상 복구한다.
        }

        if(!isBreak) {
            // 현재 남아있는 블록의 개수를 구한다.
            minBlock = Math.min(minBlock, countRemainBlock(map));
        }
    }

    static int countRemainBlock(int[][] map) {
        int cnt = 0;
        for(int x=0; x<height; x++) {
            for (int y = 0; y < width; y++) {
                if(map[x][y] > 0) cnt++;
            }
        }
        return cnt;
    }

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[][] breakBlock(int targetX, int targetY, int[][] map) {
        // map 배열을 복사해서 원본 배열을 건들이지 않고, 새로운 배열을 만든다.
        int[][] mapCopy = new int[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                mapCopy[x][y] = map[x][y];
            }
        }

        // 타겟 위치를 기준으로 상 하 좌 우 로 깨트린다.
        Queue<int[]> q = new LinkedList<>();    // 연쇄적으로 터뜨릴 범위를 저장한다. [x, y, 범위]
        q.offer(new int[] {targetX, targetY, mapCopy[targetX][targetY]});
        while(!q.isEmpty()) {   // 더 이상 터뜨릴 수 없을 때 까지
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int range = cur[2];

            mapCopy[x][y] = 0;
            for(int i=1; i<range; i++) {
                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir]*i;
                    int ny = y + dy[dir]*i;

                    // 범위를 벗어난다면
                    if(nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
                    // 이미 터져있다면
                    if(mapCopy[nx][ny] == 0) continue;

                    // 터뜨릴 수 있다면
                    q.offer(new int[] {nx, ny, mapCopy[nx][ny]});
                    mapCopy[nx][ny] = 0;
                }
            }
        }

        // 비어있는 공간을 당겨준다.
        fillBlank(mapCopy);

        return mapCopy;
    }

    // 하단에서 위로 탐색하며, 빈 공간을 채운다.
    // 현재 행에 블록 개수를 세어준 뒤, 하단에서부터 채워준다.
    static void fillBlank(int[][] map) {
        for(int y=0; y<width; y++) {
            // 현재 행에 블록들을 기록한다.
            Queue<Integer> q = new LinkedList<>();
            for(int x=height-1; x>-1; x--) {
                if(map[x][y] > 0) q.offer(map[x][y]);
                map[x][y] = 0;
            }

            // 하단에서부터 블록을 순차적으로 채운다.
            int x = height-1;
            while(!q.isEmpty()) {
                map[x--][y] = q.poll();
            }
        }
    }

    // 해당 열에 부술만한 블록이 있는지
    // 있다면 해당 위치를 반환한다.
    static int isWorthIt(int y, int[][] map) {
        for(int x=0; x<height; x++) {
            if(map[x][y] > 0) return x;
        }
        return -1;
    }
}

/*
    구슬은 N 번 쏠 수 있다.
    W x H 격자가 주어진다.

    0     : 빈 공간
    1 ~ 9 : 벽돌

    구슬은 좌 우 로만 움직인다.
    항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
    -> 구슬이 명중한 벽돌은 상하좌우로 벽돌에 적힌 숫자 - 1 칸 만큼 같이 제거된다.

    터지는 것은 전이된다.

    최대한 많은 벽돌을 제거했을 때, 남은 벽돌의 수를 구하라
 */