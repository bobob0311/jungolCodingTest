package ih.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
양팔저울
주어진 추만 사용해 구슬의 무게를 확인할 수 있는지를 결정
*/

public class _07_15_1352 {

    static int N;
    static boolean dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new boolean[40001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int w = Integer.parseInt(st.nextToken());
            for (int j = 40000; j >=0 ; j--) {
                if(j-w >0 && dp[j-w]){
                    dp[j] = true;
                }
            }
            dp[w] = true;
        }
        int resultL = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultL; i++) {
            int now = Integer.parseInt(st.nextToken());
            if(chk(now)){
                sb.append('Y');
            }else{
                sb.append('N');
            }
            sb.append(' ');
        }
        System.out.println(sb.toString());
    }

    static public boolean chk(int now){
        if(dp[now]) return true;
        for (int j = 0; j <= 40000-now ; j++) {
            if(dp[j] && dp[j + now]){
                return true;
            }
        }
        return false;
    }

}
