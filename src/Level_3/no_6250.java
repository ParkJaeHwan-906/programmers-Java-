package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 5회 정기 코딩 인증평가 기출] 성적 평가
// https://www.softeer.ai/practice/6250
public class no_6250 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        int[][] finalScore = new int[n][2];
        no_6250 problem = new no_6250();
        for(int i=0; i<3; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            // [순서, 점수]
            int[][] score = new int[n][2];
            for(int j=0; j<n; j++){
                score[j][0] = j;
                score[j][1] = Integer.parseInt(st.nextToken());

                finalScore[j][0] = j;
                finalScore[j][1] += score[j][1];
            }
            int[] answer = problem.solution(score);
            for(int rank : answer){
                bw.write(rank+" ");
            }
            bw.write("\n");
        }

        int[] answer = problem.solution(finalScore);
        for(int rank : answer){
            bw.write(rank+" ");
        }

        bw.flush();
        bw.close();
    }

    public int[] solution(int[][] score){
        int[] answer = new int[score.length];

        Arrays.sort(score, (a, b) -> b[1] - a[1]);

        for(int i=0; i < answer.length; i++) {
            int[] arr = score[i];
            int idx = arr[0];
            int s = arr[1];

            if(i == 0) {
                answer[idx] = i+1;
                continue;
            }

            if(s == score[i-1][1]) {    // 동점이라면
                answer[idx] = answer[score[i-1][0]];
            } else{
                answer[idx] = i+1;
            }
        }


        return answer;
    }
}
