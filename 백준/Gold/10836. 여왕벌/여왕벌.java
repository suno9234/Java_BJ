import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n,m;
    static int [][] board;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        m = Integer.parseInt(tokens[0]);
        n = Integer.parseInt(tokens[1]);
        board = new int[m][m];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < m; j++){
                board[i][j] = 1;
            }
        }

        for(int i = 0 ; i < n ; i++){
            tokens = br.readLine().split(" ");
            int [][] temp = new int[m][m];

            int [] numbers = new int[2*m-1];
            int idx = 0;

            int zeros = Integer.parseInt(tokens[0]);
            int ones = Integer.parseInt(tokens[1]);
            int twos = Integer.parseInt(tokens[2]);

            for(int j = 0 ; j < zeros; j++){
                numbers[idx++] = 0;
            }
            for(int j = 0 ; j < ones; j++){
                numbers[idx++] = 1;
            }
            for(int j = 0 ; j < twos; j++){
                numbers[idx++] = 2;
            }

            idx = 0;
            for(int j = 0 ; j < m ; j++){
                temp[m-j-1][0] = numbers[idx++];
            }
            for(int j = 1 ; j < m ; j++){
                temp[0][j] = numbers[idx++];
            }
            pushMatrix(temp,1);
            for(int j = 0 ; j < m ; j++){
                for(int k= 0 ; k < m ; k++){
                    board[j][k] += temp[j][k];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < m ; j++){
                sb.append(board[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    static void pushMatrix(int [][] matrix,int cnt){
        if(cnt == m){
            return;
        }
        matrix[cnt][cnt] = Math.max(matrix[cnt][cnt-1],Math.max(matrix[cnt-1][cnt-1],matrix[cnt-1][cnt]));
        for(int i = cnt+1; i < m ; i++){
            matrix[cnt][i] = Math.max(matrix[cnt-1][i],matrix[cnt][i-1]);
            matrix[i][cnt] = Math.max(matrix[i-1][cnt],matrix[i][cnt-1]);
        }
        pushMatrix(matrix,cnt+1);
    }
}
