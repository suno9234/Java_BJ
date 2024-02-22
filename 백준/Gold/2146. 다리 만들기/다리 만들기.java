import java.io.*;
import java.util.*;

public class Main{
	static int n , answer;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static int[][] _map;
	static List<int[]> []islands;
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        
        n = Integer.parseInt(br.readLine());
        _map = new int[n][n];
        islands = new List[10000];
        answer = Integer.MAX_VALUE;
        for(int i = 0 ; i < 10000; i++) {
        	islands[i] = new ArrayList<>();
        }
        for(int i = 0 ; i < n ; i++) {
        	tokens = br.readLine().split(" ");
        	for(int j = 0 ; j < n ; j++) {
        		_map[i][j] = Integer.parseInt(tokens[j]);
        	}
        }
        
        
        int islandsNum = 2;
        for(int i = 0 ; i < n ; i++) {
        	for(int j = 0 ; j < n; j++) {
        		if(_map[i][j] == 1) {
        			dfs(i, j, islandsNum++);
        		}
        	}
        }
        
        for(int i = 0 ; i < n ; i++) {
        	for(int j = 0 ; j < n ; j++) {
        		if(_map[i][j] > 0 ) {
        			check(i,j,_map[i][j]);
        		}
        	}
        }
        
        System.out.println(answer);
        br.close();
	}
    
    static void check(int x , int y , int num) {
    	boolean [][] v = new boolean[n][n];
    	ArrayDeque<int[]> queue = new ArrayDeque<>();
    	queue.add(new int[] {x,y,0});
    	v[x][y] = true;
    	while(!queue.isEmpty()) {
    		int [] now = queue.poll();
    		x = now[0];
    		y = now[1];
    		int dist = now[2];
    		if(_map[x][y]>0 && _map[x][y]!= num) {
    			answer = Math.min(answer, dist-1);
    			return;
    		}
    		for(int i = 0 ; i < 4 ; i++) {
        		int nx = x+dx[i];
        		int ny = y+dy[i];
        		if(nx >=0 && ny >=0 && nx <n && ny < n && !v[nx][ny] && dist < answer) {
        			v[nx][ny] = true;
        			queue.add(new int[] {nx,ny,dist+1});
        		}
        	}
    	}
    }
    
    static void dfs(int x , int y, int num) {
    	_map[x][y] = num;
    	for(int i = 0 ; i < 4 ; i++) {
    		int nx = x+dx[i];
    		int ny = y+dy[i];
    		if(nx >=0 && ny >=0 && nx <n && ny < n && _map[nx][ny] == 1) {
    			dfs(nx,ny,num);
    		}
    	}
    }
}
