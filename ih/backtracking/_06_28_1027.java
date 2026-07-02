package ih.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1 2 3 으로만 이루어진 수열

임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 있으면

N인 좋은 수열들을 N자리의 정수로 보아 그 중 가장 작은 수를 나타내라??
길이가 N인 좋은 수열 들 중에서 가장 작은 수를 나타내는 수열만 출력

어떻게 가장  작은  수열을  만들 수 있을까?
*/

public class _06_28_1027 {

    static int N;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        sol();
    }

    public static boolean sol(){
        if(sb.length() == N) {
            System.out.println(sb.toString());
            return true;
        };

        for (int i = 1; i <= 3 ; i++) {
            char now = (char) (i+ '0');

            sb.append(now);
            if(canString(sb.toString())){
                if(sol()){
                    return true;
                }
            }
            sb.setLength(sb.length()-1);
        }
        return false;
    }

    public static boolean canString(String target){
        for (int i = 1; i <= target.length()/2 ; i++) {
            String str1 = target.substring(target.length() - i);
            String str2 = target.substring(target.length()- 2*i,target.length()-i);
            if(str2.equals(str1)) return false;
        }
        return true;
    }
}
