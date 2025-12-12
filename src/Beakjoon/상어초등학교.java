package Beakjoon;

import java.util.*;
import java.io.*;

public class 상어초등학교 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    /**
     * N x N 크기의 교실이 있다.
     * 학생 수는 N^2 이다. / 좌상단이 (1, 1), 우하단이 (N, N)
     *
     * 학생의 순서를 정했다.
     * 각 학생이 좋아하는 학생 4명도 모두 조사했다.
     *
     * 자리를 정하려한다.
     * 각 칸에는 한 명의 학생만 앉을 수 있다.
     *
     * 1. 비어있는 칸 중, 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 정한다.
     * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
     * 3. 2를 만족하는 칸도 여러 개인 경우에는, 행의 번호가 가장 작은 칸으로 자리를 정한다.
     * 4. 3을 만족하는 칸도 여러 개인 경우에는, 열의 번호가 가장 작은 칸으로 자리를 정한다.
     */
    static StringTokenizer st;
    static int n;
    static Queue<Student> studentsQ;
    static int[][] board;
    static int satisfactionScore;
    static void init() throws IOException {
        studentsQ = new ArrayDeque<>();
        satisfactionScore = 0;
        n = Integer.parseInt(br.readLine().trim());
        board = new int[n][n];
        for(int i=0; i<n*n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int no = Integer.parseInt(st.nextToken());
            int[] preferStudents = new int[4];
            for(int s=0; s<4; s++) {
                preferStudents[s] = Integer.parseInt(st.nextToken());
            }
            studentsQ.offer(new Student(no, preferStudents));
        }
//        System.out.println("wait Students : " + studentsQ.size());
        allocateStudents();
//        for(int[] arr : board) System.out.println(Arrays.toString(arr));
        System.out.println(satisfactionScore);
    }

    static class Student {
        int no;     // 학생 번호
        int[] preferStudents;

        public Student(int no, int[] preferStudents) {
            this.no = no;
            this.preferStudents = preferStudents;
        }
    }

    static class Sit{
        int x;
        int y;
        int preferStudentsCnt;      // 선호하는 학생 수
        int emptySitCnt;            // 빈 자리 수

        public Sit(int x, int y, int preferStudentsCnt, int emptySitCnt) {
            this.x = x;
            this.y = y;
            this.preferStudentsCnt = preferStudentsCnt;
            this.emptySitCnt = emptySitCnt;
        }
    }

    static void allocateStudents() {
        Map<Integer, int[]> processedStudents = new HashMap<>();
        while(!studentsQ.isEmpty()) {
            Student curStudent = studentsQ.poll();      // 자리를 배정할 학생
            findBestLocation(curStudent);
            processedStudents.put(curStudent.no, curStudent.preferStudents);
        }

        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                int preferStudentsCnt = 0;
                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;

                    for(int preferStudent : processedStudents.get(board[x][y])) {
                        if(preferStudent == board[nx][ny]) {
                            preferStudentsCnt++;
                            break;
                        }
                    }
                }
                calcSatisfactionScore(preferStudentsCnt);
            }
        }
    }

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static void findBestLocation(Student student) {
        // 친한 친구 많은 순 > 빈 자리 많은 순 > 행 작은 순 > 열 직은 순
        PriorityQueue<Sit> pq = new PriorityQueue<>((a, b) -> {
            if(a.preferStudentsCnt == b.preferStudentsCnt) {
                if(a.emptySitCnt == b.emptySitCnt) {
                    if (a.x == b.x) return Integer.compare(a.y, b.y);
                    return Integer.compare(a.x, b.x);
                }
                return Integer.compare(b.emptySitCnt, a.emptySitCnt);
            }
            return Integer.compare(b.preferStudentsCnt, a.preferStudentsCnt);
        });

        // 모든 자리를 순회하며, 선호하는 친구를 확인
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                if(board[x][y] != 0) continue;

                int preferStudentsCnt = 0, emptySitCnt = 0;
                for(int dir=0; dir<4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                    if(board[nx][ny] == 0) {    // 빈자리인 경우
                        emptySitCnt++;
                        continue;
                    }
                    for(int preferStudent : student.preferStudents) {
                        if(preferStudent == board[nx][ny]) {
                            preferStudentsCnt++;
                            break;
                        }
                    }
                }
                pq.offer(new Sit(x, y, preferStudentsCnt, emptySitCnt));
            }
        }

        Sit bestSitLocation = pq.poll();
        board[bestSitLocation.x][bestSitLocation.y] = student.no;
    }

    static void calcSatisfactionScore(int preferStudentCnt) {
        switch (preferStudentCnt) {
            case 0:
                satisfactionScore += 0;
                break;
            case 1:
                satisfactionScore += 1;
                break;
            case 2:
                satisfactionScore += 10;
                break;
            case 3:
                satisfactionScore += 100;
                break;
            case 4:
                satisfactionScore += 1000;
                break;
        }
    }
}
