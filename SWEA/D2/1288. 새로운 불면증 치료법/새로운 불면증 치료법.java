import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(bufferedReader.readLine());
        for(int tc = 0 ; tc < test_case; tc++){
            long n = Long.parseLong(bufferedReader.readLine());
            int answer = 0;
            int check = 0;
            while( check != ((1 << 10) -1)){
                answer+=1;
                long nCopy = n*answer;
                while(nCopy > 0){
                    check = check | (1<<(nCopy%10));
                    nCopy /= 10;
                }
            }
            System.out.printf("#%d %d\n",tc+1,answer*n);
        }
    }

}