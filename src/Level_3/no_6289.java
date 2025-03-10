package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// 우물안 개구리
// https://softeer.ai/practice/6289
public class no_6289 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int memberNum, relationNum;
    static int[] members;
    static ArrayList<Integer>[] relations;
    public static void main(String[] args) throws IOException {
        no_6289 problem = new no_6289();

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        memberNum = Integer.parseInt(st.nextToken());   // 회원의 수
        members = new int[memberNum+1];
        relationNum = Integer.parseInt(st.nextToken()); // 관계의 수
        relations = new ArrayList[memberNum+1];
        for(int idx=0; idx <= memberNum; idx++) {
            relations[idx] = new ArrayList<Integer>();
        }

        st = new StringTokenizer(br.readLine().trim()); // 각 회원이 들 수 있는 무게
        for(int idx = 1; idx <= memberNum; idx++) {
            members[idx] = Integer.parseInt(st.nextToken());
        }

        // 각 친분 관계를 연결
        for(int idx=0; idx < relationNum; idx++){
            st = new StringTokenizer(br.readLine().trim());

            int memberA = Integer.parseInt(st.nextToken());
            int memberB = Integer.parseInt(st.nextToken());

            relations[memberA].add(memberB);
            relations[memberB].add(memberA);
        }
        br.close();

        bw.write(String.valueOf(problem.getImtheBest()));
        bw.flush();
        bw.close();
    }

    int answer;
    boolean[] checked;
    private int getImtheBest() {
        answer = 0;
        checked = new boolean[memberNum+1];

        compareStorng();

        return answer;
    }

    // 각 회원을 확인한다
    /*
    현재 회원과 관계가 있는 회원을 찾는다
        본인이 더 크다면 체크한다
        본인이 더 작다면 넘어간다
     */
    private void compareStorng() {
        for(int idx = 1; idx <= memberNum; idx++) {
            if(relations[idx].size() == 0) {    // 연결되어 있는 관계가 없음
                // 내가 최고
                answer++;
                continue;
            }

            // 연결되어 있는 관계가 있음
            // 모든 상대의 최대 무게를 확인하여 비교한다.
            boolean imKing = true;
            for(int member : relations[idx]) {
                if(members[idx] <= members[member]) {    // 내가 들 수 있는 무게보다 상대가 들 수 있는 무게가 큰 경우
                    imKing = false;
                    break;
                }
            }
            answer = imKing ?  answer + 1 : answer;
        }
    }
}
