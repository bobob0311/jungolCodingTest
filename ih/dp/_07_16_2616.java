package ih.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
App

N개의 앱 활성화 중
Ai -> mi 바이트만큼 메모리 사용중
    추가적으로 드는 비용 ci

M바이트의 메모리가 필요할 때 비활성화했을 때 드는 비용의 ci를 최소화해라

*/

public class _07_16_2616 {
    static int N,M;
    static int[][] infos;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        infos = new int[N][2];
        dp = new int[10000 +1];

        StringTokenizer stM = new StringTokenizer(br.readLine());
        StringTokenizer stC = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int nowMemory = Integer.parseInt(stM.nextToken());
            int nowCost = Integer.parseInt(stC.nextToken());

            int[] next = dp.clone();

            for (int j = 0; j < 10001 - nowCost; j++) {
                if(dp[j] != 0){
                    next[j+nowCost] = Math.max(nowMemory+ dp[j], dp[j+nowCost]);
                }
            }

            dp = next;
            dp[nowCost] = Math.max(dp[nowCost],nowMemory);

        }

        for (int i = 0; i < 10001; i++) {
            if(dp[i] >= M){
                System.out.println(i);
                return;
            }
        }
    }
}
