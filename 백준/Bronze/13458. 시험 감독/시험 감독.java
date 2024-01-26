import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [] arr = new int[n];
		String [] tokens = br.readLine().split(" ");
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(tokens[i]);
		}
		tokens = br.readLine().split(" ");
		int b, s;
		b = Integer.parseInt(tokens[0]);
		s = Integer.parseInt(tokens[1]);
		BigInteger answer = new BigInteger("0");
		for(int i = 0 ; i < n ; i++) {
			
			arr[i]-= b;
			answer =answer.add(new BigInteger("1"));
			if(arr[i] > 0) {
				answer = answer.add(new BigInteger(Integer.toString(arr[i]/s)));
			}
			if(arr[i]%s > 0) {
				answer = answer.add(new BigInteger("1"));
			}
		}
		System.out.println(answer.toString());
		
	}

}
