import java.io.*;
import java.util.*;

public class Main {

	static int n, m, k, time;
	static int[] sharkD;
	static int[][] sharkMap;
	static int [][] nextSharkMap;
	static int[][][] smellMap; // 0 = 상어번호 1 = 냄새를 뿌린 시간
	static int[][][] dPriority;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int answer;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/19237input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		n = Integer.parseInt(tokens[0]);
		m = Integer.parseInt(tokens[1]);
		k = Integer.parseInt(tokens[2]);
		sharkMap = new int[n][n];
		smellMap = new int[n][n][2];
		for (int i = 0; i < n; i++) {
			tokens = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				sharkMap[i][j] = Integer.parseInt(tokens[j]);
			}
		}
		sharkD = new int[m + 1];
		tokens = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			sharkD[i + 1] = Integer.parseInt(tokens[i]) - 1;
		}
		dPriority = new int[m+1][4][4];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < 4; j++) {
				tokens = br.readLine().split(" ");
				for (int k = 0; k < 4; k++) {
					dPriority[i + 1][j][k] = Integer.parseInt(tokens[k]) - 1;
				}
			}
		}

		// 맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (sharkMap[i][j] != 0) {
					smellMap[i][j][0] = sharkMap[i][j];
				}
			}
		}
		time = 0;
		while (time < 1001) {
			int cnt = 0;
			nextSharkMap = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (sharkMap[i][j] != 0) {
						move(i, j);
						cnt++;
					}
				}
			}
			if(cnt == 1) {
				break;
			}
			sharkMap = nextSharkMap;
			time++;
			for(int i = 0 ; i < n ; i++) {
				for(int j = 0 ; j < n ; j++) {
					if(sharkMap[i][j] != 0 ) {
						smellMap[i][j][0] = sharkMap[i][j];
						smellMap[i][j][1] = time;
					}
				}
			}
			// 1초 time = 1
		}
		if(time > 1000) {
			System.out.println(-1);
			return;
		}
		System.out.println(time);
	}

	static void move(int x, int y) {
		int sharkNum = sharkMap[x][y];
		int dir = sharkD[sharkNum];
		int[] nexts = dPriority[sharkNum][dir];
		int flag = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[nexts[i]];
			int ny = y + dy[nexts[i]];
			if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
				if (smellMap[nx][ny][0] == 0|| time - smellMap[nx][ny][1] >= k) {
					// 탐색했을때 빈곳이 있으면 빈곳 먼저 가고 break
					if(nextSharkMap[nx][ny] == 0) {
						nextSharkMap[nx][ny] = sharkNum;
					}else {
						nextSharkMap[nx][ny] = Math.min(nextSharkMap[nx][ny], sharkNum);
					}
					sharkD[sharkNum] = nexts[i];
					flag = 1;
					break;
				}
			}
		}
		if(flag == 0) {
			// 주위에 빈 칸이 없는 경우
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[nexts[i]];
				int ny = y + dy[nexts[i]];
				if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if (smellMap[nx][ny][0] == sharkNum && time - smellMap[nx][ny][1] < k) {
						// 1초에 냄새를 뿌리고 k = 4일경우 time = 5초에 사라짐 =  
						// 방구낀지 k초 미만에 번호가 같은 곳으로 이돟
						if(nextSharkMap[nx][ny] == 0) {
							nextSharkMap[nx][ny] = sharkNum;
						}else {
							nextSharkMap[nx][ny] = Math.min(nextSharkMap[nx][ny], sharkNum);
						}
						sharkD[sharkNum] = nexts[i];
						break;
					}
				}
			}
		}
		
		
	}

}
