package Level_3;

import java.util.*;
import java.io.*;

// Softeer Lv.3
// [21년 재직자 대회 예선] 마이크로서버
// https://softeer.ai/practice/6264
public class no_6264 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for(int testCase=0; testCase<TC; testCase++){
            init();
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int waitTaskNums;  // 현재 대기중인 업무들 개수
    static int[] waitTasks;  // 현대 대기중인 업무들(걸리는 시간)
    static void init() throws IOException {
        waitTaskNums = Integer.parseInt(br.readLine().trim());
        waitTasks = new int[waitTaskNums];

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for(int waitTaskIdx=0; waitTaskIdx<waitTaskNums; waitTaskIdx++) {
            waitTasks[waitTaskIdx] = Integer.parseInt(st.nextToken());
        }

        bindingTasks();
    }

    static void bindingTasks() {
        Arrays.sort(waitTasks);  // 업무들을 메모리 순으로 오름차순 정렬

        int left = 0;
        int right = waitTaskNums-1;
        int servers = 0; // 필요한 서버의 수

        // 601 ~ 900 의 메모리를 가지는 업무는 서버 하나를 통째로 할당해줘야한다
        // 조합이 안됨
        while(left <= right && waitTasks[right] > 600) {
            servers++;
            --right;
        }

        // 300 과 600 은 하나의 서버로 묶을 수 있음
        while(left <= right && waitTasks[left] == 300 && waitTasks[right] == 600) {
            ++left;
            --right;
            servers++;
        }

        // 남아있는 300 의 수를 센다
        int remain_300 = 0;
        while(left <= right && waitTasks[left] == 300) {
            left++;
            remain_300++;
        }

        // 위 반복문을 수행하면 300 ~ 600 사이의 값들만 남아있게 된다.
        // 나머지 중간 값들을 투포인터를 활용해 처리한다.
        while(left < right) {
            int needMemory = waitTasks[left] + waitTasks[right]; // 현재 업무들을 수행시기기 위해 필요한 메모리의 수

            if(needMemory <= 900) {  // 최대 실제 메모리를 차지할 수 있는 경우
                left++;
                right--;
            }
            else if(remain_300 > 0) {   // 900은 넘지만, 300이 남아있는 경우
                // 600 이하의 값들만 있으므로 300과 합쳐 할당한다.
                remain_300--;
                right--;
            }
            else {  // 900 이 넘고 묶을 수 없는 경우
                right--; // 메모리를 많이 사용하는 업무부터 처리한다.
            }

            servers++;
        }

        if(left == right) { // 업무가 한개만 남은 경우
            if(remain_300 > 0) {
                remain_300--;
            }
            servers++;
        }

        // 남아있는 300의 업무를 할당
        if(remain_300 > 0) {
            servers +=  remain_300 % 3 == 0 ? remain_300 / 3 : remain_300 / 3 +1; // 3개씩 묶어서 처리
        }
        sb.append(servers);
    }

}
/*
    클러스터는 여러 대의 마이크로서버로 구성됨
    각 마이크로서버는 정확하게 1000MiB 의 메모리를 갖는다.
    이 중 100MiB 는 예비용으로 남겨둔다 -> 실 메모리 900MiB

    하나의 마이크로서버에 여러 개의 마이크로서비스를 실행할 수 있음 -> 실행 공간은 실 메모리 공간을 넘을 수 없음
    각 서비스는 m[i] 만큼의 메모리만을 요구한다.

    모든 마이크로서비스를 실행하기 위해 최소 몇 대의 마이크로 서버가 필요한지

    첫 줄에 TC 개수가 주어진다.
    N 개의 마이크로서비스가 실행 대기중이다 -> 최대 10만
    모든 작업은 300 ~ 900 의 실행 시간을 갖는다. -> 최소 1개 ~ 3 개의 업무가 묶일 수 있다. -> 3 개의 업무가 묶일 수 있는 경우는 [300,300,300] 이거 하나밖에 없음

    Knapsack 과 비슷한듯
    -> 한 개의 서버 (900) 에 최대한 밀어넣어야한다.

    최대로 넣고 해당 대기 프로그램을 제거해야함


    1. 업무를 오름차순으로 정렬 후 투포인터를 활용
        1-1. 배열의 첫 번째 값을 가리키는 left 와 마지막 값을 가리키는 right 를 투포인터로 사용
            a. [가장 가벼운 것, 무거운 것] 의 쌍으로 묶어봄
            b. 두 합이 900 이 넘는다면 우선 무거운 것을 먼저 넘김 -> rigth--;
            c. 두 합이 900 이하라면 -> left++, right--
                i. 두 합이 900 하고도 추가로 다른 업무를 넣을 수 있다면 넣어서 보냄

    ----------------------------------------------------------------------------------------------
    2. 1번을 사용하되 약간의 보강 작업이 필요
        2-1. 서버에는 최대 900 만큼의 메모리가 올라갈 수 있다.
            a. 601 이상 900 이하의 업무들을 단독으로 서버를 할당 받아야한다.
            b. 300 인 업무와 600 인 업무는 서로 묶이는 것이 최소로 서버를 줄일 수 있다.
            c. 301 ~ 599 의 업무를 처리한다.
*/
