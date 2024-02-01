import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {
	static int test_case,n,m;
	static int [][] _map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		test_case = Integer.parseInt(tokens[0]);
		for(int tc = 1; tc<test_case+1;tc++) {
			tokens = br.readLine().split(" ");
			n = Integer.parseInt(tokens[0]);
			m = Integer.parseInt(tokens[1]);
			_map = new int[n][n];
			int answer = 0 ;
			for(int i = 0 ; i < n ; i++) {
				tokens = br.readLine().split(" ");
				for(int j = 0 ; j < n ; j++) {
					_map[i][j] = Integer.parseInt(tokens[j]);
				}
			}
			int [][] _sumMap = new int[n+1][n+1];

			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					_sumMap[i+1][j+1] = _sumMap[i][j+1]+_sumMap[i+1][j]-_sumMap[i][j]+_map[i][j];
				}
			}
			
			for(int i = 1 ; i < n-m+2 ; i++ ) {
				for(int j = 1 ; j < n-m+2; j++) {
					int _sum = 0;
					_sum+= _sumMap[i+m-1][j+m-1];
					_sum-= _sumMap[i+m-1][j-1];
					_sum-= _sumMap[i-1][j+m-1];
					_sum+= _sumMap[i-1][j-1];
					if(answer < _sum) {
						answer = _sum;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
	System.out.println(sb.toString());
	}
}
