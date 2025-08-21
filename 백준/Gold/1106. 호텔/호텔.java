import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int c, n;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        c = Integer.parseInt(tokens[0]);
        n = Integer.parseInt(tokens[1]);
        // 최소 c명을 초대하기 위한 돈의 최소값
        dp = new int[c + 1]; // dp[k] = k명을 늘리기 위해 투자해야하는 돈의 최소값
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            tokens = br.readLine().split(" ");
            int price = Integer.parseInt(tokens[0]);
            int customer = Integer.parseInt(tokens[1]);
            for (int j = 1; j <= Math.min(customer,c); j++) {
                dp[j] = Math.min(dp[j], price);
            }
            for (int j = customer; j < c + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j - customer] + price);
            }
        }
        System.out.println(dp[c]);
    }
}
