package ih.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
무게 <= W

보석은 한개씩
*/

public class _07_15_1278 {

    static int N,W;
    static int[][] infos;
    static HashMap<Integer,Integer> map = new HashMap<>();
    static int[] dp;


    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        infos = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            infos[i] = new int[] {w,p};
        }


        dp = new int[W+1];

        for (int i = 0; i < N; i++) {
            int[] nowNode = infos[i];
            int nowW = nowNode[0];
            int nowV = nowNode[1];

            for (int j = W; j > 0 ; j--) {
                if(j - nowW > 0 && dp[j-nowW] != 0){
                    dp[j] = Math.max(dp[j], nowV + dp[j-nowW]);
                }
            }
            dp[nowW] = Math.max(dp[nowW], nowV);
        }

        int answer = 0;
        for (int i = 0; i <= W; i++) {
            answer = Math.max(answer,dp[i]);
        }
        System.out.println(answer);
    }

}
