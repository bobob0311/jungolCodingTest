package ih;/*
14 : 25 -> 14:45
길이가 Li인 N개의 널빤지 필요


Li -> 1 ~ 50,000
N  -> 1 ~ 20,000

길이가 N인 널빤지를 둘로 나누면 N의 비용을 지불해야합니다.

=> 가장 가운데로 반에 가깝게 잘라야 게속 자르는 비용이 제일 적어짐

역으로 생각 L1~ LN까지 있는데 위에 테이프를 붙여서 이을때 이은 거 만큼 비용이 든다.

long 생각을 안하고 했음
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class _1929 {

    static int N;
    static PriorityQueue<Long> pq = new PriorityQueue();

    static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }

        System.out.println(sol());
    }

    private static long sol() {
        long answer = 0;

        while(pq.size() != 1){
            long num1 = pq.poll();
            long num2 = pq.poll();

            long result = num1 + num2;
            answer += result;
            pq.add(result);
        }
        return answer;
    }
}

