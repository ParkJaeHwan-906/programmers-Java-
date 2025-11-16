package CodingTest;

import java.util.*;
import java.io.*;

public class AutoEver251117_2 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static StringTokenizer st;
    static void init() throws IOException {
        int pathCnt = Integer.parseInt(br.readLine().trim());
        int[][] paths = new int[pathCnt][2];
        for(int i=0; i<pathCnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            paths[i][0] = Integer.parseInt(st.nextToken());
            paths[i][1] = Integer.parseInt(st.nextToken());
        }
        int recordCnt = Integer.parseInt(br.readLine().trim());
        int[][] records = new int[recordCnt][2];
        for(int i=0; i<recordCnt; i++) {
            st = new StringTokenizer(br.readLine().trim());
            records[i][0] = Integer.parseInt(st.nextToken());
            records[i][1] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(paths, records));
    }

    static long solution(int[][] paths, int[][] records) {
        int n = paths.length+1;
        make(n);
        for(int[] path : paths) {
            int from = path[0];
            int to = path[1];

            if(from == 1) continue;
            union(from, to);
        }

        long max = Long.MIN_VALUE;
        long[] used = new long[n+1];
        for(int[] record : records) {
            int root = find(record[0]);
            used[root] += record[1];
            max = Math.max(max, used[root]);
        }
        return max;
    }

    static int[] mountain;
    static void make(int n) {
        mountain = new int[n+1];
        for(int i=0; i<n+1; i++) mountain[i]=i;
    }

    static int find(int target) {
        if(mountain[target] == target) return target;

        return mountain[target] = find(mountain[target]);
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return;

        mountain[b] = rootA;
    }
}
