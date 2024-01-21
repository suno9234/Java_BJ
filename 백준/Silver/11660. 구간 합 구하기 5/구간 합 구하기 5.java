
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        String  [] tokens = bufferedReader.readLine().split(" ");
        int n  = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        int [][] arr = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            tokens = bufferedReader.readLine().split(" ");
            arr[i][0] = Integer.parseInt(tokens[0]);
            for(int j = 1 ; j < n ; j++){
                arr[i][j] = Integer.parseInt(tokens[j])+arr[i][j-1];
            }
        }
        for(int i = 0 ; i < m ; i++){
            int x1,y1,x2,y2;
            tokens = bufferedReader.readLine().split(" ");
            x1 = Integer.parseInt(tokens[0])-1;
            y1 = Integer.parseInt(tokens[1])-1;
            x2 = Integer.parseInt(tokens[2])-1;
            y2 = Integer.parseInt(tokens[3])-1;
            int answer = 0;
            for(int j = x1 ; j<= x2 ; j++){
                answer+= arr[j][y2];
                if (y1 -1>= 0){
                    answer-=arr[j][y1-1];
                }
            }
            stringBuilder.append(answer).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
