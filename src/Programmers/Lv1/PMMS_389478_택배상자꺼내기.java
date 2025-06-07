package Programmers.Lv1;

import java.util.*;
import java.io.*;

public class PMMS_389478_택배상자꺼내기 {
    public static void main(String[] args) throws IOException {
        int n=22, w=6, num=8;

        System.out.println(new PMMS_389478_택배상자꺼내기().solution(n, w, num));
    }

    public int solution(int n, int w, int num) {    // n : 택배 개수, w : 너비, num : 타겟
        // 박스를 쌓는다.
        makeBoxStack(n,w);

        // 쌓은 박스에서 타겟 위치를 찾는다.
        int[] point = findTarget(num, w);

        // 위로 몇 개의 박스가 있는지 확인한다.
        int remove=0;
        for(int x=point[0]; x>-1; x--) {
            if(boxes[x][point[1]] == 0) break;
            remove++;
        }

        return remove;
    }

    int[] findTarget(int num, int w) {
        for(int x=0; x<h; x++) {
            for(int y=0; y<w; y++) {
                if(boxes[x][y] == num) return new int[] {x, y};
            }
        }
        return null;
    }

    /*
        박스를 쌓는다.
     */
    int[] dy = {1,-1};
    int[][] boxes;      // 쌓여 있는 박스를 기록
    int h;
    void makeBoxStack(int n, int w) {
        h = n%w == 0 ? n/w : n/w+1;

        boxes = new int[h][w];
        /*
            박스가 쌓이는 방향은
            왼쪽 위 오른쪽 위 반복
         */
        int dir = 0;    // 시작방향은 왼쪽 ( 방향 전환은 (dir+1)%2 )
        int x=h-1, y=0; // 시작 위치는 좌하단

        int num = 1;    // 상자 번호
        while(num <= n) {
            boxes[x][y] = num++;

            // 새로운 위치
            int ny = y + dy[dir];

            if(ny < 0 || ny >= w) { // 너비를 벗어나는 경우
                // 위로 이동, 기존 y 위치로 이동
                x--;
                ny = y;
                dir = (dir+1)%2;
            }
            y = ny;
        }
    }
}
