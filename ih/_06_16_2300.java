package ih;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1~10억 -> 산성
-10억~-1억 -> 알칼리성

0 에 가까운 용액을 만들어내는 두 용액을 찾아라~
*/
public class _06_16_2300 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N-1;

        int answerLeft = 0;
        int answerRight = N-1;

        Arrays.sort(nums);

        int prevSum = nums[start] + nums[end];
        int answer = Math.abs(prevSum);


        while(start +1 != end){
            int nextStart = start+1;
            int nextEnd = end-1;

            int startSum = prevSum - nums[start] + nums[nextStart];
            int endSum = prevSum - nums[end] + nums[nextEnd];

            if(Math.abs(startSum) < Math.abs(endSum)){
                prevSum = startSum;
                start++;
            }else{
                prevSum = endSum;
                end--;
            }
            if(answer > Math.abs(prevSum)){
                answer = Math.abs(prevSum);
                answerLeft = start;
                answerRight = end;
            }

        }
        System.out.println(nums[answerLeft] + " "+nums[answerRight]);
    }
}
