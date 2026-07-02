package ih;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
무게가 양의 정수인 N개의 저울 추

측정할 수 없는 양의 정수 무게 중 최소값

N은 1000이하
각 추의 무게는 1이상 1,000,000이하

1,000,000,000 => 20억이하이므로 ㄱㅊ
근데 차피 이렇게 구하는건 아닌거같은데

다 더하고 하나씩 빼볼까..?

1 1 2 3 6 7 30

1
2
4
7
13
20

*/

public class _2499 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        st= new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int sum = 0;
        for(int num : nums){
            if(sum +1 < num){
                System.out.println(sum+1);
                return;
            }else{
                sum+=num;
            }
        }
        System.out.println(sum+1);

    }
}
