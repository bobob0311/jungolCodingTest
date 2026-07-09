package ih.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
A, B 해밍거리

다르면 해밍거리 +1

해밍경로 => 인접한 두 코드 사이의 해밍거리가 1인경로

두 코드 사이에 가장 짧은 해밍 경로를 구하는 프로그래밍
*/

public class _07_07_2261 {
    static int N, K;

    static int[] codes;
    static int[] parents;

    static Queue<int[]> que = new LinkedList<>();

    static HashMap<Integer,Integer> map = new HashMap<>();

    static LinkedList<Integer> answerList = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        codes = new int[N+1];

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine(),2);
            codes[i+1] = num;
            map.put(num,i+1);
        }

        st = new StringTokenizer(br.readLine());

        int startCode = Integer.parseInt(st.nextToken());
        int endCode = Integer.parseInt(st.nextToken());

        que.add(new int[]{codes[startCode],-1});
        parents[startCode] = -1;
        map.remove(codes[startCode]);

        bfs();

        int now = endCode;
        StringBuilder sb = new StringBuilder();

        if(parents[now] == 0){
            System.out.println(-1);
            return;
        }

        sb.append(startCode).append(' ');

        while(parents[now] != -1){
            answerList.add(parents[now]);
            now = parents[now];
        }
        Collections.reverse(answerList);
        for (Integer i : answerList) {
            sb.append(i).append(' ');
        }


        sb.append(endCode);
        System.out.println(sb.toString());
    }

    static public void bfs() {

        while (!que.isEmpty()) {
            int[] nowNode = que.poll();

            int now = nowNode[0];
            int parent = nowNode[1];

            for (int i = 0; i < K; i++) {
                int next = now ^ (1 << i);
                if (map.get(next) != null) {
                    int code = map.get(next);
                    que.add(new int[]{next, code});
                    parents[code] = parent;
                    map.remove(next);
                }
            }
        }
    }
}
