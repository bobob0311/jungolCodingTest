package ih.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
수리 1분


1. 수리해주고 ci원 받기
2. 1분 명상 -> M 마법력 회복 -> 최대치 없음


=> 2차원 배열이 아닌 1차원으로 풀 수 있는 문제였음
60000이라는 최대값을 잡고 가는건 비효율적이였음 K+ M*N +1로 제일 효율적인 공간만을 차지할 수 있음
조금 더 생각합세
*/

public class _07_17_4480 {
    static int N,M,K;

    static int[] costs;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        costs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        // N번째 사람이 i만큼의 마법력을 가지고 val 만큼 회복한상태
        dp = new int[N+1][60001];

        for (int i = 0; i < N+1; i++) {
            Arrays.fill(dp[i],-1);
        }

        // 명상하기
        dp[1][K+M] = 1;

        // 수리해주기
        if(K-costs[0] >=0){
            dp[1][K-costs[0]] = 0;
        }


        for (int i = 1; i < N; i++) {
            int nowCost = costs[i];
            for (int j = 0; j < 60001; j++) {
                // 이전꺼에 파생되어서
                if(dp[i][j] != -1){
                    // 수리해주는 경우
                    if(j-nowCost >=0){
                        dp[i+1][j-nowCost] = Math.max(dp[i][j],dp[i+1][j-nowCost]);
                    }
                    // 명상하는 경우
                    dp[i+1][j+M] =Math.max(dp[i][j] +1, dp[i+1][j+M]);
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < 60001 ; i++) {
            int totalRest = dp[N][i];
            if(totalRest == -1){
                continue;
            }
            answer = Math.max(totalRest * M + K - i, answer);
        }

        System.out.println(answer);

    }
}
