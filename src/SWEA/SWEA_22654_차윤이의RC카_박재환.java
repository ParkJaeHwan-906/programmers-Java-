package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_22654_차윤이의RC카_박재환 {
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

        System.out.println(sb);
    }

    static StringTokenizer st;
    static int mapSize, command;
    static char[][] map;
    static int sX, sY;
    static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new char[mapSize][mapSize];
        for(int x=0; x<mapSize; x++) {
            String input = br.readLine().trim();
            for(int y=0; y<mapSize; y++) {
                map[x][y] = input.charAt(y);

                if(map[x][y] == 'X') {
                    sX = x;
                    sY = y;
                }
            }
        }

        command = Integer.parseInt(br.readLine().trim());
        for(int i=0; i<command; i++) {
            st = new StringTokenizer(br.readLine().trim());

            int commandLen = Integer.parseInt(st.nextToken());

            // 실질적인 명령어
            String commandStr = st.nextToken();
            char[] arr = new char[commandLen];
            for(int j=0; j<commandLen; j++) {
                arr[j] = commandStr.charAt(j);
            }

            doCommand(arr);
        }
    }

    /*
        전달받은 명령어를 실행한다.
     */
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void doCommand(char[] arr) {
        int[] rcCar = {sX, sY, 3};

        for(char c : arr) {
            switch (c) {
                case 'R' :  // 오른쪽으로 회전한다.
                    rcCar[2] = (rcCar[2]+1)%4;
                    break;
                case 'L' :  // 왼쪽으로 회전한다.
                    rcCar[2] = (rcCar[2]+3)%4;
                    break;
                case 'A' :  // 전진한다 ( 나무가 있다면 갈 수 없다 )
                    int nx = rcCar[0] + dx[rcCar[2]];
                    int ny = rcCar[1] + dy[rcCar[2]];

                    if(nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize) break;
                    if(map[nx][ny] == 'T') break;

                    rcCar[0] = nx;
                    rcCar[1] = ny;
                    break;
            }
        }

        sb.append(map[rcCar[0]][rcCar[1]] == 'Y' ? 1 : 0).append(' ');
    }
}

/*
    N x N 격자
    G : 이동 가능
    T : 나무 ( 이동 X )
    X : 현 위치 -> 항상 위를 바라보고 시작한다.
    Y : 목표 지점

    A : 앞으로 이동 -> 현재 방향 정보로 이동
    L : 왼쪽으로 회전
    R : 오른쪽으로 회전
 */