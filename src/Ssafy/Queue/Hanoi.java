package Ssafy.Queue;

// 하노이의 탑
public class Hanoi {
    public static void main(String[] args) {
        // [움직일 원판의 수, 출발, 경유, 도착]
        hanoi(3, 1,2,3);
    }

    private static void hanoi(int n, int from, int mid, int to) {
        if(n==0) return;

        hanoi(n-1, from, to, mid);
        System.out.println(n +" : " + from + " -> " + to);
        hanoi(n-1, mid, from, to);
    }
}
