package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [21년 재직자 대회 예선] 로드 밸런서 트래픽 예측
// https://softeer.ai/practice/6263
public class no_6263 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int nodes;   // N : 노드의 개수 ( 로드 밸런서, 워커 노드 모두 포함 )
    static int tasks;   // K : 주어지는 요청의 수
    static ArrayList<int[]>[] servers; // P : 서버 그래프 구성
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        init();

        // 입력 테스트
        for(int idx=1; idx<=nodes; idx++) {
            System.out.print(idx+ " : ");
            for(int[] nodeArr : servers[idx]) {
                for(int i : nodeArr) {
                    System.out.print(i+" ");
                }
            }
            System.out.println();
        }

        no_6263 problem = new no_6263();
        problem.loadBalance();

    }

    public static void init() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        nodes = Integer.parseInt(st.nextToken());
        tasks = Integer.parseInt(st.nextToken());

        servers = new ArrayList[nodes+1];   // 쉬운 접근을 위해 idx+1
        for(int idx=0; idx<=nodes; idx++) { // 배열 초기화
            servers[idx] = new ArrayList<>();
        }

        // 서버 정보 입력
        for(int idx=1; idx<=nodes; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            // 연결 노드의 개수 (0 이면 워커 노드, 1이상 로드밸런서)
            int connNodes = Integer.parseInt(st.nextToken());
            if(connNodes == 0) continue;
            int[] conn = new int[connNodes];
            for(int i=0; i<connNodes; i++) {
                conn[i] =  Integer.parseInt(st.nextToken());
            }
            servers[idx].add(conn);
        }
    }

    int[] inEdges;
    private long[] loadBalance() {
        /*
        위상 정렬을 위해 들어오는 간선의 개수를 구해
        왼쪽부터 오른쪽으로 정렬한다.
         */
        inEdges = new int[nodes+1]; // 노드로 들어오는 간선의 개수를 구한다.
        calcInEdge();
        // inEdges 확인
//        for(int idx=1; idx <= nodes; idx++) {
//            System.out.print(inEdges[idx]+ " ");
//        }

        return new long[0];
    }

    // 노드로 들어오는 간선의 개수를 구한다.
    private void calcInEdge() {
        for(int node=1; node<=nodes; node++) {  // 각 노드의 연결을 확인한다
            for(int[] otherNodes : servers[node]) { // 노드로 들어오는 간선의 개수를 세어준다.
                for(int otherNode : otherNodes) {
                    inEdges[otherNode]++;
                }
            }
        }
    }

    // 위상 정렬을 수행한다.
    private void topologySort() {
        // 위상 정렬을 위한 Stack
        Stack<Integer> st = new Stack<>();

        // 각 노드를 순회하며, 들어오는 간선의 개수가 0인 노드가 우선순위를 갖는다.
        for(int node=1; node<=nodes; node++) {
            if(inEdges[node] == 0) {
                st.push(node);
            }
        }

        while(!st.isEmpty()) {
            int nowNode = st.pop();


        }
    }
}

/*
총 N 개의 서버가 있다.
호드 밸런서와 워커 노드 모두 서버라고 지칭
하나의 서버가 로드 밸런서이면서 워커 노드일 수 없음 -> 한번에 한개만 ㅇㅇ

i 번쩨 서버가 로드 밸런서라면 중복을 포함하여 r[i] 개 서버로 트래픽을 분산한다.
r[i] = 0 -> i 번째는 워커노드로 료청을 직접 처리 / 1 이상이라면 로드밸런서로 RR 방식으로 트래픽 분산
-> 라운드 로빈이면 Queue 혹은 수식이 있는 듯
-> 제한 범위가 너무 커서 수식일듯? - 상수시간으로 접근?

각 로드밸런서에는 x[i] 변수가 있음. x[i] 는 처음에 1
=> 로드밸런서로 요청 1개 들어옴 -> p[i] x[i] 서버로 트래픽 전달 -> x[i] = x[i]%r[i]+1 -> 해당 수식은 그냥 다음 서버 가리키는 듯?
1번 서버는 루트 로드 밸런서로,  모든 요청은 여기로 옴

트래픽 분산 규칙
i -> p[i][j] 간선들로 구성된 그래프에서 사이클 X == 1 번 서버로 많은 요청 -> 모든 서버로 적어도 1개 요청 전달

총 K 개의 요청 -> 각 서버로 들어오는 요청의 개수가 몇 개?

제약조건
2 ≤ N ≤ 100,000
1 ≤ K ≤ 10^18
r1 + r2 + ... + rN ≤ 500,000 이다.
 */

/*
📌 위상 정렬 사용
 */