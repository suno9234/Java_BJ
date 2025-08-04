import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    static int n, m;
    static int[][] board;
    static int[][] value;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        board = new int[n][m];
        value = new int[n][m];
        for (int i = 0; i < n; i++) {
            tokens = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(tokens[j]);
                for(int k = 0 ; k < 3 ; k++){
                    value[i][j] = Integer.MIN_VALUE;
                }
            }
        }
        value[0][0] = board[0][0];
        for(int i = 1 ; i < m ;i++){
            value[0][i] = value[0][i-1] + board[0][i];
        }
        for(int i = 1 ; i< n ; i++){
            for(int j = 0 ; j < m ; j++){
                value[i][j] = value[i-1][j] + board[i][j];
            }
            int [] left = new int[m];
            int [] right = new int[m];
            left[0] = value[i][0];
            right[m-1] = value[i][m-1];
            for(int j = 1 ; j < m ; j++){
                left[j] = Math.max(left[j-1]+board[i][j],value[i][j]);
            }
            for(int j = m-2 ; j >=0 ; j--){
                right[j] = Math.max(right[j+1]+board[i][j] , value[i][j]);
            }
            for(int j = 0 ; j < m ; j++){
                value[i][j] = Math.max(left[j],right[j]);
            }
        }
        System.out.println(value[n-1][m-1]);
    }

}
