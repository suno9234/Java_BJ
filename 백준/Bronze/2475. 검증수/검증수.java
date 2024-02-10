import java.util.*;
import java.io.*;

class Main{
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens = br.readLine().split(" ");
        int _powSum = 0;
        for(int i = 0 ; i < 5; i++){
            int now = Integer.parseInt(tokens[i]);
            _powSum+= now*now;
        }
        System.out.println(_powSum%10);
    }
}