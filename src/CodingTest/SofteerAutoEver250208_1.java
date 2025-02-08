package CodingTest;

import java.util.*;
import java.io.*;

public class SofteerAutoEver250208_1 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int Node, Edge;  // 노드의 개수, 간선의 개수
    static ArrayList<Integer>[] graph;  // 각 노드의 연결 관계를 저장
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        Node = Integer.parseInt(st.nextToken());
        Edge = Integer.parseInt(st.nextToken());

        graph = new ArrayList[Node];    // 노드의 개수만큼 초기화
        for(int idx=0; idx<Node; idx++) {
            graph[idx] = new ArrayList<>();
        }

        for(int i=0; i<Edge; i++) { // 각 노드를 연결시킴
            st = new StringTokenizer(br.readLine().trim());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph[a].add(b);
            graph[b].add(a);
        }
        br.close();;
        SofteerAutoEver250208_1 problem = new SofteerAutoEver250208_1();
        bw.write(String.valueOf(problem.getMaxNode()));
        bw.flush();
        bw.close();
    }

    boolean[] isCheck;
    public int getMaxNode() {
        int answer = 0;
        isCheck = new boolean[Node]; // 확인한 노드인지 기록하는 배열

        for (int idx = 0; idx < Node; idx++) {
            if (isCheck[idx]) continue; // 이미 방문한 노드라면

            Queue<int[]> q = new ArrayDeque<>();
            int count = 1; // 현재 노드와 연결되어있는 모든 노드들의 수
            boolean cycle = false;
            q.offer(new int[]{idx, -1}); // 현재 노드, 부모 노드
            isCheck[idx] = true;

            while (!q.isEmpty()) { // 현재 노드와 연쇄적으로 연결되어 있는 노드를 확인
                int[] nowNode = q.poll();
                int curNode = nowNode[0];
                int parent = nowNode[1];

                for (int otherNode : graph[curNode]) {
                    if (otherNode == parent) continue; // 양방향 연결은 확인하지 않음

                    if (isCheck[otherNode]) {   // 양방향 ( 부모 노드 ) 이 아닌 타 노드를 재방문한 경우 -> 사이클이 존재
                        cycle = true;
                    } else {
                        isCheck[otherNode] = true;
                        count++;
                        q.offer(new int[]{otherNode, curNode}); // 부모 갱신
                    }
                }
            }

            // 사이클을 구분하여 계산한다.
            answer += cycle ? count - (count / 2) - 1 : count > 2 ? count - (count / 2) : 1;
        }
        return answer;
    }

}
/*
문제
각 정점 의 개 N 과 간선의 개수 M 이 주어진다.
한 개의 정점은 최대 2개의 간선을 가질 수 있다.

가장 많은 정점을 선택하려 한다.
이때 선택된 정점과 연결되어 있는 정점은 선택할 수 없다.
 */