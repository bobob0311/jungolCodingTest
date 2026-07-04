package ih.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
20: 05

간격이 1
N * M (100이하)
K개의 직사각형을 그리면 나머지 부분이 분리된 영역이 된다.

- 분리된 영역이 몇개이고 각 영역의 넓이가 얼마인지를 구해라

1. map에 칠해진 영역을 구하는 함수 생성 후 map 생성
2. dfs(bfs)로 순회
3. dfs(bfs) 순회 결과를 list에 저장해서 출력

dfs, bfs 둘다 구현해보자
*/

public class _07_03_1457 {

    static int M,N,K;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    static Deque<int[]> que = new ArrayDeque<>();
    static Stack<int[]> stk = new Stack<>();
    static LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            makeRectangle(x1,y1,x2,y2);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0 && !visited[i][j]){
                    visited[i][j] = true;
                    bfs(i,j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append('\n');

        Collections.sort(list);
        for (Integer num : list) {
            sb.append(num).append(' ');
        }
        System.out.println(sb.toString());
    }

    public static void bfs(int x, int y){
        int cnt = 1;
        que.add(new int[]{x,y});

        while(!que.isEmpty()){
            int[] nowPos = que.poll();
            int nowX = nowPos[0];
            int nowY = nowPos[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if(isCan(nextX,nextY)){
                    visited[nextX][nextY] = true;
                    que.add(new int[] {nextX,nextY});
                    cnt++;
                }
            }
        }
        list.add(cnt);
    }

    public static void dfs(int x, int y){
        int cnt = 1;
        stk.add(new int[]{x,y});

        while(!stk.isEmpty()){
            int[] nowPos = stk.pop();
            int nowX = nowPos[0];
            int nowY = nowPos[1];

            for (int i = 0; i < 4; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                if(isCan(nextX,nextY)){
                    visited[nextX][nextY] = true;
                    stk.add(new int[] {nextX,nextY});
                    cnt++;
                }
            }
        }
        list.add(cnt);
    }

    static public void makeRectangle(int x1, int y1, int x2, int y2){
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                map[i][j] = 1;
            }
        }
    }

    public static boolean isCan(int x, int y){
        if(x >= 0 && x< N && y >=0 && y< M && map[x][y] == 0 && !visited[x][y]){
            return true;
        }
        return false;
    }
}
