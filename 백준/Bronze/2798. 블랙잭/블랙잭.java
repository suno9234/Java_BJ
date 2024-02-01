import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int n,m,answer;
	static int[] nums,ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		nums = new int[n];
		ans  = new int[3];
		tokens = br.readLine().split(" ");
		for(int i = 0 ; i < n ; i++) {
			nums[i] = Integer.parseInt(tokens[i]);
		}
		comb(0,0);
		System.out.println(answer);
	}
	static void comb(int start , int cnt) {
		if(cnt == 3) {
			int _sum = 0;
			for(int n : ans) {
				_sum+=n;
			}
			if(_sum <= m && _sum > answer) {
				answer = _sum;
			}
			return;
		}
		for(int i = start ; i< n ; i++) {
			ans[cnt] = nums[i];
			comb(i+1,cnt+1);
		}
	}

}
