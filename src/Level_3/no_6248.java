package Level_3;

import java.sql.Array;
import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 6회 정기 코딩 인증평가 기출] 출퇴근길
// https://softeer.ai/practice/6248
public class no_6248 {
    static ArrayList<Integer>[] road;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        road = new ArrayList[n];
        for(int i = 0; i < n; i++){
            road[i] = new ArrayList<>();
        }

        // 도로 정보 저장
        while(m > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;

            road[a].add(b);
            m--;
        }

//        //  ✅ 도로 표현
//        for(int i = 0; i < road.length; i++){
//            System.out.println(i+1 + "연결");
//            for(int j=0; j < road[i].size(); j++){
//                System.out.print(road[i].get(j)+1+" ");
//            }
//            System.out.println();
//        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken())-1;

        no_6248 problem = new no_6248();
        System.out.println(problem.solution(start, end));
    }

    Set<Integer> sToE = new HashSet<>();
    Set<Integer> eToS = new HashSet<>();
    public int solution(int s, int e) {
        dfs(s, e, new boolean[road.length], 1);
//        System.out.println();
        dfs(e, s, new boolean[road.length], -1);

//        System.out.println("출근길");
//        for(int i : sToE) {
//            System.out.print(i+" ");
//        }
//        System.out.println();
//        System.out.println("퇴근길");
//        for(int i : eToS) {
//            System.out.print(i+" ");
//        }

//        System.out.println(eToS.contains(0));
        return findDuplicated(s, e);
    }

    public void dfs(int s, int e, boolean[] visited, int flag) {
        // 경로를 추가
        if (flag == -1) {  // 퇴근
            if(!eToS.contains(s)){
                eToS.add(s);
            }
        } else {    // 출근
            if(!sToE.contains(s)){
                sToE.add(s);
            }
        }

        if (s == e) { // 목적지에 도착한 경우
            return;
        }

        visited[s] = true; // 방문 처리

        for (int next : road[s]) {
            if (visited[next]) continue; // 이미 방문한 경우 스킵

            // 다음 노드가 유효한 경로로 연결되는 경우만 기록
            dfs(next, e, visited, flag);

        }

        visited[s] = false; // 방문 해제 (백트래킹)
    }

    private int findDuplicated(int s, int e) {
        int answer = 0;
        if(sToE.size() > eToS.size()){
            for(int i : sToE){
                if(i == e || i == s || !eToS.contains(i)) continue;
                answer++;
            }
        } else{
            for(int i : eToS){
                if(i == e || i == s || !sToE.contains(i)) continue;
                answer++;
            }
        }

        return answer;
    }
}
