package ih.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
16:35 ->
가로,세로,3*3 정사각형 1~9까지 한번씩만 나타나야함
게임 시작 전 스도쿠 판에 쓰여있는 숫자들의 정보가 주어질  때
모든 칸이 채워진 최종 모습을 출력해야한다.

빈칸 -> 0 으로 채워져있음
방법이 여러개인 경우 그 중 하나만을 출력한다.

9*9

1. row 체크
2. column 체크
3. 정사각형 체크
*/

public class _07_02_1824 {

    static int[][] map;
    static boolean[][] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        map = new int[9][9];
        visited = new boolean[10][10];

        // 초기 스도쿠 판 세팅
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num != 0){
                    visited[i][num] = true;
                }
                map[i][j] = num;
            }
        }

        dfs(0,0);
        System.out.println(sb.toString());
    }

    public static void dfs(int line, int column){
        if(!sb.isEmpty()) return;
        // 만약 다 채우면 이게 정답인디
        if(line == 9){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(' ');
                }
                sb.append('\n');
            }
            return;
        }

        if(column == 9){
            dfs(line +1,0);
            return;
        }

        if(map[line][column] != 0){
            dfs(line, column+1);
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if(allChk(line,column,i)){
                map[line][column] = i;
                visited[line][i] = true;

                dfs(line, column +1);

                map[line][column] = 0;
                visited[line][i] = false;
            }
        }

    }

    public static boolean allChk(int line, int column, int nowPickNum){
        if(rowChk(line,nowPickNum) && columnChk(nowPickNum,column) && sChk(line,column,nowPickNum)){
            return true;
        }
        return false;
    }

    public static boolean rowChk(int rowIdx, int nowPickNum){
        if(visited[rowIdx][nowPickNum]){
            return false;
        }
        return true;
    }

    public static boolean columnChk(int nowPickNum,int column){
        for (int i = 0; i < 9; i++) {
            if(map[i][column] == nowPickNum){
                return false;
            }
        }
        return true;
    }

    public static boolean sChk(int rowIdx, int columnIdx, int nowPickNum) {
        int rowGroup = rowIdx /3;
        int columnGroup = columnIdx /3;

        for (int i = rowGroup *3; i < rowGroup *3 +3 ; i++) {
            for (int j = columnGroup *3; j <columnGroup *3 + 3 ; j++) {
                if(map[i][j] == nowPickNum){
                    return false;
                }
            }
        }

        return true;
    }
}
