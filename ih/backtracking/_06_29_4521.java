package ih.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
N개중 몇개 선택으로 영양분이 일정 이상

조건을 만족시키면서도 비용이 최소가 되는 합리적인 선택을 해야한다.
*/

public class _06_29_4521 {
    static int N, answer;
    static int[][] infos;
    static int[] target;
    static long minCost = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        infos = new int[1 << N][5];
        target = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int mp = Integer.parseInt(st.nextToken());
            int mf = Integer.parseInt(st.nextToken());
            int ms = Integer.parseInt(st.nextToken());
            int mv = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            infos[1<<i] = new int[] {mp,mf,ms,mv,cost};
        }

        sol(0,0,0,0,0,0,0);

        if(answer <=0){
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minCost).append('\n');



        for (int i = 0; i < N; i++) {
            if ((answer & (1 << i)) != 0) {
                sb.append(i + 1).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

    public static void sol(int idx, int p, int f, int s, int v, int cost, int selected){
        // 조기 종료
        if (cost > minCost){
            return;
        }

        // 끝까지 왔으니까 확인
        if(idx == N){
            if(p >= target[0] && f >= target[1] && s>=target[2] && v >=target[3]){
                if(cost < minCost){
                    minCost = cost;
                    answer = selected;
                } else if(cost == minCost && chk(selected,answer)){
//                    answer = selected;
                }
            }
            return;
        }

        sol(idx +1, p + infos[1<<idx][0],
                f + infos[1<<idx][1],
                s + infos[1<<idx][2],
                v + infos[1<<idx][3],
                cost + infos[1<<idx][4],
                selected + (1 << idx)
        );
        sol(idx+1,p,f,s,v,cost,selected);
    }

    static boolean chk(int selected, int nowAnswer){
        for (int i = 0; i < N; i++) {
            int temp = (1 << i);
            int selectedChk = temp & selected;
            int answerChk = temp & nowAnswer;

            if(selectedChk < answerChk){
                return false;
            }else if(selectedChk > answerChk){
                return true;
            }
        }
        return true;
    }
}
