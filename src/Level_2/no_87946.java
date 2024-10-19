package Level_2;

// 프로그래머스 Lv.2
// 피로도
// https://school.programmers.co.kr/learn/courses/30/lessons/87946
public class no_87946 {
    boolean[] visited;
    // 탐험 가능한 최대 던전 수 리턴
    int answer = -1;
    public int solution(int k, int[][] dungeons){
        visited = new boolean[dungeons.length];
        dfs(k, 0, dungeons);
        return answer;
    }

    public void dfs(int k, int count, int[][] dungeons){
        // ⚠️ 모든 던전을 탐험하지 않더라도 정답을 찾을 수 있는 경우가 있기에 depth 를 사용하지 않음
        for(int i = 0; i < dungeons.length; i++){
            if(visited[i]) continue;

            if(k >= dungeons[i][0]){
                visited[i] = true;
                dfs(k - dungeons[i][1], count +1, dungeons);
                visited[i] = false;
            }
        }
        answer = Math.max(answer, count);
    }

    public static void main(String[] args){
        int k = 80;
        // [최소 필요 피로도, 소모 피로도]
        int[][] dungeons ={{80,20}, {50,40}, {30,10}};

        no_87946 problem = new no_87946();
        System.out.println(problem.solution(k, dungeons));
    }
}
