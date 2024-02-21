import java.util.*;
import java.io.*;

public class Solution{

	static int answer,n,la,lb;
	static int [] da;
	static int [] db;
	static int [][] _map;
	static List<int[]> people;
	static int[][] stair;
	static boolean [] v;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test_case = Integer.parseInt(br.readLine())+1;
		for(int tc = 1; tc < test_case; tc++) {
			answer = Integer.MAX_VALUE;
			String [] tokens = br.readLine().split(" ");
			n = Integer.parseInt(tokens[0]);
			_map = new int[n][n];
			people= new ArrayList<>();
			stair = new int[2][3];
			int idx = 0;
			for(int i = 0; i < n ; i++) {
				tokens = br.readLine().split(" ");
				for(int j = 0 ; j < n ; j++) {
					_map[i][j] = Integer.parseInt(tokens[j]);
					if(_map[i][j] == 1) {
						people.add(new int[] {i,j});
					}else if(_map[i][j]>1) {
						stair[idx][0] = i;
						stair[idx][1] = j;
						stair[idx++][2] = _map[i][j];
					}
				}
			}
			v = new boolean[people.size()];
			da = new int[people.size()];
			db = new int[people.size()];
			la = stair[0][2];
			lb = stair[1][2];
			for(int i = 0 ; i < people.size();i++) {
				int [] now = people.get(i);
				da[i] = Math.abs(now[0]-stair[0][0])+Math.abs(now[1]-stair[0][1]);
				db[i] = Math.abs(now[0]-stair[1][0])+Math.abs(now[1]-stair[1][1]);
			}
			subs(0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void calc() {
		int time = 0;
		ArrayDeque<Integer> aP = new ArrayDeque<>();
		ArrayDeque<Integer> bP = new ArrayDeque<>();
		PriorityQueue<Integer> pqA = new PriorityQueue<>();
		PriorityQueue<Integer> pqB = new PriorityQueue<>();
		
		for(int i = 0 ; i < people.size() ; i++) {
			if(v[i]) pqA.add(da[i]);
			else pqB.add(db[i]);
		}
		while(true) {
			while(!aP.isEmpty()) {
				int now = aP.peek();
				if(time-now > la) {
					aP.poll();
				}else {
					break;
				}
			}
			while(!bP.isEmpty()) {
				int now = bP.peek();
				if(time-now > lb) {
					bP.poll();
				}else {
					break;
				}
			}
			while(aP.size()<3) {
				if(!pqA.isEmpty() && pqA.peek() <= time) {
					int now = pqA.poll();
					if(now < time) aP.add(time-1);
					else aP.add(time);
				}else {
					break;
				}
			}
			while(bP.size()<3) {
				if(!pqB.isEmpty() && pqB.peek() <= time) {
					int now = pqB.poll();
					if(now < time) bP.add(time-1);
					else bP.add(time);
				}else {
					break;
				}
			}
			
			
			if(aP.isEmpty()&& bP.isEmpty()&& pqA.isEmpty() && pqB.isEmpty()) {
				break;
			}
			time++;
		}
		answer = Math.min(answer, time);
	}
	static void subs(int cnt) {
		calc();
		if(cnt == people.size()) {
			return;
		}
		v[cnt] = true;
		subs(cnt+1);
		v[cnt] = false;
		subs(cnt+1);
	}

}
