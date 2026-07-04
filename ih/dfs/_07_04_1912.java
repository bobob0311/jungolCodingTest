package ih.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
16 25 ->

방이 N개인 미로의 지도 (1~N번의 번호)
N개의 방 사이에는 M개의 문으로 서로 다른 두 방을 연결한다
1번 방에서 출발해 N개의 방을 모두 탐색함

항상 새로운 방을 찾아야한다.
위치하나 방과 연결된 방중 한 번도 들르지 않은 방중 가장 번호가 작은방

1. 현재 위치에서 가장 작은 방을 간다.
2. 현재 위치에서 갈 수 있는 방이 없으면 왔던 길을 되돌아간다.

ArrayList에 rooms개수만큼 넣고 rooms에 연결되어있는 방을 낮을 순으로 LinkedList로 정렬
dfs를 통해서 for문 돌아서 넣어주고 담꺼 가고 하면 될듯
*/

public class _07_04_1912 {

    static int N,M;
    static ArrayList<LinkedList<Integer>> rooms = new ArrayList();
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static Stack<Integer> stk = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];
        for (int i = 0; i <= N; i++) {
            rooms.add(new LinkedList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            rooms.get(v1).add(v2);
            rooms.get(v2).add(v1);
        }

        for (int i = 0; i <= N; i++) {
            Collections.sort(rooms.get(i));
        }
        stk.push(1);
        visited[1] = true;
        sb.append(1).append(' ');
        dfs();
        System.out.println(sb.toString());
    }

    static public void dfs(){
        while(!stk.isEmpty()){
            int roomNum = stk.peek();

            LinkedList<Integer> nowList = rooms.get(roomNum);

            while(true){
                if(nowList.isEmpty()){
                    stk.pop();
                    break;
                }
                int now = nowList.get(0);
                nowList.remove(0);
                if(!visited[now]){
                    visited[now] = true;
                    stk.push(now);
                    sb.append(now).append(' ');
                    break;
                }
            }

        }
    }


}
