package ih.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
20 35 ->

정류장은 네자리 소스

한 정류장의 번호와 다른 정류장의 번호의 각자리 비교시 자리가 하나만 다른 경우

번호가 하나 다른거만 탈 수 ㅣㅇㅆ는데 거쳐 가는 정류장의 개수를 최소 한다로 줄여서
효율적으로 갈 수 있도록

1. 정류장은 네 자리 소수
2. 한자리 차이나는 경우에만 이동이 가능하다.

네자리 가능한 소수를 전부 나열??

*/

public class _07_07_1336 {
    static int start, end;

    static Queue<int[]> que = new LinkedList<>();

    static boolean[] busStop;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st= new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        busStop = new boolean[10000];


        initBusStop();

        que.offer(new int[] {start,0});
        busStop[start] = false;
        sol();
    }

    static public void sol(){

        while (!que.isEmpty()) {
            int[] nowNode = que.poll();

            int now = nowNode[0];
            int cnt = nowNode[1];


            if(now == end){
                System.out.println(cnt);
                return;
            }
            for (int digit = 1; digit <= 4; digit++) {
                    int left = (now/ (int)Math.pow(10,digit)) * (int)Math.pow(10,digit);
                    int right = now % (int)Math.pow(10,digit -1);
                for (int j = 0; j <= 9; j++) {
                    int d = (int)Math.pow(10,digit -1) * j;

                    int next = left + right + d;

                    if(next >= 1000 && busStop[next]){
                        busStop[next] = false;
                        que.offer(new int[] {next,cnt+1});
                    }
                }
            }
        }
    }

    static public void initBusStop(){
        Arrays.fill(busStop,true);

        for (int i = 2; i * i < 10000; i++) {
            if(busStop[i]){
                for (int j = i; j < 10000 ; j+=i) {
                    busStop[j] = false;
                }
            }
        }

        for (int i = 0; i < 1000; i++) {
            busStop[i] = false;
        }
    }

}
