package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_7369_나무수확 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int mapSize;     //  최대 1000
    static long[][] map;     // 각 칸은 최대 1000
    static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new long[mapSize+1][mapSize+1];

        for(int x=1; x<mapSize+1; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=1; y<mapSize+1; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        // 입력 확인
//        for(long[] arr : map) {
//            System.out.println(Arrays.toString(arr));
//        }

        getMaxFruits();
    }

    /*
        수로가 놓일 수 있는 방향은 오른쪽, 아래 두 방향이다.
        -> 동적계획법을 사용해 접근해본다.
        -> 이전의 최적 해는 반대로 생각하면, 왼쪽, 위 두 방향에서 올 수 있다.
        -> 각 위치에서 선택 가능한 이전의 최적 해를 선택한다.
     */
    static void getMaxFruits() {
        /*
            이전 최적해를 사용할 DP 배열을 생성한다
            [x][y][누적 합, 경로에 있는 최대값]
         */
        long[][][] dpArr = new long[mapSize+1][mapSize+1][2];

        for(int x=1; x<mapSize+1; x++) {
            for(int y=1; y<mapSize+1; y++) {
                // 이전 위치 ( 위, 왼쪽 ) 들 중에서 최적 해를 선택한다.
                // 경로에 있는 최대값을 갱신하며 진행한다.

                // 이전 위치 중 최적 해를 선택한다.

                // 1 번 방법
                if(dpArr[x-1][y][0] > dpArr[x][y-1][0]) {   // 위에서 내려오는 값이 더 큰 경우
                    dpArr[x][y][0] = dpArr[x-1][y][0] + map[x][y];
                    dpArr[x][y][1] = Math.max(map[x][y], dpArr[x-1][y][1]);
                } else if(dpArr[x-1][y][0] < dpArr[x][y-1][0]) {   // 왼쪽에서 오는 값이 더 큰 경우
                    dpArr[x][y][0] = dpArr[x][y-1][0] + map[x][y];
                    dpArr[x][y][1] = Math.max(map[x][y], dpArr[x][y-1][1]);
                } else {    // 둘이 같은 경우 -> 경로 상 최대 값만 갱신
                    dpArr[x][y][0] = dpArr[x][y-1][0] + map[x][y];
                    dpArr[x][y][1] = Math.max(map[x][y], Math.max(dpArr[x][y-1][1], dpArr[x-1][y][1]));
                }

                // 2 번 방법
                if(dpArr[x-1][y][0] + Math.max(dpArr[x-1][y][1], map[mapSize][mapSize]) >
                        dpArr[x][y-1][0] + Math.max(dpArr[x][y-1][1], map[mapSize][mapSize])) {   // 위에서 내려오는 값이 더 큰 경우
                    dpArr[x][y][0] = dpArr[x-1][y][0] + map[x][y];
                    dpArr[x][y][1] = Math.max(map[x][y], dpArr[x-1][y][1]);
                } else if(dpArr[x-1][y][0] + Math.max(dpArr[x-1][y][1], map[mapSize][mapSize]) <
                        dpArr[x][y-1][0] + Math.max(dpArr[x][y-1][1], map[mapSize][mapSize])) {   // 왼쪽에서 오는 값이 더 큰 경우
                    dpArr[x][y][0] = dpArr[x][y-1][0] + map[x][y];
                    dpArr[x][y][1] = Math.max(map[x][y], dpArr[x][y-1][1]);
                } else {    // 둘이 같은 경우 -> 경로 상 최대 값만 갱신
                    dpArr[x][y][0] = dpArr[x][y-1][0] + map[x][y];
                    dpArr[x][y][1] = Math.max(map[x][y], Math.max(dpArr[x][y-1][1], dpArr[x-1][y][1]));
                }
            }
        }

        // 1
        System.out.println(
                Math.max(
                        dpArr[mapSize-1][mapSize][0] + Math.max(map[mapSize][mapSize], dpArr[mapSize-1][mapSize][1]),
                        dpArr[mapSize][mapSize-1][0] + Math.max(map[mapSize][mapSize], dpArr[mapSize][mapSize-1][1])
                ) + map[mapSize][mapSize]
        );
        // 2
        System.out.print(dpArr[mapSize][mapSize][0] + dpArr[mapSize][mapSize][1]);
    }
}

/*
    N x N 크기의 격자
    각 칸은 열매의 수확량을 나타낸다.

    수로를 설치하려한다.
    수로 설치 방향은 오른쪽 혹은 아래쪽 으로 설치가 가능하다.
    (1,1) -> (N,N)

    수로에는 스프링클러를 놓을 수 있다.
    스프링클러를 놓으면 수확량이 2배가 된다.
 */