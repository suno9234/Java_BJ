import java.util.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens ;
        int [][] arr = new int[100][100];
        int n = Integer.parseInt(br.readLine());
        for (int i = 0 ; i< n; i++){
            tokens = br.readLine().split(" ");
            int x = Integer.parseInt(tokens[0]);
            int y = Integer.parseInt(tokens[1]);
            for(int j = 0 ; j < 10; j++){
                for(int k = 0 ; k<10; k++){
                    arr[x+j][y+k] = 1;
                }
            }
        }
        int answer = 0;
        for(int i = 0; i< 100; i++){
            for(int j = 0 ; j < 100; j++){
                if(arr[i][j] == 1){
                    answer +=1;
                }
            }
        }
        System.out.println(answer);
    }
}