import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n,m;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens ;
		n = Integer.parseInt(br.readLine());
		int [] switches = new int[n+1];
		tokens = br.readLine().split(" ");
		for(int i = 0 ; i < n; i++) {
			switches[i+1] = Integer.parseInt(tokens[i]);
		}
		m = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < m ; i++) {
			tokens = br.readLine().split(" ");
			int gen = Integer.parseInt(tokens[0]);
			int num = Integer.parseInt(tokens[1]);
			if(gen == 1) {
				int idx = 1;
				while(num*idx < n+1) {
					switches[num*idx] ^= 1;
					idx++;
				}
			}else {
				int idx = 1;
				switches[num]^=1;
				while(true) {
					if(num-idx < 1 || num+idx > n) {
						break;
					}
					if(switches[num-idx] == switches[num+idx]) {
						switches[num-idx]^=1;
						switches[num+idx]^=1;
					}else {
						break;
					}
					idx++;
				}
			}
		}
		for(int i = 0 ; i < n;i++) {
			if(i % 20 == 0 && i>0) {
				sb.append("\n");
			}
			sb.append(switches[i+1]).append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
