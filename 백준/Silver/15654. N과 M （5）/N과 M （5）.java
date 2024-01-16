
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static int m;
    static StringBuilder stringBuilder;
    static int[] arr;
    static boolean [] _visited ;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String [] tokens =  bufferedReader.readLine().split(" ");
        stringBuilder = new StringBuilder();
        n = Integer.parseInt(tokens[0]);
        m = Integer.parseInt(tokens[1]);
        arr = new int[n];
        _visited = new boolean[n];
        tokens = bufferedReader.readLine().split(" ");
        for(int i = 0 ; i < n ; i++){
            arr[i] = Integer.parseInt(tokens[i]);
        }
        Arrays.sort(arr);
        comb(new int[m],m);
        System.out.println(stringBuilder.toString());
    }
    public static void comb(int [] _arr ,int count){
        //System.out.println(Arrays.toString(_arr));
        //System.out.printf("%d %d\n",start,count);
        if(count == 0){
            for(int i = 0 ; i < m ; i++){
                stringBuilder.append(arr[_arr[i]]).append(" ");
            }
            stringBuilder.append("\n");
            //System.out.println(stringBuilder.toString());
            return;
        }
        for(int i = 0; i < n ; i++){
            if(!_visited[i]) {
                _arr[m - count] = i;
                _visited[i] = true;
                count -= 1;
                comb(_arr, count);
                count += 1;
                _visited[i] = false;
            }
        }
    }
}
