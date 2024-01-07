import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int black = 0 ;
    static int white = 0 ;
    public static void main(String [] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
        int n = Integer.parseInt(bufferedReader.readLine());
        int [][] arr = new int[n][n];
        for(int i = 0 ; i <n ; i++){
            String [] tokens =  bufferedReader.readLine().split(" ");
            for(int j = 0 ;j<n;j++){
                arr[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        calc(arr,n,n,0,0);
        System.out.println(white);
        System.out.println(black);
    }
    public static void calc(int [][] arr , int n, int len, int x, int y){
        int _sum = 0;
        for(int i = x ; i < x+len; i++){
            for(int j = y ; j <y+len; j++){
                _sum += arr[i][j];
            }
        }
        if (_sum == 0){
            white+= 1;
        }else if(_sum == len*len){
            black+= 1;
        }else{
            calc(arr,n,len/2,x,y);
            calc(arr,n,len/2,x+len/2,y);
            calc(arr,n,len/2,x,y+len/2);
            calc(arr,n,len/2,x+len/2,y+len/2);
        }
    }

}
