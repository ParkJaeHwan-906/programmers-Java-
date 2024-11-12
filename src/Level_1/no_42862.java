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
        Arrays.fill(student,1);
        makeStudent(lost, reserve);

        for(int i=0; i<n; i++){
            if(student[i] != 0) continue;

            if(i==0){
                if(student[i+1] == 2){
                    student[i]++;
                    student[i+1]--;
                }
            }else if(i == n-1){
                if(student[i-1] == 2){
                    student[i]++;
                    student[i-1]--;
                }
            }else{
                if(student[i-1] == 2){
                    student[i]++;
                    student[i-1]--;
                } else if(student[i+1] == 2){
                    student[i]++;
                    student[i+1]--;
                }
            }
        }
        return countStudent();
    }

    public void makeStudent(int[] lost, int[] reserve) {
        for(int i=0; i< lost.length; i++){
            student[lost[i]-1] = 0;
        }
        for(int i=0; i< reserve.length; i++){
            student[reserve[i]-1] = 2
            ;
        }
    }

    public int countStudent(){
        int count = 0;
        for(int i : student){
            if(i !=0) count++;
        }
        return count;
    }
}
