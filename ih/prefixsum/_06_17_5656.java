package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
0~9사이에 숫자
몸통 구간에 새겨진 숫자의 합과 몸통 구간의 길이가 일치하는 모든 경우의 수

=> 구간합과 구간의 길이가 일치하는 경우의 수를 출력
우선 구간 합은 누적합으로 구하면 되는데
길이가 일치해야하니까..
결국 sums[j] - sum[i] = j - i 여야 하는건데
N -> 10만개

0으로 만들 수 있지 않을까??
누적합을 1씩 빼는 것이죠 -> 1씩 뺀 값을 둬서 결국 0으로 될 수 있게


6 0 0 0 0 5

6 6 6 6 6 11 -> 누적 합

5 4 3 2 1 5  ->  1 씩 뺀건데 같은게 나오면 구간 j-i 이 sum[j] - sum[i] 랑 같은거네

*/
public class _06_17_5656 {

    static int N;

    static HashMap<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        String numStr = br.readLine();

        int[] sums = new int[numStr.length() +1];
        sums[0] = 0;
        map.put(0,1);
        for (int i = 1; i < numStr.length()+1; i++) {
            int num = numStr.charAt(i-1) - '0';
            int sum = sums[i-1] + num - 1;
            sums[i] = sum;
            map.put(sum,map.getOrDefault(sum,0) +1);
        }

        long answer = 0;
        for (Integer num : map.keySet()) {
            long cnt = map.get(num);
            if(cnt > 1){
                answer += (cnt * (cnt-1)) /2;
            }
        }
        System.out.println(answer);
    }
}
