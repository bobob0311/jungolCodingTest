package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
18: 47 -> 598

N개의 마을로 구성

xi 위치에 놓여있으며 ai명이 거주
=> 서로 다른 두 마을이 같은 위치 X

모든 사람들이 모임 장소로 도착하기 위해 이동해야 하는 거리 -> 누적 거리
x -> f(x) -> ai X |xi - x| (1~N까지)
후보 장소 위치 qj

서로 다른  두 후보 장소의 위치가 같은 경우X
마을 위치와 후보 장소의 위치가 같을 수 있다.


N -> 200,000
ai -> 1000
xi -> 10억

Q -> 200000
qi -> 10억

모든 후보자리까지의 누적거리를 출력

후보지의 위치가 중요한거같음

1. 10억 씩 다 더한다음에 정렬(계산 편하게 하려고)
2. 1~ N까지 cnt까지 고려한 누적합과 인원 누적합
3. N ~ 1 까지 cnt를 고려한 거꾸로 누적합과 인원 누적합
4. 이분 탐색으로 후보 위치가 어디 보다 큰지 확인
5 정답 도출


sum -> 1부터 시작해서 누적합
sumReverse -> N부터 시작해서 누적합
cnt -> 1부터 시작해서 cnt 누적합
cntReverse -> N부터 시작해서 cnt 누적합
*/

public class _06_21_4803 {
    static int N,Q;

    static int[][] info;
    static int[] candi;
    static long[] sum,sumReverse,cnts,cntsReverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        info = new int[N+1][2];
        candi = new int[Q];

        sum = new long[N+1];
        sumReverse = new long[N+1];
        cnts = new long[N+1];
        cntsReverse = new long[N+1];


        for (int i = 0; i < N; i++) {
            st= new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken()) + 1000000000;
            info[i] = new int[] {pos,cnt};
        }
        info[N] = new int[]{2000000000,0};

        Arrays.sort(info, (a,b) -> Integer.compare(a[0], b[0]));

        for (int i = 0; i < Q; i++) {
            int can = Integer.parseInt(br.readLine()) + 1000000000;
            candi[i] = can;
        }

        calcSum();


        for (int i = 0; i < Q; i++) {
            int nowCandi = candi[i];

            int idx = findIndex(nowCandi);

            long answer = 0;

            if(idx == -1){
                answer += sumReverse[idx+1];
                answer += cntsReverse[0] * (info[0][0] -nowCandi);
                System.out.println(answer);
                continue;
            }

            answer = sum[idx] + sumReverse[idx+1];
            answer += cnts[idx] * (nowCandi - info[idx][0]);
            answer += cntsReverse[idx+1] *(info[idx+1][0] - nowCandi);
            System.out.println(answer);
        }


    }

    public static int findIndex(int target){
        int left = 0;
        int right = N-1;
        int answer = -1;

        while(left <= right){
            int mid = (left + right) /2;

            if(info[mid][0] <= target){
                answer = mid;
                left = mid +1;
            }else{
                right = mid -1;
            }
        }
        return answer;
    }

    public static void calcSum(){
        sum[0] = 0;
        cnts[0] = info[0][1];

        for (int i = 1; i <= N; i++) {
            int[] nowInfo =info[i];

            int prevPos = info[i-1][0];
            int pos = nowInfo[0];
            int cnt = nowInfo[1];

            // 이전거랑 이동거리
            long diff = pos - prevPos;

            sum[i] = (diff * cnts[i-1]) + sum[i-1];
            cnts[i] = cnts[i-1] + cnt;
        }




        sumReverse[N-1] = 0;
        cntsReverse[N-1] = info[N-1][1];
        for (int i = N-2; i >= 0; i--) {
            int[] nowInfo = info[i];

            int prevPos = info[i+1][0];
            int pos = nowInfo[0];
            int cnt = nowInfo[1];

            long diff = prevPos - pos;

            sumReverse[i] = (diff * cntsReverse[i+1]) + sumReverse[i+1];
            cntsReverse[i] = cntsReverse[i+1] + cnt;
        }


    }
}
