package Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Softeer Lv.2
// GBC
// https://www.softeer.ai/practice/6270
public class no_6270 {
    static int[] arr = new int[101];
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int next = 1;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int bound = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            for(int j = next; j < next+bound; j++){
                arr[j] = speed;
            }
            next += bound;
        }

        next = 1;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int bound = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            for(int j = next; j < next + bound; j++){
                answer = Math.max(answer, speed - arr[j]);
            }

            next += bound;
        }

        System.out.println(answer);
    }


}
