package Level_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 프로그래머스 Lv.3
// 여행경로
// https://school.programmers.co.kr/learn/courses/30/lessons/43164
public class no_43164 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] tickets = new String[][]
               {{"ICN", "JFK"},{"HND", "IAD"},{"JFK", "HND"}};

        no_43164 problem = new no_43164();
        String[] result = problem.solution(tickets);
        for(String s : result){
            System.out.print(s+" ");
        }
    }

    boolean[] visited;
    List<String> routes = new ArrayList<>();
    public String[] solution(String[][] tickets){
        visited = new boolean[tickets.length];

        makeRoute(tickets, "ICN", 0);
        // 알파벳 기준 정렬
        Collections.sort(routes);
        String[] result = routes.get(0).split(" ");
        return result;
    }

    private void makeRoute(String[][] tickets, String s, int depth){
        if(depth == visited.length){    // 모두 방문한 경우
            routes.add(s);
            return;
        }

        // 현재 위치
        String now = s.substring(s.length()-3);

        for(int i=0; i< tickets.length; i++){
            String depart = tickets[i][0];
            String arrive = tickets[i][1];

            // 현위치랑 출발지가 같지 않거나, 이미 방문한 경우
            if(!depart.equals(now) || visited[i]) continue;

            visited[i] = true;
            makeRoute(tickets, s+" "+arrive, depth+1);
            visited[i] = false;
        }
    }
}
