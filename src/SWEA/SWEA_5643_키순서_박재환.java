package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_5643_키순서_박재환 {
	static BufferedReader br;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine().trim());
        for (int testCase = 1; testCase < TC+1; testCase++) {
			sb.append('#').append(testCase).append(' ');
			init();
			sb.append('\n');
		}
        br.close();
        System.out.println(sb);
    }

    static StringTokenizer st;
    static int nodes, edges;    // 노드의 개수, 간선의 개수
    static List<Integer>[] graph;  // 학생 간의 연결 ( 정방항 )
    static List<Integer>[] reverseGraph;  // 학생 간의 연결 ( 역방항 )
    static int correctStudent;              // 순서가 정확한 학생
    static void init() throws IOException {
        nodes = Integer.parseInt(br.readLine().trim());
        edges = Integer.parseInt(br.readLine().trim());

        graph = new ArrayList[nodes+1];
        reverseGraph = new ArrayList[nodes+1];
        for(int student=0; student<=nodes; student++) {
            graph[student] = new ArrayList<>();
            reverseGraph[student] = new ArrayList<>();
        }

        for(int edge=0; edge<edges; edge++) {
            st = new StringTokenizer(br.readLine().trim());

            int tall = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());

            graph[tall].add(small);
            reverseGraph[small].add(tall);
        }

        correctStudent = 0;
        compareHeight();

        sb.append(correctStudent);
    }

    static boolean[] checkedStudents;
    static void compareHeight() {
        for(int student=1; student<=nodes; student++) {
           checkedStudents = new boolean[nodes+1];
           checkedStudents[student] = true;

            searchCompare(student, false);
            searchCompare(student, true);

            // 모든 연결 관계를 확인한다.
            // 모든 학생이 체크되어있다면, 이는 선후 관계를 확실하게 알 수 있다.
            if(isCorrect()) correctStudent++;
        }
    }

    static boolean isCorrect() {
        for(int student=1; student <= nodes; student++) {
            if(!checkedStudents[student]) return false;
        }
        return true;
    }

    /*
        현재 학생과 연결되어 있는 관계를 모두 찾는다.
        이때 정방향 역방향을 flag 값으로 두어 비교 대상을 다르게 구분한다.
     */
    static void searchCompare(int startStudent, boolean isReverse) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(startStudent);

        while(!q.isEmpty()) {
            int nowStudent = q.poll();

            for(int connStudent : isReverse ? reverseGraph[nowStudent] : graph[nowStudent]) {
                // 이미 확인 된 학생이라면 넘어간다.
                if(checkedStudents[connStudent]) continue;

                // 확인하지 않은 학생이라면
                checkedStudents[connStudent] = true;
                q.offer(connStudent);
            }
        }
    }
}

/*
 * 학생들의 키 순서를 비교한다. 
 * 일부 학생들의 대소관계만 주어진다.  
 */