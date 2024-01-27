
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for(int tc = 0 ; tc < test_case; tc++){
            String [] tokens = bufferedReader.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            int m = Integer.parseInt(tokens[1]);
            boolean answer = true;
            int p = (1 << n) -1;
            if (p == (p & m)){
                stringBuilder.append("#").append(tc+1).append(" ON\n");
            }else{
                stringBuilder.append("#").append(tc+1).append(" OFF\n");
            }
        }
        System.out.println(stringBuilder.toString());
    }
}
