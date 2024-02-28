import java.util.*;
import java.io.*;

public class Solution{

	static int answer;
	static int n;
	static int [][] _map;
	static ArrayList<int[]> cores ;
	static int [] dx = {1,-1,0,0};
	static int [] dy = {0,0,1,-1};
	static int coreCount;
	static int maxCount;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String [] tokens;
		int test_case = Integer.parseInt(br.readLine())+1;
		for(int tc = 1; tc < test_case; tc++) {
			answer = Integer.MAX_VALUE;
			n = Integer.parseInt(br.readLine());
			_map = new int[n][n];
			coreCount = 0;
			maxCount = -1;
			cores = new ArrayList<>();
			for(int i = 0 ; i < n; i++) {
				tokens = br.readLine().split(" ");
				for(int j = 0 ; j <n ; j++) {
					_map[i][j] = Integer.parseInt(tokens[j]);
					if(_map[i][j] == 1 && i > 0 && j > 0 && i <n-1 && j < n-1) {
						cores.add(new int[] {i,j});
						coreCount++;
					}
				}
			}
			perm(0,0,0);
			
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void perm(int cnt, int _sum , int on) {
		if(on > maxCount) {
			maxCount = on;
			answer = _sum;
		}else if(on == maxCount) {
			if(_sum < answer) {
				answer = _sum;
			}
		}
		if(cnt == coreCount || coreCount-cnt+on < maxCount) {
			return;
		}
		int [] now = cores.get(cnt);
		
		for(int i = 0 ; i < 4; i++) {
			// 각 방향에 대하여
			int nx = now[0]+dx[i];
			int ny = now[1]+dy[i];
			ArrayList<int[]> draw = new ArrayList<>();
			int flag = 0;
			while(nx >= 0 && ny >=0 && nx < n && ny < n) {
				// 해당 방향의 범위 끝까지 검사
				if(_map[nx][ny]!=0) {
					// nx,ny에 뭐가 있으면 flag = 1로 바꾸고 return
					flag = 1;
					break;
				}
				// 뭐가 없을 시 전선을 설치하고, 전선을 관리하는 배열 draw에 해당 좌표 추가
				_sum++;
				_map[nx][ny] = 2;
				draw.add(new int[] {nx,ny});
				nx += dx[i];
				ny += dy[i];
			}
			if(flag == 0) {
				// 끝까지 걸리지 않고 선로를 설치했다면
				// 다음 core부터 검사하는 on에 성공적으로 설치했다고 1을 더한 후 perm 호출
				perm(cnt+1,_sum, on+1);
			}
			for(int [] coor : draw) {
				_map[coor[0]][coor[1]] = 0;
				_sum--;
				// 루프 종료시 _map을 원래 상태로 되돌림
			}
		}
		// 해당 코어를 설치하지 않는 경우 호출
		perm(cnt+1,_sum ,on);
	}
}
