package ih;/*
16 47 -> 13

N개 꽃 같은 해에 피어서 같은 해에 짐

4 6 9 11 -> 30
1 3 5 7 8 10 12 -> 31
2 -> 28


3/11 ~ 11/30 까지 하나 이상
갸장 적게

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class _2461 {

    static class Flower implements Comparable<Flower> {
        int startMonth;
        int startDay;
        int endMonth;
        int endDay;


        public Flower(int[] flowerInfo){
            startMonth =flowerInfo[0];
            startDay =flowerInfo[1];
            endMonth =flowerInfo[2];
            endDay =flowerInfo[3];
        }

        @Override
        public int compareTo(Flower o) {
            if (this.startMonth != o.startMonth) {
                return Integer.compare(this.startMonth, o.startMonth);
            }

            if (this.startDay != o.startDay) {
                return Integer.compare(this.startDay, o.startDay);
            }
            return Integer.compare(this.endMonth, o.endMonth);
        }
    }


    static int[] days = new int[] { 0, 31, 28, 31,30,31,30,31,31,30,31,30,31 };
    static int N;

    static List<Flower> fList = new LinkedList<>();
    static PriorityQueue<Flower> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<Flower>((a,b) -> {
           if(a.endMonth !=b.endMonth){
               return -Integer.compare(a.endMonth,b.endMonth);
           }
           return -Integer.compare(a.endDay,b.endDay);
        });


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startM = Integer.parseInt(st.nextToken());
            int startD = Integer.parseInt(st.nextToken());
            int endM = Integer.parseInt(st.nextToken());
            int endD = Integer.parseInt(st.nextToken());

            Flower flower = new Flower(new int[] {startM,startD,endM,endD});
            fList.add(flower);
        }

        Collections.sort(fList);

        int answer = sol();

        System.out.println(answer);
    }

    static public int sol(){
        int answer = 0;

        int nowMonth = 3;
        int nowDay = 1;

        for (Flower fl : fList) {
            if(isCan(fl.startMonth,fl.startDay,nowMonth,nowDay)){
                pq.add(fl);
                continue;
            }

            // 연결할 수 있는 꽃이 없는 경우
            if(pq.isEmpty()) return 0;

            // 만약 이제 pq중에 하나 넣어야한다면 최대 month, 최대 day뽑아서 갱신해준 후 들어가지 못한거 들어감
            Flower flower = pq.poll();

            pq.clear();
            nowMonth = flower.endMonth;
            nowDay = flower.endDay;
            /// System.out.println(flower.endMonth+"/"+flower.endDay);
            answer++;

            // 조건을 만족
            if(nowMonth > 11) return answer;

            // 다시 현재 판단
            if(isCan(fl.startMonth,fl.startDay,nowMonth,nowDay)){
                pq.add(fl);
            }
        }

        // pq가 남았다는 것은 list중에 마지막에 넣어줬을 수 있음 남아있고 목표 달성 못할시 한번더 체크
        if(!pq.isEmpty() && nowMonth <12) {
            Flower now = pq.poll();
            //남아있는게 결국 만족해줄 경우
            if(now.endMonth > 11){
                answer++;
            }else{
                return 0;
            }
        }else{
            if(nowMonth <12) return 0;
        }

        return answer;
    }


    // 현재 최대 month,day가 꽃이 있는 month,day 보다 작으면 선택할 수 있다.
    // 만약 day랑 month까지 같으면?? 그래도 그때 펴도 되니까 13일이면 12일까지 핌
    static public boolean isCan(int startMonth, int startDay, int nowMonth, int nowDay){
        if(startMonth < nowMonth){
            return true;
        }else if(startMonth == nowMonth){
            if(startDay <= nowDay){
                return true;
            }
        }

        return false;
    }


}
