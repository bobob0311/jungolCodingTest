package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
동굴의 길이 N 높이는 H
석순이랑 종유석이 번갈아 나타나는 형태

가장 장애물을 적게 부시면서 가는 방법
*/

public class _06_22_5628 {

    static int N,H;
    static int[] cave,caveReverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        cave = new int[H*2 +2];
        caveReverse = new int[H*2+ 2];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine()) *2;

            if(i % 2 == 0){
                cave[0] +=1;
                cave[num+1] -=1;
            }else{
                caveReverse[2*H-num] +=1;
            }
        }

        for (int i = 1; i < 2* H; i++) {
            cave[i] = cave[i-1] + cave[i];
            caveReverse[i] = caveReverse[i-1] + caveReverse[i];
        }


        for (int i = 0; i < 2* H; i++) {
            cave[i]+= caveReverse[i];
        }

        int answer = Integer.MAX_VALUE;

        int cnt = 0;
        for (int i = 1; i < H*2; i+=2) {
            if(answer > cave[i]){
                answer = cave[i];
                cnt = 1;
            }else if(answer == cave[i]) {
                cnt++;
            }
        }

        System.out.print(answer+ " "+cnt);
    }
}
