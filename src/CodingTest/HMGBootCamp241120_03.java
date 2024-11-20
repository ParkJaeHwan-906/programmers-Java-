package CodingTest;

import java.util.*;
import java.io.*;

public class HMGBootCamp241120_03 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] card = new int[n];
        for(int i=0; i<n; i++){
            card[i] = i;
        }
        int p = Integer.parseInt(br.readLine());

        HMGBootCamp241120_03 problem = new HMGBootCamp241120_03();
        for(int i=0; i<p; i++){
            int mix = Integer.parseInt(br.readLine());
            card = problem.solution(card, mix);
        }

        for(int i : card){
            System.out.printf("%d ", i+1);
        }
    }


    public int[] solution(int[] card, int mix){
        int length = card.length;
        int left = 0;
        int right = length-1;

        List<Integer> list = new ArrayList<>();

        while(true){
            int[] origin = new int[mix*2];
            int i1 = 0;
            int[] slice = new int[length - origin.length];
            int i2 = 0;
            for(int i = left; i<=right; i++){
                if(i < left + mix) {
                    origin[i1++] = card[i];
                } else if(i > right - mix) {
                    origin[i1++] = card[i];
                } else {
                    slice[i2++] = card[i];
                }
            }

            for(int i = origin.length-1; i > -1; i--){
                list.add(origin[i]);
            }

            left = slice[0];
            right = slice[slice.length-1];
            length = length - (mix * 2);

            if(slice.length <= 2 * mix){
                for(int i=slice.length-1; i>-1; i--){
                    list.add(slice[i]);
                }
                break;
            }
        }

        int[] answer = new int[list.size()];
        int idx = 0;
        for(int i=list.size()-1; i > -1; i--){
            answer[idx++] = list.get(i);
        }

        return answer;
    }
}
