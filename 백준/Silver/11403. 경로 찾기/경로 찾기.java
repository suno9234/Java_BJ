import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder stringBuilder = new StringBuilder();
        int n = Integer.parseInt(bufferedReader.readLine());
        int [][] arr=  new int[n][n];
        int [][] answer = new int[n][n];

        for(int i = 0 ; i < n ; i++){
            String []  tokens = bufferedReader.readLine().split(" ");
            for(int j = 0 ; j < n;j++){
                arr[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        for(int k = 0 ; k < n;k++){
            for(int i = 0 ; i < n; i++){
                for(int j = 0 ; j<n;j++){
                    if(arr[i][j] == 0){
                        if(arr[i][k] == 1 && arr[k][j] == 1){
                            arr[i][j] = 1;
                        }
                    }
                }
            }
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j<n;j++) {
                if (arr[i][j] > 0 ){
                    stringBuilder.append("1 ");
                }else{
                    stringBuilder.append("0 ");
                }
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());

    }
}
