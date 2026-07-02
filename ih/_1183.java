package ih;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
14: 48 ->

되도록 많은 개수의 동전 사용
정확한 액수만

최대 개수의 동전을 이용해 자판기 물건을 구입

W => 50 * 500 ... -> int로 될듯
500, 100, 50, 10, 5, 1 (1~50)

전체 금액  - 원하는 금액
=> 이걸 최소한의 동전 개수를 사용해서 만들면된다..
*/

public class _1183 {
    static int W;
    static int[] coins;
    static int[] answer;

    static int[] size = new int[] {500,100,50,10,5,1};

    static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        coins = new int[6];
        answer = new int[6];
        W = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 6; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }





        sol();

        int total = 0;
        for (int i = 0; i < 6; i++) {
            total += answer[i];
        }

        System.out.println(total);
        for (int i = 0; i < 6; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    static public void sol(){
        int now = W;

        for (int i = 0; i < 6; i++) {
            int m = now / size[i];
            now =  now % size[i];

            answer[i] = m;
        }
    }

}
