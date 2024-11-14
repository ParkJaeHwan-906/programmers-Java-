package Level_1;

import java.io.*;
import java.util.Arrays;

// 프로그래머스 Lv.1
// 체육복
// https://school.programmers.co.kr/learn/courses/30/lessons/42862
public class no_42862 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] lostArr = br.readLine().split(" ");
        int[] lost = new int[lostArr.length];
        String[] reserveArr = br.readLine().split(" ");
        int[] reserve = new int[reserveArr.length];
        for(int i=0; i< lostArr.length; i++){
            lost[i] = Integer.parseInt(lostArr[i]);
        }
        for(int i=0; i< reserveArr.length; i++){
            reserve[i] = Integer.parseInt(reserveArr[i]);
        }

        no_42862 problem = new no_42862();
        System.out.println(problem.solution(n, lost, reserve));
    }

    int[] student;
    public int solution(int n, int[] lost, int[] reserve){
        student = new int[n];
        makeStudent(lost, reserve);

        for(int i=0; i<n; i++){
            if(student[i] == 1){    // 여벌이 있는 경우
                if(i-1 >= 0 && student[i-1] == -1){ // 범위 내이고, 이전 학생이 잃어버린 경우
                    student[i]--;
                    student[i-1]++;
                }else if(i+1 < n && student[i+1] == -1){
                    student[i]--;
                    student[i+1]++;
                }
            }
        }

        return countStudent();
    }

    // 학생 배열을 생성한다.
    // 여벌의 체육복을 가져온 학생도 도난당할 수 있음
    public void makeStudent(int[] lost, int[] reserve) {
        for(int i : lost){
            student[i-1]--;
        }
        for(int i : reserve){
            student[i-1]++;
        }
    }

    public int countStudent(){
        int count = 0;
        for(int i : student){
            if(i >= 0) count++;
        }
        return count;
    }
}
