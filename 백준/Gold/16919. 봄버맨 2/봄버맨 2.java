import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int r, c, n;
	static char[][][] map;

	public static void main(String[] args) throws IOException {
//    	System.setIn(new FileInputStream("res/input_d2_2001.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new char[r][c][6];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j][0] = s.charAt(j);
				map[i][j][1] = s.charAt(j);
			}
		}

		if(n == 1) {
			// n == 1인경우 그냥 출력
			for(int i = 0 ; i < r ; i++) {
				for(int j = 0 ; j < c ; j++) {
					sb.append(map[i][j][1]);
				}
				sb.append("\n");
			}
		}else {
			
			for (int i = 2; i < 6; i++) {
				if (i % 2 == 0) {
					for (int j = 0; j < r; j++) {
						for (int k = 0; k < c; k++) {
							map[j][k][i] = 'O';
						}
					}
				} else {
					for (int j = 0; j < r; j++) {
						for (int k = 0; k < c; k++) {
							if(map[j][k][i] == '\0') {
								map[j][k][i] = map[j][k][i-1];
							}
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
	}
}
