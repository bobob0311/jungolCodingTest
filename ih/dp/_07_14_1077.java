package ih.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
배낭 채우기
W넘어가면 배낭이 망가짐
총 무게 W안넘으면서 보석 값어치 최대로
최대 값어치 구해라
*/

public class _07_14_1077 {

    static int N,W;
    static int[][] infos;

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        infos = new int[N][2];
        dp = new int[W+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            infos[i] = new int[] {w,p};
            dp[w] = p;
        }

        System.out.println(sol());
    }


    static public int sol(){
        for (int i = 1; i <= W; i++) {
            int max = 0;

            for (int[] info : infos) {
                int w = info[0];
                int p = info[1];

                if(i -w >= 0){
                    max = Math.max(max, dp[i-w] + p);
                }
            }

            dp[i] = Math.max(dp[i], max);
        }

        int maxV = 0;
        for (int i = 0; i <= W; i++) {
            maxV = Math.max(maxV, dp[i]);
        }
        return maxV;
    }

}
