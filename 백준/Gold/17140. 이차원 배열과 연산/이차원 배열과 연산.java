import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] tokens = br.readLine().split(" ");
		int r,c,k ;
		int [][] A = new int[100][100];
		r = Integer.parseInt(tokens[0]);
		c = Integer.parseInt(tokens[1]);
		k = Integer.parseInt(tokens[2]);
		for(int i = 0 ; i < 3 ; i++) {
			tokens = br.readLine().split(" ");
			for(int j = 0 ; j < 3 ; j++) {
				A[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		
		int time = 0;
		int row = 3;
		int col = 3;
		while(time < 101) {
			if(A[r-1][c-1] == k) {
				System.out.println(time);
				return;
			}
			if(row >= col) {
				// R 연산
				// 모든 행에 대하여
				// 각 숫자별 등장 횟수 세기
				for(int i = 0 ; i < row; i++) {
					// 모든 행에 대하여
					int [] _info = new int[101]; 
					for(int j = 0 ; j < col; j++) {
						if(A[i][j] > 0) {
							_info[A[i][j]]+=1; // 3=> info[3] += 1
						}
					}
					//센 것을 바탕으로 새 배열 만들어서 넣어 주기
					ArrayList<Tup> ls = new ArrayList<>();
					for(int j = 1 ; j < 101; j++) {
						if(_info[j]!= 0){
							ls.add(new Tup(j,_info[j]));
						}
					}
					ls.sort(new Comparator<Tup>() {
						@Override
						public int compare(Tup t1, Tup t2) {
							if(t1.count == t2.count) {
								return t1.num - t2.num;
							}
							// TODO Auto-generated method stub
							return t1.count - t2.count;
						}
					});
					for(int j = 0 ; j < ls.size() && j <100;j++) {
						Tup now = ls.get(j);
						A[i][j*2] = now.num;
						A[i][j*2+1] = now.count; 
						// 2 * size -1
					}
					for(int j = ls.size()*2; j < 100; j++) {
						A[i][j] = 0;
					}
					col = Math.max(col, ls.size()*2);
					if(col > 100) {
						col = 100;
					}
				}
				
			}else {
				// C 연산
				// 각 숫자별 등장 횟수 세기
				for(int i = 0 ; i < col; i++) {
					// 모든 행에 대하여
					int [] _info = new int[101]; 
					for(int j = 0 ; j < row; j++) {
						if(A[j][i] > 0) {
							_info[A[j][i]]+=1; // 3=> info[3] += 1
						}
					}
					//센 것을 바탕으로 새 배열 만들어서 넣어 주기
					ArrayList<Tup> ls = new ArrayList<>();
					for(int j = 1 ; j < 101; j++) {
						if(_info[j]!= 0){
							ls.add(new Tup(j,_info[j]));
						}
					}
					ls.sort(new Comparator<Tup>() {
						@Override
						public int compare(Tup t1, Tup t2) {
							if(t1.count == t2.count) {
								return t1.num - t2.num;
							}
							// TODO Auto-generated method stub
							return t1.count - t2.count;
						}
					});
					for(int j = 0 ; j < ls.size() && j <100;j++) {
						Tup now = ls.get(j);
						A[j*2][i] = now.num;
						A[j*2+1][i] = now.count; 
						// 2 * size -1
					}
					for(int j = ls.size()*2; j < 100; j++) {
						A[j][i] = 0;
					}
					row = Math.max(row, ls.size()*2);
					if(row > 100) {
						row = 100;
					}
				}
				
			}
//			for(int i = 0 ; i < 4 ; i++) {
//				for(int j = 0; j < 4 ; j++) {
//					System.out.printf("%d ", A[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			time++;
		}
		System.out.println(-1);
	}
	static class Tup{
		int num;
		int count;
		public Tup(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}
}
