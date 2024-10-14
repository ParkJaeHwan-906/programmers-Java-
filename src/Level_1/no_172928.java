package Level_1;

import java.util.Arrays;

// 프로그래머스 Lv.1
// 공원 산책
// https://school.programmers.co.kr/learn/courses/30/lessons/172928
public class no_172928 {
    /*
    지나다니는 길을 O, 장애물을 X 로 표현, 출발 지점은 S로 표현
    명령어(routes)는 "방향 거리" 로 주어짐
    E : 동쪽, W : 서쪽, S : 남쪽, N : 북쪽

    ⚠️ 공원을 벗어나거나 장애물을 만나는경우 명령생략 -> 다음 명령 실행
     */

    private int w;
    private int h;
    public int[] solution(String[] park, String[] routes){
        try {
            h = park.length;
            w = park[1].length();
            // 시작 지점 찾기
            int[] start = findStart(park);
            if (Arrays.stream(start).count() == 0) throw new IllegalArgumentException("시작 지점이 존재하지 않음");
            int x = start[0];
            int y = start[1];
            // 이동하기
            for (String s : routes) {
                // 방향과 거리로 나누기
                String[] command = s.split(" ");
                int distance = Integer.parseInt(command[1]);
                boolean b = true;
                switch (command[0]) {
                    case "E":   // 동쪽 (오른쪽)
//                        System.out.println(command[0]);
//                        System.out.println(x+" "+y);
                        if (y + distance < w) {
                            for (int i = 1; i <= distance; i++) {
                                if (park[x].charAt(y + i) == 'X') {
                                    b = false;
                                    break;
                                }
                            }
                            if (b) y += distance;
                        }
                        break;
                    case "W":   // 서쪽 (왼쪽)
//                        System.out.println(command[0]);
//                        System.out.println(x+" "+y);
                        if (y - distance >= 0) {
                            for (int i = 1; i <= distance; i++) {
                                if (park[x].charAt(y - i) == 'X') {
                                    b = false;
                                    break;
                                }
                            }
                            if (b) y -= distance;
                        }
                        break;
                    case "N":   // 북쪽 (위쪽)
//                        System.out.println(command[0]);
//                        System.out.println(x+" "+y);
                        if (x - distance >= 0) {
                            for (int i = 1; i <= distance; i++) {
                                if (park[x - i].charAt(y) == 'X') {
                                    b = false;
                                    break;
                                }
                            }
                            if (b) x -= distance;
                        }
                        break;
                    case "S":   // 남쪽 (아래쪽)
//                        System.out.println(command[0]);
//                        System.out.println(x+" "+y);
                        if (x + distance < h) {
                            for (int i = 1; i <= distance; i++) {
                                if (park[x + i].charAt(y) == 'X') {
                                    b = false;
                                    break;
                                }
                            }
                            if (b) x += distance;
                        }
                        break;
                }
            }

            return new int[]{x, y};
        } catch(Exception e){
            System.out.println(e);
            return new int[]{};
        }
    }

    public int[] findStart(String[] park){
        for(int x = 0; x < park.length; x++){
            for(int y = 0; y < park[x].length(); y++){
                if(park[x].charAt(y) == 'S') return new int[] {x, y};
            }
        }
        return new int[]{};
    }

    public static void main(String[] args){
//        String[] park = new String[] {"SOO","OOO","OOO"};
//        String[] park = new String[] {"SOO","OXX","OOO"};
        String[] park = new String[] {"OSO", "OOO", "OXO", "OOO"};
//        String[] routes = new String[] {"E 2","S 2","W 1"};
//        String[] routes = new String[] {"E 2","S 2","W 1"};
        String[] routes = new String[] {"E 2", "S 3", "W 1"};

        no_172928 problem = new no_172928();
        int[] answer = problem.solution(park, routes);

        for(int i : answer){
            System.out.print(i+" ");
        }
    }
}
/*
O S O
O O O
O X O
O O O
 */