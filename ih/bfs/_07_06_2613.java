package ih.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
토메이로~
익은거 주변에 있으면 익어요~
*/

public class _07_06_2613 {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static Queue<int[]> que = new LinkedList<>();

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];



        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());

                if(num == 1){
                    visited[i][j] = true;
                    que.offer(new int[] {i,j,0});
                }

                map[i][j] = num;
            }
        }

        bfs();

        // 마지막에 que다 돌았는데 안익은 토마토가 있는지 검사 안익은거 있으면 -1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(answer);
    }

    static public void bfs(){

        while(!que.isEmpty()){
            int[] nowNode = que.poll();

            int x = nowNode[0];
            int y = nowNode[1];
            int cnt = nowNode[2];

            // 가장 오래 걸린 날 계속 갱신
            answer = Math.max(cnt,answer);

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(isCan(nextX,nextY)){
                    visited[nextX][nextY] = true;

                    // 익었어여~ 표시 사실 여기다 하나 while문에서 poll했을 때 하나 똑같다고 생각되는데
                    // 이미 처음에 들어온거는 map[x][y]가 1일때 들어왔으니까 그거에 맞춰줄라고 여기서 1로 변경하고 넣어줌
                    // 마지막에 안익은게 있는지 확인해야하기때문에 필수
                    // 아니면 visited 배열 돌면서 map[x][y] = 0인데 visited = false인걸 찾아도 되긴함  근데 굳이?
                    map[nextX][nextY] =1;
                    que.offer(new int[]{nextX,nextY,cnt +1});
                }
            }
        }
    }

    static public boolean isCan(int x, int y){
        return x<N && x>=0 && y<M && y>=0 && map[x][y] == 0 && !visited[x][y];
    }
}
