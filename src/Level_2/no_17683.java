package Level_2;

import java.util.ArrayList;
import java.util.PriorityQueue;

// 프로그래머스 Lv.2
// [3차] 방금그곡
// https://school.programmers.co.kr/learn/courses/30/lessons/17683
class Music implements Comparable<Music>{
    int time;
    String name;
    int seq;

    public Music(int time, String name, int seq){
        this.time = time;
        this.name = name;
        this.seq = seq;
    }

    @Override
    public int compareTo(Music other){
        if(this.time < other.time) return 1;
        if(this.time == other.time){
            if(this.seq < other.seq) return -1;
            return 1;
        }
        return -1;
    }
}

public class no_17683 {
    public String solution(String m, String[] musicinfos){
        // 기억하고 있는 음악 코드 (반복이 있을 수 있음)
        ArrayList<String> m_list = mk_code_list(m);
        // System.out.println(m_list);

        // 기억하고 있는 음악 코드와 일치하는 음악의 정보를 저장
        // 일치하는 음악이 2개 이상인 경우, 음악의 재생시간이 긴 것을 우선으로 잡음
        PriorityQueue<Music> pq = new PriorityQueue<>();
        int seq = 0;
        // 음악 리스트를 순회
        for(String s : musicinfos){
            String[] arr = s.split(",");
            // 음악이 재생된 시간을 구함 (분 단위)
            int time = running_time(arr[0], arr[1]);
            // 음악 제목
            String name = arr[2];
            // 음악 코드
            String code = arr[3];

            // 음악의 코드를 list 형식으로 만듬
            ArrayList<String> code_list =  mk_code_list(code);
            // 음악 재생 시간이 음악의 코드 길이보다 긴 경우 반복 됨
            int sub = time - code_list.size();
            if(sub >= 0){
                for(int i = 0; i < sub; i++){
                    code_list.add(code_list.get(i));
                }
            }else{
                for(int i = sub; i < 0; i++){
                    code_list.remove(code_list.size()-1);
                }
            }


            // System.out.println(code_list);

            // 음악의 코드와 기억하고 있는 코드가 일치하는지 판별
            if(check_code(code_list, m_list)){
                // System.out.println("일치");
                // 일치한다면 우선순위 큐에 삽입
                pq.add(new Music(time, name, seq));
                seq++;
            }
        }
        // 우선 순위 큐가 비어있으면 (None) 반환
        // 비어있지 않으면 첫 번째 원소 반환
        return pq.isEmpty() ? "(None)" : pq.poll().name;
    }

    // 주어진 음악의 코드를 리스트 형식으로 변환
    // # 이 붙은 코드를 구별
    public ArrayList<String> mk_code_list(String code){
        // 리스트 형식으로 반환
        ArrayList<String> code_list = new ArrayList<>();
        // 각 코드를 저장할 문자형 변수
        String s1 = "";
        // 코드를 한자리씩 확인하며 순회
        for(char c : code.toCharArray()){
            // s1 이 비어있지 않고, 현재 문자가 # 이 아닌경우
            if(c != '#' && !s1.isEmpty()){
                // 리스트에 s1 을 추가
                code_list.add(s1);
                // s1을 현재 문자로 초기화
                s1 = c+"";
            }else{
                s1 += c;
            }
        }

        // 반복문을 종료 이후 마지막 s1을 리스트에 삽입
        if(!s1.isEmpty()) code_list.add(s1);
        return code_list;
    }

    // 기억하고 있는 코드와 음악의 코드가 일치하는지 판별
    public boolean check_code(ArrayList<String> list, ArrayList<String> list2){
        // 음악의 코드를 기억하고 있는 코드와 비교하기 위해 list.size() - list2.size() 만큼 반복
        for(int i = 0; i <= (list.size() - list2.size()); i++){
            // 일치하는지 확인하기 위한 boolean
            boolean b = true;
            // list의 현 위치부터 list2의 크기만큼 일치하는지 확인
            for(int j = 0; j < list2.size(); j++){
                // 하나라도 다르다면 바로 반복문을 종료, b를 false 로 반환
                if(!list2.get(j).equals(list.get(i+j))){
                    b = false;
                    break;
                }
            }
            // 만약 위의 반복문에서 모두 일치한다면 true 를 바로 반환
            if(b) return true;
        }
        // 반목문을 종료해도 일치하는 것이 없다면 false 반환
        return false;
    }

    // 음악의 재생시간을 구하는 함수
    public int running_time(String start_time, String end_time){
        // 시작 시간과 종료 시간은 ":" 를 기준으로 나눔
        String[] st = start_time.split(":");
        String[] en = end_time.split(":");

        // 시작 시간의 시간, 분 / 종료 시간의 시간, 분 을 정수로 변환
        int st_h = Integer.parseInt(st[0]);
        int st_m = Integer.parseInt(st[1]);
        int en_h = Integer.parseInt(en[0]);
        int en_m = Integer.parseInt(en[1]);

        // 종료 분 과 시작 분을 빼줌
        int m = en_m - st_m;
        // 음수 일 경우 종료 시간 -1 , 분 + 60
        if(m < 0){
            m += 60;
            en_h--;
        }
        int h = en_h - st_h;
        return h*60 + m;
    }

    public static void main(String[] args){
        no_17683 problem = new no_17683();
//        String m = "ABCDEFG";
        String m = "A";
//        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        String[] musicinfos = {"12:00,12:01,Sing,A", "12:00,12:01,Song,A"};
        String result = problem.solution(m,musicinfos);
        System.out.println(result);
    }
}
