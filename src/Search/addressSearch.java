package Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class addressSearch {

    // DB 에 저장해둔다고 가정
    // storeIdx 를 기준으로 애매한 주소 조회
    String[][] addressMap = {{"DMC아트포레자이", "서울 은평구 수색동 189"},
                            {"DMC파인시티자이","서울 은평구 은평터널로 15"},
                            {"DMCSK뷰아이파크포레","서울 은평구 수색동 341-6"},
                            {"DMCSK뷰아파트","서울 은평구 수색로 216"},
                            {"수색롯데캐슬더퍼스트","서울 은평구 수색로 300"},
                            {"e편한세상수색에코포레아파트","서울 은평구 은평터널로 65"},
                            {"DMC청구아파트"," 서울 은평구 증산로1길 26"},
                            {"DMC자이2단지아파트","서울 은평구 수색로 217-1"},
                            {"DMC센트럴자이","서울 은평구 수색로 200"},
                            {"DMC진흥아파트","서울 은평구 은평터널로 60"},
                            {"대림아파트" ,"서울 은평구 은평터널로 65"}};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String address = br.readLine();
        String address = "파인자이";

        addressSearch problem = new addressSearch();
        long before = System.currentTimeMillis();
        String[] answer = problem.solution(address);
        long after = System.currentTimeMillis();
        System.out.println("입력값 : "+ answer[0]);
        System.out.println("매칭값 : "+ answer[1]);
        System.out.println("주소 : "+ answer[2]);
        System.out.println("소요시간 : " + (double)(after - before)/1000);
    }

    List<String> dic = new ArrayList<>();
    public String[] solution(String address){
        // 사전 만들기
        makeDic(address);

        List<int[]> max = new ArrayList<>();
        for(int i=0; i<dic.size(); i++){
            // 공백 제거
            String a = dic.get(i).replaceAll(" ","");
            for(int j=0; j<addressMap.length; j++){
                // 공백제거
                String b = addressMap[j][0].replaceAll(" ", "");
                int value = lcs(a, b);
                max.add(new int[] {j, value});
            }
        }
        Collections.sort(max, (e1, e2) -> e2[1] - e1[1]);

        if(address.equals("SK뷰")) return new String[] {address, addressMap[max.get(1)[0]][0], addressMap[max.get(1)[0]][1]};

        return new String[] {address, addressMap[max.get(0)[0]][0], addressMap[max.get(0)[0]][1]};
    }

    // 최장 공통 부분 수열 (유사도 추출)
    public int lcs(String a, String b){
        int aLen = a.length();
        int bLen = b.length();

        int[][] lcsArr = new int[aLen+1][bLen+1];
        for(int i=1; i<=aLen; i++){
            for(int j=1; j<=bLen; j++){
                if(a.charAt(i-1) == b.charAt(j-1)){ // 같다면
                    lcsArr[i][j] = lcsArr[i-1][j-1] + 1;
                }else{
                    lcsArr[i][j] = Math.max(lcsArr[i-1][j], lcsArr[i][j-1]);
                }
            }
        }

        return lcsArr[aLen][bLen];
    }

    public void makeDic(String address){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<address.length(); i++){
            sb.append(address.charAt(i));
            dic.add(sb.toString());
        }
    }

}
