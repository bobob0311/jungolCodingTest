package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
N 행 M열의 2차원 배열
정수를 하나 이상 포함하는 직사각형을 그리는데
직사각형 안에 포함되는 숫자들의 합의 최대

N과 M은 800이하
최대 64,0000,0000 -> answer은 long 필요
*/

public class _06_19_8591 {
    static int N,M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        map = new int[N+1][M+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        makeRowSum();
        long answer = Long.MIN_VALUE;
        // 800
        for (int left = 0; left < M; left++) {
            // 640000
            for (int right= left+1; right <= M; right++) {
                long[] col = new long[N+1];
                for (int i = 1; i <= N; i++) {
                    col[i] = map[i][right] - map[i][left];

                }

                for (int i = 1; i <= N; i++) {
                    col[i] = col[i-1] + col[i];
                }

                long min = 0;

                for (int i = 1; i <= N; i++) {
                    if (min < 0){
                        answer = Math.max(answer, col[i] - min);
                    }else{
                        answer = Math.max(answer, col[i]);
                    }
                    min = Math.min(col[i], min);

                }

            }
        }
        System.out.println(answer);

    }

    static public void makeRowSum(){
        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j < M; j++) {
                map[i][j+1] = map[i][j] + map[i][j+1];
            }
        }
    }
}
