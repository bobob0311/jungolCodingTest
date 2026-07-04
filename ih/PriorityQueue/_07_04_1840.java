package ih.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
치즈~

치즈에는 하나 이상의 구멍이 있을 수 있다.

공기 중에 놓으면 녹는다. 공기와 접촉된 칸은 한 시간이 지나면 녹아 없어진다.
구멍이 열리면 구멍속으로 공기가 들어가게 된다.
왜 dfs 챕터에 있지?? PQ를 써야할거같은데
*/

public class _07_04_1840 {
    static int N,M;

    static PriorityQueue<int[]> pq  = new PriorityQueue<>((a,b) -> {
        return Integer.compare(a[2],b[2]);
    });

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static int[][] map;
    static boolean[][] visited;

    static int answer = 0;
    static HashMap<Integer,Integer> hMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M+1];
        visited = new boolean[N][M+1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < M+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pq.offer(new int[] {0,0,0});

        sol();
        System.out.println(answer);
        System.out.println(hMap.get(answer));
    }

    static public void sol(){

        while(!pq.isEmpty()){
            int[] nowNode = pq.poll();

            int x = nowNode[0];
            int y = nowNode[1];
            int turn = nowNode[2];

            if(map[x][y] == 1){
                hMap.put(turn,hMap.getOrDefault(turn,0)+1);
            }

            answer =Math.max(turn,answer);

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(isCan(nextX,nextY)){
                    visited[nextX][nextY] = true;
                    if(map[nextX][nextY] == 0){
                        pq.offer(new int[] {nextX,nextY, turn});
                    }else{
                        pq.offer(new int[] {nextX,nextY,turn +1});
                    }
                }
            }
        }
    }

    static public boolean isCan(int x, int y){
        if(x >=0 && x< N && y >=0 && y <M+1 && !visited[x][y]){
            return true;
        }
        return false;
    }
}
