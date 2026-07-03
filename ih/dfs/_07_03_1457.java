package ih.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/*
19 40 ->

1이 집이 있는 곳
0이 집이 없는 곳

단지 수를 출력하고 단지에 속하는 집의 수를 오름차순으로 정렬
*/

public class _07_03_1695 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static LinkedList<Integer> list = new LinkedList<>();
    static Stack<int[]> stk = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                int num = str.charAt(j) - '0';
                map[i][j] = num;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visited[i][j]){
                    visited[i][j] = true;
                    dfs(i,j);
                }
            }
        }

        Collections.sort(list);
        StringBuilder sb = new StringBuilder();

        sb.append(list.size()).append('\n');
        for (Integer num : list) {
            sb.append(num).append('\n');
        }
        System.out.println(sb.toString());
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

    public static boolean isCan(int x, int y){
        if(x >= 0 && x< N && y >=0 && y<N && map[x][y] == 1 && !visited[x][y]){
            return true;
        }
        return false;
    }
}
