package ih.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
각 칸은 L육지 W바다
한칸 이동에 한시간
보물은 가장 먼 육지 두곳에 묻혀있음 => 멀리 돌어가거나 두번 이상 지나가면 안됩니다

보물이 묻혀있는 두 곳 간의 최단 거리로 이동하는 시간을 구해라

가로 세로 50 X 50
2500 * 50 => 괜찮긴한데 이렇게 푸는 방법 밖에 없을까..
*/

public class _07_06_1462 {
    static int N,M;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    static Queue<int[]> que = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        int answer = 0;

        // map이 int인게 편해서 그냥 0이랑 1로 구분해서 넣어줬습니다.
        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            for (int j = 0; j < M; j++) {
                char ch = str.charAt(j);
                if(ch == 'W'){
                    map[i][j] = 0;
                }else{
                    map[i][j] = 1;
                }
            }
        }

        // 결국 육지랑 육지 사이거리중 제일 먼 거리 구하는거라
        // 모든 육지들 사이의 거리를 구한다고 생각했습니다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 만약 육지이면 방문 배열 초기화하고 넣어주기
                if (map[i][j] == 1){
                    // 안에 for문 또 만들기 싫어서 외부로 뺐어여
                    initVisited();

                    visited[i][j] = true;
                    que.offer(new int[]{i,j,0});
                    answer = Math.max(bfs(),answer);
                }
            }
        }

        System.out.println(answer);
    }

    // 처음 들어온 육지하고 제일 먼 육지까지의 거리를 반환해주는 bfs
    static public int bfs(){
        int maxDist = 0;
        while(!que.isEmpty()){
            int[] nowNode = que.poll();

            int x = nowNode[0];
            int y = nowNode[1];
            int dist = nowNode[2];

            // 처음 que에  들어온 육지와 가장 먼 육지까지의 거리 계속 갱신
            maxDist = Math.max(maxDist,dist);

            for (int i = 0; i < 4; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];

                if(isCan(nextX,nextY)){
                    visited[nextX][nextY] = true;
                    que.offer(new int[]{nextX,nextY,dist +1});
                }
            }
        }

        return maxDist;
    }
    static public boolean isCan(int x, int y){
        return x<N && x>=0 && y<M && y>=0 && map[x][y] == 1 && !visited[x][y];
    }

    static public void initVisited(){
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i],false);
        }
    }
}
