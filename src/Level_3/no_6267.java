package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [21년 재직자 대회 예선] 좌석 관리
// https://softeer.ai/practice/6267
public class no_6267 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb = new StringBuilder();
    static final String IN_EATTING = "%d already seated.\n";
    static final String IN_LEAVE = "%d already ate lunch.\n";
    static final String IN_FULL = "There are no more seats.\n";
    static final String IN_SEAT = "%d gets the seat (%d, %d).\n";

    static final String OUT_NOT_YET = "%d didn't eat lunch.\n";
    static final String OUT_ATE = "%d already left seat.\n";
    static final String OUT_SEAT = "%d leaves from the seat (%d, %d).\n";
    static int X, Y;
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        map = new boolean[X][Y];
        int TC = Integer.parseInt(st.nextToken());

        Map<Integer, int[]> inMap = new HashMap<>();    // 들어온 사람을 기록 (x, y)
        Set<Integer> outSet = new HashSet<>(); // 나간 사원을 기록 ( 중복 X )

        for(int tc=0; tc<TC; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            String command = st.nextToken();
            int id = Integer.parseInt(st.nextToken());

            if(command.equals("In")) {
                in(id, inMap, outSet);
            } else {
                out(id, inMap, outSet);
            }

        }

        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int[] dx = new int[] {0,1,0,-1};
    static int[] dy = new int[] {1,0,-1,0};

    public static void in(int id, Map<Integer, int[]> inMap, Set<Integer> outSet) {
        // 1. 이미 먹고 나간 사원인지 확인한다.
        if(outSet.contains(id)) {
            sb.append(String.format(IN_LEAVE, id));
            return;
        }

        // 2. 먹고 있는지 확인한다.
        if(inMap.containsKey(id)) {
            sb.append(String.format(IN_EATTING, id));
            return;
        }

        // 3. 앉을 수 있는 자리가 있는지 탐색한다.
        for(int x=0; x<X; x++) {
            for(int y=0; y<Y; y++) {
                if(!map[x][y]) {    // 아직 방문하지 않았다면 ( 아무도 앉아있지 않다면 )
                    if(checkNearBy(x,y)) { // 앉을 수 있는지 확인한다
                        inMap.put(id, new int[] {x,y});

                        // 방문 처리를 해준다.
                        map[x][y] = true;

                        sb.append(String.format(IN_SEAT, id, x+1, y+1));
                        return;
                    }
                }
            }
        }

        sb.append(IN_FULL);
    }

    public static void out(int id, Map<Integer, int[]> inMap, Set<Integer> outSet) {
        if(inMap.containsKey(id)) { // 현재 밥을 먹고 있다면
            int[] points = inMap.get(id);
            inMap.remove(id);

            map[points[0]][points[1]] = false;

            outSet.add(id);
            sb.append(String.format(OUT_SEAT, id, points[0]+1 , points[1]+1));
            return;
        }

        if(outSet.contains(id)) {   // 이미 먹고 나갔다면
            sb.append(String.format(OUT_ATE,id));
            return;
        }

        sb.append(String.format(OUT_NOT_YET, id));
    }

    public static void checkSeat(int x, int y, boolean b) { // 앉은 자리를 체크한다.
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= X || ny >= Y) continue;
            map[nx][ny] = b ? true : false; // In ( true ) 명령어가 들어오면 true 로 값을, Out ( false ) 명령어가 들어오면 false 로 값을 변경한다.
        }
    }

    public static boolean checkNearBy(int x, int y) { // 상하좌우를 확인하여 앉을 수 있는지 확인한다.
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= X || ny >= Y) continue;    // 범위를 벗어나는 경우 넘어간다
            if(map[nx][ny]) return false;   // 상하좌우에 누군가 있다면 앉을 수 없다
        }

        return true;
    }
}
