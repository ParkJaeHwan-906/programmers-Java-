package Level_3;

import java.util.*;
import java.util.stream.Collectors;

public class no_42579 {
    class Music implements Comparable<Music>{
        private int idx;
        private int play;

        public Music(int idx, int play){
            this.idx = idx;
            this.play = play;
        }

        public int getIdx(){
            return this.idx;
        }

        public int getPlay(){
            return this.play;
        }

        @Override
        public int compareTo(Music other){  // play 횟수 기준으로 오름차순
            return other.play - this.play;
        }
    }

    public int[] solution(String[] genres, int[] plays){

        Map<String, Integer> map = new HashMap<>();
        Map<String, List<Music>> map2 = new HashMap<>();
//        PriorityQueue<Music> pq = new PriorityQueue<>();

        // 1. 장르별로 재생 횟수를 센다.
        for(int i = 0; i < genres.length; i++){
            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
            // 2. 해당 장르의 재생순으로 정렬해둔다.
            map2.putIfAbsent(genres[i], new ArrayList<>()); // 해당 키 값이 없다면 list 추가
            map2.get(genres[i]).add(new Music(i, plays[i]));
        }

        // 재생 수 많은 카테고리 뽑기
        List<String> rank = map.entrySet().stream()
                .sorted((e1, e2) ->
                    e2.getValue().compareTo(e1.getValue())
                ).map(Map.Entry::getKey)
                .collect(Collectors.toList());


        List<Integer> answer = new ArrayList<>();
        for(String s : rank){
            List<Music> music = map2.get(s);
            Collections.sort(music);
            answer.add(music.get(0).getIdx());
            // 장르에 속한 곡이 하나라면 1개만 추가
            if(music.size() > 1){
                answer.add(music.get(1).getIdx());
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args){
        String[] genres = new String[] {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = new int[] {500, 600, 150, 800, 2500};

        no_42579 problem = new no_42579();
        int[] answer = problem.solution(genres, plays);

        for(int i : answer){
            System.out.print(i+" ");
        }
    }
}
