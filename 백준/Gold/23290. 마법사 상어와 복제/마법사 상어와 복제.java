import java.io.*;
import java.util.*;

public class Main{

	static int m, s, sx, sy;
	static int finalScore, sharkRoute, answer;
	static int[] ds = new int[3];
	static int[][][] map, nMap;
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] sDx = { -1, 0, 1, 0 };
	static int[] sDy = { 0, -1, 0, 1 };
	static ArrayDeque<int[][]> smells;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		map = new int[4][4][8];
		smells = new ArrayDeque();
		for (int i = 0; i < 2; i++) {
			int[][] smell = new int[4][4];
			smells.add(smell);
		}
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken()) - 1;
			int fy = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			map[fx][fy][d]++;
		}
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		int[][] smell = new int[4][4];
		while (s > 0) {
			// 1. 상어에게 복제 마법을 건다.
			// map은 건들지 않을거임
			nMap = new int[4][4][8];
			// 2. 물고기가 한 칸 이동한다.
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < 8; k++) {
						int cnt = map[i][j][k];
						if (cnt > 0) {
							// k = 방향 cnt = 마리수
							int keyCnt = 0;
							while (keyCnt < 8) {
								int now = (k - keyCnt + 8) % 8;
								// 현재 방향 now
								int nx = i + dx[now];
								int ny = j + dy[now];
								if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && !(nx == sx && ny == sy) && smell[nx][ny] == 0) {
									//  현재 방향의 칸 검사해서 된다면
									nMap[nx][ny][now] += cnt;
									break;
								}
								keyCnt++;
							}
							if (keyCnt == 8) {
								// 움직일 수 없는 경우
								nMap[i][j][k] += cnt;
								// 그냥 추가
							}
						}
					}
				}
			}
			// 3. 상어 이동
			finalScore = -1;
//			sharkRoute = 999;
//			getSharkRoute(sx, sy, 0, 0, 0, nMap);
//			for (int j = 0; j < 3; j++) {
//				ds[2 - j] = sharkRoute % 10 - 1;
//				sharkRoute /= 10;
//			}
			getSharkRoute2(sx, sy);
			int[][] nowSmell = new int[4][4];
			for (int i = 0; i < 3; i++) {
				sx += sDx[ds[i]];
				sy += sDy[ds[i]];
				int flag = 0;
				for (int j = 0; j < 8; j++) {
					if (flag == 0 && nMap[sx][sy][j] > 0) {
						flag = 1;
						nowSmell[sx][sy]++;
						smell[sx][sy]++;
					}
					nMap[sx][sy][j] = 0;
				}
			}
			smells.add(nowSmell);

			// 4. 2턴 전 물고기 냄새 제거
			int[][] dSmell = smells.poll();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (dSmell[i][j] > 0) {
						smell[i][j]--;
					}
				}
			}

			// 5. 1에서 마법에 걸린 상어가 복제된다.
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int k = 0; k < 8; k++) {
						nMap[i][j][k] += map[i][j][k];
					}
				}
			}
			map = nMap;
			s--;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 8; k++) {
					answer += map[i][j][k];
				}
			}
		}
		System.out.println(answer);
		br.close();
	}

	static void getSharkRoute2(int x, int y) {
		int score = 0;
		int ns0 = 0;
		int ns1 = 0;
		int ns2 = 0;
		for (int i = 0; i < 4; i++) {
			x += sDx[i];
			y += sDy[i];
			if (x >= 0 && y >= 0 && x < 4 && y < 4) {
				ns0 = 0;
				for (int k = 0; k < 8; k++) {
					ns0 += nMap[x][y][k];
				}
				score += ns0;
				for (int j = 0; j < 4; j++) {
					x += sDx[j];
					y += sDy[j];
					if (x >= 0 && y >= 0 && x < 4 && y < 4) {
						ns1 = 0;
						for (int k = 0; k < 8; k++) {
							ns1 += nMap[x][y][k];
						}
						score += ns1;
						for (int k = 0; k < 4; k++) {
							x += sDx[k];
							y += sDy[k];
							if (x >= 0 && y >= 0 && x < 4 && y < 4) {
								ns2 = 0;
								if ((j + 2) % 4 != k) {
									for (int l = 0; l < 8; l++) {
										ns2 += nMap[x][y][l];
									}
								}
								score += ns2;
								if (score > finalScore) {
									finalScore = score;
									ds = new int[] { i, j, k };
								}
								score -= ns2;
							}
							x -= sDx[k];
							y -= sDy[k];
						}
						score -= ns1;
					}
					x -= sDx[j];
					y -= sDy[j];
				}
				score -= ns0;
			}
			x -= sDx[i];
			y -= sDy[i];
		}
	}

	static void getSharkRoute(int x, int y, int cnt, int route, int score, HashMap<Integer, Integer>[][] map) {
		if (cnt == 3) {
			if (score > finalScore) {
				// 우선순위가 높은곳부터 탐색하므로 같은경우는 신경x
				finalScore = score;
				sharkRoute = route;
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + sDx[i];
			int ny = y + sDy[i];
			if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
				int ns = 0;
				if (cnt == 2 && route % 10 - 1 == (i + 2) % 4) {
					// 경로의 길이가 2이고 , 현재 방향과 마지막에 넣은 것이 반대방향이면 물고기는 이미 없다
					getSharkRoute(nx, ny, cnt + 1, route * 10 + (i + 1), score, map);
				} else {
					for (int key : map[nx][ny].keySet()) {
						ns += map[nx][ny].get(key);
					}
				}
				getSharkRoute(nx, ny, cnt + 1, route * 10 + (i + 1), score + ns, map);
			}
		}
	}
}