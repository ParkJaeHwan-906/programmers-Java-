package Programmers.Lv3;

import java.util.*;

public class PMMS_150365_미로탈출명령어 {
    public static void main(String[] args) {
        System.out.println(new PMMS_150365_미로탈출명령어().solution(3,4,2,3,3,1,5));
    }

    int n, m, r, c, k;
    String answer;     // 정답 경로
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n; this.m = m;
        this.r = r-1; this.c = c-1;
        this.k = k;
        findRoute(x-1, y-1, 0, new StringBuilder());
        return answer == null ? "impossible" : answer;
    }

    // 사전순으로 정의
    String[] cmd = {"d", "l", "r", "u"};
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};
    void findRoute(int x, int y, int cnt, StringBuilder sb) {
        /*
            DFS 를 사용해 탐색한다.
            탐색 방향은 사전순 오름차순으로 탐색 -> 경로를 찾으면 그 경로가 최적 경로가 된다.
         */
        if(answer != null) return;      // 이미 최적 경로를 탐색 완료

        // 현위치가 도착위치인지 확인
        if(x == r && y == c && cnt == k) {
            answer = sb.toString();
            return;
        }

        // 더 이상 움직이는 것이 의미 없을 때
        if(cnt >= k) return;
        int remainDist = Math.abs(x-r) + Math.abs(y-c);     // 도착지까지 이동거리
        if(remainDist > k-cnt) return;                      // 도착지까지 이동하면 k 를 넘는 경우
        if(((k-cnt)-remainDist)%2 != 0) return;             // 도착지에 도착 후, 왔다갔다하며 턴을 소비할 수 없는 경우

        // 더 탐색
        for(int dir=0; dir<4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            sb.append(cmd[dir]);
            findRoute(nx, ny, cnt+1, sb);
            sb.setLength(sb.length()-1);
        }
    }
}

/*
    n x m 격자 미로가 주어진다.
    (x, y) -> (r, c)
    이동 거리가 총 k 여야한다.
    같은 격자를 두 번 이상 방문 가능하다.
 */