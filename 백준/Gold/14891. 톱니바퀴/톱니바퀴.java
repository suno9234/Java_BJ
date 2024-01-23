
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] wheels = new String[4];
		int [] head = new int[4];
		for(int i = 0 ; i < 4 ; i++) {
			wheels[i] = br.readLine();
		}
		int n = Integer.parseInt(br.readLine());
		for(int i = 0 ; i < n ; i++) {
			String [] tokens = br.readLine().split(" ");
			int idx = Integer.parseInt(tokens[0])-1;
			int dir = Integer.parseInt(tokens[1]);
			int [] nextHead = new int[4];
			int dirCopy = dir;
			for(int j = idx-1; j>=0;j--) {
				if(wheels[j].charAt((head[j]+2)%8) != wheels[j+1].charAt((head[j+1]+6)%8)){
					if(dirCopy == 1) {
						nextHead[j] = (head[j]+1)%8;
						dirCopy = -1;
					}else {
						nextHead[j] = (head[j]+7)%8;
						dirCopy = 1;
					}
				}else {
					for(int k = j ; k>=0; k--) {
						nextHead[k] = head[k];
					}
					break;
				}
			}
			
			dirCopy = dir;
			for(int j = idx+1; j<4;j++) {
				if(wheels[j-1].charAt((head[j-1]+2)%8) != wheels[j].charAt((head[j]+6)%8)){
					if(dirCopy == 1) {
						nextHead[j] = (head[j]+1)%8;
						dirCopy = -1;
					}else {
						nextHead[j] = (head[j]+7)%8;
						dirCopy = 1;
					}
				}else {
					for(int k = j ; k<4; k++) {
						nextHead[k] = head[k];
					}
					break;
				}
			}
			if(dir == 1) {
				nextHead[idx] = (head[idx]+7)%8;
			}else {
				nextHead[idx] = (head[idx]+1)%8;
			}
			head = nextHead;
		}
		int answer = 0;
		//System.out.println(Arrays.toString(head));
		for(int i = 0 ; i < 4 ; i++) {
			if(wheels[i].charAt(head[i]) == '1') {
				answer+= (int)Math.pow(2, i);
			}
		}
		System.out.println(answer);
	}

}
