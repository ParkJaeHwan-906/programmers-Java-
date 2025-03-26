package Level_3;

import java.util.*;

public class PMMERS_84021_퍼즐조각채우기_박재환 {

    public static void main(String[] args) {
        int[][] game_board = {
                {1,1,0,0,1,0},
                {0,0,1,0,1,0},
                {0,1,1,0,0,1},
                {1,1,0,1,1,1},
                {1,0,0,0,1,0},
                {0,1,1,1,0,0}};

        int[][] table = {
                {1,0,0,1,1,0},
                {1,0,1,0,1,0},
                {0,1,1,0,1,1},
                {0,0,1,0,0,0},
                {1,1,0,1,1,0},
                {0,1,0,0,0,0}
        };

        System.out.println(new PMMERS_84021_퍼즐조각채우기_박재환().solution(game_board,table));
    }

    int[] dx = {0,1,0,-1};
    int[] dy = {1,0,-1,0};
    // 블록들을 저장할 배열
    List<boolean[][]> tableBlocks;
    List<boolean[][]> gameBoardBlocks;
    public int solution(int[][] game_board, int[][] table) {
        tableBlocks = new ArrayList<>();
        gameBoardBlocks = new ArrayList<>();

        findBlock(table,tableBlocks,1);
        findBlock(game_board,gameBoardBlocks,0);

//        System.out.println("보드");
//        for(boolean[][] arr : gameBoardBlocks) {
//        	for(boolean[] arr2 : arr) {
//        		for(boolean b : arr2) {
//        			System.out.print(b ? 1:0);
//        		}
//        		System.out.println();
//        	}
//        	System.out.println();
//        }
//
//        System.out.println("테이블");
//        for(boolean[][] arr : tableBlocks) {
//        	for(boolean[] arr2 : arr) {
//        		for(boolean b : arr2) {
//        			System.out.print(b ? 1:0);
//        		}
//        		System.out.println();
//        	}
//        	System.out.println();
//        }

        return 0;
    }

    /*
     * 도형을 비트로 만든다. ( = 문자열 )
     * rotate 값이 true 일 경우 90 도씩 총 3번 즉 4개의 케이스를 만들어 저장한다. 
     */
    static void makeBit(List<boolean[][]> blocks, boolean rotate) {
    	for(boolean[][] block : blocks) {
    		StringBuilder sb = new StringBuilder();
    		// 정방향일 때, 저장 
    		// 좌 -> 우 
    		for(boolean[] arr : block) {
    			for(boolean b : arr) {
    				sb.append(b?1:0);
    			}
    		}
    	}
    }


    void findBlock(int[][] arr, List<boolean[][]> blockList, int targetSpace) {
        boolean[][] checked = new boolean[arr.length][arr.length];

        for(int x=0; x<arr.length; x++) {
            for(int y=0; y<arr.length; y++) {
                // 블록이 아님, 이미 확인함
                if(arr[x][y] != targetSpace || checked[x][y]) continue;
//                 System.out.println(x+","+y);
                // 블록이다.
                // 블록의 영역을 구함
                int minX, maxX, minY, maxY; // 정사각형 영역을 구할거임
                // 값 초기화
                minX = maxX = x;
                minY = maxY = y;

                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[] {x,y});
                checked[x][y] = true;

                // 인접 영역을 모두 탐색한다.
                while(!q.isEmpty()) {
                    int[] curPoints = q.poll();
                    int curX = curPoints[0];
                    int curY = curPoints[1];

                    for(int dir=0; dir<4; dir++){
                        int nx = curX + dx[dir];
                        int ny = curY + dy[dir];

                        // 범위 체크
                        if(nx < 0 || ny < 0 || nx >= arr.length || ny >= arr.length) continue;
                        // 방문 여부, 블록 여부
                        if(checked[nx][ny] || arr[nx][ny] != targetSpace) continue;

                        // 블록이다
                        checked[nx][ny] = true;
                        q.offer(new int[] {nx, ny});

                        // 영역 계산
                        minX = Math.min(minX, nx);
                        maxX = Math.max(maxX, nx);
                        minY = Math.min(minY, ny);
                        maxY = Math.max(maxY, ny);
                    }

                }

                int diffX = Math.abs(maxX-minX);
                int diffY = Math.abs(maxY-minY);
                // 영역의 크기만큼 도형을 저장
                boolean[][] block = new boolean[diffX+1][diffY+1];

                for(int x1=minX; x1<=maxX; x1++) {
                    for(int y1=minY; y1<=maxY; y1++) {
                    	if(arr[x1][y1] == targetSpace) {
                    		block[x1-minX][y1-minY] = true;
                    	}
                    }
                }

                blockList.add(block);
            }
        }
    }
}
/*
    보드는 정사각형 격자

    1. 조각은 한 번에 하나씩 채워 넣는다.
    2. 조각을 회전시킬 수 있다.
    3. 조각을 뒤집을 수는 없다.
    4. 새로 채워넣은 퍼즐조각과 인접한 칸이 비어있으면 안된다. -> 꽉 차야한다.

    격자의 최대 크기는 50 x 50

    1. 퍼즐 조각 구분하기
        상하좌우로 인접한 구역은 하나의 퍼즐이다. -> 이것도 정사각형으로 추출됨
        우째 저장할겨?


    예제 테케
    블록 : 5 개
    4^5 * 격자?
*/