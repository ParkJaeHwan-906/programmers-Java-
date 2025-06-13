package Programmers.Lv2;

import java.util.*;
import java.io.*;

public class PMMS_340211_충돌위험찾기 {
    static BufferedReader br;
    static StringTokenizer st;
    static int[][] points;
    static int[][] routes;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
//        init();
        points = new int[][] {{3,2},{6,4},{4,7},{1,4}};
        routes = new int[][] {{4,2},{1,3},{2,4}};
        System.out.print("결과 : " + new PMMS_340211_충돌위험찾기().solution(points, routes));
    }

    static void init() throws IOException {
        System.out.print("포인트의 개수를 입력하세요 : ");
        int pointCnt = Integer.parseInt(br.readLine());
        points = new int[pointCnt][];
        System.out.println("각 포인트의 좌표를 입력하세요.");
        for(int i=0; i<pointCnt; i++) {
            System.out.printf("%d 번 포인트 : ", i+1);
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i] = new int[] {x, y};
        }
        System.out.println("입력 포인트 확인");
        for(int[] point : points) {
            System.out.print(Arrays.toString(point)+" ");
        }
        System.out.println();
        System.out.print("로봇의 개수를 입력하세요 : ");
        int robotCnt = Integer.parseInt(br.readLine());
        routes = new int[robotCnt][];
        System.out.println("각 로봇의 경로를 입력하세요.");
        for(int i=0; i<robotCnt; i++) {
            System.out.printf("%d 번 로봇 : ", i+1);
            st = new StringTokenizer(br.readLine());
            int routeLen = st.countTokens();
            int[] route = new int[routeLen];
            for(int j=0; j<routeLen; j++) route[j] = Integer.parseInt(st.nextToken());
            routes[i] = route;
        }
        System.out.println("입력 포인트 확인");
        for(int[] route : routes) {
            System.out.print(Arrays.toString(route)+" ");
        }
        System.out.println();
    }

    ArrayList<int[]>[] robotsRoute;      // 각 로봇의 이동 경로
    int maxRouteLen;                     // 로봇들의 이동 경로 중 가장 긴 경로의 길이
    public int solution(int[][] points, int[][] routes) {
        maxRouteLen = 0;
        robotsRoute = new ArrayList[routes.length];

        // 각 로봇이 움직일 수 있는 최단 경로를 저장한다.
        for(int i=0; i<routes.length; i++) {
//            System.out.printf("%d 번 로봇 : ", i+1);
            findShortestPath(routes[i], i);
//            for(int[] arr : robotsRoute[i]) {
//                System.out.print(Arrays.toString(arr)+" ");
//            }
//            System.out.println();
        }

        // 구한 경로에서 충돌되는 경로를 구한다.
        // 동 시간대 겹치는 경로를 확인하기 위해 map 사용
        Map<Integer, Integer> checkDuplicate = new HashMap<>();
        int ans = 0;
        for(int time=0; time<maxRouteLen; time++) {
            // 매 시간 map 초기화
            checkDuplicate.clear();

            for(int robotIdx=0; robotIdx<routes.length; robotIdx++) {
                // 현 시간에 움직이지 않는다면
                if(robotsRoute[robotIdx].size() <= time) continue;

                // 움직인다면
                int key = 1000000*robotsRoute[robotIdx].get(time)[0] + 10000*robotsRoute[robotIdx].get(time)[1];
                checkDuplicate.put(key, checkDuplicate.getOrDefault(key, 0)+1);
            }

            // 중복 개수를 카운트
            for(int key : checkDuplicate.keySet()) {
                if(checkDuplicate.get(key) > 1) ans++;
            }
        }

        return ans;
    }

    class Node {
        int x, y;   // 현재 좌표
        Node prev;   // 이전 좌표

        public Node(int x, int y, Node prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }
    }

    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    // 최단 경로를 구한다.
    void findShortestPath(int[] route, int robotIdx) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visitied = new boolean[101][101];

        int nextIdx = 0;
        // 1. 시작 위치를 설정한다.
        int startPoint = route[nextIdx++];
        visitied[points[startPoint-1][0]][points[startPoint-1][1]] = true;
        q.offer(new Node(points[startPoint-1][0], points[startPoint-1][1], null));

        // 2. 시작위치에서 출발해서, 모든 경로를 거쳐 이동하는 최단 경로를 찾는다.
        //      이때, 이동은 x 축 좌표가 우선적으로 이동한다.
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
//            Node prev = cur.prev;

            // 현 위치가 가고자하는 위치에 도착했는지
            if(points[route[nextIdx]-1][0] == x && points[route[nextIdx]-1][1] == y) {
                // 다음에 또 탐색할 위치가 있는지 확인
                if(++nextIdx == route.length) {
                    // 모든 경로를 역추적하고 종료
                    robotsRoute[robotIdx] = traceRoute(cur);
                    return;
                }
                // 더 탐색할 수 있다면 계속해서 탐색
                // 이전까지 경로는 삭제 후 새로 처리
                // 방문처리도 새로 처리 필요
                q.clear();
                visitied = new boolean[101][101];
                visitied[cur.x][cur.y] = true;
            }
            
            for(int dir=0; dir<4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                // 범위를 벗어나지 않는지 확인 
                if(nx < 0 || ny < 0 || nx > 100 || ny > 100) continue;
                if(visitied[nx][ny]) continue;
                
                // 범위를 벗어나지 않는다면 탐색
                visitied[nx][ny] = true;
                q.offer(new Node(nx, ny, cur));
            }
        }
    }

    // 역추적하여 경로를 구성한다.
    ArrayList<int[]> traceRoute(Node node) {
        ArrayList<int[]> list = new ArrayList<>();
        Node cur = node;

        while(cur.prev != null) {
            list.add(0, new int[] {cur.x, cur.y});
            cur = cur.prev;
        }
        list.add(0, new int[] {cur.x, cur.y});
        maxRouteLen = Math.max(maxRouteLen, list.size());
        return list;
    }
}

/*
    2차원 격자가 존재한다.

    로봇마다 정해진 운송 경로가 존재한다.
    운송 경로는 m 개의 포인트로 구성되고, 순서대로 방문해야한다.

    사용되는 로봇은 x 대이고, 모든 로봇은 0초에 동시 출발한다.
    로봇은 1초마다 r 좌표와 c 좌표 중 하나가 1 만큼 감소하거나, 증가한 좌표로 이동할 수 있다.

    다음 포인트로 이동할 때는 항상 최단 경로로 이동한다.
    최단 경로가 여러 가지일 경우, r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저이다.
*/