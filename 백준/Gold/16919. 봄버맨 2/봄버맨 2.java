import java.io.*;
import java.util.*;

public class Main{
	static int r, c, n;
	static char[][][] map;

	public static void main(String[] args) throws Exception {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens;
		StringBuilder sb = new StringBuilder();
		tokens = br.readLine().split(" ");

		r = Integer.parseInt(tokens[0]);
		c = Integer.parseInt(tokens[1]);
		n = Integer.parseInt(tokens[2]);
		map = new char[r][c][6];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j][0] = s.charAt(j);
				map[i][j][1] = s.charAt(j);
			}
		}

		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < r; j++) {
				for (int k = 0; k < c; k++) {
					map[j][k][i] = map[j][k][i-1];
				}
			}
			if (i % 2 == 0) {
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						map[j][k][i] = 'O';
					}
				}
			} else {
				for (int j = 0; j < r; j++) {
					for (int k = 0; k < c; k++) {
						if (map[j][k][i - 3] == 'O' && map[j][k][i - 2] == 'O') {
							if (k + 1 < c)
								map[j][k + 1][i] = '.';
							if (k - 1 >= 0)
								map[j][k - 1][i] = '.';
							if (j - 1 >= 0)
								map[j - 1][k][i] = '.';
							if (j + 1 < r)
								map[j + 1][k][i] = '.';
							map[j][k][i] = '.';
						}

					}
				}

			}

		}
		if(n == 1) {
			for(int i = 0 ; i < r ; i++) {
				for(int j = 0 ; j < c ; j++) {
					sb.append(map[i][j][1]);
				}
				sb.append("\n");
			}
		}else {
			int modN = n%4;
			if (modN == 0) modN = 4;
			if(modN == 1) modN = 5;
			for(int i = 0 ; i < r ; i++) {
				for(int j = 0 ; j < c ; j++) {
					sb.append(map[i][j][modN]);
				}
				sb.append("\n");
			}
		}

		

		System.out.println(sb);
		br.close();
	}
}
