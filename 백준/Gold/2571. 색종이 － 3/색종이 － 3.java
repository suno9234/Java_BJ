import java.io.*;
import java.util.*;

public class Main {

	static int n ;
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		ArrayList<Integer> coorX = new ArrayList<>();
		ArrayList<Integer> coorY = new ArrayList<>();
		int [][] _map = new int[101][101];
		for(int i = 0 ; i < n ; i++) {
			String [] tokens = br.readLine().split(" ");
			int l = Integer.parseInt(tokens[0]);
			int h = Integer.parseInt(tokens[1]);
			if(!coorX.contains(l)) coorX.add(l);
			if(!coorX.contains(l+10))coorX.add(l+10);
			if(!coorY.contains(h))coorY.add(h);
			if(!coorY.contains(h+10))coorY.add(h+10);
			for(int j = 0 ; j < 10; j++) {
				for(int k = 0 ; k < 10 ; k++) {
					_map[l+j][h+k] = 1;
				}
			}
		}
		Collections.sort(coorX);
		Collections.sort(coorY);
		// 종이 한 변의 길이 = 10
		int answer = 0;
		for(int i = 0 ; i < coorX.size(); i++) {
			for(int j = i+1 ; j < coorX.size() ; j++) {
				int x1 = coorX.get(i);
				int x2 = coorX.get(j);
				for(int k = 0 ; k < coorY.size() ; k++) {
					for(int l = k+1 ; l < coorY.size() ; l++) {
						int y1 = coorY.get(k);
						int y2 = coorY.get(l);
						//System.out.printf("%d %d %d %d\n",x1,y1,x2,y2);
						int flag = 0;
						for(int t= x1; t <x2; t++) {
							if(flag == 1) {
								break;
							}
							for(int u = y1; u < y2 ; u++) {
								if(_map[t][u] == 0) {
									//System.out.printf("%d %d\n",t,u);
									flag = 1;
									break;
								}
							}
						}
						if(flag == 0) {
							answer = Math.max(answer, (x2-x1)*(y2-y1));
						}
						
					}
				}
				
				
				
			}
		}
		System.out.println(answer);
		
	}

}
