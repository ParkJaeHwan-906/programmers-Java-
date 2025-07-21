package Programmers.Lv3;

import java.util.*;

public class PMMS_150366_표병합 {
    public static void main(String[] args) {
//        String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
//        String[] commands = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"};
//        String[] commands = {"UPDATE 1 1 menu", "MERGE 1 1 1 2", "MERGE 1 1 1 3", "MERGE 1 1 1 4", "MERGE 1 2 1 3", "UPDATE 1 1 hansik", "PRINT 1 1", "PRINT 1 2", "PRINT 1 3", "PRINT 1 4"};
//        String[] commands = {"MERGE 1 1 2 2", "MERGE 1 1 3 3", "UPDATE 3 3 A", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3"};
//        String[] commands = {"UPDATE 1 1 A", "UPDATE 2 2 B", "UPDATE 3 3 C", "UPDATE 4 4 D", "MERGE 1 1 2 2", "MERGE 3 3 4 4", "MERGE 1 1 3 3", "UNMERGE 1 1", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3", "PRINT 4 4"};
//        String[] commands = {"MERGE 1 1 2 2", "PRINT 1 1"};
        String[] commands = {"UPDATE 1 1 A", "UPDATE 2 2 B", "UPDATE 3 3 C", "UPDATE 4 4 D", "MERGE 1 1 2 2", "MERGE 3 3 4 4", "MERGE 1 1 4 4", "UNMERGE 3 3", "PRINT 1 1", "PRINT 2 2", "PRINT 3 3", "PRINT 4 4"};
        System.out.println(Arrays.toString(new PMMS_150366_표병합().solution(commands)));
    }

    /*
        50 x 50 표
        초기에 모든 셀은 비어있음
        각 셀은 문자열을 가질 수 있고, 다른 셀과 병합될 수 있음

        위에서 r 번째, 왼쪽에서 c 번째 위치 (r,c)
        [명령어]
        1. UPDATE r c value -> 해당 위치 value 로 업데이트
        2. UPDATE value1 value2 -> value1 값을 모두 value2 로 업데이트
        3. MERGE r1 c1 r2 c2 -> 두 셀을 병합한다.
            - 두 위치의 셀이 같은 셀일 경우 무시
            - 두 셀 중 한 셀이 값을 가지고 있는 경우, 병합된 셀은 그 값을 가진다.
            - 두 셀 모두 값이 있다면 r1 c1 의 값으로 병합된다.
            - 이후 r1 c1, r2 c2 증 어느 위치를 선택해도 병합된 셀로 접근한다
        4. UNMERGE r c -> MERGE 를 해제한다. -> Union-Find ??
            - 초기 상태로 돌아간다.
        5. PRINT r c -> 해당 위치의 값을 출력한다. ( 비어있는 경우 EMPTY 출럭 )
     */
    Queue<String> result;       // print 결과를 저장할 Queue
    Cell[][] arr;
    public String[] solution(String[] commands) {
        result = new LinkedList<>();

        // Margin 을 두고 설정
        // commands 의 idx 그대로 사용
        arr = new Cell[51][51];
        for(int r=1; r<51; r++) {
            for(int c=1; c<51; c++) {
                arr[r][c] = new Cell("", null);
                arr[r][c].merge = arr[r][c];
            }
        }

        excuteCommands(commands);

        String[] resultArr = new String[result.size()];
        for(int i=0; i<resultArr.length; i++) resultArr[i] = result.poll();
        return resultArr;
    }

