package ih.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
16: 45

키를 비교한 결과의 일부인데
자신의 키가 몇 번째 인지 알 수 있는 학생들이 모두 몇명인지 계산하여 출력

a인 학생이 b보다 키가 작다~

들어오는걸 inbound
나가는 걸 outbound로 해서 구한다?

그럴필요없을 듯 저렇게하면 하위꺼 세지도못함
배열하나 줘서 n보다 작은 값들이 몇개있는지 세어보자~ 근데 작은 사람만 셋지 큰 사람은 어케셉니까?
*/

public class _07_05_2462 {
    static int N,M;
    
    static ArrayList<LinkedList<Integer>> list = new ArrayList<>();
    static ArrayList<LinkedList<Integer>> reverseList = new ArrayList<>();
    static int[] up,down;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        up = new int[N+1];
        down = new int[N+1];
        visited = new boolean[N+1];

        for (int i = 0; i < N+1; i++) {
            list.add(new LinkedList<>());
            reverseList.add(new LinkedList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            reverseList.get(b).add(a);
        }

        for (int i = 1; i < N+1; i++) {
            Arrays.fill(visited,false);
            dfs(list,down,i,i);
            Arrays.fill(visited,false);
            dfs(reverseList,up,i,i);
        }

        int answer = 0;

        for (int i = 1; i < N+1; i++) {
            if(up[i] + down[i] == N-1){
                answer++;
            }
        }

        System.out.println(answer);

    }

    static public void dfs(ArrayList<LinkedList<Integer>> nowList,int[] arr,int nowNum, int parentNum){
        LinkedList<Integer> list = nowList.get(nowNum);

        // 만약 맨 끝이면 자기보다 작은 사람이 없음
        if(list.isEmpty()) return;

        for (Integer num : list) {
            // 만약 이미 아래가 몇명인지 세어져있다면 그대로 더함
            if(!visited[num]){
            // 만약 몇명인지 안 세어져있다면 세어주러감
                visited[num] = true;
                arr[parentNum] += 1;
                dfs(nowList,arr,num,parentNum);
            }
        }
    }
}
