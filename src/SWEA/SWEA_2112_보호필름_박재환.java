package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_2112_보호필름_박재환 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase <= TC; testCase++) {
            sb.append('#').append(testCase).append(' ');
            init();
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

        br.close();
    }

    static int height, width, cutLine;    // 두께, 넓이, 심사기준
    static int[][] film;    // 보호필름
    static int drugTime;    // 약품 투약 횟수
    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        cutLine = Integer.parseInt(st.nextToken());
        drugTime = height;  // 최대 투입 횟수는 필름의 두께

        film = new int[height][width];
        for(int h=0; h<height; h++) {
            st = new StringTokenizer(br.readLine().trim());
            for(int w=0; w<width; w++) {
                film[h][w] = Integer.parseInt(st.nextToken());
            }
        }

        // 약품 투입 없이 통과가 가능하다면
        if(testFilm()) {
            sb.append(0);
            return;
        }

        // 약품 투입이 필요하다면
        putDrug(0,0);
        sb.append(drugTime);
    }

    /*
     * 약품을 투입한다.
     * 이전의 최적해를 넘는 경우 탐색 종료 -> 가지치기
     * 끝까지 탐색한 경우 -> 검사 ㄱㄱ
     * 
     * 더 탐색할 수 있음 
     * 1. 약품 안넣음
     * 2. 약품 넣음 -> 기존 배열을 복사해야지 되돌릴 수 있음
     *  a. A
     *  b. B
     */
    static void putDrug(int heightIdx, int drug) {
        // 이전의 최적해를 넘는 경우 탐색할 필요가 없음
        if(drug >= drugTime) return;

        // 모든 행을 탐색했다면, 검사한다
        if(heightIdx == height) {
            if(testFilm()) {
                drugTime = drug;
            }
            return;
        }

        // 더 탐색할 수 있다.

        // 1. 약품 투입을 안함
        putDrug(heightIdx+1, drug);

        // 2. 약품 투입
        // 원래 행을 기록해야 다시 돌려놓을 수 있음
        int[] copyFilm = film[heightIdx].clone();
        // a. A(0) 으로 전부 교체
        changeAttr(heightIdx, 0);
        putDrug(heightIdx+1, drug+1);
        // b. B(1) 으로 전부 교체
        changeAttr(heightIdx, 1);
        putDrug(heightIdx+1, drug+1);

        // 되돌리기
        film[heightIdx] = copyFilm;
    }

    // 특정 행을 A(0) 또는 B(1)로 변경
    static void changeAttr(int filmIdx, int attr) {
        Arrays.fill(film[filmIdx], attr);
    }

    // 필름을 검사한다.
    // 중복 범위 반복을 막기 위해 슬라이딩 윈도우 기법을 사용
    static boolean testFilm() {
        // 검사 방향은 상 -> 하
        for (int w = 0; w < width; w++) {
            int aCnt = 0;
            int bCnt = 0;
            // 초기값 설정 (0부터 cutLine 전까지)
            for (int h = 0; h < cutLine; h++) {
                if (film[h][w] == 0) aCnt++;
                else bCnt++;
            }

            // 초기 슬라이딩 윈도우 검사
            if (aCnt >= cutLine || bCnt >= cutLine) continue;

            boolean isOk = false;
            // 슬라이딩 윈도우 적용
            for (int h = 0; h <= height - cutLine - 1; h++) {
                // 이전 윈도우 값 제거
                if (film[h][w] == 0) aCnt--;
                else bCnt--;

                // 새로운 윈도우 값 추가
                if (film[h + cutLine][w] == 0) aCnt++;
                else bCnt++;

                // 조건 충족 여부 확인
                if (aCnt >= cutLine || bCnt >= cutLine) {
                    isOk = true;
                    break;
                }
            }

            // 해당 열이 조건을 만족하지 않을 경우
            if (!isOk) return false;
        }

        return true;
    }

}

/*
 * 약물을 통해 보호필름 단면 한줄을 A 또는 B 로 변경할 수 있다.
 *
 * 연속적으로 A 또는 B 가 K 개 이상 있어야지 검사를 통과할 수 있다.
 *
 * 1.완탐? 2^13 + 20*13 정도면 되지 않나
 *         a. 각 필름을 A 또는 B 로 변경시킨다.
 *             변경 후에 검사를 진행한다. -> 검사가 통과된다면 바로 종료? 어차피 최소검사횟수 구하는거 아닌가?
 * ?? 검사를 통과하지 못하는 경우는 주어지지 않는듯 ??
 */