package Programmers.Lv3;

import java.util.*;

public class PMMS_250134_수레움직이기 {
    public static void main(String[] args) {
        int[][] maze = {{5,0,3,5},{0,0,0,0},{5,0,4,0},{5,2,1,0}};

        System.out.println(new PMMS_250134_수레움직이기().solution(maze));
    }

    /*
        n, m 은 1 ~ 4
        maze 원소
        0 : 빈 칸
        1 : 빨간 수레의 시작
        2 : 파란 수레의 시작
        3 : 빨간 수레의 도착
        4 : 파란 수레의 도착
        5 : 벽
     */
    int[][] maze;
    boolean[][][] visited;  // [n][m][수레]
    int minTurn;
    public int solution(int[][] maze) {
        /*
            각 수레는 한 번 이동한 칸은 다시 갈 수 없음 -> visited
            동시에 같은 칸에 있을 수 없음
            서로 위치를 바꿀 수 없음 -> 가는 도중 마주칠 수 없음 -> 수레마다 이동했던 칸을 각자 기록
                                                                + 시간을 기록
         */
        this.maze = maze;
        visited = new boolean[maze.length][maze[0].length][2];
        minTurn = Integer.MAX_VALUE;
        
        checkDepartArrive();

        // 출발 위치 체크
        visited[depart[0][0]][depart[0][1]][0] = true;  // 빨강
        visited[depart[1][0]][depart[1][1]][1] = true;  // 파랑
        findMinTurn(depart[0][0], depart[0][1], depart[1][0], depart[1][1], false, false, 0);

        // 파랑 먼저 이동 -> 빨강 이동

        return minTurn == Integer.MAX_VALUE ? 0 : minTurn;
    }



    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    // 수레 idx, x, y, 현재 걸린 시간, 이전 수레가 걸린 시간
    void findMinTurn(int rx, int ry, int bx, int by, boolean rArrive, boolean bArrive,
                        int turn) {
        if(turn >= minTurn) return;      // 이전의 최적해를 넘는 경우

        // 빨간 수레 도착
        if(!rArrive && arrive[0][0] == rx && arrive[0][1] == ry) {
            rArrive = true;
        }
        // 파란 수레 도착
        if(!bArrive && arrive[1][0] == bx && arrive[1][1] == by) {
            bArrive = true;
        }
        // 모든 수레 도착
        if(rArrive && bArrive) {
            minTurn = Math.min(minTurn, turn);
            return;
        }

        // 수레를 이동
        // 1. 빨간 수레 이동
        for(int dir=0; dir<4; dir++) {
            int nrx = rx + dx[dir];
            int nry = ry + dy[dir];

            // 수레가 도착하지 않았을 때만 이동
            if(rArrive) {
                nrx = rx;
                nry = ry;
            } else {
                // maze 를 벗어나는지, 벽인지
                if (isBoard(nrx, nry)) continue;
                // 이전에 방문 이력이 있는지
                if(visited[nrx][nry][0]) continue;
            }

            // 파란 수레가 도착지에 도착하여 고정되어 있는 경우
            if(bArrive && arrive[1][0] == nrx && arrive[1][1] == nry) continue;

            // 1-1. 파란 수레 이동
            for(int dir2=0; dir2<4; dir2++) {
                int nbx = bx + dx[dir2];
                int nby = by + dy[dir2];

                // 수레가 도착하지 않았을 때만 이동
                if(bArrive) {
                    nbx = bx;
                    nby = by;
                } else {
                    // maze 를 벗어나는지, 벽인지
                    if (isBoard(nbx, nby)) continue;
                    // 이전에 방문 이력이 있는지
                    if(visited[nbx][nby][1]) continue;
                }

                // 빨간 수레가 도착지에 도착하여 고정되어 있는 경우
                if(rArrive && arrive[0][0] == nbx && arrive[0][1] == nby) continue;

                // 동시에 같은 위치에 도착하는지
                if(nrx == nbx && nry == nby) continue;
                // 서로 위치를 바꾸는지 ( 이동 중 충돌 )
                if(nrx == bx && nry == by && nbx == rx && nby == ry) continue;

                visited[nrx][nry][0] = true;
                visited[nbx][nby][1] = true;
                findMinTurn(nrx, nry, nbx, nby, rArrive, bArrive, turn+1);
                visited[nrx][nry][0] = false;
                visited[nbx][nby][1] = false;
            }
        }
    }

    boolean isBoard(int x, int y) {
        return x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 5;
    }

    /*
        각 수레의 출발지 / 도착지 를 찾는다.
        1 : 빨간 수레의 시작 칸
        2 : 파란 수레의 시작 칸
        3 : 빨간 수레의 도착 칸
        4 : 파란 수레의 도착 칸
     */
    int[][] depart;
    int[][] arrive;
    void checkDepartArrive() {
        depart = new int[2][2];
        arrive = new int[2][2];

        for(int x=0; x<maze.length; x++) {
            for(int y=0; y<maze[x].length; y++) {
                switch(maze[x][y]) {
                    case 1:
                        depart[0] = new int[] {x, y};
                        break;
                    case 2 :
                        depart[1] = new int[] {x, y};
                        break;
                    case 3 :
                        arrive[0] = new int[] {x, y};
                        break;
                    case 4 :
                        arrive[1] = new int[] {x, y};
                        break;
                }
            }
        }
    }
}

/*
    n x m 격자 
    각 칸에는 빨간 수레 / 파란 수레 존재 
    각 수레는 시작 칸에서 도착 칸까지 이동해야함 
    
    모든 수레가 도착 칸으로 이동하면 퍼즐을 풀 수 있다. 
    각 턴마다 모든 수레를 상하좌우로 인접한 칸 중 한 칸으로 이동 
    <규칙>
    - 수레는 벽이나 격자 판 밖으로 움직일 수 없음 
    - 수레는 자신이 방문했던 칸으로 움직일 수 없음
    - 도착 칸에 도달하면 더 이상 움직이지 않음 
    - 동시에 두 개 이상의 수레가 동일한 칸에 놓일 수 없음
    - 수레끼리 자리를 바꾸며 움직일 수 없음

    퍼즐을 푸는데 필요한 턴의 최솟값을 반환 / 풀 수 없는 경우 0 반환
 */
