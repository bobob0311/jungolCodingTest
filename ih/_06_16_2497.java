package ih;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _06_16_2497 {
    static int N,K;

    static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;

        for (int i = 0; i < K; i++) {
            answer+= nums[i];
        }

        int start = 0;
        int end = K;

        int nowSum = answer;

        while(end < N){
            nowSum = nowSum - nums[start++] + nums[end++];

            answer = Math.max(nowSum,answer);
        }

        System.out.println(answer);
    }
}
