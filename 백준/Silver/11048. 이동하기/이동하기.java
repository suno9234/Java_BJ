import java.util.*;
import java.io.*;

public class Main{
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        int [][] _map = new int[n][m];
        int [] dx = {0,1};
        int [] dy = {1,0};
        for(int i = 0 ; i < n ; i++){
            tokens = br.readLine().split(" ");
            for(int j = 0 ; j < m ; j++){
                _map[i][j] = Integer.parseInt(tokens[j]);
            }
        }
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(i == 0 ){
                    if(j>0)_map[i][j] += _map[i][j-1];
                }else if(j==0){
                    if(i >0) _map[i][j] += _map[i-1][j];
                }else{
                    _map[i][j] += Math.max(_map[i][j-1],_map[i-1][j]); 
                }
                
      
            }
        }
        System.out.println(_map[n-1][m-1]);
    }
}