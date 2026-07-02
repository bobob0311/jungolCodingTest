package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
분노하는 정도 -> 모든 빵 맛 차이의 합

최소 분노도를 출력하는 프로그램을 작성해라


각 빵들의 차이를 절대값으로 만들어서 다 더하면 총 분노도.
N개 중에서 K개를 뽑아서 가장 차이가 안나게 뽑아야하는 것인디.
10만개중에 K개 뽑아서 K개중에 2개씩 계산하면 죽습니다.

빵 맛은 10억이하

배열을 정렬한다음에

근본은 그냥 제일 차이가 안나게 만드는 것인데
전이랑 얼마나 차이나는지를?
그래서 sum을..?


일반 식 -> 빵 맛 *\ - sum
*/
public class _06_17_3185 {
    static int N,K;
    static long answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        answer = Long.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        long[] breads = new long[N];

        for (int i = 0; i < N; i++) {
            breads[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(breads);

        long anger = 0;
        long windowSum = 0;

        // i에서 j의 절대값의 차이를 구하는 식
        // 1 3 7 에서 차이를 구하면 7 - 3,  7-1 => 7* 2 - (3+1);
        for (int i = 0; i < K; i++) {
            anger += breads[i] * i -windowSum;
            windowSum += breads[i];
        }

        long answer = anger;

        for(int left = 0; left + K < N; left++){
            long removeBread = breads[left];
            long addBread = breads[left + K];

            long removeAnger = windowSum - removeBread * K;
            anger -= removeAnger;
            windowSum -= removeBread;

            long addAnger = addBread * (K-1) - windowSum;
            anger += addAnger;
            windowSum += addBread;

            answer = Math.min(answer,anger);
        }
        System.out.println(answer);
    }
}
