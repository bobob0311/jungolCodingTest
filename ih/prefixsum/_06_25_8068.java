package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
N일동안 새로운 단어 암기

i일에 암기한  단어 i+1부터 M개씩 사라짐
해당 단어의 수가 M개 보다 적다면 남은 모든 영단어 잊음

잊어버리는 영단어의 수와 기억하고 있는 영단어의 수를 구해라

N -> 기간     100000
M -> 잊는 수   10000
Q -> qeury로 100000

누적합과
빼지는걸 따로 구해서 하면 되지 않을까라는게 생각

단어 외우는거 누적합은 원래 하던데로 하고

빼지는건 값 / M해서 몇번 빼지는지 확인하고 그 이후에는 (나머지) 그다음에 (M-나머지) 더이상 빠지지 않도록 관리

32 39 53 55 56 65
   3  6

*/

public class _06_25_8068 {
    static int N,M,Q;
    static int[] words;

    static int[] sum,minus, minusSum;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        sum = new int[N+10003];
        minus = new int[N+10003];
        minusSum = new int[N+ 10003];
        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        int p = cnt / M;
        int q = cnt % M;
        sum[0] = cnt;
        if(cnt <= M){
            minus[1] += cnt;
            minus[2] += -cnt;
        }else{
            if(q==0){
                minus[1] = M;
                minus[p+1] = -M;
            }else{
                minus[1] = M;
                minus[p+1] += -(M-q);
                minus[p+2] += -(q);
            }

        }
//        System.out.println();
//        for (int j = 0; j < N; j++) {
//            System.out.print(minus[j]+ " ");
//        }
//        System.out.println();

        for (int i = 2; i < N+1; i++) {
            cnt = Integer.parseInt(st.nextToken());

            sum[i-1] = sum[i-2] + cnt;
            if (cnt <= M){
                minus[i] += cnt;
                minus[i+1] += -cnt;
                continue;
            }

            p = cnt / M;
            q = cnt % M;
            minus[i] += M;
            if(q== 0){
                minus[i+p] += -M;
            }else{
                minus[i+p] += -(M-q);
                minus[i+p+1] += -(q);
            }
        }


        for (int i = 1; i <= N; i++) {
            minus[i] = minus[i-1] + minus[i];
        }


        for (int i = 1; i <=N ; i++) {
            minusSum[i] = minusSum[i-1] + minus[i];
        }





        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken())-1;

            int remeber = sum[t] - minusSum[t];
            int forget = minus[t];

            if(c ==1) {
                if(remeber < 0) {
                    sb.append(0);
                }else{
                    sb.append(remeber);
                }
            }else{

                sb.append(forget);

            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
