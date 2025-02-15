package Ssafy.Algorithm.Stack;

import java.util.*;

public class Browser {
    final static String baseUrl = "http://www.ssafy.com/";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> browser = new ArrayDeque<>();    // 브라우저를 기록한다.
        ArrayDeque<String> forward = new ArrayDeque<>();    // 앞으로 이동할 페이지를 기록한다
        browser.offer(baseUrl); // 기본 페이지 세팅

        while(true) {
            String[] command = sc.nextLine().split(" ");    // 명령어를 공백을 기준으로 입력 받는다.

            if(command[0].equals("Q")) break;   // 종료 조건

            switch (command[0]) {
                case "V" :
                    System.out.println(command[1]); // 방문한 페이지를 출력한다.
                    browser.offerLast(command[1]);  // 방문한 페이지를 Deque 마지막에 삽입한다.
                    forward.clear();    // 페이지를 방문하면 앞으로 방문할 페이지 기록을 초기화한다/
                    break;
                case "B":
                    if(browser.peekLast().equals(baseUrl)) {    // 더 이동할 페이지가 없을 때
                        System.out.println("Ignored");
                        break;
                    }
                    forward.offerFirst(browser.pollLast()); // 현재 페이지를 forward Deque 로 이동 시킨다.
                    System.out.println(browser.peekLast()); // 현재 페이지를 출력한다.
                    break;
                case "F":
                    if(forward.isEmpty()) {  // 더 이동할 페이지가 없을 때
                        System.out.println("Ignored");
                        break;
                    }
                    browser.offerLast(forward.pollFirst()); // 가장 마지막으로 빠져나온 페이지를 브라우저의 맨 뒤로 이동 시킨다.
                    System.out.println(browser.peekLast());
                    break;
            }
        }

    }
}

/*
기본 홈페이지
http://www.ssafy.com/

V : url 로 방문
B : 뒤로
F : 앞으로

>> 입력
V http://www.google.com
V http://www.naver.com
B
V http://edu.ssafy.com
F
B
F
B
B
B
Q


>> 출력
http://www.google.com
http://www.naver.com
http://www.google.com
http://edu.ssafy.com
Ignored
http://www.google.com
http://edu.ssafy.com
http://www.google.com
http://www.ssafy.com/
Ignored

 */