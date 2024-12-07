package CodingTest;

import java.util.*;
import java.io.*;

public class HSAT241206_5 {
    static int n, m;
    static int[][][] item;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());   // 아이템의 개수  (최대 100,000)
        m = Integer.parseInt(st.nextToken());   // 아이템 속성의 개수  (최대 5)

        item = new int[m][n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<m; j++){
                item[j][i][0] = (i+1);  // 아이템 번호
                item[j][i][1] = Integer.parseInt(st.nextToken());   // 해당 속성의 값
            }
        }

        // 정렬
        // 특정 속성의 값 기준으로, 오름차순 정렬
        for(int i=0; i<m; i++) {
            Arrays.sort(item[i], (a, b) -> {
                return a[1] - b[1];
            });
        }

        HSAT241206_5 problem = new HSAT241206_5();

        int q = Integer.parseInt(br.readLine());    // 검색 횟수  (최대 300,000)
        for(int i=0; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());    // 내부 원소들의 합  (최대 300,000)

            List<Integer> search = new ArrayList<>();
            for(int j=0; j<size; j++){
                search.add(Integer.parseInt(st.nextToken()));
            }

            int[] answer = problem.solution(search, size);
            for(int value : answer){
                bw.write(value+" ");
            }
            if(i != q-1) bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    // 어떤 속성 값 기준으로 정렬 했는지, 정렬 후의 범위    /   불가능이면 -1
    public int[] solution(List<Integer> search, int size) {
        for(int j = 0; j < m; j++) {
            // 범위를 확인해가며 범위 내에 원하는 값이 모두 있는지 확인 ( Sliding Window )
            for(int l = 0; l <= item[j].length-size; l++) {
                boolean find = true;
                for(int r = l; r < l+size; r++){
                    // 그룹의 값이 범위 내에 없는 경우
                    if(!search.contains(item[j][r][0])){
                        find = false;
                        break;
                    }
                }
                if(!find) continue;
                // 그룹 내에 값이 있는 경우
                return new int[] {j+1, (l+1), (l+size)};
            }
        }
        return new int[] {-1};
    }
}

/*
입력
5 3
1 99 1
3 55 2
5 97 1
7 93 2
9 95 1
9
5 1 2 3 4 5
4 1 2 5 4
4 1 3 4 5
4 1 3 2 5
3 5 3 1
2 1 5
2 1 3
3 1 4 5
1 4

출력
1 1 5
-1
-1 2 5
3 1 4
2 3 5
-1
2 4 5
-1
1 4 4
 */