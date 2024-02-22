import java.io.*;
import java.util.*;

public class Main{
	static int n,answer;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static int [][] _map;
    static int [][] country;
	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        answer = Integer.MAX_VALUE;
        _map = new int[n][n];
        for(int i = 0 ; i < n ; i++) {
        	tokens = br.readLine().split(" ");
        	for(int j = 0 ; j < n ; j++) {
        		_map[i][j] = Integer.parseInt(tokens[j]);
        	}
        }
        
        for(int i = 1 ; i< n-1 ; i++) {
        	for(int j = 1 ; j < n-1 ; j++) {
        		for(int k =1 ; k <n; k++) {
        			for(int l = 1 ; l < n ; l++) {
        				// i = x j = y k = d1 l = d2
        				if(j-k>=0 && i+k+l < n && j+l < n) {
        					country = new int[n][n];
        					for(int m = 1 ; m < n ; m++) {
        						if(i-m>=0) {
        							country[i-m][j] = 1;
        						}else {
        							break;
        						}
        					}
        					for(int m = 1 ; m < n ; m++) {
        						if(j+l+m < n) {
        							country[i+l][j+l+m] = 2;
        						}else {
        							break;
        						}
        					}
        					for(int m = 1 ; m < n ; m++) {
        						if(j-k-m >=0) {
        							country[i+k][j-k-m] = 3;
        						}else {
        							break;
        						}
        					}
        					for(int m = 1 ; m < n ; m++) {
        						if(i+k+l+m < n) {
        							country[i+k+l+m][j+l-k] = 4;
        						}
        					}
        					for(int m = 0 ; m <=k ; m++) {
        						country[i+m][j-m] = 5;
        						country[i+l+m][j+l-m] = 5;
        					}
        					for(int m = 0 ; m <= l ; m++) {
        						country[i+m][j+m] = 5;
        						country[i+k+m][j-k+m] = 5;
        					}
        					dfs(0,0,1);
        					dfs(0,n-1,2);
        					dfs(n-1,0,3);
        					dfs(n-1,n-1,4);
        					List<Integer> results = new ArrayList<>();
        					results.add(addPeople(0,0,1));
        					results.add(addPeople(0,n-1,2));
        					results.add(addPeople(n-1,0,3));
        					results.add(addPeople(n-1,n-1,4));
        					results.add(addPeople(i,j,5));
        					Collections.sort(results);
        					answer = Math.min(answer, results.get(4)-results.get(0));
        				}
        			}
        		}
        	}
        }
        
        
        System.out.println(answer);
        br.close();
	}
	static int addPeople(int x, int y, int num) {
		int answer = _map[x][y];
		country[x][y] = -1;
		for(int i = 0 ; i < 4 ; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(num == 5) {
				if(nx >=0 && ny >=0 && nx < n && ny < n && (country[nx][ny] == num || country[nx][ny] == 0)) {
					answer+=addPeople(nx,ny,num);
				}
			}
			else if(nx >=0 && ny >=0 && nx < n && ny < n && country[nx][ny] == num) {
				answer+=addPeople(nx,ny,num);
			}
		}
		return answer;
	}
	
	static void dfs(int x , int y, int num) {
		country[x][y] = num;
		for(int i = 0 ; i < 4 ; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(nx >=0 && ny >=0 && nx < n && ny < n && country[nx][ny] == 0) {
				dfs(nx,ny,num);
			}
		}
	}
}
