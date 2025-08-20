import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n, answer;
    static int[][] board;
    static int[][][] maxValue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        maxValue = new int[n][10][1 << n];
        // n < 15
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 1 << n; j++) {
                for(int k = 0; k < 10; k++) {
                    maxValue[i][k][j] = -1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }

        getMaxValue(0, 0, 0);
        System.out.println(answer);
    }

    static int getMaxValue(int now, int price, int v) {
        if (maxValue[now][price][v] != -1) return maxValue[now][price][v];
        maxValue[now][price][v] = Math.max(maxValue[now][price][v], 1);
        v = v | (1 << now);
        int[] next = board[now];
        for (int i = 0; i < n; i++) {
            if ((v & (1 << i)) == 0 && next[i] >= price) {
                // 다음사람에게 팔 수 있는 경우
                maxValue[now][price][v] = Math.max(maxValue[now][price][v], getMaxValue(i, next[i], v | 1 << i) + 1);
            }
        }
        answer = Math.max(answer, maxValue[now][price][v]);
        return maxValue[now][price][v];
    }
}
