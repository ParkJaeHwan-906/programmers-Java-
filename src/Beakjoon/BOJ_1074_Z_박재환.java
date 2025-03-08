package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_1074_Z_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        init();
        bw.flush();
        bw.close();
    }

    static int mapSize, col, row;       // 배열의 크기, 가로, 세로
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        br.close();

        findSeq();
    }

    /*
        찾고자하는 영역이 몇 번째 구역에 있는지를 우선적으로 판단한다.

        사분면을 기준으로 찾고자하는 원소가 있는 사분면을 탐색 후
        row, col 값을 보정해준다. -> 이는 나눈 구역에서의 다음 탐색을 위함이다.
     */
    static void findSeq() throws IOException {
        int size = (int) Math.pow(2, mapSize);
        int seq = 0;    // 찾고자하는 영역의 탐색 순서

        // 구역을 계속해서 분할해가며 탐색한다.
        while(size > 1) {
            size /= 2;  // 현재 영역을 반으로 나눈다.

            // 왼쪽 위
            if(row < size && col < size) continue;
            // 오른쪽 위
            else if(row  < size && col >= size)  {
                seq += size * size;
                col -= size;
            }
            // 왼족 아래
            else if(row >= size && col < size) {
                seq += size * size * 2;
                row -= size;
            }
            // 오른쪽 아래
            else {
                seq += size * size * 3;
                row -= size;
                col -= size;
            }
        }

        bw.write(String.valueOf(seq));
    }
}

/*
    2^N * 2^N 크기의 배열을 Z 모양으로 탐색한다.

   탐색 순서 : 2*2 -> 4*4 -> 8*8
   첫 입력 사각형을 가장 작은 단위 2*2 까지 쪼개서 순차적으로 탐색한다.

   N 의 최대 값은 15, 탐색 배열의 최대 크기는 2^15

   왼쪽위 -> 오른쪽 위 -> 왼쪽 아래 -> 오른쪽 아래 순서로 탐색한다.
 */