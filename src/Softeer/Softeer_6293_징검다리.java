package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_6293_징검다리 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int stoneCnt;
    static void init() throws IOException {
        stoneCnt = Integer.parseInt(br.readLine().trim());

        /*
            입력과 동시에 LIS ( 이분탐색 ) 을 활용한다.
         */
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine().trim());
        list.add(Integer.parseInt(st.nextToken()));
        while(stoneCnt-- > 1) {
            int num = Integer.parseInt(st.nextToken());

            if(list.get(list.size()-1) < num) {
                list.add(num);
                continue;
            }

            int insertIdx = findInsertIdx(num, list);
            list.set(insertIdx, num);
        }

        System.out.println(list.size());
    }

    /*
        target 이 들어갈 위치 인덱스를 반환한다.
        LowerBound
     */
    static int findInsertIdx(int target, List<Integer> list) {
        int l = 0; int r = list.size()-1;

        while(l < r) {
            int mid = l + (r-l)/2;

            if(list.get(mid) < target) {
                l = mid+1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}

/*
    징검다리로 이동할 때 점점 높은 돌을 밟으면서 개울을 지나간다.
    => LIS => 최장 증가 부분 수열?
 */