package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1992_쿼드트리_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        init();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static int mapSize;     // 격자의 크기
    static char[][] map;     // 격자
    static void init() throws IOException {
        mapSize = Integer.parseInt(br.readLine().trim());
        map = new char[mapSize][mapSize];

        for(int x=0; x<mapSize; x++) {
            String mapInput = br.readLine().trim();
            for(int y=0; y<mapSize; y++) {
                map[x][y] = mapInput.charAt(y);
            }
        }
        br.close();
        zipVideo(0,0,mapSize);
    }

    // 구역을 분할하며, 같은 색상으로 이루어져 있는지 확인한다.
    // 만약 같은 색상으로 이루어져있다면, 0 또는 1을 반환한다.
    // 같은 색상으로 이루어져있지 않다면 구역을 4개로 분할하여 재 탐색한다.
    //      이때 ( ) 안에 값을 작성해주어야한다.
    // [탐색을 시작할 x, 탐색을 시작할 y, 탐색 범위 size]
    static void zipVideo(int x, int y, int size) {
        if(canZip(x,y,size)) {  // 현재 범위가 압축할 수 있다면
            sb.append(map[x][y]);
            return;
        }

        // 압축할 수 없다면
        // 영역을 분할하여 압축을 시도한다.
        sb.append('(');
        zipVideo(x, y, size/2); // 왼쪽 위
        zipVideo(x, y+size/2, size/2); // 오른쪽 위
        zipVideo(x+size/2, y, size/2); // 왼쪽 아래
        zipVideo(x+size/2, y+size/2, size/2); // 오른쪽 아래
        sb.append(')');
    }


    // 같은 색상으로 이루어져있는지 확인한다.
    // 탐색하고자하는 위치부터 탐색 범위를 확인하며 모두 같은 값인지 확인한다.
    // [탐색을 시작할 x, 탐색을 시작할 y, 탐색 범위 size]
    static boolean canZip(int x, int y, int size) {
        char standard = map[x][y];  // 비교값을 고정한다.
        for(int row=x; row<x+size; row++) {
            for(int col=y; col<y+size; col++) {
                if(standard != map[row][col]) return false;
            }
        }
        return true;
    }
}

/*
    흰 : 0, 검 : 1

    모두 0 으로만 되어있으면 압축 결과 0
    모두 1 로만 되어있으면 압축 결과 1

    만약 섞여있다면
    왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래
    총 4 개의 구역으로 나누어 압축

    모든 구역이 0 또는 1 로 이루어질때까지 분할이 필요하다.
 */