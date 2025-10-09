package CodeTree;

import java.util.*;
import java.io.*;

public class 해적선장코디 {
    static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        init();
        br.close();
        System.out.println(sb);
    }

    /**
     * 총 T 개의 명령을 순차적으로 내린다.
     *
     * [공격준비]
     * - N척의 선박에 사격 준비를 지시한다.
     * - 각 선박은 id, 공격력, 재장전 시간을 가진다. ( 초기 상태는 사격 대기 )
     *
     * [지원요청]
     * - 추가 병력을 요청하여 새로운 선박이 합류한다.
     * - 새로 합류한 선박은 사격 대기 상태로 추가 된다.
     *
     * [함포교체]
     * - id 번 선박의 함포를 교체한다.
     * - 교체 후 선박의 공격력이 변화된다.
     *
     * [공격명령]
     * - 사격 대기 상태인 선박 중 공격력이 가장 높은 선박 5척에 사격명령을 내린다.
     * - 공격력이 같다면 id 가 작은 선박을 우선 선택한다.
     * - 사격한 선박은 즉시 재장전에 들어가며, 사격 시점을 포함해 r 시간이 지나면 다시 사격 대기 상태로 전환된다.
     * - 재장전 중인 선박은 공격할 수 없다
     *
     * => 공격명령이 주어질 때마다, 그 시간에 대형 함선에 가한
     * [총 피해량, 사격 선박 수, 사격한 선박들의 선박 번호]
     */
    static StringTokenizer st;
    static int time;
    static int curTime;
    static void init() throws IOException {
        time = Integer.parseInt(br.readLine().trim());
        /**
         * 각 명령은 1시간 단위로 실행된다.
         * 즉 0 부터 Time 까지 실행한다고 생각한다.
         *
         * 현 시간을 기준으로 함선의 공격 가능 여부를 판단한다.
         */
        for(curTime=0; curTime<time; curTime++) {
            st = new StringTokenizer(br.readLine().trim());
            int commandType = Integer.parseInt(st.nextToken());

            switch(commandType) {
                case 100:       // 공격 준비 ( 1회만 주어짐 )
                    readyToAttack();
                    break;
                case 200:       // 지원 요청
                    addBattleShip();
                    break;
                case 300:       // 함포 교체
                    changePower();
                    break;
                case 400:       // 공격 명령
                    attack();
                    break;
            }
        }
    }
    static class BattleShip {
        int id;                 // id
        int power;              // 공격력
        int reloadTime;         // 재장전 시간
        /**
         * 마지막으로 공격을 한 시간을 기준으로
         * 현재 시간과 비교하여 공격가능한 상태인지 확인한다.
         */
        int lastAttack;         // 마지막으로 공격한 시간

        /**
         * 두 개의 생성자를 사용한다. 
         * 
         * 가장 처음 readyAttack 단계에서 함선을 생성할 때 사용할 생성자
         * 함포의 공격력을 증가시킬 때 사용할 생성자
         */
        public BattleShip(int id, int power, int reloadTime) {
            this.id = id;
            this.power = power;
            this.reloadTime = reloadTime;
            this.lastAttack = Integer.MIN_VALUE;
        }

        public BattleShip(int id, int power, int reloadTime, int lastAttack) {
            this.id = id;
            this.power = power;
            this.reloadTime = reloadTime;
            this.lastAttack = lastAttack;
        }
    }
    static PriorityQueue<BattleShip> battleShips;
    static Map<Integer, BattleShip> battleShipState;       // 함선의 최신 상태를 유지
    static void readyToAttack() {
        battleShips = new PriorityQueue<>((a, b) -> {
           if(a.power == b.power) return Integer.compare(a.id, b.id);
           return Integer.compare(b.power, a.power);
        });
        battleShipState = new HashMap<>();
        int battleShipCnt = Integer.parseInt(st.nextToken());
        while(battleShipCnt-- > 0) {
            addBattleShip();
        }
    }

    static void addBattleShip() {
        int id = Integer.parseInt(st.nextToken());
        int power = Integer.parseInt(st.nextToken());
        int reloadTime = Integer.parseInt(st.nextToken());
        BattleShip battleShip = new BattleShip(id, power, reloadTime);
        battleShipState.put(id, battleShip);
        battleShips.offer(battleShip);
    }

    /**
     * 💡함포를 교체할 때마다, PQ 를 순회하며 값을 갱신하는 것은 O(n)의 연산을 계속해서 해야함
     *
     * 지연 갱신 (Lazy Evaluation) 기법을 사용한다.
     * Map 에 id 에 해당하는 함포의 공격력을 늘 최신의 상태로 유지한다.
     *
     * 새로운 객체를 생성해서 PQ 에 넣어, 정렬 상태를 유지한다.
     */
    static void changePower() {
        int id = Integer.parseInt(st.nextToken());
        int newPower = Integer.parseInt(st.nextToken());
        BattleShip battleShip = battleShipState.get(id);
        BattleShip newBattleShip = new BattleShip(battleShip.id, newPower, battleShip.reloadTime, battleShip.lastAttack);
        battleShipState.put(id, newBattleShip);
        battleShips.offer(newBattleShip);
    }
    static void attack() {
        int attackLimit = 5;
        int totalDamage = 0;
        List<BattleShip> pickedList = new ArrayList<>();
        List<BattleShip> temp = new ArrayList<>();      // 임시 저장

        while(attackLimit > 0 && !battleShips.isEmpty()) {
            BattleShip ship = battleShips.poll();
            /**
             * 이 부분에서 최신값이 아닌 함선들은 제외시킨다.
             */
            if(battleShipState.get(ship.id).power != ship.power) continue;
            if (ship.lastAttack + ship.reloadTime <= curTime) { // 공격 가능한 경우만
                totalDamage += ship.power;
                pickedList.add(ship);
                ship.lastAttack = curTime;
                attackLimit--;
            }
            temp.add(ship);
        }
        pickedList.sort((a, b) -> {
            if(a.power == b.power) return Integer.compare(a.id, b.id);
            return Integer.compare(b.power, a.power);
        });
        for (BattleShip s : temp) battleShips.offer(s);

        sb.append(totalDamage).append(' ')
                .append(pickedList.size()).append(' ');
        for (BattleShip battleShip : pickedList) sb.append(battleShip.id).append(' ');
        sb.append('\n');
    }
}
/**
7
100 3 10 5 2 3 7 1 8 7 3
400
400
200 2 9 2
400
300 3 6
400
---
19 3 3 8 10
7 1 3
28 4 2 3 8 10
20 3 2 3 10
 */