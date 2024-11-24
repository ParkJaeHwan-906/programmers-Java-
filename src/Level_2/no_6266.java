package Level_2;

import java.util.*;
import java.io.*;

// Softeer Lv.2
// [21년 재직자 대회 예선] 회의실 예약
// https://www.softeer.ai/practice/6266
public class no_6266 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 회의실 수
        int n = Integer.parseInt(st.nextToken());
        // 예약된 회의 수
        int m = Integer.parseInt(st.nextToken());

        String[] meetingRoom = new String[n];
        for(int i=0; i<n; i++){
            meetingRoom[i] = br.readLine();
        }

        // [회의실, 시작시간, 종료시간]
        String[][] booking = new String[m][3];
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            booking[i][0] = st.nextToken(); // 회의실
            booking[i][1] = st.nextToken(); // 시작시간
            booking[i][2] = st.nextToken(); // 종료시간
        }
        br.close();

        no_6266 problem = new no_6266();
        problem.solution(meetingRoom, booking);
    }

    public void solution(String[] meetingRoom, String[][] booking) {
        Arrays.sort(meetingRoom); // 회의실 이름 정렬

        for (int r = 0; r < meetingRoom.length; r++) {
            String room = meetingRoom[r];
            System.out.printf("Room %s:\n", room);

            boolean[] time = checkTime(room, booking); // 각 시간대 예약 확인
            StringBuilder sb = new StringBuilder();
            boolean flag = false;
            int count = 0;

            // ⚠️ 형식 지정자 사용하여 2자리 표기
            for (int i = 0; i < 10; i++) {
                if (!time[i] && !flag) { // 예약 가능 시작
                    sb.append(String.format("%02d-", i + 9));
                    flag = true;
                    count++;
                }
                if ((time[i] && flag) || (!time[i] && flag && i == 9)) { // 예약 가능 끝
                    sb.append(String.format("%02d\n", i + 9));
                    flag = false;
                }
            }

            if (count == 0) {
                System.out.println("Not available");
            } else {
                System.out.printf("%d available:\n", count);
                System.out.print(sb.toString());
            }

            if (r < meetingRoom.length - 1) { // 마지막 회의실이 아닐 경우에만 구분선 출력
                System.out.println("-----");
            }
        }
    }

    public boolean[] checkTime(String room, String[][] booking) {
        boolean[] time = new boolean[10];

        for (String[] book : booking) {
            if (!book[0].equals(room)) continue; // 해당 회의실의 예약이 아니면 건너뜀

            int s = Integer.parseInt(book[1]) - 9;
            int e = Integer.parseInt(book[2]) - 9;
            if(e != 9) --e; // 18시에 회의가 끝나는것이 아니라면 끝나는 시간에 회의실 사용이 가능하므로 -1 해줌

            for (int i = s; i <= e; i++) { // 종료 시간을 포함하지 않음
                time[i] = true;
            }
        }
        return time;
    }
}
