package Level_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Softeer Lv.1
// 근무시간
// https://www.softeer.ai/practice/6254
public class no_6254 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[][] work = new String[5][2];

        for(int i=0; i<5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            work[i][0] = st.nextToken();
            work[i][1] = st.nextToken();
        }
        no_6254 problem = new no_6254();
        System.out.println(problem.solution(work));
    }

    public int solution(String[][] work) {
        int hours = 0;
        int minutes = 0;
        for(String[] onOff : work){
            String[] on = onOff[0].split(":");
            String[] off = onOff[1].split(":");

            // 출근 시간
            int onHours = Integer.parseInt(on[0]);
            int onMinute = Integer.parseInt(on[1]);
            // 퇴근 시간
            int offHours = Integer.parseInt(off[0]);
            int offMinute = Integer.parseInt(off[1]);

            hours += offHours - onHours;
            minutes += offMinute - onMinute;
        }

        return hours*60 + minutes;
    }
}
