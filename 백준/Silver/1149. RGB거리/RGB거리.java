
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int [] redEnd = new int[n];
        int [] greenEnd = new int[n];
        int [] blueEnd = new int[n];

        for(int i = 0 ; i < n ; i++){
            String [] tokens = bufferedReader.readLine().split(" ");
            int red = Integer.parseInt(tokens[0]);
            int green = Integer.parseInt(tokens[1]);
            int blue = Integer.parseInt(tokens[2]);
            if(i == 0 ){
                redEnd[0] = red;
                greenEnd[0] = green;
                blueEnd[0] = blue;
            }else{
                redEnd[i] = (int)Math.min(greenEnd[i-1],blueEnd[i-1])+red;
                greenEnd[i] = (int)Math.min(redEnd[i-1],blueEnd[i-1])+green;
                blueEnd[i] = (int)Math.min(redEnd[i-1],greenEnd[i-1])+blue;
            }
        }
        int answer = (int)Math.min(blueEnd[n-1], (int)Math.min(redEnd[n-1],greenEnd[n-1]));
        System.out.println(answer);

    }
}
