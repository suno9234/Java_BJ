
import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int [] nums;
	static int [] opers;
	static boolean [] _visited;
	static int [] finalOpers ;
	static int answerMax = -Integer.MAX_VALUE;
	static int answerMin = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String [] tokens = br.readLine().split(" ");
		nums = new int[n];
		opers = new int[n-1];
		finalOpers = new int[n-1];
		_visited = new boolean[n-1];
		for(int i = 0 ; i < n ; i++) {
			nums[i] = Integer.parseInt(tokens[i]);
		}
		tokens = br.readLine().split(" ");
		int plus = Integer.parseInt(tokens[0]);
		int minus = Integer.parseInt(tokens[1]);
		int mul = Integer.parseInt(tokens[2]);
		int div = Integer.parseInt(tokens[3]);
		for(int i = 0 ; i < plus;i++) {
			opers[i] = 1;
		}
		for(int i = plus;i<plus+minus;i++) {
			opers[i] = 2;
		}
		for(int i = plus+minus; i<plus+minus+mul;i++) {
			opers[i] = 3;
		}
		for(int i = plus+minus+mul; i<plus+minus+mul+div;i++) {
			opers[i] = 4;
		}
		back(n-1);
		System.out.println(answerMax);
		System.out.println(answerMin);
		
	}
	static void back(int cnt) {
		if(cnt == 0) {
			//System.out.println(Arrays.toString(finalOpers));
			int temp = nums[0] ;
			for(int i = 0 ; i < n-1;i++) {
				if      (finalOpers[i] == 1) {
					temp += nums[i+1];
				}else if(finalOpers[i] == 2) {
					temp -= nums[i+1];
				}else if(finalOpers[i] == 3) {
					temp *= nums[i+1];
				}else if(finalOpers[i] == 4) {
					if(temp < 0) {
						temp = -temp;
						temp /= nums[i+1];
						temp = -temp;
					}else {
						temp /= nums[i+1];
					}
				}
			}
			if (temp > answerMax) {
				answerMax = temp;
			}
			if(temp < answerMin) {
				answerMin = temp;
			}
			return;
		}
		for(int i = 0 ; i < n-1;i++) {
			if(!_visited[i]) {
				_visited[i] = true;
				finalOpers[cnt-1] = opers[i];
				cnt--;
				back(cnt);
				cnt++;
				_visited[i] = false;
			}
		}
	}
}


