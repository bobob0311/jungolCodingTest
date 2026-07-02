package ih;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
회전 벨트
같은 종류 있을 수 있음

- 벨트의 특정 위치부터 k개의 접시 연속이면 할인된 정액 가격으로 제공
- 각 고객에 종류가 쓰인 쿠폰 발행 1번 행사 참여시 적힌거 무료제공
    벨트위에 없을시 새로 만들어 제공

N => 접시 수
D => 초밥 가지수
K => 연속 접시 수
C => 쿠폰 번호

=> 손님이 먹을 수 있는 초밥 가짓수의 최대값을 구해라 !!

해쉬 맵으로? 아님 배열로 가짓수 만큼 만들고 현재 담은거 ++ 해서 하면 될듯?
0되면 가짓수에서 빼고 확인해보고 0이엿으면 가짓수 plus하면서 넣어주고
*/

public class _06_16_2577 {
    static int N,D,K,C;
    static int[] dish;


    static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dish = new int[D+1];

        int[] items = new int[N];

        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(br.readLine());
        }

        int now = 0;
        int answer = 0;

        for (int i = 0; i < K; i++) {
            int nowDish = items[i];

            if(dish[nowDish] == 0) now++;
            dish[nowDish]++;
        }

        int start = 0;
        int end = K;
        while (start != N){
            if(end >= N) end %= N;

            int exitDish = items[start];
            int comeDish = items[end];

            if(--dish[exitDish] == 0){
                now--;
            }

            if(dish[comeDish]++ == 0){
                now++;
            }


            start++;
            end++;

            answer = Math.max(now + chkCo(C),answer);
        }

        System.out.println(answer);
    }
    public static int chkCo(int dishNum){
        if(dish[dishNum] == 0) return 1;
        return 0;
    }
}
