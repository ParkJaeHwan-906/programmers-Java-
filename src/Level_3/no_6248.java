package Level_3;

import java.sql.Array;
import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 6회 정기 코딩 인증평가 기출] 출퇴근길
// https://softeer.ai/practice/6248
public class no_6248 {
    static ArrayList<Integer>[] road;
    static ArrayList<Integer>[] reverseRoad;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // road와 reverseRoad의 크기를 n으로 초기화
        road = new ArrayList[n];
        reverseRoad = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            road[i] = new ArrayList<>();
            reverseRoad[i] = new ArrayList<>();
        }

        // 도로 정보 저장
        while (m > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            // a에서 b로 가는 도로와 b에서 a로 가는 역방향 도로 추가
            road[a].add(b);
            reverseRoad[b].add(a);
            m--;
        }

        // 출발지와 도착지 입력 받기
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;
        br.close();

        no_6248 problem = new no_6248();
        int result = problem.solution(start, end);
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    boolean[] visited1; // 출근하면서 갈 수 있는 곳
    boolean[] visited2; // 각 정점에서 s 로 갈 수 있는지
    boolean[] visited3; // 퇴근하면서 갈 수 있는 곳
    boolean[] visited4; // 각 정점에서 e로 갈 수 있는지 여부
    public int solution(int s, int e) {
        visited1 = new boolean[road.length];
        visited2 = new boolean[road.length];
        visited3 = new boolean[road.length];
        visited4 = new boolean[road.length];

        // 회사에 도착한다면
        visited1[e] = true;
        dfs(s, visited1, road);  // 출근길 탐색

        dfs(e, visited2, reverseRoad);

        // 집에 도착한다면
        visited3[s] = true;
        dfs(e, visited3, road); // 퇴근길 탐색

        dfs(e, visited4, reverseRoad);

        return findDuplicated(s, e);
    }

    public void dfs(int s, boolean[] visited, ArrayList<Integer>[] list) {
        if(visited[s]) return;

        visited[s] = true;
        for(int i : list[s]) {
            if(visited[i]) continue;

            dfs(i, visited, list);
        }
    }


    // 공통 노드를 찾아서 개수를 반환
    public int findDuplicated(int s, int e) {
        int cnt = 0;
        for (int i = 0; i < visited1.length; i++) {
            if (i == s || i == e) continue;  // 출발지와 목적지는 제외
            if (visited1[i] && visited2[i] && visited3[i] && visited4[i]) cnt++;  // 두 경로 모두에 포함된 노드 개수
        }
        return cnt;
    }
}
