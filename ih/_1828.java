package ih;/*
14:00 -> 14: 21
N개의 화학 물질
최저 온도와 최고 온도 사이에 보관되어야 안전

N 1~100
최저 온도와 최고 온도 N개 (-270 ~10,000)
냉장고는 아주 크다. => 용량 제한 X

가장 높은 온도로 오름차순으로 정렬
첫번째 꺼내서
이거랑 다음꺼부터 작은거 비교해서 넘어가는 순간
가장 높은 온도를 바꿔주고 반복
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _1828 {
    static int N;
    static int[][] tp;
    static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tp = new int[N][2];

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int bottom = Integer.parseInt(st.nextToken());
            int top = Integer.parseInt(st.nextToken());

            tp[i] = new int[] {bottom, top};
        }

        Arrays.sort(tp, (int[]a, int[]b) -> Integer.compare(a[1],b[1]) );


        System.out.println(sol());
    }

    static public int sol(){
        int cnt = 1;
        int now = tp[0][1];

        for(int i = 1; i< N; i++){
            int top = tp[i][1];
            int bottom = tp[i][0];

            if(bottom > now){
                cnt++;
                now = top;
            }
        }
        return cnt;
    }
}
