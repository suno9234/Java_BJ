import java.util.*;
import java.io.*;

public class Solution{

	static int answer, n, m, k , time;
	static ArrayDeque<int[]> cells;
	static int [][] _map;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_5653.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine())+1;
		for(int tc = 1; tc < test_case; tc++) {
			answer = 0;
			String [] tokens = br.readLine().split(" ");
			n = Integer.parseInt(tokens[0]);
			m = Integer.parseInt(tokens[1]);
			k = Integer.parseInt(tokens[2]);
			_map = new int[1000+n][1000+m];
			cells = new ArrayDeque<>();
			for(int i = 0 ; i < n ; i++) {
				tokens = br.readLine().split(" ");
				for(int j = 0 ; j < m ; j++) {
					_map[500+i][500+j] = Integer.parseInt(tokens[j]);
					if(_map[500+i][500+j]>0) cells.add(new int[] {500+i,500+j,_map[500+i][500+j],0});
				}
			}
			time = 0;
			PriorityQueue<int[]> activeCells = new PriorityQueue<>(new Comparator<int[]>() {
				public int compare(int[] a , int [] b) {
					return b[2]-a[2];
				}
			});
			HashSet<Integer> hs = new HashSet<>();
			while(time <= k) {
				
				// nextCells => 노란세포
				// activeCells => 파란세포
				ArrayDeque<int[]> nextCells = new ArrayDeque<>();
				
				while(!activeCells.isEmpty()) {
					int [] cell = activeCells.poll();
					int x = cell[0];
					int y = cell[1];
					int lifePower = cell[2];
					for(int i = 0 ; i < 4 ; i++) {
						int nx = x+dx[i];
						int ny = y+dy[i];
						if(nx>=0 && ny >=0 && nx<1000+n && ny < 1000+m && _map[nx][ny] == 0){
							_map[nx][ny] = lifePower;
							nextCells.add(new int[] {nx,ny,lifePower,time});
						}
					}
					_map[x][y] = -1;
				}
				
				while(!cells.isEmpty()) {
					// cell  = x,y,lifePower,installTime
					int [] cell = cells.poll();
					int x = cell[0];
					int y = cell[1];
					int lifePower = cell[2];
					int installTime = cell[3];
					if(time - installTime == lifePower) {
						// 설치한지 lifePower만큼 지난 시간이면
						activeCells.add(cell);
					}else {
						nextCells.add(cell);
					}
				}
				
				for(int [] cell : nextCells) {
					int x = cell[0];
					int y = cell[1];
					int lifePower = cell[2];
					int installTime = cell[3];
					if(installTime+lifePower*2 > k) {
						hs.add(x*(1000+m)+y);
					}
				}
				cells = nextCells;
				time++;
			}
			answer = hs.size();
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

}
