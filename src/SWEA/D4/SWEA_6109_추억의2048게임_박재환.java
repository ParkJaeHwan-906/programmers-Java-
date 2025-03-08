package SWEA.D4;

import java.util.*;
import java.io.*;

public class SWEA_6109_추억의2048게임_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase<=TC; testCase++) {
            sb.append('#').append(testCase).append('\n');

            init();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

        br.close();
    }

    static int mapSize;    // 격자의 크기
    static ArrayDeque<Integer>[] map;
    static int dir;
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        mapSize = Integer.parseInt(st.nextToken());

        // 명령어의 방향을 결정한다.
        dir = decisionDir(st.nextToken());

        map = new ArrayDeque[mapSize];
        for(int idx=0; idx<mapSize; idx++) {
            map[idx] = new ArrayDeque<>();
        }

        // up / down
        if(dir == 1 || dir == 3) {
            for(int x=0; x<mapSize; x++) {
                st = new StringTokenizer(br.readLine().trim());
                for(int y=0; y<mapSize; y++) {
                    int ele = Integer.parseInt(st.nextToken());
                    if(ele == 0) continue;
                    map[y].offer(ele);
                }
            }
        }
        // right / left
        else {
            for(int x=0; x<mapSize; x++) {
                st = new StringTokenizer(br.readLine().trim());
                for(int y=0; y<mapSize; y++) {
                    int ele = Integer.parseInt(st.nextToken());
                    if(ele == 0) continue;
                    map[x].offer(ele);
                }
            }
        }


        // map 생성 확인 출력
//        for(int idx=0; idx<mapSize; idx++) {
//            System.out.print(idx+" : ");
//            System.out.println(map[idx]);
//        }

        moveBlocks();
//        System.out.println();
//        // 이동 후 map 생성 확인 출력
//        for(int idx=0; idx<mapSize; idx++) {
//            System.out.print(idx+" : ");
//            System.out.println(map[idx]);
//        }

        // 들어온 명령어에 따라 출력 방향을 바꾼다.

        // up / down
        if(dir == 1 || dir == 3) {
            for (int y = 0; y < mapSize; y++) {
                for (int x = 0; x < mapSize; x++) {
                    sb.append(map[x].poll()).append(' ');
                }
                sb.append('\n');
            }
        }
        // left / right
        else {
            for (int x = 0; x < mapSize; x++) {
                for (int y = 0; y < mapSize; y++) {
                    sb.append(map[x].poll()).append(' ');
                }
                sb.append('\n');
            }
        }
    }

    /*
     * 들어온 명령어에 따라 블록을 이동시킨다
     * down / right : offerFirst 연산
     * up / left : offerLast 연산
     */
    static void moveBlocks() {
        // 초기 탐색 위치를 결정한다.
        // down / right 의 경우 왼쪽에서 오른쪽으로 -> 0
        // up / left 의 경우 위에서 아래로 -> -1
        int searchDir = (dir == 0 || dir == 1) ? 0 : -1;

        for(int idx=0; idx<mapSize; idx++) {
            ArrayDeque<Integer> subDeque = new ArrayDeque<>();    // 처리 완료 상태의 원소를 저장
            while(!map[idx].isEmpty()) {    // 현재 Deque 를 모두 처리할 때 까지
                // 현재 노드를 뽑는다.
                int curNode = searchDir == 0 ? map[idx].pollLast() : map[idx].pollFirst();
                // 다음 노드를 확인한다.
                int nextNode = -1;
                if(!map[idx].isEmpty()) {
                    nextNode = searchDir == 0 ? map[idx].peekLast() : map[idx].peekFirst();
                }

                // 현재 노드와 비교 노드 출력
//                System.out.println("현재 노드 : " + curNode);
//                System.out.println("비교 노드 : " + nextNode);

                if(curNode == nextNode && curNode != 2048) { // 합칠 수 있다면 원소를 합친다.
//                    System.out.println("add");
                    nextNode = searchDir == 0 ? map[idx].pollLast() : map[idx].pollFirst();
                    curNode += nextNode;
                }

                if(searchDir == 0) {
                    subDeque.offerFirst(curNode);
                } else {
                    subDeque.offerLast(curNode);
                }

                // 합치는 작업 이후 현재 Deque 상태 출력
//                System.out.println(map[idx]);
            }

            // 0 값 처리
            while(subDeque.size() < mapSize) {
                if(searchDir == 0) {
                    subDeque.offerFirst(0);
                } else {
                    subDeque.offerLast(0);
                }
            }
            map[idx] = subDeque;
//            System.out.println();
        }

    }

    /*
     * 입력받은 명령어로 방향을 결정한다.
     *
     * up : 아래에서 위로 탐색해야한다.
     * down : 위에서 아래도 탐색해야한다.
     * left : 왼쪽으로 탐색해야한다.
     * right : 오른쪽으로 탐색해야한다.
     */
    static int decisionDir(String command) {
        int dir = -1;
        switch (command) {
            case "right":
                dir = 0;
                break;
            case "down":
                dir = 1;
                break;
            case "left":
                dir = 2;
                break;
            case "up":
                dir = 3;
                break;
        }

        return dir;
    }


}

/*
 * 격자 위에서 숫자가 적힌 타일들을 밀어 합치고, 최종적으로 2048을 만드는 것이 목표
 * 한번 타일을 밀면 격자 위에 있는 모든 타일이 그 방향으로 밀린다. -> 방향은 상하좌우
 *
 * 두 타일의 숫자가 같다면 합쳐진다. (+ 연산) -> 합치는 연산은 한번만
 * 다른 타일은 합쳐질 수 없음
 *
 * 연산을 모두 수행하고나면 격자가 어떻게 변할지 계산하는 프로그램을 작성헤라
 *
 * 이동은 한번만 한다
 */