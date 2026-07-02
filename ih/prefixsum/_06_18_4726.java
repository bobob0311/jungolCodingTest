package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
좌우로 N개의 장소가 있음

꿀통 하나를 두고 이제 양옆에서 혹은 한쪽에서 오면서
1. 지나가면 다 따여~
2. 어떤 벌의 시작 장소는 못따여~

한쪽에 있는 경우 (꿀통보다 앞에 두벌이 존재/ 꿀통보다 뒤에 두벌이 존재)
=> 이 경우에는 끝에 꿀통이 있는 것이 가장 큰 수확을 할 수 밖에 없다.
-> 같은 위치에서 벌이 시작한다고하면 끝까지 꿀을 따는게 무조건 이득이니까
-> 꿀통은 0 또는 N-1의 인덱스를 가지고 있고
    => 앞의 경우에는 0부터 시작하는 벌과 i로 시작하는 벌 (꿀통은 N-1)
    => 뒤의 경우에는 N-1부터 시작하는 벌과 i로 시작하는 벌 (꿀통은 0)
이걸 봐줘야하는 이유는 시작 점은 제외하기 때문에
- 만약 1 10000000000 1 1 1 1 1 1 이렇게 극단적인 수가 있을 때 보면
0,1 시작 보다 0,2 시작이 좋다.

양쪽에 있는 경우 그냥 sum
꿀통의 위치를 i로 잡고 1부터 i 그리고 i부터 N-2해주면 된다.
-> 벌의 시작점은 빼주기 위해 0하고 N-1안 넣음

N은 10만 꿀은 10000이하
최대 20억 안넘어요
*/

public class _06_18_4726 {
    static int N;
    static int[] honey;
    static int[] sums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        honey = new int[N];
        sums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i <N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
        }
        sums[0] = honey[0];
        for (int i = 1; i < N; i++) {
            sums[i] = sums[i-1] + honey[i];
        }

        int answer = 0;
        // 중간 값 확인
        for(int i =1; i< N-1; i++){
            int leftSums =  sums[i] - honey[0];
            int rightSums = sums[N-2] - sums[i-1];
            answer = Math.max(leftSums + rightSums, answer);
        }

        // 앞 값 확인
        for (int i = 1; i < N-1; i++) {
            int leftSums = sums[N-1] - honey[i] - honey[0];
            int rightSums = sums[N-1] - sums[i];
            answer = Math.max(leftSums + rightSums, answer);
        }



        // 뒷 값 확인
        for (int i = N-2; i >0; i--) {
            int rightSums = sums[N-2] - honey[i];
            int leftSums = sums[i-1];
            answer = Math.max(leftSums + rightSums, answer);
        }

        System.out.println(answer);
    }
}
