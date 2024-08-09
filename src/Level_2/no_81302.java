package Level_2;

import java.util.LinkedList;
import java.util.Queue;

public class no_81302 {

    final int[] dx = new int[] {0,1,0,-1};
    final int[] dy = new int[] {1,0,-1,0};
    public int[] solution(String[][] places){
        int[] answer = new int[places.length];

        for(int i = 0; i < places.length; i++){
            // 거리두기를 지키는 고사장인지 확인하는 변수
            boolean check = true;
            for(int x=0; x<5 && check; x++){
                for(int y=0; y<5 && check; y++){
                    // 응시자가 있는 자리라면
                    if(places[i][x].charAt(y) == 'P'){
                        // 거리를 잘 두고 있는지 확인
                        check = bfs(x,y,places[i]);
                    }
                }
            }
            answer[i] = check ? 1 : 0;
        }
        return answer;
    }

    public boolean bfs(int x, int y, String[] place){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});

        while(!q.isEmpty()){
            int[] xy = q.poll();

            for(int i=0; i<4; i++){
                int nx = xy[0] + dx[i];
                int ny = xy[1] + dy[i];

                if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && !(nx == x && ny == y)){
                    int d = Math.abs(nx-x) + Math.abs(ny-y);

                    if(place[nx].charAt(ny) == 'P' && d <= 2){
                        return false;
                    } else if(place[nx].charAt(ny) == 'O' && d < 2) {
                        q.add(new int[] {nx, ny});
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[][] places = new String[][] {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                                            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                                            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                                            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                                            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        no_81302 problem = new no_81302();
        int[] result = problem.solution(places);
        System.out.print("[ ");
        for(int i : result){
            System.out.print(i+" ");
        }
        System.out.print("]");
    }
}
