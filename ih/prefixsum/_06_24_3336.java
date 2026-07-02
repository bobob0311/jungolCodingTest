package ih.prefixsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
20:28 ->
다각형의 각 변이 x축과 y축에 평행한 다각형-> 직각 다각형
=> 위로 오른쪽으로 혹은 왼쪽으로 혹은 아래 쪽으로 밖에 움직이지 않는다.
x축과 y축에 평행한 선분으로 꼭짓점을 제외하고는 만나지않는다.

수평선과 다각형의 수직선분이 교차하는 수
수직선과 다각형의 수평선분이 교차하는 수

옮길 수 있다.
-> 가장 많이 교차하는 수평선 H와 수직선 V의 위치를 찾아 그때의 교차 횟수를 구해라
겹치는건 안된다

-> 주어지는 꼭짓점은 시계 방향이다?
h,v 기 가장
*/

public class _06_24_3336 {
    public static int N;
    public static int[] sumX, sumY;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        sumX = new int[2000002];
        sumY = new int[2000002];

        st = new StringTokenizer(br.readLine());



        int firstX = (Integer.parseInt(st.nextToken()) + 500000) *2;
        int firstY = (Integer.parseInt(st.nextToken()) + 500000) *2;

        int prevX = firstX;
        int prevY = firstY;

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nowX = (Integer.parseInt(st.nextToken()) + 500000) * 2;
            int nowY = (Integer.parseInt(st.nextToken()) + 500000) * 2;

            if(nowX == prevX){
                if(prevY < nowY){
                    sumY[prevY]++;
                    sumY[nowY+1]--;
                }else{
                    sumY[nowY]++;
                    sumY[prevY+1]--;
                }
            }else{
                if(prevX < nowX){
                    sumX[prevX]++;
                    sumX[nowX+1]--;
                }else{
                    sumX[nowX]++;
                    sumX[prevX+1]--;
                }
            }
            prevX = nowX;
            prevY = nowY;
        }

        if(firstX == prevX){
            if(prevY < firstY){
                sumY[prevY]++;
                sumY[firstY+1]--;
            }else{
                sumY[firstY]++;
                sumY[prevY+1]--;
            }
        }else{
            if(prevX < firstX){
                sumX[prevX]++;
                sumX[firstX+1]--;
            }else{
                sumX[firstX]++;
                sumX[prevX+1]--;
            }
        }

        int answer = -1;

        for (int i = 1; i < 2000000; i++) {
            sumX[i] = sumX[i-1] + sumX[i];
            sumY[i] = sumY[i-1] + sumY[i];
        }

        for (int i = 1; i < 2000001; i+=2) {
            answer = Math.max(answer,Math.max(sumX[i],sumY[i] ));
        }
        System.out.println(answer);
    }
}
