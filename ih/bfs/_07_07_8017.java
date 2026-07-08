package ih.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
수직선 도로 위에 N개의 가로등

어두은 정도 -> 가장 가까운 가로등까지의 거리

*/

public class _07_07_8017 {
    static long L;
    static int N,K;

    static Queue<long[]> que = new LinkedList<>();

    static long[] dd = new long[] {1,-1};

    static int answerCnt = 0;

    static HashSet<Long> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Long.parseLong(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long idx = Long.parseLong(st.nextToken());
            que.add(new long[] {idx, 0});
            set.add(idx);
            sb.append(0).append('\n');
            answerCnt++;
            if(answerCnt == K){
                System.out.println(sb.toString());
                return;
            }
        }

        bfs();
        sb.setLength(sb.length() -1);
        System.out.println(sb.toString());
    }

    static public void bfs(){

        while(!que.isEmpty()){
            long[] nowNode = que.poll();

            long now = nowNode[0];
            long cnt = nowNode[1];



            for (int i = 0; i < 2; i++) {
                long next = now + dd[i];
                int pervSize = set.size();
                set.add(next);
                if(next <= L && next >=0L && pervSize != set.size()){
                    que.add(new long[] {next,cnt +1});
                    sb.append(cnt +1).append('\n');
                    answerCnt++;
                    if(answerCnt == K) return;
                }
            }
        }
    }

}
