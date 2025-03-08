package CodeBattle;

import java.util.*;
import java.io.*;

// CodeBattle
// No. 12 [Pro] 섬지키기
public class no_12 {
    private final static int CMD_INIT					= 1;
    private final static int CMD_NUMBER_OF_CANDIDATE	= 2;
    private final static int CMD_MAX_AREA				= 3;

    private final static UserSolution usersolution = new UserSolution();

    private static int[][] mMap = new int[20][20];
    private static int[] mStructure = new int[5];

    private static boolean run(BufferedReader br) throws Exception
    {
        StringTokenizer st;

        int numQuery;
        int N, M, mSeaLevel;
        int userAns, ans;

        boolean isCorrect = false;

        numQuery = Integer.parseInt(br.readLine());

        for (int q = 0; q < numQuery; ++q)
        {
            st = new StringTokenizer(br.readLine(), " ");

            int cmd;
            cmd = Integer.parseInt(st.nextToken());

            switch (cmd)
            {
                case CMD_INIT:
                    N = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < N; i++)
                        for (int j = 0; j < N; j++)
                            mMap[i][j] = Integer.parseInt(st.nextToken());
                    usersolution.init(N, mMap);
                    isCorrect = true;
                    break;
                case CMD_NUMBER_OF_CANDIDATE:
                    M = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < M; i++)
                        mStructure[i] = Integer.parseInt(st.nextToken());
                    userAns = usersolution.numberOfCandidate(M, mStructure);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
                    break;
                case CMD_MAX_AREA:
                    M = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < M; i++)
                        mStructure[i] = Integer.parseInt(st.nextToken());
                    mSeaLevel = Integer.parseInt(st.nextToken());
                    userAns = usersolution.maxArea(M, mStructure, mSeaLevel);
                    ans = Integer.parseInt(st.nextToken());
                    if (userAns != ans)
                    {
                        isCorrect = false;
                    }
                    break;
                default:
                    isCorrect = false;
                    break;
            }
        }
        return isCorrect;
    }

    public static void main(String[] args) throws Exception
    {
        int TC, MARK;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase)
        {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}

class UserSolution
{
    public void init(int N, int mMap[][])
    {
    }

    public int numberOfCandidate(int M, int mStructure[])
    {
        return 0;
    }

    public int maxArea(int M, int mStructure[], int mSeaLevel)
    {
        return 0;
    }
}

/*
섬은 N x N 이고, 바다로 둘러쌓여있다.
-> (N+1) * (N+1)
각 칸의 숫자는 지역의 고도를 나타낸다.
해수면이 mSeaLevel 만큼 상승하면 고도가 mSeaLevel-1 이하인 지역을 침투가 가능하다
=> 단 바닷물은 상하좌우 4방향으로만 육지에 침투가 가능하다.

구조물이 섬에 설치될 수 있다. 구조물이 설치된 M 개 지역의 고도는 해당 구조물 부분 높이만큼 올라간다
⚠️ 고도가 증가한 이후에는 구조물을 설치한 위치의 고도가 모두 일치해야한다.
구조물은 시계방향으로 회전시킬 수 있다


N : 섬의 한 변의 길이 (5 ≤ N ≤ 20)
mMap : 섬의 각 지역의 고도 (1 ≤ mMap[][] ≤ 5)
구조물의 길이 1<= M <= 5

📌 int numberOfCandidate(int M, int mStructure[])
구조물 mStructure를 1개 설치했을 때, 나타날 수 있는 경우의 수를 반환한다.
설치 지역이 모두 동일하면, 같은 경우로 취급한다.
설치 지역이 1개라도 다르다면, 다른 경우로 취급한다.

구조물 mStructure의 크기는 1 x M이며, 1 x 1 크기의 정사각형 모양인 부분들로 이루어져 있다.
(가장 쉽게 생각하는 방법)
1. 구조물을 가로로 모든 방향 시도 -> 돌려서 모든 방향 시도 -> 돌려서 모든 방향 시도 -> 돌려서 모든 방향 시도
=> 시간 초과 -> 이 함수는 15만번 호출되고, 모든 경우는 4방향 N^2

⚠️ 반복이 있을 것이다.
M = 1 -> 나올 수 있는 경우 : 5
M = 2 -> 나올 수 있는 경우 : 25
M = 3 -> 나올 수 있는 경우 : 125
M = 4 -> 나올 수 있는 경우 : 625
M = 5 -> 나올 수 있는 경우 : 3125
=> ✅ 전처리 방법 사용

 */