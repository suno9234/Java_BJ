import java.io.*;
import java.util.Arrays;

public class Main{
	static int n,m;
	static int [][] _map;
	static int [][] _chick; 
	static int _chickCount;
	static int [] _choosed;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		_map = new int[n][n];
		_chick = new int[n*n][2];
		_choosed = new int[m];
		for(int i = 0 ; i < n ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < n ; j++) {
				_map[i][j] = Integer.parseInt(tokens[j]);
				if(_map[i][j] == 2) {
					_chick[_chickCount][0] = i;
					_chick[_chickCount][1] = j;
					_chickCount++;
				}
			}
			
		}	
		back(0,m);
		System.out.println(answer);
	}
	static void back(int start, int cnt) {
		if(cnt == 0) {
			//System.out.println();
			// 계산
			// _choosed = 남은 치킨집
			int temp = 0;
			for(int i = 0 ; i < n;i++) {
				for(int j = 0 ; j < n ; j++) {
					if(_map[i][j] == 1) {
						//System.out.println(i+" "+j);
						int _chickdist = Integer.MAX_VALUE;
						for(int k = 0 ; k < m ; k++) {
							_chickdist = (int)Math.min(_chickdist, (int)Math.abs(_chick[_choosed[k]][0]-i) + (int)Math.abs( _chick[_choosed[k]][1]-j));
						}
						//System.out.println(_chickdist);
						temp+= _chickdist;
					}
				}
			}
			if(temp < answer) {
				answer = temp;
			}
			return;
		}
		for(int i = start ; i < _chickCount;i++) {
			cnt--;
			_choosed[cnt] = i;
			back(i+1,cnt);
			cnt++;
		}
	}
	

}
