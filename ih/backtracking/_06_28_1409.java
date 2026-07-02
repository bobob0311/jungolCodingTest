package ih.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
19:25 ->

n개의 벽장
문은  n-2개

변장문의 이동횟수를 최소로


오른쪽 왼쪽으로만 움직일 수 있습니다.
*/

public class _06_28_1409 {
    static int N, left,right, cnt;
    static int[] target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        left = Integer.parseInt(st.nextToken());
        right = Integer.parseInt(st.nextToken());

        cnt = Integer.parseInt(br.readLine());
        target = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            target[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(dfs(left,right,0,0));


    }

    public static int dfs(int left, int right, int idx, int answer){
        if(idx == cnt){
            return answer;
        }

        int targetNum = target[idx];

        // left 이동
        int costLeft = Math.abs(left - targetNum);
        int costRight = Math.abs(right - targetNum);

        return Math.min(dfs(targetNum,right,idx+1,answer + costLeft),
                dfs(left,targetNum,idx+1,answer + costRight));
    }
}
