import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n,m;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		perm(m, new int[m]);
		System.out.println(sb.toString());
	}
	static void perm(int cnt,int [] arr) {
		if(cnt == 0) {
			for(int i: arr) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		for(int i = 1 ; i < n+1; i++) {
			arr[m-cnt] = i;
			perm(cnt-1,arr);
		}
	}
}
