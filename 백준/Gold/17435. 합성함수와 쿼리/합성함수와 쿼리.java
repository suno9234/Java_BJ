import java.io.*;
import java.util.*;

public class Main {
	static int m, q;
	static int[] f;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        
        m = Integer.parseInt(br.readLine());
        f = new int[m+1];
        dp = new int[20][m+1];
        tokens = br.readLine().split(" ");
        for(int i = 1 ; i < m+1 ; i++){
        	f[i] = Integer.parseInt(tokens[i-1]);
        }
        for(int i = 1 ; i < m+1 ; i++) {
        	dp[0][i] = f[i];
        }
        for(int j = 1 ; j < 20 ; j++) {
        	for(int i = 1 ; i < m+1 ; i++) {
        		dp[j][i] = dp[j-1][dp[j-1][i]];
        	}
        }
        q = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < q ; i++) {
        	tokens = br.readLine().split(" ");
        	int n = Integer.parseInt(tokens[0]);
        	int x = Integer.parseInt(tokens[1]);
        	// fn(x) 구하기
        	// f8(x) = f(f(f(f(f(f(f(f(f(x)))))))))
        	//        =f4(f4(x))
        	// n < 500000
        	// 2^ 10 = 1000 11 = 2048 12 5096 13 = 10000
        	// 14 = 20000 15 = 40000 16 = 80000 17 = 160000 18 = 320000
        	// 19 = 640000
        	// log n = 
        	// f51(3) = f2^5(f2^4(f2^1(f2^0(3))))
        	// 110011
        	//   8421
        	int idx = 0;
        	while(n > 0) {
        		if ((1 & n)> 0) {
        			x = dp[idx][x];
        		}
        		idx++;
        		n = n >> 1 ;
        	}
        	sb.append(x).append("\n");
        }
        System.out.println(sb);
        br.close();
	}
}
