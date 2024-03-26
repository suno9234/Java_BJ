import java.io.*;
import java.util.*;

public class Main{
	static int n;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
    public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens;
        StringBuilder sb = new StringBuilder();
        int cnt = 1;
        while(true) {
        	n = Integer.parseInt(br.readLine());
        	if(n == 0) {
        		break;
        	}
        	int [][] _map = new int[n][n];
        	int [][] minDist = new int[n][n];
        	boolean [][] v = new boolean[n][n];
        	for(int i = 0 ; i < n ; i++) {
        		tokens = br.readLine().split(" ");
        		for(int j = 0 ; j < n ; j++) {
        			_map[i][j] = Integer.parseInt(tokens[j]);
        			minDist[i][j] = Integer.MAX_VALUE;
        		}
        	}
        	
        	PriorityQueue<int []> pq = new PriorityQueue<>((o1,o2)->Integer.compare(o1[2], o2[2]));
        	pq.add(new int[] {0,0,_map[0][0]});
        	while(!pq.isEmpty()) {
        		int [] now = pq.poll();
        		int x = now[0];
        		int y = now[1];
        		int dist = now[2];
        		if(v[x][y]) continue;
        		v[x][y] = true;
        		minDist[x][y] = dist;
        		if(x == n-1 && y == n-1) {
        			break;
        		}
        		for(int i = 0 ; i < 4 ; i++) {
        			int nx = x + dx[i];
        			int ny = y + dy[i];
        			if(nx>=0 && ny >=0 && nx < n && ny < n && !v[nx][ny]) {
        				if(minDist[nx][ny] > dist+_map[nx][ny]) {
        					minDist[nx][ny] = dist+_map[nx][ny];
        					pq.add(new int[] {nx,ny,minDist[nx][ny]});
        				}
        			}
        		}
        	}
        	sb.append("Problem ").append(cnt).append(": ").append(minDist[n-1][n-1]).append("\n");
        	cnt++;
        }
         
        System.out.println(sb);
        br.close();
	}
}
