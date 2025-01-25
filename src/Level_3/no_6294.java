package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 성적 평균
// https://softeer.ai/practice/6294
public class no_6294 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int studentNum, testCase;
    static int[] scores;
    static int[] rangeSum;
    public static void main(String[] args) throws IOException {
        no_6294 problem = new no_6294();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        studentNum = Integer.parseInt(st.nextToken());  // 학생 수
        testCase = Integer.parseInt(st.nextToken());    // 주어지는 구간의 수
        scores = new int[studentNum+1];   // 성적 배열 초기화
        rangeSum = new int[studentNum+1]; // 구간 합을 위한 배열

        st = new StringTokenizer(br.readLine().trim());
        for(int idx=1; idx<=studentNum; idx++) { // 성적 입력
            scores[idx] = Integer.parseInt(st.nextToken());
        }

        problem.calcRangeSum(); // 구간합을 계산한다

        StringBuilder sb = new StringBuilder();
        for(int tc=0; tc<testCase; tc++) {  // 구간을 입력 받는다
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            double avg = problem.gradeAvg(start, end);
            sb.append(String.format("%.2f", avg)).append('\n');
        }
        br.close();
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void calcRangeSum() {   // 구간합을 구한다
        for(int idx=1; idx <= studentNum; idx++){
            rangeSum[idx] = rangeSum[idx-1] + scores[idx];
        }
    }

    private double gradeAvg(int start, int end) {
         return (double)(rangeSum[end] - rangeSum[start-1]) / (end-start+1);
    }
}