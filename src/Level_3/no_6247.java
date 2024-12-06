package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [HSAT 7회 정기 코딩 인증평가 기출] 자동차 테스트
// https://softeer.ai/practice/6247
public class no_6247 {
    // ⚠️ 중앙값과 평균값의 차이 구분
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        long[] car = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            car[i] = Long.parseLong(st.nextToken());
        }
        // 연비를 오름차순 정렬
        Arrays.sort(car);

        for(int i=0; i<q; i++){
            long test = Long.parseLong(br.readLine());

            int answer = 0;
            // Arrays 의 binarySearch 사용
            int idx = Arrays.binarySearch(car, test);

            if(idx >= 0) {
                // 중간값을 기준으로 왼쪽에 있는 값들 중 왼쪽것을 뽑는 경우
                // 중간값을 기준으로 오른쪽에 있는 값들 중 오른쪽것을 뽑는 경우
                answer = idx * (n-idx-1);
            }
            bw.write(answer+"\n");
        }

        bw.flush();
        bw.close();
    }

    // 이분탐색 구현
    public static int findMid(long[] car, long test) {
        int s = 0;
        int e = car.length-1;

        while(s <= e) {
            int mid = s + (e-s) / 2;

            if(car[mid] == test) return mid;
            else if(car[mid] < test) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return -1;
    }
}
