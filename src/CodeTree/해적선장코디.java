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
    static int t;
    static void init() throws IOException {
        t = Integer.parseInt(br.readLine().trim());
        while(t-- > 0) {
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
            decreaseRemainTimeToAttack();
        }
    }
    static class BattleShip {
        int id;                 // id
        int power;              // 공격력
        int reloadTime;         // 재장전 시간
        int remainTimeToAttack; // 다음 공격까지 남은 시간

        public BattleShip(int id, int power, int reloadTime) {
            this.id = id;
            this.power = power;
            this.reloadTime = reloadTime;
            this.remainTimeToAttack = 0;
        }

        public void decreaseRemainTimeToAttack() {
            if(this.remainTimeToAttack == 0) return;
            this.remainTimeToAttack--;
        }
    }
    static PriorityQueue<BattleShip> battleShips;
    static void readyToAttack() {
        battleShips = new PriorityQueue<>((a, b) -> {
           // 1. 당장 공격이 가능한 함선인지
           if(a.remainTimeToAttack != b.remainTimeToAttack) return Integer.compare(a.remainTimeToAttack, b.remainTimeToAttack);
           // 2. 공격력이 더 높은지
           if(a.power != b.power) return Integer.compare(b.power, a.power);
           // 3. id 가 낮은지
           return Integer.compare(a.id, b.id);
        });
        int battleShipCnt = Integer.parseInt(st.nextToken());
        while(battleShipCnt-- > 0) {
            addBattleShip();
        }
    }

    static void addBattleShip() {
        int id = Integer.parseInt(st.nextToken());
        int power = Integer.parseInt(st.nextToken());
        int reloadTime = Integer.parseInt(st.nextToken());

        battleShips.offer(new BattleShip(id, power, reloadTime));
    }

    static void changePower() {
        int id = Integer.parseInt(st.nextToken());
        int newPower = Integer.parseInt(st.nextToken());
        for(BattleShip battleShip : battleShips) {
            if(battleShip.id == id) battleShip.power = newPower;
        }
    }
    static void attack() {
        int attackLimit = 5;
        int totalDamage = 0;
        List<BattleShip> pickedList = new ArrayList<>();
        List<BattleShip> temp = new ArrayList<>();      // 임시 저장

        while(attackLimit > 0 && !battleShips.isEmpty()) {
            BattleShip ship = battleShips.poll();
            if (ship.remainTimeToAttack == 0) { // 공격 가능한 경우만
                totalDamage += ship.power;
                pickedList.add(ship);
                ship.remainTimeToAttack = ship.reloadTime;
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

    static void decreaseRemainTimeToAttack() {
        for(BattleShip battleShip : battleShips) battleShip.decreaseRemainTimeToAttack();
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