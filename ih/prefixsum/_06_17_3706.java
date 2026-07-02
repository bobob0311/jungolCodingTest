package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
구간 i,j에 있어서 합이 0이되는 모든 경우의 수를 구해라

N은 10만

0~j까지의 누적합을 구함

만약 sums[i] = 5이고 sums[j] 도 5이면 두개 사이는 0인듯

*/
public class _06_17_3706 {

    static int N;
    static HashMap<Integer,Integer> map = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] sums = new int[N];
        sums[0] = nums[0];
        map.put(nums[0],1);

        for (int i = 1; i < N; i++) {
            int nowSumValue = sums[i-1] + nums[i];
            sums[i] = nowSumValue;
            if(map.get(nowSumValue) != null){
                map.put(nowSumValue, map.get(nowSumValue)+1);
            }else{
                map.put(nowSumValue,1);
            }
        }

        long answer = 0;
        for (Integer num : map.keySet()) {
            long cnt = map.get(num);

            if(num == 0){
                answer += cnt;
            }
            if(cnt > 1){
                answer += (cnt * (cnt-1)) /2;
            }
        }
        System.out.println(answer);
    }
}
