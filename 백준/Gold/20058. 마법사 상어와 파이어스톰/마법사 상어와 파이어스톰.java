import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	static int N, Q ,len;
	static int [][] board ;
	static boolean [][] _visited;
	static int [][] remover;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,-1,1};
	static int answer = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		Q = Integer.parseInt(tokens[1]);
		len = (int)Math.pow(2, N);
		board = new int[len][len];
		_visited = new boolean[len][len];
		for(int i = 0 ; i < len ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < len; j++) {
				board[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		tokens = br.readLine().split(" ");
		for(String t : tokens) {
			remover = new int[len][len];
			int l = Integer.parseInt(t);
			int n = (int)Math.pow(2, l);
			for(int i = 0 ; i < len ; i+=n) {
				for(int j = 0 ; j < len; j+=n) {
					turn(i,j,n);
				}
			}
			for(int i = 0 ; i < len; i++) {
				for(int j = 0 ; j < len;j++) {
					int flag = 0;
					for(int k = 0 ; k < 4 ; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(nx>=0 && ny >= 0 && nx <len && ny < len && board[nx][ny]>0) {
							flag++;
						}
					}
					if(flag < 3) {
						remover[i][j] = -1;
					}
				}
			}
			for(int i = 0 ; i < len; i++) {
				for(int j = 0 ; j < len;j++) {
					board[i][j] = Math.max(0, board[i][j]+ remover[i][j]);
				}
			}
		}
		int total = 0;
		for(int i = 0 ; i < len ; i++) {
			for(int j = 0; j < len; j++) {
				total+= board[i][j];
				if(!_visited[i][j] && board[i][j] > 0) {
					_visited[i][j] = true;
					count(i,j);
				}
			}
		}
		System.out.println(total);
		System.out.println(answer);
		
	}
	static void turn(int x, int y , int n) {
		int [][] temp = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				temp[i][j] = board[x+n-j-1][y+i];
			}
		}
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				board[x+i][y+j] = temp[i][j];
			}
		}
	}
	static void count(int x, int y) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(x*len+y);
		int temp = 0;
		while(!queue.isEmpty()) {
			temp++;
			int now = queue.poll();
			int nowX = now/len;
			int nowY = now%len;
			for(int i = 0 ; i < 4 ; i++) {
				int nx = nowX+dx[i];
				int ny = nowY+dy[i]; 
				if(nx>=0 && ny >=0 && nx<len && ny <len && !_visited[nx][ny] &&board[nx][ny]> 0) {
					_visited[nx][ny] = true;
					queue.add(nx*len+ny);
				}
			}
		}
		answer = Math.max(answer, temp);
	}
}
