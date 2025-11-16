package CodingTest;

import java.util.*;
import java.io.*;

public class AutoEver251117_1 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static void init() throws IOException {
        String initStr = br.readLine().trim();
        int commandCnt = Integer.parseInt(br.readLine().trim());
        String[][] commands = new String[commandCnt][2];
        for(int i=0; i<commandCnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            commands[i][0] = st.nextToken();
            commands[i][1] = st.nextToken();
        }

        System.out.println(solution(initStr, commands));
    }

    static String solution(String initStr, String[][] commands) {
        Map<String, Long> etcStr = new HashMap<>();
        int initLen = initStr.length();
        for(String[] command : commands) {
            String target = command[0];
            String replace = command[1];
            // 텍스트 치환
            initStr = initStr.replace(target, replace);
            // 기타 문자열에 대해서 개수 카운트
            if(etcStr.containsKey(target)) {
                long remain = etcStr.get(target);
                etcStr.remove(target);

                for(String s : replace.split("")) {
                    etcStr.put(s, etcStr.getOrDefault(s, 0L)+remain);
                }
            }

            // 기존 문자열에서 계산되지 않아도 되는 부분 제거
            if(initStr.length() > initLen*2) {
                String removeTarget = initStr.substring(initLen*2);
                initStr = initStr.substring(0, initLen*2);

                for(String s : removeTarget.split("")) {
                    etcStr.put(s, etcStr.getOrDefault(s, 0L)+1);
                }
            }
        }

        if(etcStr.isEmpty()) return initStr;
        StringBuilder sb = new StringBuilder(initStr);
        long cnt = 0L;
        for(String key : etcStr.keySet()) cnt += etcStr.get(key);
        return sb.append('+').append(cnt).toString();
    }
}
