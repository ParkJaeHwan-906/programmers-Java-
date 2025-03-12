package Level_3;

import java.util.*;

public class PMMERS_388354_홀짝트리_박재환 {
    public static void main(String[] args) {
        int[] nodes = {11, 9, 3, 2, 4, 6};
        int[][] edges = {{9, 11}, {2, 3}, {6, 3}, {3, 4}};

        System.out.println(Arrays.toString(new Solution().solution(nodes, edges)));
    }

    static class Solution {
        Map<Integer, List<Integer>> graph;    // 노드들의 연결 관계를 기록
        Set<Integer> connNodes;                // 연결되어 있는 노드의 집합을 기록
        int normalTree, reverseTree;            // 홀짝 트리, 역홀짝 트리 의 개수

        public int[] solution(int[] nodes, int[][] edges) {
            graph = new HashMap<>();

            // Node 정보 입력
            for (int node : nodes) {
                graph.put(node, new ArrayList<Integer>());
            }

            // 연결 정보 입력
            for (int[] edge : edges) {
                int nodeA = edge[0];
                int nodeB = edge[1];
                graph.get(nodeA).add(nodeB);
                graph.get(nodeB).add(nodeA);
            }

            //------------------------------------------

            normalTree = reverseTree = 0;

            // 노드를 순차적으로 탐색한다.
            for (int node : nodes) {
                if (graph.containsKey(node)) {    // 현재 노드를 탐색할 수 있다면
                    connNodes = new HashSet<>();

                    seperateGraph(node, -1);

                    // 그래프 구분 확인
//	        		System.out.println(connNodes);

                    // 루트 노드를 변경해가며 트리를 구분한다.
                    rotateRootNode();

                    // 탐색이 완료된 노드들을 재탐색하지 않게 제거한다.
                    deleteNodes();
                }
            }

            return new int[]{normalTree, reverseTree};
        }

        /*
         * 1. 루트 노드를 결정한다.
         * 2. 루트노드를 기준으로 내려가며 자식의 개수를 파악한다.
         *
         * 루트노드를 제외한 모든 노드들은 진출 차수가 1씩 작아진다.
         */
        void rotateRootNode() {
            int evenOdd = 0, reverseEvenOdd = 0;        // 홀짝, 역홀짝 노드

            for (int node : connNodes) {
                if ((node % 2 == 0 && graph.get(node).size() % 2 == 0) ||
                        (node % 2 == 1 && graph.get(node).size() % 2 == 1)) {    // 홀짝 노드인 경우
                    evenOdd++;
                } else if ((node % 2 == 0 && graph.get(node).size() % 2 == 1) ||
                        (node % 2 == 1 && graph.get(node).size() % 2 == 0)) {    // 역홀짝 노드인 경우
                    reverseEvenOdd++;
                }
            }

            // 역홀짝 혹은 홀짝 트리가 될 수 있는 경우
            if (evenOdd == 1) {
                normalTree++;
            } else if (reverseEvenOdd == 1) {
                reverseTree++;
            }
        }

//	    void searchChildNodes(int curNode, int parentNode) {
//	    	// 탐색 수 줄이기
//	    	// 홀수 노드 와 역홀짝 노드가 섞여있으면 홀짝 트리 또는 역홀짝 트리가 될 수 없다.
//	    	if(evenOdd * reversEvenOdd != 0) return;
//
//	    	// 1. 현재 노드가 짝수인지 홀수인지 구분한다.
//	    	boolean isEvenNode = curNode%2 == 0;
//
//	    	int childNodes = 0;
//	    	// 2. 현재 노드의 자식 개수를 확인한다.
//	    	for(int node : graph.get(curNode)) {
//	    		// 사이클 방지
//	    		if(node == parentNode) continue;
//
//	    		childNodes++;
//
//	    		searchChildNodes(node, curNode);
//	    	}
//
//	    	// 노드를 구분한다.
//
//	    	// 1. 짝수 노드 : 짝수 번호를 갖고, 짝수개의 자식을 갖는다.
//	    	if(isEvenNode && childNodes % 2 == 0) evenOdd++;
//	    	// 2. 홀수 노드 : 홀수 번호를 갖고, 홀수개의 자식을 갖는다.
//	    	else if(!isEvenNode && childNodes % 2 == 1) evenOdd++;
//	    	// 3. 역짝수 노드 : 짝수 번호를 갖고, 홀수개의 자식을 갖는다.
//	    	else if(isEvenNode && childNodes % 2 == 1) reversEvenOdd++;
//	    	// 4. 역홀수 노드 : 홀수 번호를 갖고, 짝수개의 자식을 갖는다.
//	    	else if(!isEvenNode && childNodes % 2 == 0) reversEvenOdd++;
//	    }

        /*
         * 탐색을 완료한 노드들을 제거한다.
         */
        void deleteNodes() {
            for (int node : connNodes) {
                graph.remove(node);
            }
        }

        /*
         * 하나의 그래프를 구분한다.
         * 연결되어 있는 모든 노드들을 구분하여 Set 에 저장한다.
         *
         * 현재 노드와 연결되어 있는 노드가 부모노드와 같다면 해당 간선은 탐색하지 않는다.
         *
         * [현재 노드, 부모 노드]
         */
        void seperateGraph(int curNode, int parentNode) {
            connNodes.add(curNode);

            // 현재 노드와 연결되어 있는 모든 노드를 탐색한다.
            for (int node : graph.get(curNode)) {
                // 사이클 방지
                if (node == parentNode) continue;

                seperateGraph(node, curNode);
            }
        }
    }
}
