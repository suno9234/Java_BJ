import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for(int tc = 0 ; tc < t ; tc++){
            int n = Integer.parseInt(bufferedReader.readLine());
            int [] top = new int[n];
            int [] bottom = new int[n];
            String [] tokens = bufferedReader.readLine().split(" ");
            for(int i = 0 ; i < n ; i++){
                top[i] = Integer.parseInt(tokens[i]);
            }
            tokens = bufferedReader.readLine().split(" ");
            for(int i = 0 ; i < n ; i++){
                bottom[i] = Integer.parseInt(tokens[i]);
            }

            int [] _top = new int[n];
            int [] _bottom = new int[n];
            _top[0] = top[0];
            _bottom[0] = bottom[0];
            if(n > 1) {
                _top[1] = bottom[0] + top[1];
                _bottom[1] = top[0] + bottom[1];
            }
            for(int i = 2 ; i < n ; i++){
                _top[i] = (int)Math.max( _bottom[i-2]+top[i],(int)Math.max(_bottom[i-1]+top[i],_top[i-2]+top[i]));
                _bottom[i] =(int)Math.max(_top[i-2]+bottom[i],(int)Math.max(_top[i-1]+bottom[i], _bottom[i-2]+bottom[i]));
            }
            stringBuilder.append((int)Math.max(_top[n-1],_bottom[n-1])).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
