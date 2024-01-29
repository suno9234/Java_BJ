import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
	static int n,m;
	static int [][] A;
	static ArrayList<Integer> cloud = new ArrayList<>();
	static int [] dx = {0,-1,-1,-1,0,1,1,1};
	static int [] dy = {-1,-1,0,1,1,1,0,-1};
	static int [] xDx = {1,1,-1,-1};
	static int [] xDy = {1,-1,1,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		A = new int[n][n];
		cloud.add((n-1)*n);
		cloud.add((n-1)*n+1);
		cloud.add((n-2)*n);
		cloud.add((n-2)*n+1);
		for(int i = 0 ; i < n ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < n ; j++) {
				A[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		// 100*2500*
		for(int magic = 0 ; magic < m ; magic++) {
			tokens = br.readLine().split(" ");
			int d = Integer.parseInt(tokens[0])-1;
			int s = Integer.parseInt(tokens[1]);
			ArrayList <Integer> newCloud = new ArrayList<>();
			
			for(Integer cl : cloud) {
				newCloud.add(((cl/n+s*dx[d]+n*100)%n)*n +(cl%n+s*dy[d]+n*100)%n);
			}
			for(Integer cl : newCloud) {
				A[cl/n][cl%n] +=1;
			}
			for(Integer cl : newCloud) {
				int temp = 0;
				for(int i = 0 ; i< 4 ; i++) {
					int nx = cl/n+xDx[i];
					int ny = cl%n+xDy[i];
					if(nx >=0 && ny >= 0 && nx < n && ny <n && A[nx][ny]>0) {
						temp++;
					}
				}
				A[cl/n][cl%n] += temp;
			}
			ArrayList <Integer> returnCloud = new ArrayList<>();
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					if(A[i][j] >= 2 && !newCloud.contains(i*n+j) && ! returnCloud.contains(i*n+j) ) {
						A[i][j] -= 2;
						returnCloud.add(i*n+j);
						
					}
				}
			}
			cloud = returnCloud;
		}
		int answer = 0;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				answer+= A[i][j];
			}
		}
		System.out.println(answer);
		
	}

}
