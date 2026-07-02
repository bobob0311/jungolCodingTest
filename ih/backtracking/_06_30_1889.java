package ih.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
가로 세로 대각선
x표시에 위치했을때 모든 queen들이 서로 잡아먹을 수없어야한다.
N * N 에서
N개의 queen

N -> 13


X일때 한번 확인해볼라면 3 * N

첫번째, 두번째, 세번째 이렇게 되는 것들을 누적해가야할듯.

그냥 가지치기만 간단히 하고 다 확인해본다??

센걸 어떻게 또 안셀 수 있게 짤 수 있을까??

LinkedList로 가지고 있기?

13 * 13 * 31 * 13

1차로 라인으로 구분 이건 인자로 주고
2차로 배열로 n번째 라인이 어느번째 칼럼에 있는지 구분
3차로 대각선을 구분해야하는데 2차 배열에 어떤 라인에서 선택됐는지 숫자를 줘서 로직으로 구분?


*/

public class _06_30_1889 {
    static int N;
    static int[] columns;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        columns = new int[N];
        Arrays.fill(columns,-1);
        answer = 0;


        dfs(0);

        System.out.println(answer);
    }

    public static void dfs(int line){
        if(line == N){
            answer++;
            return;
        }

        for (int i = 0; i < N ; i++) {
            // 이미 선택된 라인임
            if(columns[i] != -1) {
                continue;
            }else{
                if(can(line,i)){
                    columns[i] = line;
                    dfs(line+1);
                    columns[i] = -1;
                }
            }
        }
    }

    public static boolean can(int x, int y){
        int nowX = x;
        int nowY = y;

        while(nowX>0 && nowY>0){
            if(columns[--nowY] == --nowX) return false;
        }
        nowX = x;
        nowY = y;

        while(nowX< N-1 && nowY < N-1){
            if(columns[++nowY] == ++nowX) return false;
        }

        nowX = x;
        nowY = y;

        while(nowX < N-1 && nowY >0){
            if(columns[--nowY] == ++nowX) return false;
        }

        nowX = x;
        nowY = y;

        while(nowX > 0 && nowY < N-1){
            if(columns[++nowY] == --nowX) return false;
        }


        return true;
    }

}
