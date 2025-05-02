package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_6258_거리합구하기 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int nodeCnt;             // 노드의 개수
    static List<int[]>[] graph;    // 그래프 정보
    static long[] distSum;
    static long[] subTree;
    static void init() throws IOException {
        nodeCnt = Integer.parseInt(br.readLine().trim());
        graph = new ArrayList[nodeCnt+1];
        for(int i=0; i<nodeCnt+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<nodeCnt-1; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[nodeA].add(new int[] {nodeB, dist});
            graph[nodeB].add(new int[] {nodeA, dist});
        }

        distSum = new long[nodeCnt+1];
        subTree = new long[nodeCnt+1];
        // 1-base 로 1 번 노드부터 탐색
        getSubTree(1,1);
//        System.out.println(Arrays.toString(distSum));
//        System.out.println(Arrays.toString(subTree));
        getDist(1,1);
//        System.out.println(Arrays.toString(distSum));
//        System.out.println(Arrays.toString(subTree));

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<nodeCnt+1; i++) {
            sb.append(distSum[i]).append('\n');
        }
        System.out.println(sb);
    }

    /*
        서브 트리의 크기를 구한다.
        현재 노드의 서브트리의 크기를 구한다. (자기 자신 포함)
     */
    static void getSubTree(int curNode, int parentNode) {
        // 서브트리의 초기 값은, 자기 자신 하나 뿐이다.
        subTree[curNode] = 1;

        for(int[] conn : graph[curNode]) {
            int connNode = conn[0];
            int connDist = conn[1];

            // 부모를 재참조 -> 사이클 방지
            if(parentNode == connNode) continue;
            // 리프노드까지 내려가 바텀업 방식으로 서브트리의 크기를 갱신한다.
            getSubTree(connNode, curNode);
            distSum[curNode] += distSum[connNode] + connDist*subTree[connNode];
            subTree[curNode] += subTree[connNode];
        }
    }

    /*
        각 노드에서 모든 노드로의 거리 합을 구한다.
        현재 노드의 거리합
        = 루트 노드의 거리합 + 현노드와 루트노드 사이 거리(전체 노드 개수 - 서브트리 개수)
        - 현노드와 루트노드 사이 거리(서브트리 개수)
     */
    static void getDist(int curNode, int parentNode) {
        for(int[] conn : graph[curNode]) {
            int connNode = conn[0];
            int connDist = conn[1];

            if(connNode == parentNode) continue;
            distSum[connNode] = distSum[curNode] + connDist*(nodeCnt-2*subTree[connNode]);
            getDist(connNode, curNode);
        }
    }
}

/*
    N 개의 노드를 갖는 트리형태이다.
    => 노드의 연결이 정확하게 N-1 개 있다.
    양방향 연결이다. 통신에 걸리는 시간이 가중치로 주어진다.

    => 각 노드에서 다른 노드로의 모든 합을 구한다.
    => 플로이드 워셜 이 가장 먼저 떠오르지만 주어진 노드의 최대 개수는 2 x 10^5
    => 2 를 제외하더라도, 10^15 가 되어버린다.

    ==> 상수 시간 내에 해결해야 문제를 해결할 수 있다.
 */
