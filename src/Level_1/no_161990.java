package Level_1;

// 프로그래머스 Lv.1
// 바탕화면 정리
// https://school.programmers.co.kr/learn/courses/30/lessons/161990
public class no_161990 {
    /*
    빈칸 : "."
    파일이 있는 칸 : "#"

    최소한의 드래그로 모든 파일을 선택해 한번에 지우려한다.

    드래그는 S(lux, luy) 에서 E(rdx rdy) 로 드래그한다.
    드래그 한 거리는 |rdx-lux| + |rdy-luy|
     */
    private int w;
    private int h;
    public int[] solution(String[] wallpaper){
        h = wallpaper.length;
        w = wallpaper[0].length();

        // 드래그 시작 점 (가장 작은)
        int[] l = new int[] {h,w};
        // 드래그 끝 점 (가장 큰)
        int[] r = new int[] {0,0};

        int[] answer = {};
        for(int x = 0; x < wallpaper.length; x++){
            for(int y = 0; y < wallpaper[x].length(); y++){
                if(wallpaper[x].charAt(y) == '#'){
                    l[0] = Math.min(l[0], x);
                    l[1] = Math.min(l[1], y);

                    r[0] = Math.max(r[0], x);
                    r[1] = Math.max(r[1], y);
                }
            }
        }

        return new int[] {l[0],l[1], r[0]+1, r[1]+1};
    }

    public static void main(String[] args){
        no_161990 problem = new no_161990();

        // 바탕화면의 상태를 나타낸 문자열
        String[] wallpaper = new String[] {".#...", "..#..", "...#."};

        int[] answer = problem.solution(wallpaper);
        for(int i : answer){
            System.out.print(i+" ");
        }
    }
}
