import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int [] arr = new int [n];
		String [] tokens = br.readLine().split(" ");
		for(int i = 0 ; i < n ; i++) {
			arr[i] = Integer.parseInt(tokens[i]);
		}
		int [] answer = new int [n];
		for(int i = 0 ; i < n ; i++) {
			int temp = 1;
			for(int j = 0 ; j < i ; j++) {
				if(arr[j] < arr[i]) {
					temp = Math.max(temp, answer[j]+1);
				}
			}
			answer[i] = temp;
		}
		int _max = 0;
		for(int i = 0 ; i < n ; i++) {
			_max = Math.max(_max, answer[i]);
		}
		System.out.println(_max);
	}

}
