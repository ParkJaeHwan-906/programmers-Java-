package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_17135_캐슬디펜스_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        init();

        bw.flush();
        bw.close();
    }

    static int height, width, dist;      // 세로 크기, 가로 크기, 공격 제한 범위
    static int[][] map;                 // 격자
    static int enemy;                   // 격자 내에 표시되어 있는 적의 수
    static int maxKill;                 // 최대로 처지할 수 있는 적의 수
    static void init() throws IOException {
        enemy = maxKill = 0;

        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        dist = Integer.parseInt(st.nextToken());

        map = new int[height+1][width];     // N+1 번째 칸에 성이 있다.

        // 적들 정보를 입력 받는다.
        for(int x=0; x<height; x++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int y=0; y<width; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
                if(map[x][y] == 1) enemy++;
            }
        }
        br.close();

        locateAttacker(0,0);

        bw.write(String.valueOf(maxKill));
    }

    // 1. 궁수를 배치하는 위치를 구한다.
    //      궁수는 총 3명 배치한다.
    //      궁수의 배치 위치는 N 번째 행이다.
    // [배치한 궁수의 수, 궁수가 배치될 수 있는 위치의 시작점]
    static void locateAttacker(int attackerIdx, int y) {
        if(attackerIdx == 3) {  // 모든 궁수 배치를 완료했다.
            // 깊은 복사를 통해 배열을 복사하여 전달한다.
            int[][] copyMap = new int[height+1][width];
            for(int x=0; x<=height; x++) {
                copyMap[x] = map[x].clone();
            }

            // 현 상태 출력 확인
//            for(int x=0; x<=height; x++) {
//                System.out.println(Arrays.toString(map[x]));
//            }
//            System.out.println();

            // 여기서 게임 시뮬레이션을 돌려야한다.
            simDefense(copyMap);

            // 궁수 배치 위치 확인 출력
//            System.out.println(Arrays.toString(map[height]));
            return;
        }

        // 궁수 배치를 할 수 있다.
        // 이전 궁수가 배치된 위치 이후부터, 다음 궁수를 배치한다.
        // 중복되는 경우를 없앤다.
        for(int yIdx=y; yIdx<width; yIdx++) {
            map[height][yIdx] = 2;
            locateAttacker(attackerIdx+1, yIdx+1);
            map[height][yIdx] = 0;
        }
    }

    // 현 궁수의 배치에서 처지할 수 있는 적의 수를 구한다.
    static void simDefense(int[][] copyMap) {
        int disappearedEnemy = 0;   // 사라진 적의 수
        int killed = 0;             // 처치한 적의 수

        while(disappearedEnemy+killed < enemy) {  // 격자 상에서 모든 적이 사라질 때까지 반복
            Set<int[]> targetEnemy = new HashSet<>();   // 각 궁수들이 공격할 적들을 저장 ( 중복 X )

            // 왼쪽에 있는 궁수부터 적을 처치한다.
            for(int attackerIdx=0; attackerIdx<width; attackerIdx++) {
                // 궁수가 배치되어 있지 않는 칸은 넘어간다.
                if(copyMap[height][attackerIdx] != 2) continue;

                // 궁수가 배치되어 있다면 공격할 수 있는 적을 찾는다.
                findEnemy(attackerIdx, copyMap, targetEnemy);

                // 현 위치의 궁수가 제거할 적이 없다면 다음 궁수로 넘어간다.
            }

            // 공격할 수 있는 적들을 한 번에 처리한다.
//            System.out.println(targetEnemy.size());
//            for(int[] arr : targetEnemy) {
//                System.out.println(Arrays.toString(arr));
//            }
            killed += attackEnemy(targetEnemy, copyMap);

            // 적을 한칸씩 아래로 이동 시킨다.
            disappearedEnemy += moveEnemy(copyMap);
        }

        maxKill = Math.max(maxKill, killed);
    }

    static int attackEnemy(Set<int[]> targetEnemy, int[][] copyMap) {
        int killed = 0;
        for(int[] enemy : targetEnemy) {
            int x = enemy[0];
            int y = enemy[1];

            // Set 으로 중복처리를 하려했는데 뭔가 좀 이상함
            if(copyMap[x][y] == 1) {
                copyMap[x][y] = 0;
                killed++;
            }
        }

        return killed;
    }

    // 적들을 한 칸씩 아래로 이동 시킨다.
    // 이때 N+1 행에 도달하는 적들은 사라지는 처리를 한다.
    // 사라지는 적들을 기록 후 그 값을 반환한다.
    // [클론된 map 배열]
    static int moveEnemy(int[][] copyMap) {
        int disappearedEnemy = 0;
        // 1. N 행에 있는 적들은 내려가게 되면 성에 도달하므로 제거한다.
        for(int y=0; y<width; y++) {
            if(copyMap[height-1][y] == 1) {
                disappearedEnemy++;
                copyMap[height-1][y] = 0;
            }
        }

        // 나머지 적들을 한 칸씩 아래로 당긴다.
        for(int x=height-1; x>0; x--) {
            for(int y=0; y<width; y++) {
                if(copyMap[x-1][y] != 1) continue;

                // 적을 한 칸 아래로 이동 시킨다.
                copyMap[x][y] = copyMap[x-1][y];
                copyMap[x-1][y] = 0;
            }
        }

        return disappearedEnemy;
    }

    // 현재 궁수의 위치에서 처리할 수 있는 적을 찾는다.
    // 처리할 적의 우선 순위는, 가장 가깝고, 왼쪽에 있는 적부터 처치한다.
    // [궁수의 y 좌표, 클론된 map 배열, 궁수들이 공격할 수 있는 적들을 기록한 Set]
    static void findEnemy(int y1, int[][] copyMap, Set<int[]> targetEnemy) { // 궁수의 행위치는 height 이다.
        // 수정 : 같은 거리에 있는 적이 여럿 있다면 가장 왼쪽의 적부터 처리한다.
        //          PriorityQueue 를 사용해 정렬하고, 한 명의 적만 처리한다.

        // 현재 궁수가 공격할 수 있는 적들을 기록한다.
        // [x,y,dist]
        PriorityQueue<int[]> enemy = new PriorityQueue<>((a,b) -> {
            if(a[2] == b[2]) {  // 거리가 같다면, 가장 왼쪽의 적 부터
                return a[1]-b[1];
            }

            return a[2]-b[2];   // 가까운 적부터
        });

        // 적은 궁수와 가장 가까운 적부터, 가장 왼쪽에 있는 적부터 탐색한다.
        for(int x2=height-1; x2 > -1; x2--) {
            for(int y2=0; y2<width; y2++) {
                // 해당 칸에 적이 없다면 넘어간다.
                if(copyMap[x2][y2] == 0) continue;

                // 적이 있다면
                // 1. 거리를 구한다.
                int nowDist = calcDist(height, y1, x2, y2);

                // 2. 공격할 수 있는 거리인지 확인한다.
                if(nowDist <= dist) {
                    enemy.offer(new int[] {x2, y2, nowDist});
                }
            }
        }

        // 현 궁수의 공격할 수 있는 적들 중 가장 우선 순위가 높은 적들을 타겟한다.
        // 단, 이미 Set 내부에 해당 적이 포함되어 있다면, 다음 우선 순위의 적을 추가한다.
        while(!enemy.isEmpty()) {
            // set 에 추가할 수 있으면 true, 없으면 false
            if(targetEnemy.add(enemy.poll())) break;
        }
    }

    // 궁수와 적 사이 거리를 계산한다.
    // (x1, y1) : 궁수, (x2,y2) : 적
    static int calcDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }
}
/*
    N X M 크기의 격자가 있다.
    각 칸에 포함된 적의 수는 최대 1
    격자판의 N번행 바로 아래 N+1 번행의 모든 칸에는 성이 있다.

    성을 지키기위해 궁수 3명을 배치한다. (성이 있는 칸에 배치할 수 있다.)
    하나의 칸에 1명의 궁수만 배치할 수 있다. 각 턴마다 한명의 적만 공격 가능
    거리가 D 이하인 적 중 가장 가까운 왼쪽의 적을 우선적으로 공격한다.
    궁수의 공격이 끝나면 적은 이동한다.
    적은 아래로 한 칸 이동한다. 성이 있는 칸에 도착하면 게임에서 제외한다. -> 모든 적이 제외되면 게임은 끝난다.

    궁수의 위치에 따라 최대로 제거할 수 있는 적의 수를 구하자

    N, M 은 최소 3, 최대 15
    거리는 최대 10

    궁수가 있을 수 있는 자리는 15 C 3 -> 455 개
    => 매 시뮬레이션 마다 배열을 clone() 을 사용해 깊은 복사를 해야한다.

    시뮬레이션
    1. 궁수가 거리가 D 이하인 적들을 우선적으로 공격한다.
        a. 가장 가까운 좌측의 적부터 공격한다.
              i. N번째 행의 0번째 열부터 탐색을 시작해서 적을 제거한다.
              ii. 탐색 수를 줄이기 위해서는 거리가 넘어가는 시점에선 탐색을 종료한다.
    2. 적들을 한칸씩 아래로 내린다. -> N*M 의 연산 필요
        a. N 번째 행부터 위로 올라가며 처리하는게 좋아보인다.

    ?? 어떤 궁수가 공격을 했는지 기록이 필요하다. (한 번의 공격만 가능하기 때문)
 */