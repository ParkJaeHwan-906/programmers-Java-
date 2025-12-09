package Beakjoon;

import java.util.*;
import java.io.*;

public class 톱니바퀴 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    /**
     * 총 8개의 톱니를 가진 톱니바퀴가 4개 있다.
     * 톱니는 N 또는 S 극 중 하나를 가지고 있다.
     * 톱니바퀴에는 번호가 매겨져있는데, 가장 왼쪽부터 [1,2,3,4] 이다.
     *
     * 톱니바퀴를 총 K 번 회전시키려고 한다.
     * 톱니바퀴의 회전은 한 칸을 기준으로 한다.
     * 회전은 시계 방향과, 반시계 방향이 있다.
     *
     * 회전시킬 톱니바퀴와, 회전시킬 방향을 결정해야한다.
     * 톱니바퀴를 회전시킬 때, 옆 톱니바퀴와 맞닿은 면의 극이 다르다면, 반대방향으로 회전한다.
     *
     * -> 마주보는 극이 같다면 회전 X
     * -> 마주보는 극이 다르다면 반대 방향으로 회전
     */
    static StringTokenizer st;
    static int[][] cogWheels;
    static void init() throws IOException {
        // 1. 톱니바퀴 입력
        cogWheels = new int[5][8];      // 총 4개의 톱니바퀴 (1~4), 8개의 톱니
        for(int i=1; i<5; i++) {
            String input = br.readLine().trim();
            // 1-1. N극은 0, S극은 1
            for(int j=0; j<8; j++) cogWheels[i][j] = input.charAt(j) - '0';
        }
        // 2. 톱니바퀴 회전
        int commandCnt = Integer.parseInt(br.readLine().trim());
        while(commandCnt-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int targetCogWheel = Integer.parseInt(st.nextToken());
            int rotateDir = Integer.parseInt(st.nextToken());       // 시계방향 1, 반시계방향 -1

            rotateCogWheels(targetCogWheel, rotateDir);
        }

        int finalScore = 0;
        for(int i=1; i<5; i++) {
            if(cogWheels[i][0] == 0) continue;

//            finalScore += (int)Math.pow(2, i-1);

            // 비트 연산자를 이용하면 조금 더 빠름
            finalScore += 1 << (i-1);
        }
        System.out.println(finalScore);
    }

    /**
     * @param targetCogWheel
     * : 회전시킬 톱니바퀴의 번호 ( 1 ~ 4 )
     * @param rotateDir
     * : 회전시킬 방향 ( 시계 1, 반시계 -1 )
     */
    static void rotateCogWheels(int targetCogWheel, int rotateDir) {
        // 각 톱니바퀴에서 맞닿는 영역은 정해져있음
        // 2 - 6 / 6 - 2
        boolean[] isOpposite = new boolean[4];      // 맞닿는 영역은 3군데 있음 ( 1-based )

        // 1. 맞닿는면이 서로 다른 극인지 확인
        for(int i=1; i<4; i++) {
            int prevCog = cogWheels[i][2];
            int nextCog = cogWheels[i+1][6];
            // 서로 다른 극을 가지고 있다면 true, 아니라면 false
            isOpposite[i] = prevCog != nextCog;
        }

        // 2. 회전 시키기
        // targetCogWheel을 기준으로 왼쪽, 오른쪽 각각 탐색
        // 2-1. 왼쪽 부터
        int tempRotateDir = rotateDir;
        for(int i=targetCogWheel-1; i > 0; i--) {
            if(!isOpposite[i]) break;     // 회전되지 않는다면, 이후 위치의 톱니바퀴들도 연속적으로 돌지 않음
            // 서로 다른 극을 가지고 있다면, 기존 회전 방향과 반대 방향으로 회전함
            tempRotateDir = tempRotateDir == 1 ? -1 : 1;
            rotateCogWheel(i, tempRotateDir);
        }
        // 2-2. 오른쪽 까지
        tempRotateDir = rotateDir;
        for(int i=targetCogWheel+1; i<5; i++) {
            if(!isOpposite[i-1]) break;     // 회전되지 않는다면, 이후 위치의 톱니바퀴들도 연속적으로 돌지 않음
            tempRotateDir = tempRotateDir == 1 ? -1 : 1;
            rotateCogWheel(i, tempRotateDir);
        }

        rotateCogWheel(targetCogWheel, rotateDir);
    }

    static void rotateCogWheel(int targetCogWheel, int rotateDir) {
        // 1 : 시계방향 
        // -1 : 반시계방향
        switch(rotateDir) {
            case 1:
                int tempCog = cogWheels[targetCogWheel][7];
                for(int i=7; i>0; i--) {
                    cogWheels[targetCogWheel][i] = cogWheels[targetCogWheel][i-1];
                }
                cogWheels[targetCogWheel][0] = tempCog;

                // 조금 더 깔끔한 방식
                // 배열 복사
//                int[] tempArr = new int[8];
//                for(int i=0; i<8; i++) {
//                    tempArr[(i+1)%8] = cogWheels[targetCogWheel][i];
//                }
//                cogWheels[targetCogWheel] = tempArr;
                break;
            case -1:
                tempCog = cogWheels[targetCogWheel][0];
                for(int i=0; i<7; i++) {
                    cogWheels[targetCogWheel][i] = cogWheels[targetCogWheel][i+1];
                }
                cogWheels[targetCogWheel][7] = tempCog;

                // 조금 더 깔끔한 방식
                // 배열 복사
//                tempArr = new int[8];
//                for(int i=0; i<8; i++) {
//                    tempArr[i] = cogWheels[targetCogWheel][(i+1)%8];
//                }
//                cogWheels[targetCogWheel] = tempArr;
                break;
        }
    }
}
