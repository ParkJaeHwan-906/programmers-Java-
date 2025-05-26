package Programmers.Lv3;

public class PMMS_214289_에어컨 {
    public static void main(String[] args) {
        int temperature = 28;
        int t1 = 18;
        int t2 = 26;
        int a = 10;
        int b = 8;
        int[] onboard = {0,0,1,1,1,1,1};
        System.out.println(new PMMS_214289_에어컨().solution(temperature, t1, t2, a, b, onboard));
    }


    int lowLimit, highLimit;        // 최저 희망 온도, 최고 희망 온도
    int aPrice, bPrice;             // 온도를 상/하 시키는데 소모되는 비용, 온도를 유지하는데 소모되는 비용
    int minPrice;                   // 최소 비용
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        lowLimit = t1; highLimit = t2;
        aPrice = a; bPrice = b;
        minPrice = Integer.MAX_VALUE;

        // 희망온도 구간의 각 온도별로, 유지 비용을 계산한다.
        for(int targetTemp=lowLimit; targetTemp<highLimit+1; targetTemp++) {
            // 매 케이스마다, 현 온도를 외부 기온으로 맞춘다.
            calcPrice(0, 0, 0, Math.abs(temperature-targetTemp), onboard);
        }

        return minPrice;
    }

    /*
        나올 수 있는 케이스,
        1. 희망온도까지 올린 다음, 유지하기
        2. 손님이 아예 타지 않는 경우 희망온도까지 올릴 필요가 없음

        [!! 영하의 온도를 가질 수 있음 !!]
        조합?
        1. 에어컨을 가동한다.
        2. 에어컨을 가동하지 않는다.
     */
    void calcPrice(int guestIdx, int nowTemp, int sum, int targetTemp, int[] onboard) {
        if(guestIdx == onboard.length) {    // 모든 승객을 다 태운 경우
            minPrice = Math.min(sum, minPrice);
            return;
        }
        // 승객을 더 태울 수 있다.

        // 1. 승객을 태워야하는데, 희망온도를 맞추지 못하는 경우
        // 1-1. 이전의 최소 금액을 이미 넘긴 경우
        if((onboard[guestIdx] == 1 && (lowLimit > nowTemp && highLimit < nowTemp)) || sum >= minPrice) return;

        // 2. 에어컨을 켠다.
        if(nowTemp == targetTemp) { // 현재 온도와 같은 경우
            calcPrice(guestIdx+1, nowTemp, sum+bPrice, targetTemp, onboard);
        } else {
            calcPrice(guestIdx+1, nowTemp+1, sum+aPrice, targetTemp, onboard);
        }

        // 3. 에어컨을 끈다.
        calcPrice(guestIdx+1, nowTemp-1, sum, targetTemp, onboard);
    }
}

/*
    온도 : t1 ~ t2 를 유지할 수 있도록한다.
    현재 ( 0 분 ) 실내온도는 실외온도와 같다.

    희망온도는 에어컨의 전원이 켜져 있는 동안 원하는 값으로 변경할 수 있다.
    1분마다 희망온도로 1도씩 상승 / 하강 한다.
    온도가 같다면, 에어컨이 켜져있는 동안 온도가 변하지 않늗다.

    에어컨을 끄면 실외 온도와 같아지는 방향으로 1씩 상승 / 하강 한다.
    온도가 같다면 변하지 않는다.

    에어컨 소비전력은 희망온도와 실내 온도가 다르다면 1분에 a 만큼 소모,
    같다면 b 만큼 소모한다. 
 */