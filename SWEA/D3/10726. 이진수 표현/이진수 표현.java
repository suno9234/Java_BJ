import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(bufferedReader.readLine());
        for(int tc = 0 ; tc < test_case; tc++){
            String [] tokens = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            boolean answer = true;
            while(n > 0){
                if ((m & 1) == 0){
                    answer = false;
                    break;
                }
                n--;
                m = m >> 1;
            }
            if(answer){
                System.out.printf("#%d ON\n",tc+1);
            }else{
                System.out.printf("#%d OFF\n",tc+1);
            }
        }
    }
}
