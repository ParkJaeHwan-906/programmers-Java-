package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Softeer Lv.2
// [21년 재직자 대회 예선] 전광판
// https://www.softeer.ai/practice/6268
public class no_6268 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        no_6268 problem = new no_6268();
        for(int i=0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            StringBuilder sb1 = new StringBuilder(st.nextToken());
            StringBuilder sb2 = new StringBuilder(st.nextToken());
            String a = sb1.reverse().toString();
            String b = sb2.reverse().toString();

            System.out.println(problem.solution(a, b));
        }
    }

    // 표현할 수 있는 수는 정해져있음
    // 0 ~ 9 다 하드코딩해버려?
    // 끝에서부터 탐색해야함
    public int solution(String a, String b){
        int result = 0;
        if(a.length() > b.length()){
            for(int i=0; i<b.length(); i++){
                int[][] e1 = number[a.charAt(i)-'0'];
                int[][] e2 = number[b.charAt(i)-'0'];

                for(int x = 0; x< e1.length; x++){
                    for(int y = 0; y < e1[x].length; y++){
                        if(e1[x][y] != e2[x][y]) result++;
                    }
                }
            }

            for(int i=b.length(); i<a.length(); i++){
                int[][] e1 = number[a.charAt(i) - '0'];

                for(int x = 0; x< e1.length; x++){
                    for(int y = 0; y < e1[x].length; y++){
                        if(e1[x][y] == 1) result++;
                    }
                }
            }
        } else if(a.length() < b.length()){
            for(int i=0; i<a.length(); i++){
//                System.out.println("비교 : " + (a.charAt(i)-'0') + "," + (b.charAt(i)-'0'));
                int[][] e1 = number[a.charAt(i)-'0'];
                int[][] e2 = number[b.charAt(i)-'0'];

                for(int x = 0; x< e1.length; x++){
                    for(int y = 0; y < e1[x].length; y++){
                        if(e1[x][y] != e2[x][y]) result++;
                    }
                }
//                System.out.println("중간 점검 : " + result);
            }

            for(int i=a.length(); i<b.length(); i++){
                int[][] e1 = number[b.charAt(i) - '0'];

                for(int x = 0; x< e1.length; x++){
                    for(int y = 0; y < e1[x].length; y++){
                        if(e1[x][y] == 1) result++;
                    }
                }
            }
        } else {
            for(int i=0; i<a.length(); i++){
                int[][] e1 = number[a.charAt(i)-'0'];
                int[][] e2 = number[b.charAt(i)-'0'];

                for(int x = 0; x< e1.length; x++){
                    for(int y = 0; y < e1[x].length; y++){
                        if(e1[x][y] != e2[x][y]) result++;
                    }
                }
            }
        }

        return result;
    }


    int[][][] number = new int[][][]
            {
                    // 0
                    {
                            {0,1,0},
                            {1,0,1},
                            {0,0,0},
                            {1,0,1},
                            {0,1,0}
                    },
                    // 1
                    {
                            {0,0,0},
                            {0,0,1},
                            {0,0,0},
                            {0,0,1},
                            {0,0,0}
                    },
                    // 2
                    {
                            {0,1,0},
                            {0,0,1},
                            {0,1,0},
                            {1,0,0},
                            {0,1,0}
                    },
                    // 3
                    {
                            {0,1,0},
                            {0,0,1},
                            {0,1,0},
                            {0,0,1},
                            {0,1,0}
                    },
                    // 4
                    {
                            {0,0,0},
                            {1,0,1},
                            {0,1,0},
                            {0,0,1},
                            {0,0,0}
                    },
                    // 5
                    {
                            {0,1,0},
                            {1,0,0},
                            {0,1,0},
                            {0,0,1},
                            {0,1,0}
                    },
                    // 6
                    {
                            {0,1,0},
                            {1,0,0},
                            {0,1,0},
                            {1,0,1},
                            {0,1,0}
                    },
                    // 7
                    {
                            {0,1,0},
                            {1,0,1},
                            {0,0,0},
                            {0,0,1},
                            {0,0,0}
                    },
                    // 0
                    {
                            {0,1,0},
                            {1,0,1},
                            {0,1,0},
                            {1,0,1},
                            {0,1,0}
                    },
                    // 0
                    {
                            {0,1,0},
                            {1,0,1},
                            {0,1,0},
                            {0,0,1},
                            {0,1,0}
                    }
            };

}


/*
전광판 표현
0 0 0
0 0 0
0 0 0
0 0 0
0 0 0


ex) 1
0 0 0
0 0 1
0 0 0
0 0 1
0 0 0

 */