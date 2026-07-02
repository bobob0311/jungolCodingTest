package ih;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
오른쪽으로 P힘
i (1~N) Ai 방어력

힘 <= 방어력 이면 화살이 멈춤

방어력이 더 크면 Ai 만큼 줄어든 채 계속 진행

화살이 위치 i에서 멈추거나 위치 i 보다 왼쪽에서 멈추도록 하기 위해 필요한 최소 개수

못멈추면 -1

최소 개수이므로 가장 큰 값을 순서대로 더하는게 좋을 거 같아여

최소 값이 먼저 나오는 PQ를 만들고 최솟값이 빠질 수 있는 지 체킹해가면서 pq 사이즈를 확인
pq안에 있는 값들은 이미 누적되어서 더해져있는 값임.

*/

public class _8566 {
    static int N, P;

    static int[] items;

    static StringBuilder sb;

    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        items = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        sol();
        System.out.println(sb.toString());
    }

    static public void sol(){
        long sum = 0;

        for (int i = 0; i < N; i++) {
            int nowNum = items[i];

            // 현재 값 누적
            sum += nowNum;
            pq.add(nowNum);

            // 아직 더 작은 경우
            if(sum < P) {
                sb.append(-1).append(" ");
                continue;
            }


            // 만약 지금 현재 넘어가 있으면
            while(sum - pq.peek() >= P){
                int n = pq.poll();
                sum-= n;
            }
            sb.append(pq.size()).append(" ");
        }
    }


}
