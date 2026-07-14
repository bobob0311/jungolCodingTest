package ih.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
동전의 단위들이 주어지고 원하는 잔돈이 주어질 때
잔돈에 맞추기 위해 필요한 최소 동전 수
동전의 수는 무한하다.
*/

public class _07_14_2000 {

    static int N,target;
    static int[] coins,dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        coins = new int[N];


        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        target = Integer.parseInt(br.readLine());
        dp = new int[target+1];

        int answer = sol(target);
        if(answer == -1){
            System.out.println("impossible");
        }else{
            System.out.println(answer);
        }
    }

    static public int sol(int target){
        for (int i = 1; i <= target ; i++) {
            int min = Integer.MAX_VALUE;

            for (int coin : coins) {
                if(i-coin>=0){
                    int prevDp = dp[i-coin];
                    if(prevDp == -1) {
                        continue;
                    }
                    min = Math.min(min,dp[i-coin] +1);
                }
            }
            if(min == Integer.MAX_VALUE){
                dp[i] = -1;
            }else{
                dp[i] = min;
            }
        }

        return dp[target];

    }

}
