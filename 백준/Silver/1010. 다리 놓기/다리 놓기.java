import java.io.*;

public class Main{
	static int [][] dp ;
    public static void main(String [] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int tc = 0 ; tc < t ; tc++){
            String [] tokens = br.readLine().split(" ");
            int a = Integer.parseInt(tokens[0]);
            int b = Integer.parseInt(tokens[1]);
            dp = new int[b+1][b+1];
            sb.append(comb(b,b-a)).append("\n");
        }
        System.out.println(sb.toString());
    }
    static int comb(int a , int b) {
    	if(dp[a][b] >0) {
    		return dp[a][b];
    	}
    	if(a == b || b == 0) {
    		return dp[a][b] = 1;
    	}
    	return dp[a][b] = comb(a-1,b-1)+comb(a-1,b);
    }
}