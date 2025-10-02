package CodeTree;

import java.util.*;
import java.io.*;

public class 택배하차 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }
    static StringTokenizer st;
    static int n, m;
    static Queue<Command> commandList;
    static int[][] board;
    static void init() throws IOException {
        commandList = new LinkedList<>();
        st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine().trim());
            int id = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int dropPoint = Integer.parseInt(st.nextToken());
            commandList.offer(new Command(id, height, width, dropPoint));
        }

        while(!commandList.isEmpty()) {
            dropBox();
            removeBoxFromLeft();
            dropBox();
            removeBoxFromRight();
        }
    }

    /**
     * 박스를 떨어뜨릴 명령어를 저장하기위한 class
     */
    static class Command {
        int id;
        int height;
        int width;
        int dropPoint;

        public Command(int id, int height, int width, int dropPoint) {
            this.id = id;
            this.height = height;
            this.width = width;
            this.dropPoint = dropPoint;
        }
    }

    /**
     * Command 를 기반으로 박스를 떨어뜨린다.
     * dropPoint 가 가장 왼쪽의 기준 좌표가 된다.
     */
    static void dropBox() {
        board = new int[n+1][n+1];
        // 물건을 떨어뜨린다.
        // - 물건이 떨어지는 경로에 다른 물건이 있으면, 해당 위치에 멈춘다.
        for(Command curCommand : commandList) {
            int id = curCommand.id;
            int height = curCommand.height;
            int width = curCommand.width;
            int dropPoint = curCommand.dropPoint;
            // 물건을 떨어뜨릴 수 있는 기준
            // 아랫변을 기준으로 중간에 방해되는 물건이 없는 경우?
            int x = 1;
            boolean isEmpty = true;     // 물건을 떨어뜨릴 수 있는지
            for(; x<n+1; x++) {
                for(int y=dropPoint; y<dropPoint+width; y++) {
                    if(board[x][y] != 0) {
                        isEmpty = false;
                        break;
                    }
                }
                if(!isEmpty) break;
            }
            x--;
            // 물건 위치 채우기
            for(int nx=x; nx>x-height; nx--) {
                for(int y=dropPoint; y<dropPoint+width; y++) {
                    board[nx][y] = id;
                }
            }
        }
//        for(int[] arr : board) System.out.println(Arrays.toString(arr));
//        System.out.println();
    }

    /**
     * 좌측과 우측 순으로 박스를 제거한다.
     */
    static void removeBoxFromLeft() {
        int removeTarget = Integer.MAX_VALUE;
        Set<Integer> preventDuplicate = new HashSet<>();        // 중복 탐색 방지
        for(int x=n; x>0; x--) {   // 위쪽으로 올라가며 탐색
            for(int y=1; y<n+1; y++) {      // 왼쪽에서 오른쪽으로 탐색
                if(board[x][y] == 0 || !preventDuplicate.add(board[x][y])) continue;
                // 상자가 놓여있고, 아직 탐색하지 않은 상자라면
                // 이미 위에서 add 연산을 했기에 따로 연산 필요 없음
                // 해당 상자를 제거하기 위한 경로 상에 아무런 장애물이 없는지 확인
                boolean isEmpty = true;
                for(int nx=x; nx>0; nx--) {
                    if(board[x][y] != board[nx][y]) break;
                    for(int ny=y-1; ny>0; ny--) {
                        if(board[nx][ny] == 0) continue;
                        isEmpty = false;
                        break;
                    }
                    if(!isEmpty) break;
                }
                if(isEmpty) {
                    removeTarget = Math.min(removeTarget, board[x][y]);
                }
            }
        }

        Iterator iterator = commandList.iterator();
        while(iterator.hasNext()) {
            Command command = (Command) iterator.next();
            if(command.id == removeTarget) {
                commandList.remove(command);
                break;
            }
        }
        removeTarget(removeTarget);
        sb.append(removeTarget).append('\n');
    }
    static void removeBoxFromRight() {
        int removeTarget = Integer.MAX_VALUE;
        Set<Integer> preventDuplicate = new HashSet<>();        // 중복 탐색 방지
        for(int x=n; x>0; x--) {   // 위쪽으로 올라가며 탐색
            for(int y=n; y>0; y--) {      // 왼쪽에서 오른쪽으로 탐색
                if(board[x][y] == 0 || !preventDuplicate.add(board[x][y])) continue;
                // 상자가 놓여있고, 아직 탐색하지 않은 상자라면
                // 이미 위에서 add 연산을 했기에 따로 연산 필요 없음
                // 해당 상자를 제거하기 위한 경로 상에 아무런 장애물이 없는지 확인
                boolean isEmpty = true;
                for(int nx=x; nx>0; nx--) {
                    if(board[x][y] != board[nx][y]) break;
                    for(int ny=y+1; ny<n+1; ny++) {
                        if(board[nx][ny] == 0) continue;
                        isEmpty = false;
                        break;
                    }
                    if(!isEmpty) break;
                }
                if(isEmpty) removeTarget = Math.min(removeTarget, board[x][y]);
            }
        }

        Iterator iterator = commandList.iterator();
        while(iterator.hasNext()) {
            Command command = (Command) iterator.next();
            if(command.id == removeTarget) {
                commandList.remove(command);
                break;
            }
        }
        removeTarget(removeTarget);
        sb.append(removeTarget).append('\n');
    }

    static void removeTarget(int removeTarget) {
        for(int x=1; x<n+1; x++) {
            for(int y=1; y<n+1; y++) {
                if(removeTarget == board[x][y]) board[x][y] = 0;
            }
        }
    }
}

/**
 6 8
 1 2 1 3
 2 1 1 2
 6 2 2 1
 5 1 3 4
 4 2 2 5
 3 1 2 3
 8 2 4 1
 9 1 3 4

 2
 4
 3
 1
 6
 5
 8
 9

 */