    /*
        명령어를 수행한다.
     */
    void excuteCommands(String[] commands) {
        /*
            명령어의 종류 구분하기
            UPDATE
            - r c value
            - value1 value2
            MERGE r1 c1 r2 c2
            UNMERGE r c
            PRINT r c
         */
        for(String command : commands) {
            // 띄어쓰기를 기준으로 분리
            String[] cmd = command.split(" ");
//            System.out.println(Arrays.toString(cmd));
            switch (cmd[0]) {
                case "UPDATE" :
                    // 2 가지 종류가 있음
                    if(cmd.length == 4) updateByLoc(cmd);
                    else updateByValue(cmd);
                    break;
                case "MERGE" :
                    merge(cmd);
                    break;
                case "UNMERGE" :
                    unmerge(cmd);
                    break;
                case "PRINT" :
                    print(cmd);
                    break;
            }
//            for(int x=1; x<5; x++) {
//                for(int y=1; y<5; y++) {
//                    System.out.print((arr[x][y] == null ? arr[x][y].str : arr[x][y].find().str)+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }
    }

    // UPDATE r c value
    void updateByLoc(String[] cmd) {
        int r = Integer.parseInt(cmd[1]);
        int c = Integer.parseInt(cmd[2]);
        arr[r][c].find().str = cmd[3];
    }
    // UPDATE value1 value2
    void updateByValue(String[] cmd) {
        String from = cmd[1];
        String to = cmd[2];

        for(int r=1; r<51; r++) {
            for(int c=1; c<51; c++) {
                if(arr[r][c].find().str.equals(from)) arr[r][c].find().str = to;
            }
        }
    }
    // MERGE r1 c1 r2 c2
    // 그룹으로 묶는다? -> Union-Find
    void merge(String[] cmd) {
        int r1 = Integer.parseInt(cmd[1]);
        int c1 = Integer.parseInt(cmd[2]);
        int r2 = Integer.parseInt(cmd[3]);
        int c2 = Integer.parseInt(cmd[4]);

        // 1. 두 그룹이 같은 그룹인지 확인
        Cell cell1 = arr[r1][c1].find();
        Cell cell2 = arr[r2][c2].find();
        // 1-1. 같다면 묶을 필요 없음
        if(cell1 == cell2) return;

        // 2. 묶을 수 있음
        //      이때 우선순위가 정해져 있음
        //      둘 다 값이 있다면 cell1 로
        //      둘 중 하나만 있다면 있는 쪽으로

        // r2 c2 에만 값이 있을 때
        if(cell1.str.isBlank()&&!cell2.str.isBlank()) {
            cell1.merge = cell2;
            for(int r=1; r<51; r++) {
                for(int c=1; c<51; c++) {
                    if(arr[r][c].find() == cell1) arr[r][c].merge = cell2;
                }
            }
        }
        else {
            cell2.merge = cell1;
            for(int r=1; r<51; r++) {
                for(int c=1; c<51; c++) {
                    if(arr[r][c].find() == cell2) arr[r][c].merge = cell1;
                }
            }
        }

    }
    // UNMERGE r c
    void unmerge(String[] cmd) {
        int r = Integer.parseInt(cmd[1]);
        int c = Integer.parseInt(cmd[2]);

        Cell target = arr[r][c].find(); // 그룹 대표
        String origin = target.str;

        // 1. 해당 그룹에 속한 모든 셀을 찾아서 초기화
        for (int x = 1; x < 51; x++) {
            for (int y = 1; y < 51; y++) {
                if (arr[x][y].find() == target) {
                    arr[x][y].merge = arr[x][y];
                    arr[x][y].str = "";
                }
            }
        }

        // 2. 선택된 셀만 값 복원
        arr[r][c].str = origin;
    }
    // PRINT r c
    void print(String[] cmd) {
        int r = Integer.parseInt(cmd[1]);
        int c = Integer.parseInt(cmd[2]);

        result.offer(!arr[r][c].find().str.isBlank() ? arr[r][c].find().str : "EMPTY");
    }

    // 각 셀의 로그를 기록할 클래스
    class Cell {
        String str = "";    // 초기 값은 빈 문자
        Cell merge = null;   // MERGE 되는 경우

        public Cell(String str, Cell merge) {
            this.str = str;
            this.merge = merge;
        }

        // 대표 셀을 찾는 코드
        public Cell find() {
            // 현재 Cell 이 대표 Cell이라면 바로 반환
            if(this.merge == this) return this;
            // 타고 들어가서 대표 Cell 을 찾음 
            return this.merge = this.merge.find();
        }
    }
}
