import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int test_case = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < test_case; i++){
            int m,n,k;
            String [] tokens = bufferedReader.readLine().split(" ");
            m = Integer.parseInt(tokens[0]);
            n = Integer.parseInt(tokens[1]);
            k = Integer.parseInt(tokens[2]);
            int [][] farm = new int[n][m];
            for(int j = 0 ; j <k; j++){
                int x,y;
                tokens = bufferedReader.readLine().split(" ");
                x = Integer.parseInt(tokens[1]);
                y = Integer.parseInt(tokens[0]);
                farm[x][y] = 1;
            }
            int answer = 0;
            for(int j = 0 ; j < n; j++){
                for (int l = 0 ; l < m; l++){
                    if(farm[j][l] == 1){
                        answer+=1;
                        flip(farm,n,m,j,l);
                    }
                }
            }
            stringBuilder.append(answer);
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
    public static void flip(int [][] arr,int n,int m ,int x, int y){
        int [] dx = {1,-1,0,0};
        int [] dy = {0,0,1,-1};
        arr[x][y] = -1;
        for(int i = 0 ; i < 4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(nx >= 0 && nx < n && ny >=0 && ny < m){
                if(arr[nx][ny] == 1) {
                    arr[nx][ny] = -1;
                    flip(arr, n, m, nx, ny);
                }
            }
        }
    }
}