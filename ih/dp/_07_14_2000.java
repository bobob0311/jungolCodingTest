package ih.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1~34
*/

public class _07_14_2000 {

    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String str = br.readLine();
        int N = str.length();

        dp = new int[N+1];
        dp[0] = 1;

        for (int i = 1; i <= N; i++) {
            // 한 자리 추가의 경우
            int one = str.charAt(i-1) - '0';
            if(one>=1 && one<=9){
                dp[i] += dp[i-1];
            }

            // 두 자리 추가의 경우
            if(i>=2){
                String nowStr = str.substring(i-2,i);
                int num = Integer.parseInt(nowStr);

                if(num <=34 && num >=10){
                    dp[i] += dp[i-2];
                }
            }
        }
        System.out.println(dp[N]);
    }

}
