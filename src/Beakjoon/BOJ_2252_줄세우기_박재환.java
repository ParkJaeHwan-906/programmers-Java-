package Beakjoon;

import java.util.*;
import java.io.*;

public class BOJ_2252_줄세우기_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        init();

        bw.write(sb.toString());
        bw.flush();
        bw.close();

        br.close();
    }

    static int nodes, edges;    // 학생들의 수, 키 비교
    static List<Integer>[] graph; // 학생들의 키 비교 관계를 저장
    static int[] inEdges;       // 각 정점의 진입 차수를 저장
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        nodes = Integer.parseInt(st.nextToken());
        edges = Integer.parseInt(st.nextToken());

        // 1-based
        graph = new ArrayList[nodes+1];
        inEdges = new int[nodes+1];
        for(int node=0; node<=nodes; node++) {
            graph[node] = new ArrayList<>();
        }

        for(int edge=0; edge<edges; edge++) {
            st = new StringTokenizer(br.readLine().trim());

            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            // A 가 B 보다 앞에 있다
            graph[nodeA].add(nodeB);
            inEdges[nodeB]++;
        }

        lineUp();
    }

    /*
        위상 정렬을 수행한다.
        1. 진입차수가 0 인 정점들을 먼저 Queue 에 삽입한다.
        2. Queue 에 저장되어있는 정점들을 빼며, 이를 정답에 기록한다.
            a. 인접한 정점의 진입 차수를 1 감소시킨다.
        3. 이때 새로 진입차수가 0 인 정점이 생기면 큐에 삽입 후 반복한다.
     */
    static void lineUp() {
        Queue<Integer> q = new ArrayDeque<>();

        // 1. 진입 차수가 0 인 정점을 먼저 Queue 에 삽입한다.
        for(int node=1; node<=nodes; node++) {
            if(inEdges[node] == 0) q.offer(node);
        }

        // 2. Queue 에 저장되어 있는 정점에 대해 작업을 수행한다.
        while(!q.isEmpty()) {
            int curNode = q.poll();
            sb.append(curNode).append(' ');
            // 현 노드의 인접 노드들의 진입 차수를 1 감소 시킨다.
            for(int connNode : graph[curNode]) {
                inEdges[connNode]--;

                if(inEdges[connNode] == 0) q.offer(connNode);
            }
        }
    }
}

/*
    N 명의 학생들을 순서대로 줄을 세운다.
    두 학생의 키를 비교하는 방법을 사용한다. -> 일부 학생들의 키만을 비교했다. (그럼 비교 안한 애들은 가장 크다고 봐야하나?)

    위상정렬을 사용해 대소관계를 나열한다.
    1. 그래프 입력과 동시에 각 정점의 진입 차수를 계산한다.
    2. 진입 차수가 0 인 정점들을 Queue 에 넣는다.
    3. Queue 에서 값을 빼고, 해당 정점과 연결된 인접 정점의 진입 차수를 1 감소 시킨다.
    4. 이때 감소시킨 값이 0이라면 큐에 넣고 반복한다.
 */