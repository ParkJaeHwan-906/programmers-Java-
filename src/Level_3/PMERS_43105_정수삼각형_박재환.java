package Level_3;

public class PMERS_43105_정수삼각형_박재환 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] triangle = {{7}, {3,8}, {8,1,0},{2,7,4,4}, {4,5,2,6,5}};
        System.out.println(solution.solution(triangle));
    }

    static class Solution {
        public int solution(int[][] triangle) {
            return getMaxRoute(triangle);
        }

        /*
           최대 값을 가질 수 있는 경로를 구한다.
           바텀업 방식으로 리프노드 + 1 위치부터 루트 노드까지 이동하며 값을 구한다.
        */
        int getMaxRoute(int[][] triangle) {
            int height = triangle.length;

            // 리프노드 + 1 의 위치부터 탐색한다.
            // (i, j) 노드에서 얻을 수 있는 자식 노드의 위치는 (i+1, j) (i+1, j+1)
            for(int h=height-2; h > -1; h--) {
                for(int w=0; w<triangle[h].length; w++){
                    triangle[h][w] += Math.max(triangle[h+1][w], triangle[h+1][w+1]);
                }
            }

            // 바텀업 방식으로 구현했으므로, 루트 노드는 최대값을 갖는다.
            return triangle[0][0];
        }

    }
}
/*
    삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 이어지는 숫자의 합이 가장 큰 경우를 찾으려한다.

    트리의 원리와, 누적합을 이용해 답을 구한다.
    각 노드에서 다음 노드로의 이동하는 경우는 (i,j) -> (i+1, j) (i+1, j+1)
*/