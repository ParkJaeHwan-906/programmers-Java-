package Level_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 프로그래머스 Lv.1
// 모의고사
// https://school.programmers.co.kr/learn/courses/30/lessons/42840
public class no_42840 {
    class Student{
        int idx;
        int score;

        public Student(int idx, int score){
            this.idx = idx;
            this.score = score;
        }

        public void addScore(){
            this.score++;
        }
    }
    public int[] solution(int[] answers){
        /*
        1 : 1, 2, 3, 4, 5
        2 : 2, 1, 2, 3, 2, 4, 2, 5
        3 : 3, 3, 1, 1, 2, 2, 4, 4, 5, 5
         */
        int[] st1 = new int[] {1, 2, 3, 4, 5};
        int[] st2 = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] st3 = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        List<Student> list = new ArrayList<>();
        list.add(new Student(1, 0));
        list.add(new Student(2, 0));
        list.add(new Student(3, 0));
        for(int i = 0; i < answers.length; i++){
            if(answers[i] == st1[i%st1.length]){
                list.get(0).addScore();
            }
            if(answers[i] == st2[i%st2.length]){
                list.get(1).addScore();
            }
            if(answers[i] == st3[i%st3.length]){
                list.get(2).addScore();
            }
        }
        Collections.sort(list, (e1, e2) -> {
            return e2.score - e1.score;
        });
        List<Integer> answerList = new ArrayList<>();
        answerList.add(list.get(0).idx);

        for(int i = 1; i < list.size(); i++){
            if(list.get(i-1).score != list.get(i).score) break;
            answerList.add(list.get(i).idx);
        }

        int[] answer = new int[answerList.size()];
        for(int i = 0; i< answer.length; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    public static void main(String[] args){
        int[] answers = new int[] {1,2,3,4,5};
        no_42840 problem = new no_42840();
        int[] result = problem.solution(answers);
        for(int i : result){
            System.out.print(i + " ");
        }
    }
}
