package ih.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
R행 C열
. -> 비어있음

* -> 불
X -> 바위

D -> 용사의 집
S -> 시작 위치

불과 바위는 가지 못하며 불은 바위와 용사의 집에 옮겨지지 않는다.

불과 움직일 수 있는 곳을 bfs를 돌린다.
불이 먼저 번지고 움직일 수 있는 곳을 찾아서 움직여야함
*/

public class _07_12_1082 {
    static int R,C;

    static int[] dx = new int[]{0,0,1,-1};
    static int[] dy = new int[]{1,-1,0,0};

    static char[][] map;
    static boolean[][] visited;

    static int startX, startY, endX,endY;

    static Queue<int[]> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();

            for (int j = 0; j < C; j++) {
                char ch = str.charAt(j);

                if(ch == 'S'){
                    startX = i;
                    startY = j;
                }
                if(ch == 'D'){
                    endX = i;
                    endY = j;
                }

                if(ch == '*'){
                    que.offer(new int[] {i,j,0});
                    visited[i][j] =true;
                }
                map[i][j] = ch;
            }
        }

        map[startX][startY] = '.';
        map[endX][endY] = '.';

        sol();
    }

    static public void sol(){
        visited[startX][startY] = true;
        que.offer(new int[]{startX,startY,0});

        int answerValue = bfs();
        if(answerValue == -1){
            System.out.println("impossible");
        }else{
            System.out.println(answerValue);
        }
    }

    static public int bfs(){
        int turn = 0;

        while(!que.isEmpty()){
            int[] nowNode = que.poll();

            int nowX = nowNode[0];
            int nowY = nowNode[1];
            int nowTurn = nowNode[2];

            if(nowX == endX && nowY == endY){
                return nowTurn;
            }

            if(turn != nowTurn){
                turn  = nowTurn;
                if(!isPossible()){
                    return -1;
                }
            }


            // 현재 불인 경우
            if(map[nowX][nowY] == '*'){
                fire(nowX,nowY,nowTurn);
            }else{
                // 현재 불이 아닌 경우
                for (int i = 0; i < 4; i++) {
                    int nextX = nowX + dx[i];
                    int nextY = nowY + dy[i];

                    if(isArea(nextX,nextY) && !visited[nextX][nextY]){
                        if(map[nextX][nextY] == '.'){
                            que.offer(new int[] {nextX,nextY,nowTurn+1});
                        }
                        visited[nextX][nextY] = true;
                    }
                }
            }


        }
        return -1;
    }


    static public void fire(int x, int y, int nowTurn){
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(nextX == endX && nextY == endY){
                continue;
            }

            if(isArea(nextX,nextY) && !visited[nextX][nextY]){
                if(map[nextX][nextY] == '.'){
                    que.offer(new int[] {nextX,nextY,nowTurn+1});
                    map[nextX][nextY] = '*';
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    static public boolean isPossible(){
        for (int i = 0; i < 4; i++) {
            int nextX = dx[i] + endX;
            int nextY = dy[i] + endY;

            if(isArea(nextX,nextY) && map[nextX][nextY] == '.'){
                return true;
            }
        }
        return false;
    }

    static public boolean isArea(int x, int y){
        if(x>=0 && x< R && y >=0 && y<C){
            return true;
        }
        return false;
    }
}
