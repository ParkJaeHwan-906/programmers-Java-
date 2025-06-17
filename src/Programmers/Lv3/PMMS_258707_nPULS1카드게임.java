package Programmers.Lv3;

import java.util.*;

public class PMMS_258707_nPULS1카드게임 {
    public static void main(String[] args) {
        int coin = 4;
        int[] cards = {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4};
//        Arrays.sort(cards);
//        System.out.println(Arrays.toString(cards));
        System.out.println(new PMMS_258707_nPULS1카드게임().solution(coin, cards));
    }

    /*
        targetNum = cards.length + 1 을 만족해야 다음 라운드로 넘어갈 수 있다.
        1. 코인을 쓰지 않고 targetNum 을 만들 수 있다.
        2. 코인 하나를 쓰고 targetNum 을 만들 수 있다. ( 기존의 카드 + 새로운 카드 )
        3. 코인 두개를 쓰고 targetNum 을 만들 수 있다. ( 새로운 카드 + 새로운 카드 )
        4. targetNum 을 만들 수 없다.
     */
    int[] cards;
    int coin, cardCnt, targetNum;
    Set<Integer> remainCards, newCards;     // 남아있는 카드, 뽑은 새로운 카드
    int cardIdx;
    public int solution(int coin, int[] cards) {
        this.cards = cards;
        this.coin = coin;
        cardCnt = cards.length;
        targetNum = cardCnt+1;
        cardIdx = cardCnt/3;
        // 1. n/3 장의 카드를 뽑는다.
        initSelect();
        // 2. 게임을 시작한다.
        return doGame();
    }

    /*
        게임을 시작한다.
        각 라운드마다, 2장의 카드를 뽑는다.
        뽑은 카드는 coin 을 사용하여 가질 수 있다.
     */
    int doGame() {
        // 새로 뽑는 카드를 기록한다.
        newCards = new HashSet<>();
        int round = 0;

        /*
            ⚠️ newCards 를 clear 하지 않는다.
            Coin 을 사용해 새로운 카드를 뽑는 과정 때문이다.

            이전에 정확하게 어떤 card 를 Coin 을 사용해 뽑을지 확신할 수 없기 때문에,
            이전 결과를 계속 보관한 뒤, 필요할때 사용한다.
         */
        while(true) {
            // 새로운 라운드 시작
            round++;
            // 기저조건 - 카드를 모두 사용한경우
            if(cardIdx == cardCnt) break;

            // 카드를 뽑는다. (2장)
            newCards.add(cards[cardIdx++]);
            newCards.add(cards[cardIdx++]);

            boolean next = false;       // 다음 라운드로 갈 수 있는지 여부

            // 1. 기존의 카드만 사용하여 targetNum 을 만들 수 있다.
            for(int num : remainCards) {
                if(remainCards.contains(targetNum-num)) {
                    remainCards.remove(num);
                    remainCards.remove(targetNum-num);
                    next = true;
                    break;
                }
            }

            if(next) continue;
            // 2. 기존 카드만으로 targetNum 을 만들 수 없는 경우
            //      새로운 카드 1개를 추가로 사용해 targetNum 을 만들어본다.
            if(coin < 1) break;     // 새로운 카드를 뽑을 수 있을 정도의 coin 이 없다.
            for(int num : newCards) {
                if(remainCards.contains(targetNum-num)) {
                    remainCards.remove(targetNum-num);
                    newCards.remove(num);
                    coin--;
                    next = true;
                    break;
                }
            }

            if(next) continue;
            // 3. 기존 카드와, 새 카드의 조합으로 targetNum 을 만들 수 없는 경우
            //      새로운 카드만으로 targetNum 을 만들어본다.
            if(coin < 2) break;     // 새로운 카드를 뽑을 수 있을 정도의 coin 이 없다.
            for(int num : newCards) {
                if(newCards.contains(targetNum-num)) {
                    newCards.remove(num);
                    newCards.remove(targetNum-num);
                    coin-=2;
                    next = true;
                    break;
                }
            }

            // 만약 targetNum 을 만들 수 없다면 게임을 종료한다.
            if(!next) break;
        }

        return round;
    }

    /*
        사전 준비
        n/3 장의 카드를 뽑는다.
     */
    void initSelect() {
        remainCards = new HashSet<>();
        for(int i=0; i<cardIdx; i++) {
            remainCards.add(cards[i]);
        }
    }
}

/*
    1~n 수가 적힌 카드가 있음
    카드와 coin 을 이용한 게임을 하려한다.

    💡 카드 뭉치에서 카드를 뽑는 순서가 정해져있다.
    처음 카드 뭉치에서 카드 n/3 장을 뽑는다.
    카드와 교환 가능한 동전 coin 개를 갖고 있다.

    각 라운드마다 카드를 2장 뽑는다. 남은 카드가 없다면 게임은 끝난다.
    카드 한 장당 동전 하나를 소모해 갖거나, 동전을 소모하지 않고 버린다.

    카드에 적힌 수의 합이 n+1 이 되도록 카드 두장을 내고, 다음 라운드로 진행할 수 있다.
    카드 두 장을 낼 수 없다면 게임은 종료된다.
 */