import java.io.*;
import java.util.*;

public class Main{

	static int m, s, sx, sy;
	static int finalScore, sharkRoute, answer;
	static HashMap<Integer, Integer>[][] map;
	static int[] dx = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] sDx = { -1, 0, 1, 0 };
	static int[] sDy = { 0, -1, 0, 1 };
	static ArrayDeque<int[][]> smells;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		map = new HashMap[4][4];
		smells = new ArrayDeque();
		for (int i = 0; i < 2; i++) {
			int[][] smell = new int[4][4];
			smells.add(smell);
		}
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = new HashMap();
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken()) - 1;
			int fy = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			if (map[fx][fy].get(d) == null) {
				map[fx][fy].put(d, 1);
			} else {
				map[fx][fy].put(d, map[fx][fy].get(d) + 1); // 방향 마리수
			}
		}
		st = new StringTokenizer(br.readLine());
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		int[][] smell = new int[4][4];
		while (s > 0) {
			// 1. 상어에게 복제 마법을 건다.
			// map은 건들지 않을거임
			HashMap<Integer, Integer>[][] nMap = new HashMap[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					nMap[i][j] = new HashMap();
				}
			}
			// 2. 물고기가 한 칸 이동한다.
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int key : map[i][j].keySet()) {
						int cnt = map[i][j].get(key);
						// key = 방향 cnt = 마리수
						int keyCnt = 0;
						while (keyCnt < 8) {
							int nx = i + dx[(key - keyCnt + 8) % 8];
							int ny = j + dy[(key - keyCnt + 8) % 8];
							if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && !(nx == sx && ny == sy)
									&& smell[nx][ny] == 0) {
								// 다음 방향의 칸에 해당 방향의 상어가 없으면
								if (nMap[nx][ny].get((key - keyCnt + 8) % 8) == null) {
									nMap[nx][ny].put((key - keyCnt + 8) % 8, cnt);
								} else {
									nMap[nx][ny].put((key - keyCnt + 8) % 8,
											nMap[nx][ny].get((key - keyCnt + 8) % 8) + cnt);
								}
								break;
							}
							keyCnt++;
						}
						if (keyCnt == 8) {
							// 움직일 수 없는 경우
							if (nMap[i][j].get(key) == null) {
								nMap[i][j].put((key), cnt);
							} else {
								nMap[i][j].put((key), nMap[i][j].get(key) + cnt);
							}
							// 그냥 추가
						}
					}
				}
			}
			// 3. 상어 이동
			sharkRoute = 999;
			finalScore = -1;
			getSharkRoute(sx, sy, 0, 0, 0, nMap);
			int[] ds = new int[3];
			for (int j = 0; j < 3; j++) {
				ds[2 - j] = sharkRoute % 10 - 1;
				sharkRoute /= 10;
			}
			int[][] nowSmell = new int[4][4];
			for (int j = 0; j < 3; j++) {
				sx += sDx[ds[j]];
				sy += sDy[ds[j]];
				if (nMap[sx][sy].size() > 0) {
					nowSmell[sx][sy]++;
					smell[sx][sy]++;
				}
				nMap[sx][sy] = new HashMap();
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
					for (int key : map[i][j].keySet()) {
						if (nMap[i][j].get(key) == null) {
							nMap[i][j].put(key, map[i][j].get(key));
						} else {
							nMap[i][j].put(key, nMap[i][j].get(key) + map[i][j].get(key));
						}
					}
				}
			}
			map = nMap;
			s--;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int key : map[i][j].keySet()) {
					answer += map[i][j].get(key);
				}
			}
		}
		System.out.println(answer);
		br.close();
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
				if (!(route > 9 && route%10-1 == (i+2)%4)) {
					for (int key : map[nx][ny].keySet()) {
						ns += map[nx][ny].get(key);
					}
				}
				getSharkRoute(nx, ny, cnt + 1, route * 10 + (i + 1), score + ns, map);
			}
		}
	}
}