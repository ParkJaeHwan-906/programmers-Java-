package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 1회 정기 코딩 인증평가 기출] 로봇이 지나간 경로
// https://softeer.ai/practice/6275
public class no_6275 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int width, height;
    static char[][] map;
    public static void main(String[] args) throws IOException {
        no_6275 problem = new no_6275();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new char[height][width];
        for(int h=0; h<height; h++) {
            String input = br.readLine().trim();
            for(int w=0; w<width; w++) {
                map[h][w] = input.charAt(w);
            }
        }

        br.close();

        bw.write(problem.getCommand());
        bw.flush();
        bw.close();
    }

    // 명령어 기록
    StringBuilder sb = new StringBuilder();
    // 상하좌우
    private int[] dx = {0,1,0,-1};
    private int[] dy = {1,0,-1,0};
    private String getCommand() {

        Object[] startPack = findStart();
        int startX = (int)startPack[0];
        int startY = (int)startPack[1];
        int startDir = (int)startPack[2];

        sb.append(startX+1).append(' ').append(startY+1).append('\n').append(dirToImg(startDir)).append('\n');

        move(startX, startY, startDir);


        return sb.toString();
    }


    // ⚠️ 되돌아 가는 경우는 없으므로 회전은 한번만 가능하다
    private void move(int x, int y, int dir) {
        map[x][y] = '.';  // 시작점 방문 처리

        // 명령어를 찾는다
        // 계속 직진한다
        //  더 이상 못하면 회전한다
        int newDir = dir;  // 현재 보고 있는 방향을 기록
        while(true) {
            while(newDir == dir) {  // 현재 방향으로 이동할 수 있는만큼 모두 이동
                sb.append('A');
                // 2 칸 이동
                x = x + dx[newDir];
                y = y + dy[newDir];
                map[x][y] = '.';
                x = x + dx[newDir];
                y = y + dy[newDir];
                map[x][y] = '.';

                dir = newDir;
                newDir = findDir(x,y);
            }

            if(newDir == -1) break; // 더 이상 탐색할 곳이 없음

            // 회전 방향을 결정
            // ^ : 3, v : 1, < : 2, > : 0
            if((dir + 1) % 4 == newDir) sb.append('R');
            else if ((dir + 3) % 4 == newDir) sb.append('L');

            // 다시 진행을 위해 dir 과 newDir 을 맞춤
            dir = newDir;
        }
    }

//    // 회전 후 진행 방향을 알려줌
//    // ^ : 3, v : 1, < : 2, > : 0
//    private int rotateRobot(int dir, char command) {
//        return 4 + (command == 'L' ? (dir-1) % 4 : (dir+1)%4);
//    }

    // 방향을 화살표로 변환
    private String dirToImg(int dir) {
        StringBuilder dirImg = new StringBuilder();
        switch (dir){
            case 0 :
                dirImg.append('>');
                break;
            case 1 :
                dirImg.append('v');
                break;
            case 2 :
                dirImg.append('<');
                break;
            case 3 :
                dirImg.append('^');
                break;
        }
        return dirImg.toString();
    }

    private Object[] findStart() { // 시작 위치를 찾아준다
        // 모든 위치에서 본인 근처에 # 이 하나만 있는 경우를 찾아준다.
        for(int h=0; h<height; h++) {
            for(int w=0; w<width; w++) {
                if(map[h][w] == '#'){
                    int direction = findDir(h, w);
                    if(direction != -1) {   // 출발지 조건을 만족한다면
                        return new Object[] {h, w, direction};
                    }
                }
            }
        }
        return new Object[0];
    }

    private int findDir(int x, int y) { // 상하좌우를 확인하여 연결되어 있는 #의 위치를 찾는다
        int direction = -1;
        int count = 0;
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            // map 을 벗어난다
            if(nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
            // # 이 아님
            if(map[nx][ny] != '#') continue;
            // # 을 찾는다면 방향과 함께, 주변 #의 개수를 기록해준다.
            direction = dir;
            count++;
        }
        // 주변에 # 이 하나하면 방향을 반환, 두개 이상이거나, 없는 경우 -1 리턴
        return count == 1 ? direction : -1;
    }
}

/*
로봇은 상하좌우 중 한 방향을 바라본다

명령어
L : 왼쪽 90 도 회전
R : 오른쪽 90 도 회전
A : 바라보는 방향 두 칸 전진 ( map 범위 못 벗어남 )

⚠️ 회전을 구현

같은 칸을 방문하지 않음
입력할 명령어의 개수는 최소화 ( 여러개라면 그중 하나 )
-> 회전을 최소화 ( 어차피 전진은 A 밖에 못함 )

5 8
#######.
........
........
........
........

(1,1) > AAA
(1,7) < AAA

시작 위치?
모든 위치에서 본인 근처에 # 이 하나만 있는 경우
 */