package Programmers.Lv2;

public class PMMS_87946_피로도 {
    public static void main(String[] args) {
        int k = 80;
        int[][] dungeons = {{80,20},{50,40},{30,10}};
        System.out.println(new PMMS_87946_피로도().solution(k,dungeons));
    }

    int maxDungeons;        // 탐험 가능한 최대 던전 수
    // k : 유저의 현재 피로도, dungeons : 각 던전 탐험시 [최소 피로도, 소모 피로도]
    public int solution(int k, int[][] dungeons) {
        /*
            던전의 개수가 최대 8 개 -> 완탐 가능
         */
         maxDungeons = 0;
         findBestSeq(k, 0, new boolean[dungeons.length], dungeons);
        return maxDungeons;
    }

    /*
        던전을 탐험하는 순서를 임의로 조작할 수 있음
        -> 순열
        8! 시간 복잡도?

        현재 던전을 탐험하거나, 탐험하지 않을 수 있음
     */
    void findBestSeq(int hp, int cnt, boolean[] visited, int[][] dungeons) {
        // 방문하지 않은 던전 탐색
        for(int idx=0; idx<dungeons.length; idx++) {
            if(visited[idx]) continue;  // 이미 방문한 경우 패스

            // 방문이 가능
            // 1. 피로도 확인
            if(hp < dungeons[idx][0]) continue;     // 현재 피로도보다 최소 피로도가 큼

            visited[idx] = true;
            findBestSeq(hp-dungeons[idx][1], cnt+1, visited, dungeons);
            visited[idx] = false;
        }
        // 현재까지 탐험한 던전의 수를 누적 기록
        maxDungeons = Math.max(maxDungeons, cnt);
    }
}

/*
    피로도 시스템 ( 0 이상의 정수 )
    피로도를 사용해 던전을 탐험 -> 탐험하기위한 최소 필요도, 소모 필요도가 존재함

    유저가 탐험할 수 있는 최대 던전 수를 반환
 */