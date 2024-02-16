import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 }; // 위 왼위 왼 왼아래 아래 오아래 오 위오
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 0 1 2 3 4 5 6 7
	static int answer;
	static Fish[][] _map;

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("res/19236input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		_map = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			String[] tokens = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(tokens[j * 2]);
				int b = Integer.parseInt(tokens[j * 2 + 1]) - 1;
				_map[i][j] = new Fish(a, b);
			}
		}
		moveShark(0, 0, 0,_map,0);
		System.out.println(answer);
	}

	static void moveShark(int posX, int posY, int dir,Fish[][] _map, int point) {
		Fish now = _map[posX][posY];
		point += now.num;
		dir = now.dir;
		_map[posX][posY] = null;
		// 해당 좌표에 있는 물고기를 먹음
		
		answer = Math.max(answer, point);
		Fish[][] newMap = new Fish[4][4];
		for (int i = 0; i < 4; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				if(_map[i][j]== null) continue;
				newMap[i][j] = new Fish(_map[i][j].num,_map[i][j].dir);
			}
		}
		int idx = 1;
		while (idx < 17) {
			Fish nowFish = null;
			int x = 0;
			int y = 0;
			int flag = 0;
			for (int i = 0; i < 4; i++) {
				if (flag == 1) {
					break;
				}
				for (int j = 0; j < 4; j++) {
					if (newMap[i][j] == null)
						continue;
					if (newMap[i][j].num == idx) {
						nowFish = newMap[i][j];
						x = i;
						y = j;
						flag = 1;
						break;
					}
				}
			}
			if (flag == 0) {
				// 다음 번호의 생선이 없다면 idx값 증가
				idx++;
				continue;
			}

			int nowDir = nowFish.dir;

			flag = 0;
			for (int i = 0; i < 8; i++) {
				if (flag == 1) {
					break;
				}
				int nx = x + dx[(nowDir + i) % 8];
				int ny = y + dy[(nowDir + i) % 8];
				if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4) {
					if (nx == posX && ny == posY)
						continue;
					if (newMap[nx][ny] != null) {
						flag = 1;
						newMap[x][y] = newMap[nx][ny];
						newMap[nx][ny] = nowFish;
						nowFish.dir = (nowDir + i) % 8;
					}else {
						flag = 1;
						newMap[x][y] = null;
						newMap[nx][ny] = nowFish;
						nowFish.dir = (nowDir + i) % 8;
					}
					break;
				}
			}
			idx++;
		}
		// 물고기 이동 완료
		for (int i = 1; i < 4; i++) {
			int nx = posX + dx[dir] * i;
			int ny = posY + dy[dir] * i;
			if (nx >= 0 && ny >= 0 && nx < 4 && ny < 4 && newMap[nx][ny]!=null) {
				// 해당 자리에 물고기가 있으면 잡아먹음
				
				moveShark(nx, ny, dir, newMap , point);
			}
		}
		_map[posX][posY] = now;
	}
}

class Fish {
	int dir, num;

	public Fish(int num, int dir) {
		this.num = num;
		this.dir = dir;
	}

	public String toString() {
		return num + "";
	}
}
