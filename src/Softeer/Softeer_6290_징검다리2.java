package Softeer;

import java.util.*;
import java.io.*;

public class Softeer_6290_징검다리2 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static int arrLen;              // 주어지는 수열의 길이
    static int[] arr;              // 수열
    static int[] lisArr;            // 정방향 LIS
    static int[] lisArrReverse;     // 역방향 LIS
    static void init() throws IOException {
        arrLen = Integer.parseInt(br.readLine().trim());
        // 10^8 => 10,000,000 => int 가능
        arr = new int[arrLen];
        lisArr = new int[arrLen];
        lisArrReverse = new int[arrLen];
        lisArr[0] = lisArrReverse[arrLen-1] = 1;

        // 정방향 LIS 저장
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine().trim());
        arr[0] = Integer.parseInt(st.nextToken());
        list.add(arr[0]);
        for(int i=1; i<arrLen; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            // LIS
            if(list.get(list.size()-1) < arr[i]) {  // LIS 증가
                list.add(arr[i]);
                lisArr[i] = list.size();
                continue;
            }

            // 새로은 LIS 생성
            int insertIdx = findInsertIdx(arr[i], list);
            list.set(insertIdx, arr[i]);
            lisArr[i] = insertIdx+1;
        }

        // 역방향 LIS 저장
        list.clear();
        list.add(arr[arrLen-1]);
        for(int i=arrLen-2; i>-1; i--) {
            // LIS
            if(list.get(list.size()-1) < arr[i]) {  // LIS 증가
                list.add(arr[i]);
                lisArrReverse[i] = list.size();
                continue;
            }

            // 새로은 LIS 생성
            int insertIdx = findInsertIdx(arr[i], list);
            list.set(insertIdx, arr[i]);
            lisArrReverse[i] = insertIdx+1;
        }

        // 두 LIS 의 최대 조합 찾기
        int max = Integer.MIN_VALUE;
        for(int i=0; i<arrLen; i++) {
            max = Math.max(max, lisArr[i]+lisArrReverse[i]-1);
        }
        System.out.println(max);
    }

    /*
        원소 삽입 위치 찾기
        lowerbound
     */
    static int findInsertIdx(int target, List<Integer> list) {
        int l = 0, r = list.size()-1;

        while(l<r) {
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
    점점 옾을 돌을 밟다가 점점 낮은 돌을 밟아야한다.
    -> 순방향과 역방향으로 LIS 를 구해서 최대 합을 찾는다.
 */