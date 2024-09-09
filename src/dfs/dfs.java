package dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class dfs {
        boolean[] visited;

        int answer = 0;
        public void dfs_run(int[] topping, List<Integer> list1, List<Integer> list2, int depth){
            if(depth > topping.length) return;
            Set<Integer> set1 = new TreeSet<>(list1);
            Set<Integer> set2 = new TreeSet<>(list2);
            System.out.println(set1);
            System.out.println(set2);
            System.out.println("---------------");
            if(set1.size() == set2.size()){
                for(Integer i : set1){
                    set1.remove(i);
                    set2.remove(i);
                }
                if(set1.isEmpty() && set2.isEmpty()) answer++;
            }
//            System.out.println(sum1 + " : " + sum2);
            for(int i=0; i<topping.length; i++){
                if(!visited[i]){
                    visited[i] = true;
                    list1.remove(i);
                    list1.add(topping[i]);
                    dfs_run(topping, list1, list2, depth+1);
                    visited[i] = false;
                }
            }
        }

        public int solution(int[] topping) {
            visited = new boolean[topping.length];

            List<Integer> list1 = new ArrayList<>();
            for(int i : topping){
                list1.add(i);
            }
            List<Integer> list2 = new ArrayList<>();
            dfs_run(topping, list1, list2, 0);

            return answer;
        }

        public static void main(String[] args){
            int[] topping = {1,2,1,3,1,4,1,2};
            dfs s = new dfs();
            System.out.println(s.solution(topping));
        }

}
